package com.dupat.dupatmovie.ui.home.viewholder

import android.R
import android.view.View
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.dupat.dupatmovie.data.network.model.MovieModel
import com.dupat.dupatmovie.ui.utils.Constant
import com.zhpan.bannerview.BaseViewHolder
import com.zhpan.bannerview.utils.BannerUtils
import kotlinx.android.synthetic.main.item_banner.view.*


class NetViewHolder(@NonNull itemView: View?) : BaseViewHolder<MovieModel>(itemView!!) {
    override fun bindData(data: MovieModel, position: Int, pageSize: Int) {
        Glide.with(itemView.banner_image).load(Constant.baseMovieImage+data.backdrop_path).into(itemView.banner_image)
        itemView.txtTittle.text = data.original_title
    }
}