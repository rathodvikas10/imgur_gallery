package com.example.network

import com.example.network.retrofit.ImgurApi
import com.example.network.retrofit.NetworkException
import com.example.network.retrofit.RetrofitDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import kotlin.test.assertEquals


class NetworkDataSourceTest {

    private lateinit var server: MockWebServer
    private lateinit var retrofitApi: ImgurApi

    @Before
    fun setup() {
        val networkJson = Json { ignoreUnknownKeys = true }
        server = MockWebServer()
        retrofitApi = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .callFactory { OkHttpClient.Builder().build().newCall(it) }
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ImgurApi::class.java)
    }

    @After
    fun after() {
        server.shutdown()
    }

    @Test
    fun test_getEmployees_isSuccessful() = runTest {

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        val networkDataSource = RetrofitDataSource(testDispatcher,retrofitApi)
        val subject = setupMock("success.json", 200) {
            networkDataSource.searchImages(
                "top","week",1,"image","cats"
            )
        }

        val dataSizeExpected = 57
        val dataSizeActual = subject.size

        assertEquals(dataSizeExpected, dataSizeActual)
    }

    @Test
    fun test_getEmployees_for_403() {

        val exception: Exception = assertThrows(NetworkException::class.java) {
            runTest {
                val testDispatcher = UnconfinedTestDispatcher(testScheduler)
                val networkDataSource = RetrofitDataSource(testDispatcher,retrofitApi)

                setupMock("error.json", 403) {
                    networkDataSource.searchImages(
                        "top","week",1,"image","cats"
                    )
                }
            }
        }

        val expectedMessage = "This method requires authentication"
        val actualMessage = exception.message

        assertTrue(actualMessage!!.contains(expectedMessage))
    }


    private suspend fun <T> setupMock(fileName: String, responseCode: Int, block: suspend () -> T): T {
        val jsonBody = Helper.readFile(fileName)
        val mockResponse = MockResponse().apply {
            setBody(jsonBody)
            setResponseCode(responseCode)
        }
        server.enqueue(mockResponse)

        val result = block()

        server.takeRequest()

        return result
    }

}