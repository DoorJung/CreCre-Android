package com.crecrew.crecre.ui.main.community.communityBoard

import android.os.Bundle
import com.crecrew.crecre.BR
import com.crecrew.crecre.R
import com.crecrew.crecre.base.BaseFragment
import com.crecrew.crecre.base.BaseRecyclerViewAdapter
import com.crecrew.crecre.data.model.posts.Posts
import com.crecrew.crecre.databinding.FragCommunityBoardBinding
import com.crecrew.crecre.databinding.ItemPostsBinding
import com.crecrew.crecre.util.view.NonScrollLinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommunityBoardFragment : BaseFragment<FragCommunityBoardBinding, CommunityBoardViewModel>(),
    BaseRecyclerViewAdapter.OnItemClickListener {
    var isNewPosts = true

    override val layoutResID: Int
        get() = R.layout.frag_community_board
    override val viewModel: CommunityBoardViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.vm = viewModel
        setRecyclerViewAdapter()
        viewModel.getPosts(isNewPosts)
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

    override fun onItemClicked(item: Any?) {

    }

    companion object {
        private val TAG = "CommunityFragment"

        fun newInstance(isNewPosts: Boolean): CommunityBoardFragment {
            val frg = CommunityBoardFragment()
            frg.isNewPosts = isNewPosts
            return frg
        }
    }

}