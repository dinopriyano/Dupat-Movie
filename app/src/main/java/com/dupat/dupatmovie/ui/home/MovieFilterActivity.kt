package com.dupat.dupatmovie.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.databinding.ActivityHomeBinding
import com.dupat.dupatmovie.databinding.ActivityMovieFilterBinding
import com.dupat.dupatmovie.ui.home.adapter.MovieHomeAdapter
import com.dupat.dupatmovie.ui.home.helper.ItemOffsetDecoration
import com.dupat.dupatmovie.ui.utils.toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.recycler_movie
import kotlinx.android.synthetic.main.activity_movie_filter.*

class MovieFilterActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieFilterBinding
    var moviePage: Int = 1
    var totalMoviePage: Int = 0
    var genreID: Int = 0
    var genreName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie_filter)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        binding.viewmodel = movieViewModel

        toolBar.title = ""
        setSupportActionBar(toolBar)                                        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        genreID = intent.getIntExtra("genreID",0)
        genreName = intent.getStringExtra("genreName")
        txtGenreName.text = genreName

        setupRecyclerMovie()
        movieScrollListener()
        movieViewModel.getMovies().observe(this, Observer {
            recycler_movie.adapter.let {adr->
                when(adr){
                    is MovieHomeAdapter ->{
                        totalMoviePage = it.total_pages!!
                        adr.addList(it.results!!)
                    }
                }
            }
        })

        movieViewModel.getState().observe(this, Observer {
            handleUIState(it)
        })
    }

    private fun handleUIState(it : MovieState){
        when(it){
            is MovieState.Error -> {
                toast(it.err!!)
            }
            is MovieState.IsLoadingMoreMovie ->{
                binding.isLoadingMore = it.state
            }
        }
    }

    private fun loadMoreMovie()
    {
        movieViewModel.fetchMovieByGenre(moviePage,genreID)
        moviePage += 1
    }

    private fun movieScrollListener() {
        loadMoreMovie()
        var scrollListener = object : NestedScrollView.OnScrollChangeListener,
            ViewTreeObserver.OnScrollChangedListener {
            override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                if(!v?.canScrollVertically(1)!!)
                {
                    loadMoreMovie()
                }
            }

            override fun onScrollChanged() {
                TODO("Not yet implemented")
            }

        }
        scrollContainerMovieFilter.setOnScrollChangeListener(scrollListener)
    }

    private fun setupRecyclerMovie()
    {
        val spanCount = 3
        val spacing = 20
        val includeEdge = false
        recycler_movie.apply {
            layoutManager = GridLayoutManager(this@MovieFilterActivity,3)
            adapter = MovieHomeAdapter(mutableListOf(),this@MovieFilterActivity)
            addItemDecoration(ItemOffsetDecoration(spanCount, spacing, includeEdge))
        }
    }
}