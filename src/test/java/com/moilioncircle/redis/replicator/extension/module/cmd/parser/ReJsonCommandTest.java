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

import com.moilioncircle.redis.replicator.cmd.impl.ExistType;
import com.moilioncircle.redis.replicator.extension.module.cmd.impl.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Leon Chen
 * @since 1.0.0
 */
public class ReJsonCommandTest extends AbstractParserTest {
    @Test
    public void parse() throws Exception {
        {
            JsonSetParser parser = new JsonSetParser();
            JsonSetCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.set", "key", "path", "[true]", "NX"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals("[true]", command.getJson());
            assertEquals(ExistType.NX, command.getType());
            System.out.println(command);
        }

        {
            JsonSetParser parser = new JsonSetParser();
            JsonSetCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.set", "key", "path", "[true]"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals("[true]", command.getJson());
            assertEquals(ExistType.NONE, command.getType());
        }

        {
            JsonDelParser parser = new JsonDelParser();
            JsonDelCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.del", "key"}));
            assertEquals("key", command.getKey());
            assertEquals(".", command.getPath());
            System.out.println(command);
        }

        {
            JsonDelParser parser = new JsonDelParser();
            JsonDelCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.del", "key", "path"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
        }

        {
            JsonArrAppendParser parser = new JsonArrAppendParser();
            JsonArrAppendCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.arrappend", "key", "path", "true", "null"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals("true", command.getJsons()[0]);
            assertEquals("null", command.getJsons()[1]);
            System.out.println(command);
        }

        {
            JsonArrAppendParser parser = new JsonArrAppendParser();
            JsonArrAppendCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.arrappend", "key", "path", "true"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals("true", command.getJsons()[0]);
        }

        {
            JsonArrInsertParser parser = new JsonArrInsertParser();
            JsonArrInsertCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.arrinsert", "key", "path", "0", "true"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(0, command.getIndex());
            assertEquals("true", command.getJsons()[0]);
            System.out.println(command);
        }

        {
            JsonArrInsertParser parser = new JsonArrInsertParser();
            JsonArrInsertCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.arrinsert", "key", "path", "0", "true", "null"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(0, command.getIndex());
            assertEquals("true", command.getJsons()[0]);
            assertEquals("null", command.getJsons()[1]);
        }

        {
            JsonArrPopParser parser = new JsonArrPopParser();
            JsonArrPopCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.arrpop", "key"}));
            assertEquals("key", command.getKey());
            assertEquals(".", command.getPath());
            assertEquals(-1, command.getIndex());
            System.out.println(command);
        }

        {
            JsonArrPopParser parser = new JsonArrPopParser();
            JsonArrPopCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.arrpop", "key", "path"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(-1, command.getIndex());
        }

        {
            JsonArrPopParser parser = new JsonArrPopParser();
            JsonArrPopCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.arrpop", "key", "path", "100"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(100, command.getIndex());
        }

        {
            JsonArrTrimParser parser = new JsonArrTrimParser();
            JsonArrTrimCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.arrtrim", "key", "path", "100", "200"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(100, command.getStart());
            assertEquals(200, command.getStop());
            System.out.println(command);
        }

        {
            JsonNumIncrByParser parser = new JsonNumIncrByParser();
            JsonNumIncrByCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.numincrby", "key", "path", "100.542"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals(100.542d, command.getValue(), 0.000001);
            System.out.println(command);
        }

        {
            JsonNumIncrByParser parser = new JsonNumIncrByParser();
            JsonNumIncrByCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.numincrby", "key", "100.542"}));
            assertEquals("key", command.getKey());
            assertEquals(".", command.getPath());
            assertEquals(100.542d, command.getValue(), 0.000001);
        }

        {
            JsonStrAppendParser parser = new JsonStrAppendParser();
            JsonStrAppendCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.strappend", "key", "world"}));
            assertEquals("key", command.getKey());
            assertEquals(".", command.getPath());
            assertEquals("world", command.getJson());
            System.out.println(command);
        }

        {
            JsonStrAppendParser parser = new JsonStrAppendParser();
            JsonStrAppendCommand command = parser.parse(
                    toObjectArray(new Object[]{"json.strappend", "key", "path", "world"}));
            assertEquals("key", command.getKey());
            assertEquals("path", command.getPath());
            assertEquals("world", command.getJson());
        }
    }

}