package com.dupat.dupatmovie.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.data.network.model.MovieModel
import com.dupat.dupatmovie.ui.home.adapter.BannerAdapter
import com.dupat.dupatmovie.ui.home.viewholder.BannerViewHolder
import com.dupat.dupatmovie.ui.home.viewholder.NetViewHolder
import com.dupat.dupatmovie.ui.utils.toast
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.bannerview.utils.BannerUtils
import com.zhpan.indicator.enums.IndicatorSlideMode
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var bannerView: BannerViewPager<MovieModel,NetViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bannerView = findViewById(R.id.banner_view)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getMovies().observe(this, Observer {
            setupViewPager(it)
        })

        movieViewModel.getState().observe(this, Observer {
//            handleUIState(it)
        })
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.fetchAllMovie(1)
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
        }
    }
}