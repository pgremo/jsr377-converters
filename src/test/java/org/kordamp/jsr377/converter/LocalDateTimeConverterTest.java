/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2018 the original author or authors.
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
package org.kordamp.jsr377.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.kordamp.jsr377.ConversionSupport;

import javax.application.converter.ConversionException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Andres Almiray
 */
public class LocalDateTimeConverterTest extends ConversionSupport {
    @ParameterizedTest
    @MethodSource("where_value_format_result")
    public void valueWithFormatProducesResult(Object value, String format, LocalDateTime result) {
        // given:
        LocalDateTimeConverter converter = new LocalDateTimeConverter();
        converter.setFormat(format);

        // when:
        LocalDateTime output = converter.fromObject(value);

        // then:
        assertThat(output, equalTo(result));
    }

    @ParameterizedTest
    @MethodSource("where_invalid_value")
    public void invalidValueProducesError(Object value) {
        // given:
        LocalDateTimeConverter converter = new LocalDateTimeConverter();

        // when:
        assertThrows(ConversionException.class, () -> converter.fromObject(value));
    }

    public static Stream<Arguments> where_value_format_result() {
        return Stream.of(
            Arguments.of(null, null, null),
            Arguments.of(emptyList(), null, null),
            Arguments.of("", null, null),
            Arguments.of("1970-01-01T12:13:14", null, LocalDateTime.of(1970, 1, 1, 12, 13, 14)),
            Arguments.of(0, null, LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)),
            Arguments.of(epochAsDate(), null, LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)),
            Arguments.of(epochAsCalendar(), null, LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)),
            Arguments.of(asList(1970, 1, 1), null, LocalDateTime.of(1970, 1, 1, 0, 0, 0)),
            Arguments.of(LocalDate.of(1970, 1, 1), null, LocalDateTime.of(1970, 1, 1, 0, 0, 0)),
            Arguments.of(asList(1970, 1, 1, 12), null, LocalDateTime.of(1970, 1, 1, 12, 0, 0)),
            Arguments.of(asList(1970, 1, 1, 12, 13), null, LocalDateTime.of(1970, 1, 1, 12, 13, 0)),
            Arguments.of(asList(1970, 1, 1, 12, 13, 14), null, LocalDateTime.of(1970, 1, 1, 12, 13, 14)),
            Arguments.of(asList(1970, 1, 1, 12, 13, 14, 0), null, LocalDateTime.of(1970, 1, 1, 12, 13, 14, 0)),
            Arguments.of(asList("1970", "1", "1", "12", "13", "14"), null, LocalDateTime.of(1970, 1, 1, 12, 13, 14)),
            Arguments.of(LocalDateTime.of(1970, 1, 1, 12, 13, 14), null, LocalDateTime.of(1970, 1, 1, 12, 13, 14)),
            Arguments.of(LocalDateTime.of(1970, 1, 1, 12, 13, 14), null, LocalDateTime.of(1970, 1, 1, 12, 13, 14)),
            Arguments.of("", "yyyy-MM-dd HH:mm:ss", null),
            Arguments.of("1970-01-01 12:13:14", "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14)),
            Arguments.of(0, "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)),
            Arguments.of(epochAsDate(), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)),
            Arguments.of(epochAsCalendar(), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)),
            Arguments.of(LocalDate.of(1970, 1, 1), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0)),
            Arguments.of(asList(1970, 1, 1), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 0, 0, 0)),
            Arguments.of(asList(1970, 1, 1, 12), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 0, 0)),
            Arguments.of(asList(1970, 1, 1, 12, 13), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 0)),
            Arguments.of(asList(1970, 1, 1, 12, 13, 14), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14)),
            Arguments.of(asList(1970, 1, 1, 12, 13, 14, 0), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14, 0)),
            Arguments.of(asList("1970", "1", "1", "12", "13", "14"), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14)),
            Arguments.of(LocalDateTime.of(1970, 1, 1, 12, 13, 14), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14)),
            Arguments.of(LocalDateTime.of(1970, 1, 1, 12, 13, 14), "yyyy-MM-dd HH:mm:ss", LocalDateTime.of(1970, 1, 1, 12, 13, 14))
        );
    }

    public static Stream<Arguments> where_invalid_value() {
        return Stream.of(
            Arguments.of("garbage"),
            Arguments.of(Collections.emptyMap()),
            Arguments.of(asList(1, 2)),
            Arguments.of(asList(1, 2, 3, 4, 5, 6, 7, 8)),
            Arguments.of(new Object())
        );
    }
}
