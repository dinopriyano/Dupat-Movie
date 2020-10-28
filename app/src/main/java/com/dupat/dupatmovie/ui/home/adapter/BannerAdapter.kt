package com.dupat.dupatmovie.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.data.network.model.MovieModel
import com.dupat.dupatmovie.ui.home.viewholder.BannerViewHolder
import com.dupat.dupatmovie.ui.home.viewholder.NetViewHolder
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class BannerAdapter : BaseBannerAdapter<MovieModel, NetViewHolder>() {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner
    }

    override fun createViewHolder(
        parent: ViewGroup,
        itemView: View?,
        viewType: Int
    ): NetViewHolder {
        return NetViewHolder(itemView);
    }

    override fun onBind(holder: NetViewHolder?, data: MovieModel?, position: Int, pageSize: Int) {
        holder?.bindData(data!!, position, pageSize);
    }


}