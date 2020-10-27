package com.dupat.dupatmovie.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.ui.utils.toast

class HomeActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getMovies().observe(this, Observer {
            toast("Data: "+ it[0].original_title)
        })

        movieViewModel.getState().observe(this, Observer {
            handleUIState(it)
        })
    }

    override fun onResume() {
        super.onResume()
        toast("resume")
        movieViewModel.fetchAllMovie(1)
    }

    private fun handleUIState(it : MovieState){
        when(it){
            is MovieState.Error -> {
                toast(it.err!!)
            }
        }
    }
}