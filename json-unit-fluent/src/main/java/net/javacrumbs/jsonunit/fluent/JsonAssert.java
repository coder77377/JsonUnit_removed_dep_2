/**
 * Copyright 2009-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.javacrumbs.jsonunit.fluent;

import com.fasterxml.jackson.databind.JsonNode;
import net.javacrumbs.jsonunit.core.internal.Diff;

import static net.javacrumbs.jsonunit.core.internal.JsonUtils.convertToJson;


/**
 * Contains JSON related fluent assertions inspired by FEST or AssertJ. Typical usage is:
 * <p/>
 * <code>
 * assertThatJson("{\"test\":1}").isEqualTo("{\"test\":2}");
 * assertThatJson("{\"test\":1}").hasSameStructureAs("{\"test\":21}");
 * assertThatJson("{\"root\":{\"test\":1}}").node("root.test").isEqualTo("2");
 * </code>
 * <p/>
 * Please note that the method name is assertThatJson and not assertThat. The reason is that we need to accept String parameter
 * and do not want to override standard FEST or AssertJ assertThat(String) method.
 * @deprecated use JsonFluentAssert instead
 */
@Deprecated
public class JsonAssert extends JsonFluentAssert {
    private static final String ACTUAL = "actual";

    protected JsonAssert(JsonNode actual, String path, String description, String ignorePlaceholder) {
        super(actual, path, description, ignorePlaceholder);
    }

    public JsonAssert(JsonNode actual) {
        super(actual);
    }


    /**
     * Creates a new instance of <code>{@link JsonAssert}</code>.
     * It is not called assertThat to not clash with StringAssert.
     *
     * @param json
     * @return new JsonAssert object.
     */
    public static JsonAssert assertThatJson(JsonNode json) {
        return new JsonAssert(json);
    }

    /**
     * Creates a new instance of <code>{@link JsonAssert}</code>.
     * It is not called assertThat to not clash with StringAssert.
     * The json parameter is converted to JSON using ObjectMapper.
     *
     * @param json
     * @return new JsonAssert object.
     */
    public static JsonAssert assertThatJson(Object json) {
        return assertThatJson(convertToJson(json, ACTUAL));
    }
}
