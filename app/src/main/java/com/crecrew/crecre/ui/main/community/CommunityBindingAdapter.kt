package com.crecrew.crecre.ui.main.community

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.crecrew.crecre.R
import com.crecrew.crecre.data.model.board.Board
import com.crecrew.crecre.data.model.board.BoardRepository
import com.crecrew.crecre.data.model.posts.Posts
import com.crecrew.crecre.ui.main.community.communityBoard.CommunityBoardViewModel

@BindingAdapter("postsDetailText")
fun setPostsDetailText(view: TextView, posts: Posts?) {
    posts?.let {
        view.text = "추천 ${it.like_cnt} | 댓글 ${it.reply_cnt} | ${it.create_time}"
    }
}

@BindingAdapter("boardLikeImg")
fun setBoardLikeImg(view: ImageView, isLove: Int?) {
    isLove?.let {
        if(it == 1) view.setImageResource(R.drawable.icn_look_on) else view.setImageResource(R.drawable.icn_look_off)
    }
}

@BindingAdapter("postsHotImg")
fun setPostsHotImg(view: ImageView, hotImage: Int?) {
    hotImage?.let {
        if(it == 1) view.visibility = View.VISIBLE else view.visibility = View.GONE
    }
}

@BindingAdapter("likeBoard")
fun setLikeBoard(view: ImageView, hotImage: Int?) {
    view.setOnClickListener {
        hotImage?.let {
            if(it == 1) view.setImageResource(R.drawable.icn_look_off) else view.setImageResource(R.drawable.icn_look_on)
        }
    }
}