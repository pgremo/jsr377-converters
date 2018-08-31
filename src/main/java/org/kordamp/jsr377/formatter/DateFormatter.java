/*
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
package org.kordamp.jsr377.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Andres Almiray
 */
public class DateFormatter extends AbstractFormatter<Date> {
    private final SimpleDateFormat dateFormat;

    public DateFormatter() {
        this(null);
    }

    public DateFormatter(String pattern) {
        if (isBlank(pattern)) {
            dateFormat = new SimpleDateFormat();
        } else {
            dateFormat = new SimpleDateFormat(pattern);
        }
    }

    public String getPattern() {
        return dateFormat.toPattern();
    }

    @Override
    public String format(Date date) {
        return date == null ? null : dateFormat.format(date);
    }

    @Override
    public Date parse(String str) throws ParseException {
        if (isBlank(str)) { return null; }
        try {
            return dateFormat.parse(str);
        } catch (java.text.ParseException e) {
            throw new ParseException(e);
        }
    }
}
