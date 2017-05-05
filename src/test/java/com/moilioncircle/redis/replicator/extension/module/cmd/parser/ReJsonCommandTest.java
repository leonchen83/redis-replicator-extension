package com.moilioncircle.redis.replicator.extension.module.cmd.parser;

import com.moilioncircle.redis.replicator.cmd.impl.ExistType;
import com.moilioncircle.redis.replicator.extension.module.cmd.impl.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Baoyi Chen on 2017/5/5.
 */
public class ReJsonCommandTest {
    @Test
    public void parse() throws Exception {
        {
            JsonSetParser parser = new JsonSetParser();
            JsonSetCommand command = parser.parse(
                    new Object[]{"json.set", "key", "path", "[true]", "NX"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals("[true]", command.getJson());
            assertEquals(ExistType.NX, command.getType());
        }

        {
            JsonSetParser parser = new JsonSetParser();
            JsonSetCommand command = parser.parse(
                    new Object[]{"json.set", "key", "path", "[true]"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals("[true]", command.getJson());
            assertEquals(ExistType.NONE, command.getType());
        }

        {
            JsonDelParser parser = new JsonDelParser();
            JsonDelCommand command = parser.parse(
                    new Object[]{"json.del", "key"});
            assertEquals("key", command.getKey());
            assertEquals(".", command.getPath());
        }

        {
            JsonDelParser parser = new JsonDelParser();
            JsonDelCommand command = parser.parse(
                    new Object[]{"json.del", "key", "path"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
        }

        {
            JsonArrAppendParser parser = new JsonArrAppendParser();
            JsonArrAppendCommand command = parser.parse(
                    new Object[]{"json.arrappend", "key", "path", "true", "null"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals("true", command.getJsons()[0]);
            assertEquals("null", command.getJsons()[1]);
        }

        {
            JsonArrAppendParser parser = new JsonArrAppendParser();
            JsonArrAppendCommand command = parser.parse(
                    new Object[]{"json.arrappend", "key", "path", "true"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals("true", command.getJsons()[0]);
        }

        {
            JsonArrInsertParser parser = new JsonArrInsertParser();
            JsonArrInsertCommand command = parser.parse(
                    new Object[]{"json.arrinsert", "key", "path", "0", "true"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(0, command.getIndex());
            assertEquals("true", command.getJsons()[0]);
        }

        {
            JsonArrInsertParser parser = new JsonArrInsertParser();
            JsonArrInsertCommand command = parser.parse(
                    new Object[]{"json.arrinsert", "key", "path", "0", "true", "null"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(0, command.getIndex());
            assertEquals("true", command.getJsons()[0]);
            assertEquals("null", command.getJsons()[1]);
        }

        {
            JsonArrPopParser parser = new JsonArrPopParser();
            JsonArrPopCommand command = parser.parse(
                    new Object[]{"json.arrpop", "key"});
            assertEquals("key", command.getKey());
            assertEquals(".", command.getPath());
            assertEquals(-1, command.getIndex());
        }

        {
            JsonArrPopParser parser = new JsonArrPopParser();
            JsonArrPopCommand command = parser.parse(
                    new Object[]{"json.arrpop", "key", "path"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(-1, command.getIndex());
        }

        {
            JsonArrPopParser parser = new JsonArrPopParser();
            JsonArrPopCommand command = parser.parse(
                    new Object[]{"json.arrpop", "key", "path", "100"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(100, command.getIndex());
        }

        {
            JsonArrTrimParser parser = new JsonArrTrimParser();
            JsonArrTrimCommand command = parser.parse(
                    new Object[]{"json.arrtrim", "key", "path", "100", "200"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(100, command.getStart());
            assertEquals(200, command.getStop());
        }

        {
            JsonNumIncrByParser parser = new JsonNumIncrByParser();
            JsonNumIncrByCommand command = parser.parse(
                    new Object[]{"json.numincrby", "key", "path", "100.542"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(100.542d, command.getValue(), 0.000001);
        }

        {
            JsonNumIncrByParser parser = new JsonNumIncrByParser();
            JsonNumIncrByCommand command = parser.parse(
                    new Object[]{"json.numincrby", "key", "100.542"});
            assertEquals("key", command.getKey());
            assertEquals(".", command.getPath());
            assertEquals(100.542d, command.getValue(), 0.000001);
        }

        {
            JsonStrAppendParser parser = new JsonStrAppendParser();
            JsonStrAppendCommand command = parser.parse(
                    new Object[]{"json.strappend", "key", "world"});
            assertEquals("key", command.getKey());
            assertEquals(".", command.getPath());
            assertEquals("world", command.getJson());
        }

        {
            JsonStrAppendParser parser = new JsonStrAppendParser();
            JsonStrAppendCommand command = parser.parse(
                    new Object[]{"json.strappend", "key", "path", "world"});
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals("world", command.getJson());
        }
    }

}