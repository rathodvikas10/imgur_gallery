package com.example.network.model

/**
 * Application configuration parameters
 * This can be stored in secure area in android keystore
 * @property map application configuration parameters
 *
 * CURRENT CONFIGURATIONS :
 *
 * BASE_URL: Network base url
 * X_API_KEY: Api key
 *
 */
data class RemoteConfig(
    val map: Map<String, String> = mapOf()
) {
    companion object {
        const val BASE_URL = "BASE_URL"
        const val CLIENT_ID_KEY = "CLIENT_ID"
    }

    /**
     * Get base url
     * @return [String] base url
     */
    fun getBaseUrl(): String {
        return map[BASE_URL] ?: "https://api.imgur.com/3/"
    }

    fun getClientId(): String {
        return map[CLIENT_ID_KEY] ?: "83cbf6b767e4e1a"
    }
}
