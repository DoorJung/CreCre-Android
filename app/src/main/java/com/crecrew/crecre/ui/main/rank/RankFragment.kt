package com.crecrew.crecre.ui.main.rank

import android.os.Bundle
import com.crecrew.crecre.R
import com.crecrew.crecre.base.BaseFragment
import com.crecrew.crecre.databinding.FragRankBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RankFragment : BaseFragment<FragRankBinding, RankViewModel>() {

    override val layoutResID: Int
        get() = R.layout.frag_rank
    override val viewModel: RankViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.vm = viewModel
    }

    companion object {
        private val TAG = "RankFragment"
    }

}