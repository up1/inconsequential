/* Copyright (C) 2010 SpringSource
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
package org.springframework.datastore.mapping.gemfire.config;

import groovy.lang.Closure;
import org.springframework.datastore.mapping.config.groovy.MappingConfigurationBuilder;
import org.springframework.datastore.mapping.keyvalue.mapping.Family;
import org.springframework.datastore.mapping.keyvalue.mapping.KeyValue;
import org.springframework.datastore.mapping.keyvalue.mapping.config.GormKeyValueMappingFactory;
import org.springframework.datastore.mapping.model.PersistentEntity;
import org.springframework.datastore.mapping.model.config.GormProperties;
import org.springframework.datastore.mapping.reflect.ClassPropertyFetcher;

/**
 *
 * Allows GORM-style configuration of how an entity maps to a
 * Gemfire region
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public class GormGemfireMappingFactory extends GormKeyValueMappingFactory {


    public GormGemfireMappingFactory() {
        super("Gemfire");
    }

    @Override
    public Family createMappedForm(PersistentEntity entity) {
        ClassPropertyFetcher cpf = ClassPropertyFetcher.forClass(entity.getJavaClass());
        final Closure value = cpf.getStaticPropertyValue(GormProperties.MAPPING, Closure.class);
        if(value != null) {
            Region family = new Region();
            MappingConfigurationBuilder builder = new MappingConfigurationBuilder(family, KeyValue.class);
            builder.evaluate(value);
            entityToPropertyMap.put(entity, builder.getProperties());
            return family;
        }
        else {
            return super.createMappedForm(entity);
        }
    }
}
