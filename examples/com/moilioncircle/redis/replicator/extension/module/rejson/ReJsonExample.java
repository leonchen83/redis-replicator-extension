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

package com.moilioncircle.redis.replicator.extension.module.rejson;

import com.moilioncircle.redis.replicator.Configuration;
import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.cmd.Command;
import com.moilioncircle.redis.replicator.cmd.CommandListener;
import com.moilioncircle.redis.replicator.extension.module.NameableCommandParser;
import com.moilioncircle.redis.replicator.extension.module.VersionableModuleParser;
import com.moilioncircle.redis.replicator.extension.module.cmd.impl.JsonDelCommand;
import com.moilioncircle.redis.replicator.extension.module.cmd.impl.JsonSetCommand;
import com.moilioncircle.redis.replicator.extension.module.cmd.parser.JsonDelParser;
import com.moilioncircle.redis.replicator.extension.module.cmd.parser.JsonSetParser;
import com.moilioncircle.redis.replicator.extension.module.rdb.impl.JsonModule;
import com.moilioncircle.redis.replicator.extension.module.rdb.parser.JsonModuleParser;
import com.moilioncircle.redis.replicator.rdb.RdbListener;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyStringValueModule;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyValuePair;
import com.moilioncircle.redis.replicator.rdb.datatype.Module;

import java.io.IOException;

/**
 * @author Leon Chen
 * @since 1.0.0
 */
public class ReJsonExample {
    public static void main(String[] args) throws IOException {
        Replicator r = new RedisReplicator("127.0.0.1", 6379, Configuration.defaultSetting());
        NameableCommandParser<JsonSetCommand> jsonSetParser = new JsonSetParser();
        NameableCommandParser<JsonDelCommand> jsonDelParser = new JsonDelParser();
        VersionableModuleParser<JsonModule> jsonModuleParser = new JsonModuleParser(true);
        r.addCommandParser(jsonSetParser.name(), jsonSetParser);
        r.addCommandParser(jsonDelParser.name(), jsonDelParser);
        r.addModuleParser(jsonModuleParser.name(), jsonModuleParser.version(), jsonModuleParser);
        r.addCommandListener(new CommandListener() {
            @Override
            public void handle(Replicator replicator, Command command) {
                System.out.println(command);
            }
        });
        r.addRdbListener(new RdbListener.Adaptor() {
            @Override
            public void handle(Replicator replicator, KeyValuePair<?> kv) {
                if (!(kv instanceof KeyStringValueModule)) return;
                Module module = ((KeyStringValueModule) kv).getValue();
                if (!(module instanceof JsonModule)) return;
                JsonModule jsonModule = (JsonModule) module;
                System.out.println(jsonModule.getJson());
            }
        });
        r.open();
    }
}
