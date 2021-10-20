package com.krisnaboniraesa.moviecatalogue.ui.content.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.krisnaboniraesa.moviecatalogue.data.source.local.entity.DataEntity
import com.krisnaboniraesa.moviecatalogue.databinding.FragmentMovieBinding
import com.krisnaboniraesa.moviecatalogue.ui.content.ContentAdapter
import com.krisnaboniraesa.moviecatalogue.ui.content.ContentCallback
import com.krisnaboniraesa.moviecatalogue.ui.content.ContentViewModel
import com.krisnaboniraesa.moviecatalogue.ui.detail.DetailMovieActivity
import com.krisnaboniraesa.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment(), ContentCallback {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this,
                factory
            )[ContentViewModel::class.java]
            val movieAdapter =
                ContentAdapter(this@MovieFragment)

            viewModel.getMovie().observe(viewLifecycleOwner, { movies ->
                showProgressBar(false)
                movieAdapter.setMovies(movies)
                movieAdapter.notifyDataSetChanged()
            })

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        fragmentMovieBinding.progressBar.isVisible = state
        fragmentMovieBinding.rvMovie.isInvisible = state
    }

    override fun onItemClicked(data: DataEntity) {
        startActivity(
            Intent(context, DetailMovieActivity::class.java)
                .putExtra(DetailMovieActivity.EXTRA_MOVIE, data.id)
                .putExtra(DetailMovieActivity.EXTRA_TYPE, "MOVIE")
        )
    }

}