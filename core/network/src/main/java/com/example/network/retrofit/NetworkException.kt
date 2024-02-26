package com.example.network.retrofit

/**
 * Network related exception
 * @sample
 * when (code) {
 *     401 -> "Unauthorised, please login"
 *     404 -> "Not found, contact support"
 *     429 -> "To many request, please try again later"
 *     else -> "Something went wrong"
 * }
 */
class NetworkException(message: String): Exception(message)