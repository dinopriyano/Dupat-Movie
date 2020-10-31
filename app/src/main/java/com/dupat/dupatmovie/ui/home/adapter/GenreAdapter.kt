package com.dupat.dupatmovie.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.data.network.model.GenreModel
import com.dupat.dupatmovie.ui.home.MovieFilterActivity
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreAdapter(private var list: MutableList<GenreModel>, private var ctx: Context) : RecyclerView.Adapter<GenreAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_genre,parent,false))
    }

    override fun getItemCount(): Int = list.size

    fun setList(newList: List<GenreModel>)
    {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: GenreAdapter.ViewHolder, position: Int) = holder.bind(list[position],ctx)
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(model: GenreModel,ctx: Context)
        {
            itemView.txtGenre.text = model.name
            itemView.setOnClickListener {
                var intent: Intent = Intent(ctx,MovieFilterActivity::class.java)
                intent.putExtra("genreID",model.id)
                intent.putExtra("genreName",model.name)
                ctx.startActivity(intent)
            }
        }
    }
}