package com.example.network.retrofit

import com.example.common.network.AppDispatchers
import com.example.common.network.Dispatcher
import com.example.network.NetworkDataSource
import com.example.network.model.NetworkImgurAlbum
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [Retrofit] implementation of [NetworkDataSource]
 */
@Singleton
internal class RetrofitDataSource @Inject constructor(
    @Dispatcher(AppDispatchers.IO)
    private val dispatcher: CoroutineDispatcher,
    private val networkApi: ImgurApi,
) : NetworkDataSource {
    override suspend fun searchImages(
        sort: String,
        window: String,
        page: Int,
        type: String,
        query: String
    ): List<NetworkImgurAlbum> {
        return withContext(dispatcher) {
            networkApi.getGallery(sort, window, page, type, query)
                .getBodyOrThrow().data
        }
    }
}



/**
 * Extension function to get the body or throw an exception
 * @throws [Exception]
 */
private fun <T> Response<T>.getBodyOrThrow(): T {
    return if(isSuccessful) {
        body() ?: throw Exception("Error fetching request")
    } else {
        val errorMessage = errorMessage(code())
        val errorBodyMessage = errorBody()?.let {
            try {
                JSONObject(it.string())
                    .getJSONObject("data")
                    .getString("error")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        throw NetworkException(errorBodyMessage ?: errorMessage)
    }
}

/**
 * Returns the error message based on the error code
 */
private fun errorMessage(code: Int): String {
    return when (code) {
        401 -> "Unauthorised, please login"
        404 -> "Not found, contact support"
        429 -> "To many request, please try again later"
        else -> "Something went wrong"
    }
}