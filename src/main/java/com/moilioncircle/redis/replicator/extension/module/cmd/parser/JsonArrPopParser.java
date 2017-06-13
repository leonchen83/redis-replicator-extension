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
import com.moilioncircle.redis.replicator.extension.module.cmd.impl.JsonArrPopCommand;

import java.math.BigDecimal;

/**
 * @author Leon Chen
 * @since 1.0.0
 */
public class JsonArrPopParser implements NameableCommandParser<JsonArrPopCommand> {
    @Override
    public JsonArrPopCommand parse(Object[] command) {
        int idx = 1;
        String key = new String((byte[]) command[idx++], Constants.CHARSET);
        String path = idx == command.length ? "." : new String((byte[]) command[idx++], Constants.CHARSET);
        int index = idx == command.length ? -1 : new BigDecimal(new String((byte[]) command[idx++], Constants.CHARSET)).intValueExact();
        return new JsonArrPopCommand(key, path, index);
    }

    @Override
    public CommandName name() {
        return CommandName.name("JSON.ARRPOP");
    }
}
