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

package com.moilioncircle.redis.replicator.extension.module.cmd.impl;

import com.moilioncircle.redis.replicator.cmd.Command;

/**
 * @author Leon Chen
 * @see [https://github.com/RedisLabsModules/rejson]
 * @since 1.0.0
 */
public class JsonSetCommand implements Command {
    private String key;
    private String path;
    private String json;

    public JsonSetCommand() {
    }

    public JsonSetCommand(String key, String path, String json) {
        this.key = key;
        this.path = path;
        this.json = json;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "JsonSetCommand{" +
                "key='" + key + '\'' +
                ", path='" + path + '\'' +
                ", json='" + json + '\'' +
                '}';
    }
}
