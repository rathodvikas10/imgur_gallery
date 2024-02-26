package com.example.domain

import com.example.network.NetworkDataSource
import com.example.network.model.NetworkImgurAlbum
import kotlin.random.Random

class FakeDataSource: NetworkDataSource {
    private var count = 0
    override suspend fun searchImages(
        sort: String,
        window: String,
        page: Int,
        type: String,
        query: String
    ): List<NetworkImgurAlbum> {
        count++
        return (1..50).map {
            NetworkImgurAlbum(
                id = "id$it+$count",
                title = "title$it",
                datetime = Random.nextLong(),
                images = arrayListOf()
            )
        }
    }
}