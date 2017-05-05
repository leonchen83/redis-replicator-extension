package com.moilioncircle.redis.replicator.extension.module;

import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.extension.module.cmd.parser.*;
import com.moilioncircle.redis.replicator.extension.module.rdb.parser.JsonModuleParser;

/**
 * Created by Baoyi Chen on 2017/5/5.
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
