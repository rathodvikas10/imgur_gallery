package com.example.network.retrofit

import com.example.network.model.NetworkResponse
import com.example.network.NetworkDataSource
import com.example.network.model.NetworkImgurAlbum
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API declaration for [NetworkDataSource]
 */
internal interface ImgurApi {
    private companion object {
        const val SEARCH_IMAGES_ENDPOINT = "gallery/search/{sort}/{window}/{page}"
    }
    @GET(SEARCH_IMAGES_ENDPOINT)
    suspend fun getGallery(
        @Path("sort") sort: String,
        @Path("window") window: String,
        @Path("page") page: Int,
        @Query("q_type") type: String,
        @Query("q_all") query: String,
        @Query("q_size_px") size: Int = 500
    ): Response<NetworkResponse<List<NetworkImgurAlbum>>>

}