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

import com.moilioncircle.redis.replicator.extension.module.rdb.impl.JsonModule;
import com.moilioncircle.redis.replicator.io.RedisInputStream;
import com.moilioncircle.redis.replicator.rdb.module.DefaultRdbModuleParser;
import com.moilioncircle.redis.replicator.rdb.module.ModuleParser;

import java.io.IOException;

/**
 * @author Leon Chen
 * @since 1.0.0
 * JSONTYPE_NAME : ReJSON-RL
 * JSONTYPE_ENCODING_VERSION : 0
 */
public class JsonModuleParser implements ModuleParser<JsonModule> {

    private static final int N_NULL = 0x1;
    private static final int N_STRING = 0x2;
    private static final int N_NUMBER = 0x4;
    private static final int N_INTEGER = 0x8;
    private static final int N_BOOLEAN = 0x10;
    private static final int N_DICT = 0x20;
    private static final int N_ARRAY = 0x40;
    private static final int N_KEYVAL = 0x80;

    enum State {S_INIT, S_BEGIN_VALUE, S_END_VALUE, S_CONTAINER, S_END}

    private final boolean ordered;

    public JsonModuleParser(boolean ordered) {
        this.ordered = ordered;
    }

    @Override
    public JsonModule parse(RedisInputStream in) throws IOException {
        DefaultRdbModuleParser parser = new DefaultRdbModuleParser(in);
        return null;
    }
}
