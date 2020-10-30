package com.dupat.dupatmovie.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.data.network.model.MovieModel
import com.dupat.dupatmovie.databinding.ActivityHomeBinding.bind
import com.dupat.dupatmovie.databinding.ActivityLoginBinding.bind
import com.dupat.dupatmovie.databinding.ActivityRegisterBinding.bind
import com.dupat.dupatmovie.ui.utils.Constant
import kotlinx.android.synthetic.main.item_movie_home.view.*

class MovieHomeAdapter(private var list: MutableList<MovieModel>, private var ctx: Context) : RecyclerView.Adapter<MovieHomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHomeAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_movie_home,parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieHomeAdapter.ViewHolder, position: Int) = holder.bind(list[position],ctx)

    fun addList(newList: List<MovieModel>)
    {
        list.addAll(newList)
        notifyItemRangeInserted(list.size-newList.size,newList.size)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(model: MovieModel,context: Context){
            Glide.with(itemView.icMovie).load(Constant.baseMovieImage+model.poster_path).into(itemView.icMovie)
            itemView.txtTittle.text = model.original_title
            itemView.rating_bar.rating = model.vote_average!!.toFloat()/2
        }
    }
}