package com.crecrew.crecre.ui.main.community

import android.os.Bundle
import com.crecrew.crecre.R
import com.crecrew.crecre.base.BaseFragment
import com.crecrew.crecre.databinding.FragCommunityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommunityFragment : BaseFragment<FragCommunityBinding, CommunityViewModel>() {

    override val layoutResID: Int
        get() = R.layout.frag_community
    override val viewModel: CommunityViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.vm = viewModel
    }

    companion object {
        private val TAG = "CommunityFragment"
    }

}