package com.example.data.di

import com.example.data.repository.GalleryRepository
import com.example.data.repository.NetworkGalleryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsImgur(
        repository: NetworkGalleryRepository
    ): GalleryRepository
}