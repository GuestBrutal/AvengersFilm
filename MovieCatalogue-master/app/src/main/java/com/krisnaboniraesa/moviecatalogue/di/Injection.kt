package com.krisnaboniraesa.moviecatalogue.di

import android.content.Context
import com.krisnaboniraesa.moviecatalogue.data.source.MovieCatalogueRepository
import com.krisnaboniraesa.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return MovieCatalogueRepository.getInstance(remoteDataSource)
    }
}