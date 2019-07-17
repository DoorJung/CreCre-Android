package com.crecrew.crecre.util.dataBinding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crecrew.crecre.base.BaseRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    (this.adapter as? BaseRecyclerViewAdapter<Any, *>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
}

@BindingAdapter("noDataDirective")
fun setNoDataDirective(view: TextView, data: List<Any>?) {
    data?.run {
        if (size == 0) view.visibility = View.VISIBLE
        else view.visibility = View.GONE
    }
}

@BindingAdapter("glideImg")
fun setGlideImg(view: ImageView, imgUrl: String?) {
    Glide.with(view.context)
        .load(imgUrl)
        //.error(R.drawable.img_poster) //에러시 나올 이미지 적용
        .into(view)
}
