/*
 * Copyright 2014 Stormpath, Inc.
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
package com.okta.sdk.impl.api;

import com.okta.sdk.api.ApiKey;
import com.okta.sdk.lang.Strings;

/**
 * This implementation represents the api key that is used to authenticate a Tenant in Okta.
 * @since 1.0.0
 */
public class ClientApiKey implements ApiKey {

    private final String id;

    private final String secret;

    private static final String METHOD_ERROR_MESSAGE = "This method is not accessible from a client api key. Only getId() and getSecret() are available from this instance.";

    public ClientApiKey(String id, String secret) {
        if (!Strings.hasText(id)) {
            throw new IllegalArgumentException("API key id cannot be null or empty.");
        }
        if (!Strings.hasText(secret)) {
            throw new IllegalArgumentException("API key secret cannot be null or empty.");
        }
        this.id = id;
        this.secret = secret;
    }

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    @Override
    public String toString() {
        return getId(); //never ever print the secret
    }

    @Override
    public int hashCode() {
        return this.id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ClientApiKey) {
            ClientApiKey other = (ClientApiKey)o;
            return (id != null ? id.equals(other.id) : other.id == null) &&
                    (secret != null ? secret.equals(other.secret) : other.secret == null);
        }

        return false;
    }
}
