package com.krisnaboniraesa.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krisnaboniraesa.moviecatalogue.data.source.MovieCatalogueRepository
import com.krisnaboniraesa.moviecatalogue.di.Injection
import com.krisnaboniraesa.moviecatalogue.ui.content.ContentViewModel
import com.krisnaboniraesa.moviecatalogue.ui.detail.DetailMovieViewModel

class ViewModelFactory private constructor(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ContentViewModel::class.java) -> {
                ContentViewModel(movieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(movieCatalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}