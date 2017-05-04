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

package com.moilioncircle.redis.replicator.extension.module;

import com.moilioncircle.redis.replicator.rdb.datatype.Module;
import com.moilioncircle.redis.replicator.rdb.module.ModuleParser;

/**
 * @author Leon Chen
 * @since 1.0.0
 * @see <a href="https://github.com/antirez/redis/blob/unstable/src/modules/TYPES.md">TYPES.md</a>
 */
public interface VersionableModuleParser<T extends Module> extends ModuleParser<T> {

    /**
     * @return module type name
     * modules types require a 9 characters name
     * <p>
     * regex: [a-zA-Z-_]{@literal {9}}
     */
    String name();

    /**
     * @return module version
     * <p>
     * version range {@literal 0 <= version < 1024}
     */
    int version();
}
