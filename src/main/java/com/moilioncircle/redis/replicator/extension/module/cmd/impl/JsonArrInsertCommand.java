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

import java.util.Arrays;

/**
 * @author Leon Chen
 * @see <a href="https://github.com/RedisLabsModules/rejson">JSON.ARRINSERT</a>
 * @since 1.0.0
 */
public class JsonArrInsertCommand implements Command {
    private String key;
    private String path;
    private int index;
    private String[] jsons;

    public JsonArrInsertCommand() {
    }

    public JsonArrInsertCommand(String key, String path, int index, String[] jsons) {
        this.key = key;
        this.path = path;
        this.index = index;
        this.jsons = jsons;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String[] getJsons() {
        return jsons;
    }

    public void setJsons(String[] jsons) {
        this.jsons = jsons;
    }

    @Override
    public String toString() {
        return "JsonArrInsertCommand{" +
                "key='" + key + '\'' +
                ", path='" + path + '\'' +
                ", index=" + index +
                ", jsons=" + Arrays.toString(jsons) +
                '}';
    }
}
