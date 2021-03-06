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
package org.springframework.datastore.mapping.proxy;

import org.springframework.datastore.mapping.core.Session;

import java.io.Serializable;

/**
 * The factory used to create proxies
 * 
 * @author Graeme Rocher
 * @since 1.0
 */
public interface ProxyFactory {

    <T> T createProxy(Session session, Class<T> type, Serializable key);

    boolean isProxy(Object object);

    Serializable getIdentifier(Object obj);
}
