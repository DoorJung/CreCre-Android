package com.crecrew.crecre.ui.main.vote

import android.os.Bundle
import com.crecrew.crecre.R
import com.crecrew.crecre.base.BaseFragment
import com.crecrew.crecre.databinding.FragVoteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VoteFragment : BaseFragment<FragVoteBinding, VoteViewModel>() {

    override val layoutResID: Int
        get() = R.layout.frag_vote
    override val viewModel: VoteViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.vm = viewModel
    }

    companion object {
        private val TAG = "VoteFragment"
    }

}