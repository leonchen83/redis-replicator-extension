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

import com.moilioncircle.redis.replicator.Constants;
import com.moilioncircle.redis.replicator.cmd.CommandName;
import com.moilioncircle.redis.replicator.extension.module.cmd.NameableCommandParser;
import com.moilioncircle.redis.replicator.extension.module.cmd.impl.JsonArrAppendCommand;

/**
 * @author Leon Chen
 * @since 1.0.0
 */
public class JsonArrAppendParser implements NameableCommandParser<JsonArrAppendCommand> {
    @Override
    public JsonArrAppendCommand parse(Object[] command) {
        int idx = 1, newIdx = 0;
        String key = new String((byte[]) command[idx++], Constants.CHARSET);
        String path = new String((byte[]) command[idx++], Constants.CHARSET);
        String[] jsons = new String[command.length - idx];
        while (idx < command.length) {
            jsons[newIdx++] = new String((byte[]) command[idx++], Constants.CHARSET);
        }
        return new JsonArrAppendCommand(key, path, jsons);
    }

    @Override
    public CommandName name() {
        return CommandName.name("JSON.ARRAPPEND");
    }
}
