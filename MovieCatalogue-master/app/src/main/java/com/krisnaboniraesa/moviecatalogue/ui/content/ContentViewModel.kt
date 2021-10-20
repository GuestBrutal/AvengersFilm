package com.krisnaboniraesa.moviecatalogue.ui.content

import androidx.lifecycle.ViewModel
import com.krisnaboniraesa.moviecatalogue.data.source.MovieCatalogueRepository

class ContentViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {
    fun getMovie() = movieCatalogueRepository.getMovies()
    fun getTvShow() = movieCatalogueRepository.getTvShows()
}