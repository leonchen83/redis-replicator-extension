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

import java.io.IOException;

import com.moilioncircle.redis.replicator.Configuration;
import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.event.Event;
import com.moilioncircle.redis.replicator.event.EventListener;
import com.moilioncircle.redis.replicator.extension.module.Modules;
import com.moilioncircle.redis.replicator.extension.module.rdb.impl.JsonModule;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyStringValueModule;
import com.moilioncircle.redis.replicator.rdb.datatype.Module;

/**
 * @author Leon Chen
 * @since 1.0.0
 */
public class ReJsonExample {
    public static void main(String[] args) throws IOException {
        Replicator r = new RedisReplicator("127.0.0.1", 6379, Configuration.defaultSetting());
        Modules.rejson(r);
        r.addEventListener(new EventListener() {
            @Override
            public void onEvent(Replicator replicator, Event event) {
                if (!(event instanceof KeyStringValueModule)) return;
                Module module = ((KeyStringValueModule) event).getValue();
                if (!(module instanceof JsonModule)) return;
                JsonModule jsonModule = (JsonModule) module;
                System.out.println(jsonModule.getJson());
            }
        });
        r.open();
    }
}
