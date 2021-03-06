package com.krisnaboniraesa.moviecatalogue.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<MovieList>,

	@field:SerializedName("total_results")
	val totalResults: Int

)
