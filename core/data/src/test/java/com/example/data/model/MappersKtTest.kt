package com.example.data.model

import com.example.network.model.NetworkImgurAlbum
import com.example.network.model.NetworkImgurImage
import org.junit.Assert.*

import org.junit.Test

class MappersKtTest {

    @Test
    fun test_toAlbum() {
        val networkImage = NetworkImgurImage(
            id = "id",
            link = "link",
            datetime = 1
        )
        val networkAlbum = NetworkImgurAlbum(
            id = "id",
            title = "title",
            datetime = 2,
            images = listOf(networkImage)
        )
        val subject = networkAlbum.toAlbum()
        assertEquals("id", subject.id)
        assertEquals("title", subject.title)
        assertEquals(2, subject.datetime)
        assertEquals(1, subject.images?.size)
    }

    @Test
    fun test_toImage() {
        val networkImage = NetworkImgurImage(
            id = "id",
            link = "link",
            datetime = 1
        )
        val subject = networkImage.toImage()
        assertEquals("id", subject.id)
        assertEquals("link", subject.url)
        assertEquals(1, subject.datetime)
    }
}