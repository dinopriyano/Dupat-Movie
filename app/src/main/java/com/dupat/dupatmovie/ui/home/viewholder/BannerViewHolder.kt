package com.dupat.dupatmovie.ui.home.viewholder

import android.view.View
import com.dupat.dupatmovie.data.network.model.MovieModel
import com.zhpan.bannerview.BaseViewHolder
import kotlinx.android.synthetic.main.item_banner.view.*


class BannerViewHolder(itemView: View): BaseViewHolder<MovieModel>(itemView) {

    private var mOnSubViewClickListener: OnSubViewClickListener? = null

    override fun bindData(data: MovieModel?, position: Int, pageSize: Int) {
        itemView.containerBanner.setOnClickListener {
            if (null != mOnSubViewClickListener)
            {
                mOnSubViewClickListener?.onViewClick(it, getAdapterPosition());
            }

        }
    }

    fun setOnSubViewClickListener(subViewClickListener: OnSubViewClickListener) {
        mOnSubViewClickListener = subViewClickListener
    }

    interface OnSubViewClickListener {
        fun onViewClick(view: View, position: Int)
    }
}