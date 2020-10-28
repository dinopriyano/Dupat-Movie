package com.dupat.dupatmovie.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.data.network.model.MovieModel
import com.dupat.dupatmovie.ui.home.viewholder.BannerViewHolder
import com.zhpan.bannerview.BaseBannerAdapter

class BannerAdapter : BaseBannerAdapter<MovieModel, BannerViewHolder>() {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner
    }

    override fun createViewHolder(
        parent: ViewGroup,
        itemView: View?,
        viewType: Int
    ): BannerViewHolder {
        return BannerViewHolder(itemView);
    }

    override fun onBind(holder: BannerViewHolder?, data: MovieModel?, position: Int, pageSize: Int) {
        holder?.bindData(data!!, position, pageSize);
    }


}