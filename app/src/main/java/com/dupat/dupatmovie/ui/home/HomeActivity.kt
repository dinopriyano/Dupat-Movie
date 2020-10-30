package com.dupat.dupatmovie.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.data.network.model.MovieModel
import com.dupat.dupatmovie.databinding.ActivityHomeBinding
import com.dupat.dupatmovie.ui.home.adapter.BannerAdapter
import com.dupat.dupatmovie.ui.home.adapter.GenreAdapter
import com.dupat.dupatmovie.ui.home.adapter.MovieHomeAdapter
import com.dupat.dupatmovie.ui.home.helper.ItemOffsetDecoration
import com.dupat.dupatmovie.ui.home.viewholder.BannerViewHolder
import com.dupat.dupatmovie.ui.utils.toast
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.bannerview.utils.BannerUtils
import com.zhpan.indicator.enums.IndicatorSlideMode
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var bannerView: BannerViewPager<MovieModel,BannerViewHolder>
    private lateinit var binding: ActivityHomeBinding
    var moviePage: Int = 1
    var totalMoviePage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        binding.viewmodel = movieViewModel
        bannerView = findViewById(R.id.banner_view)
        setupRecyclerMovie()
        setupRecyclerGenre()
        movieScrollListener()
        movieViewModel.getMovies().observe(this, Observer {
            recycler_movie.adapter?.let { adp ->
                when(adp)
                {
                    is MovieHomeAdapter ->{
                        totalMoviePage = it.total_pages!!
                        adp.addList(it.results!!)
                    }
                }
            }
        })

        movieViewModel.getGenres().observe(this, Observer {
            recycler_genre.adapter.let {adp->
                when(adp)
                {
                    is GenreAdapter ->{
                        adp.setList(it)
                    }
                }
            }
        })

        movieViewModel.getBanner().observe(this, Observer {
            setupViewPager(it)
        })

        movieViewModel.getState().observe(this, Observer {
            handleUIState(it)
        })
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.fetchBanner(1)
        movieViewModel.fetchGenres()
    }

    private fun setupRecyclerGenre()
    {
        recycler_genre.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL, false)
            adapter = GenreAdapter(mutableListOf(),this@HomeActivity)
        }
    }

    private fun setupRecyclerMovie()
    {
        val spanCount = 3
        val spacing = 20
        val includeEdge = false
        recycler_movie.apply {
            layoutManager = GridLayoutManager(this@HomeActivity,3)
            adapter = MovieHomeAdapter(mutableListOf(),this@HomeActivity)
            addItemDecoration(ItemOffsetDecoration(spanCount, spacing, includeEdge))
        }
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
        scrollContainer.setOnScrollChangeListener(scrollListener)
    }

    private fun loadMoreMovie()
    {
        movieViewModel.fetchAllMovie(moviePage)
        moviePage += 1
    }

    private fun setupViewPager(list: List<MovieModel>) {
        bannerView.apply {
            setCanLoop(true)
            setAutoPlay(true)
            setPageStyle(PageStyle.MULTI_PAGE_OVERLAP)
            setPageMargin(10)
            setIndicatorMargin(0, 0, 0, 10)
            setIndicatorSliderGap(10)
            setInterval(5000)
            setLifecycleRegistry(lifecycle)
            setIndicatorSlideMode(IndicatorSlideMode.SCALE)
            setIndicatorSliderRadius(3, 5)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    BannerUtils.log("position:$position")
                }
            })
            adapter = BannerAdapter()
            setIndicatorSliderColor(
                ContextCompat.getColor(this@HomeActivity, android.R.color.white),
                ContextCompat.getColor(this@HomeActivity, android.R.color.holo_red_dark)
            )
        }.create(list.take(10))
    }

    private fun handleUIState(it : MovieState){
        when(it){
            is MovieState.Error -> {
                toast(it.err!!)
            }
            is MovieState.IsLoadingMoreMovie ->{
                binding.isLoadingMoreMovie = it.state
            }
        }
    }
}