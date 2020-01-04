/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 the original author or authors.
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
package org.kordamp.jsr377.converter.spi;

import org.kordamp.jsr377.converter.LocaleConverter;

import javax.application.converter.Converter;
import javax.application.converter.spi.ConverterProvider;
import java.util.Locale;

/**
 * @author Andres Almiray
 */
public class LocaleConverterProvider implements ConverterProvider<Locale> {
    @Override
    public Class<Locale> getTargetType() {
        return Locale.class;
    }

    @Override
    public Class<? extends Converter<Locale>> getConverterType() {
        return LocaleConverter.class;
    }
}
