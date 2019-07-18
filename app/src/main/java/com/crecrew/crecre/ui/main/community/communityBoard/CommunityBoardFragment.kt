package com.crecrew.crecre.ui.main.community.communityBoard

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.crecrew.crecre.BR
import com.crecrew.crecre.R
import com.crecrew.crecre.base.BaseFragment
import com.crecrew.crecre.base.BaseRecyclerViewAdapter
import com.crecrew.crecre.data.model.posts.Posts
import com.crecrew.crecre.databinding.FragCommunityBoardBinding
import com.crecrew.crecre.databinding.ItemPostsBinding
import com.crecrew.crecre.ui.main.community.board.BoardActivity
import com.crecrew.crecre.util.view.NonScrollLinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommunityBoardFragment : BaseFragment<FragCommunityBoardBinding, CommunityBoardViewModel>(),
    BaseRecyclerViewAdapter.OnItemClickListener {
    var boardIdx = 0

    override val layoutResID: Int
        get() = R.layout.frag_community_board
    override val viewModel: CommunityBoardViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.vm = viewModel

        setRecyclerViewAdapter()
        //getPosts
        viewModel.getPosts(boardIdx)

        navigator()
        moveToBoardActivity()
    }

    private fun setRecyclerViewAdapter() {
        //RecyclerView
        viewDataBinding.fragCommunityBoardRv.apply {
            adapter =
                object : BaseRecyclerViewAdapter<Posts, ItemPostsBinding>() {
                    override val layoutResID: Int
                        get() = R.layout.item_posts
                    override val bindingVariableId: Int
                        get() = BR.posts
                    override val listener: OnItemClickListener?
                        get() = this@CommunityBoardFragment
                }
            layoutManager = NonScrollLinearLayoutManager(activity!!)
        }
    }

    private fun moveToBoardActivity() {
        var title = ""

        if(boardIdx == 0) title = "최신글"
        else title = "인기글"

        viewDataBinding.fragCommunityBoardLlMore.setOnClickListener {
            viewModel.navigate(
                BoardActivity::class,
                boardIdx,
                title
            )
        }
    }

    private fun navigator() {
        viewModel.activityToStart.observe(this, Observer { value ->
            val intent = Intent(activity, value.first.java)
            value.second?.let {
                intent.putExtras(it)
            }
            startActivity(intent)
        })
    }

    override fun onItemClicked(item: Any?) {

    }

    companion object {
        private val TAG = "CommunityFragment"

        fun newInstance(boardIdx: Int): CommunityBoardFragment {
            val frg = CommunityBoardFragment()
            frg.boardIdx = boardIdx
            return frg
        }
    }

}