/*
 * Copyright (C) 2012-2018 DuyHai DOAN
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package info.archinnov.achilles.internals.codecs;

import info.archinnov.achilles.exception.AchillesTranscodingException;
import info.archinnov.achilles.internals.types.ClassAnnotatedByCodec;

public class CodecOnClass implements info.archinnov.achilles.type.codec.Codec<ClassAnnotatedByCodec, String> {
    @Override
    public Class<ClassAnnotatedByCodec> sourceType() {
        return ClassAnnotatedByCodec.class;
    }

    @Override
    public Class<String> targetType() {
        return String.class;
    }

    @Override
    public String encode(ClassAnnotatedByCodec fromJava) throws AchillesTranscodingException {
        return fromJava.toString();
    }

    @Override
    public ClassAnnotatedByCodec decode(String fromCassandra) throws AchillesTranscodingException {
        return new ClassAnnotatedByCodec();
    }
}
