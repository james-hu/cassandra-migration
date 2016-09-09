/**
 * File     : ClusterConfiguration.kt
 * License  :
 *   Original   - Copyright (c) 2015 - 2016 Contrast Security
 *   Derivative - Copyright (c) 2016 Citadel Technology Solutions Pte Ltd
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.builtamont.cassandra.migration.api.configuration

import com.builtamont.cassandra.migration.internal.util.StringUtils

/**
 * Cluster configuration.
 */
class ClusterConfiguration : Configuration() {

    /**
     * Cluster configuration properties.
     *
     * @param namespace The property namespace.
     * @param description The property description.
     */
    enum class ClusterProperty constructor(val namespace: String, val description: String) {
        CONTACT_POINTS(PROPERTY_PREFIX + "contactpoints", "Comma separated values of node IP addresses"),
        PORT(PROPERTY_PREFIX + "port", "CQL native transport port"),
        USERNAME(PROPERTY_PREFIX + "username", "Username for password authenticator"),
        PASSWORD(PROPERTY_PREFIX + "password", "Password for password authenticator")
    }

    /**
     * Cluster node IP address(es).
     * (default: ["localhost"])
     */
    var contactpoints = arrayOf("localhost")
      get set

    /**
     * Cluster CQL native transport port.
     * (default: 9042)
     */
    var port = 9042
      get set

    /**
     * The username for password authenticator.
     */
    var username: String? = null
      get set

    /**
     * The password for password authenticator.
     */
    var password: String? = null
      get set

    /**
     * ClusterConfiguration initialization.
     */
    init {
        val contactpointsProp = System.getProperty(ClusterProperty.CONTACT_POINTS.namespace)
        if (!contactpointsProp.isNullOrBlank()) this.contactpoints = StringUtils.tokenizeToStringArray(contactpointsProp, ",")

        val portProp = System.getProperty(ClusterProperty.PORT.namespace)
        if (!portProp.isNullOrBlank()) this.port = Integer.parseInt(portProp)

        val usernameProp = System.getProperty(ClusterProperty.USERNAME.namespace)
        if (!usernameProp.isNullOrBlank()) this.username = usernameProp.trim()

        val passwordProp = System.getProperty(ClusterProperty.PASSWORD.namespace)
        if (!passwordProp.isNullOrBlank()) this.password = passwordProp.trim()
    }

    /**
     * ClusterConfiguration companion object.
     */
    companion object {
        private val PROPERTY_PREFIX = BASE_PREFIX + "cluster."
    }

}