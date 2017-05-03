/*
 * Copyright 2016-2017 Leon Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moilioncircle.redis.replicator.extension.module.rdb.parser;

import com.moilioncircle.redis.replicator.extension.module.rdb.impl.JsonArray;
import com.moilioncircle.redis.replicator.extension.module.rdb.impl.JsonModule;
import com.moilioncircle.redis.replicator.extension.module.rdb.impl.JsonObject;
import com.moilioncircle.redis.replicator.io.RedisInputStream;
import com.moilioncircle.redis.replicator.rdb.module.DefaultRdbModuleParser;
import com.moilioncircle.redis.replicator.rdb.module.ModuleParser;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Stack;

import static com.moilioncircle.redis.replicator.extension.module.rdb.parser.JsonModuleParser.State.*;

/**
 * @author Leon Chen
 * @see <a href="https://github.com/RedisLabsModules/rejson">https://github.com/RedisLabsModules/rejson</a>
 * @since 1.0.0
 * JSONTYPE_NAME : ReJSON-RL
 * JSONTYPE_ENCODING_VERSION : 0
 */
public class JsonModuleParser implements ModuleParser<JsonModule> {

    private static final int N_NULL = 0x1;
    private static final int N_DICT = 0x20;
    private static final int N_ARRAY = 0x40;
    private static final int N_STRING = 0x2;
    private static final int N_NUMBER = 0x4;
    private static final int N_KEYVAL = 0x80;
    private static final int N_INTEGER = 0x8;
    private static final int N_BOOLEAN = 0x10;

    enum State {S_INIT, S_BEGIN_VALUE, S_END_VALUE, S_CONTAINER, S_END}

    private final boolean ordered;

    public JsonModuleParser() {
        this(false);
    }

    public JsonModuleParser(boolean ordered) {
        this.ordered = ordered;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public JsonModule parse(RedisInputStream in) throws IOException {
        DefaultRdbModuleParser parser = new DefaultRdbModuleParser(in);
        State state = S_INIT;
        Stack<Object> nodes = new Stack<>();
        Stack<Integer> indices = new Stack<>();
        Object node = null;
        int type = -1;
        while (state != S_END) {
            switch (state) {
                case S_INIT:
                    type = (int) parser.loadSigned();
                    state = S_BEGIN_VALUE;
                    break;
                case S_BEGIN_VALUE:
                    switch (type) {
                        case N_NULL:
                            node = null;
                            state = S_END_VALUE;
                            break;
                        case N_BOOLEAN:
                            node = parser.loadStringBuffer().equals("1");
                            state = S_END_VALUE;
                            break;
                        case N_INTEGER:
                            node = parser.loadSigned();
                            state = S_END_VALUE;
                            break;
                        case N_NUMBER:
                            node = parser.loadDouble();
                            state = S_END_VALUE;
                            break;
                        case N_STRING:
                            node = parser.loadStringBuffer();
                            state = S_END_VALUE;
                            break;
                        case N_KEYVAL:
                            String str = parser.loadStringBuffer();
                            nodes.push(new AbstractMap.SimpleEntry<>(str, null));
                            indices.push(1);
                            state = S_CONTAINER;
                            break;
                        case N_DICT:
                            int len = (int) parser.loadSigned();
                            nodes.push(new JsonObject(ordered));
                            indices.push(len);
                            state = S_CONTAINER;
                            break;
                        case N_ARRAY:
                            len = (int) parser.loadSigned();
                            nodes.push(new JsonArray());
                            indices.push(len);
                            state = S_CONTAINER;
                            break;
                    }
                    break;
                case S_END_VALUE:
                    if (!nodes.isEmpty()) {
                        Object container = nodes.peek();
                        if (container instanceof AbstractMap.SimpleEntry) {
                            ((AbstractMap.SimpleEntry<String, Object>) container).setValue(node);
                        } else if (container instanceof JsonObject) {
                            AbstractMap.SimpleEntry<String, Object> entry = (AbstractMap.SimpleEntry<String, Object>) node;
                            ((JsonObject) container).put(entry.getKey(), entry.getValue());
                        } else if (container instanceof JsonArray) {
                            ((JsonArray) container).add(node);
                        }
                        state = S_CONTAINER;
                    } else {
                        state = S_END;
                    }
                    break;
                case S_CONTAINER:
                    int len = indices.peek();
                    if (len > 0) {
                        indices.pop();
                        indices.push(len - 1);
                        type = (int) parser.loadSigned();
                        state = S_BEGIN_VALUE;
                    } else {
                        indices.pop();
                        node = nodes.pop();
                        state = S_END_VALUE;
                    }
                    break;
                case S_END:
                    break;
            }
        }
        return new JsonModule(node);
    }
}
