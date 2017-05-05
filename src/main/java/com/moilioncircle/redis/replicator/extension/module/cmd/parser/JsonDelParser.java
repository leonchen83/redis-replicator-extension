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
import com.moilioncircle.redis.replicator.extension.module.NameableCommandParser;
import com.moilioncircle.redis.replicator.extension.module.cmd.impl.JsonDelCommand;

/**
 * @author Leon Chen
 * @since 1.0.0
 */
public class JsonDelParser implements NameableCommandParser<JsonDelCommand> {

    @Override
    public JsonDelCommand parse(Object[] command) {
        int idx = 1;
        String key = (String) command[idx++];
        String path = idx == command.length ? "." : (String) command[idx++];
        return new JsonDelCommand(key, path);
    }

    @Override
    public CommandName name() {
        return CommandName.name("JSON.DEL");
    }
}
