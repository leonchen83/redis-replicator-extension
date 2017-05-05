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

import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.extension.module.cmd.parser.*;
import com.moilioncircle.redis.replicator.extension.module.rdb.parser.JsonModuleParser;

/**
 * @author Leon Chen
 * @since 1.0.0
 */
public class Modules {

    public static void redex(Replicator replicator) {
        //
    }

    public static void redisML(Replicator replicator) {
        // TODO
    }

    public static void redisSearch(Replicator replicator) {
        // TODO
    }

    public static void rejson(Replicator replicator) {
        rejson(replicator, true);
    }

    public static void rejson(Replicator replicator, boolean ordered) {
        JsonSetParser set = new JsonSetParser();
        JsonDelParser del = new JsonDelParser();
        JsonArrPopParser arrPop = new JsonArrPopParser();
        JsonArrTrimParser arrTrim = new JsonArrTrimParser();
        JsonArrAppendParser arrAppend = new JsonArrAppendParser();
        JsonArrInsertParser arrInsert = new JsonArrInsertParser();
        JsonStrAppendParser strAppend = new JsonStrAppendParser();
        JsonNumIncrByParser numIncrBy = new JsonNumIncrByParser();

        JsonModuleParser jsonModule = new JsonModuleParser(ordered);

        try {
            replicator.addCommandParser(set.name(), set);
            replicator.addCommandParser(del.name(), del);
            replicator.addCommandParser(arrPop.name(), arrPop);
            replicator.addCommandParser(arrTrim.name(), arrTrim);
            replicator.addCommandParser(arrAppend.name(), arrAppend);
            replicator.addCommandParser(arrInsert.name(), arrInsert);
            replicator.addCommandParser(strAppend.name(), strAppend);
            replicator.addCommandParser(numIncrBy.name(), numIncrBy);
        } catch (Exception ignore) {
            // might throw UnsupportedOperationException. we can ignore this exception.
        }

        try {
            replicator.addModuleParser(jsonModule.name(), jsonModule.version(), jsonModule);
        } catch (Exception ignore) {
            // might throw UnsupportedOperationException. we can ignore this exception.
        }
    }
}
