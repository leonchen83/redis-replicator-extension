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

package com.moilioncircle.redis.replicator.extension.module.cmd.parser;

import com.moilioncircle.redis.replicator.cmd.CommandName;
import com.moilioncircle.redis.replicator.extension.module.cmd.NameableCommandParser;
import com.moilioncircle.redis.replicator.extension.module.cmd.impl.JsonNumIncrByCommand;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Leon Chen
 * @since 1.0.0
 */
public class JsonNumIncrByParser implements NameableCommandParser<JsonNumIncrByCommand> {
    @Override
    public JsonNumIncrByCommand parse(Object[] command) {
        int idx = 1;
        String key = new String((byte[]) command[idx++], UTF_8);
        String path = command.length < 4 ? "." : new String((byte[]) command[idx++], UTF_8);
        double value = Double.parseDouble(new String((byte[]) command[idx++], UTF_8));
        return new JsonNumIncrByCommand(key, path, value);
    }

    @Override
    public CommandName name() {
        return CommandName.name("JSON.NUMINCRBY");
    }
}
