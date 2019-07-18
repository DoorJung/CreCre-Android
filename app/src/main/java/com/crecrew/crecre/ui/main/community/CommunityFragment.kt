package com.crecrew.crecre.ui.main.community

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.crecrew.crecre.BR
import com.crecrew.crecre.R
import com.crecrew.crecre.base.BaseFragment
import com.crecrew.crecre.base.BasePagerAdapter
import com.crecrew.crecre.base.BaseRecyclerViewAdapter
import com.crecrew.crecre.data.model.board.Board
import com.crecrew.crecre.databinding.FragCommunityBinding
import com.crecrew.crecre.databinding.ItemBoardBinding
import com.crecrew.crecre.ui.main.community.board.BoardActivity
import com.crecrew.crecre.ui.main.community.communityBoard.CommunityBoardFragment
import com.crecrew.crecre.util.view.NonScrollLinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommunityFragment : BaseFragment<FragCommunityBinding, CommunityViewModel>(),
    CommunityBoardRecyclerViewAdapter.OnItemClickListener {

    override val layoutResID: Int
        get() = R.layout.frag_community
    override val viewModel: CommunityViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.vm = viewModel

        setNewAndHotBoards()
        setRecyclerView()
        navigator()
    }

    private fun setNewAndHotBoards() {
        viewDataBinding.fragCommunityVp.adapter = BasePagerAdapter(childFragmentManager).apply {
            addFragment(CommunityBoardFragment.newInstance(0))
            addFragment(CommunityBoardFragment.newInstance(1))
        }

        viewDataBinding.fragCommunityTl.run {
            setupWithViewPager(viewDataBinding.fragCommunityVp)

            getTabAt(0)!!.text = "최신글"
            getTabAt(1)!!.text = "인기글"
        }
    }

    private fun setRecyclerView() {
        viewDataBinding.fragCommunityRvBoards.apply {
            viewModel.boards.observe(this@CommunityFragment, Observer { value ->
                if(adapter != null) {
                    (viewDataBinding.fragCommunityRvBoards.adapter as CommunityBoardRecyclerViewAdapter).apply {
                        replaceAll(value)
                        notifyItemRangeChanged(0, value.size - 1)
                    }
                } else {
                    adapter =
                        CommunityBoardRecyclerViewAdapter(value)
                            .apply { setOnItemClickListener(this@CommunityFragment) }

                    layoutManager = NonScrollLinearLayoutManager(activity!!)
                    //(this.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
                }

            })
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

    override fun onItemClicked(item: Board) {
        viewModel.navigate(BoardActivity::class, (item as Board).idx, item.name)
    }

    override fun onLikeClicked(item: Board) {
        viewModel.likeBoard(item.is_love, item.idx)
    }

    companion object {
        private val TAG = "CommunityFragment"
    }

}