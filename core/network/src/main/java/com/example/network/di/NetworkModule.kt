package com.example.network.di

import com.example.network.BuildConfig
import com.example.network.NetworkDataSource
import com.example.network.model.RemoteConfig
import com.example.network.retrofit.AuthenticationInterceptor
import com.example.network.retrofit.ImgurApi
import com.example.network.retrofit.RetrofitDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {
    @Binds
    fun binds(retrofitNetworkSource: RetrofitDataSource): NetworkDataSource

    companion object {

        // This can be inject form firebase config and stored in secure keychain
        @Provides
        @Singleton
        fun providesBaseUrl(): RemoteConfig = RemoteConfig()

        // Ignoring properties that are defined in the Imgur API model
        // to reduce development time for assignment
        @OptIn(ExperimentalSerializationApi::class)
        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false // Reason mention above
        }

        @Provides
        @Singleton
        fun okHttpCallFactory(
            authenticationInterceptor: AuthenticationInterceptor
        ): Call.Factory = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .addInterceptor(authenticationInterceptor)
            .build()

        @Provides
        @Singleton
        fun providesAuthenticationInterceptor(remoteConfig: RemoteConfig) =
            AuthenticationInterceptor(remoteConfig.getClientId())

        @Provides
        @Singleton
        fun providesOkHttpRetrofitClient(
            okhttpCallFactory: Call.Factory,
            networkJson: Json,
            remoteConfig: RemoteConfig
        ): ImgurApi = Retrofit.Builder()
            .baseUrl(remoteConfig.getBaseUrl())
            .callFactory { okhttpCallFactory.newCall(it) }
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ImgurApi::class.java)
    }
}