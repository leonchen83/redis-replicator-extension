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

import com.moilioncircle.redis.replicator.cmd.CommandParser;
import com.moilioncircle.redis.replicator.extension.module.cmd.impl.JsonSetCommand;

/**
 * @author Leon Chen
 * @since 1.0.0
 */
public class JsonSetParser implements CommandParser<JsonSetCommand> {

    @Override
    public JsonSetCommand parse(Object[] command) {
        int idx = 1;
        String key = (String) command[idx++];
        String path = (String) command[idx++];
        String json = (String) command[idx++];
        return new JsonSetCommand(key, path, json);
    }

}
