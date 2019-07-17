package com.crecrew.crecre.ui.main.myPage

import android.os.Bundle
import com.crecrew.crecre.R
import com.crecrew.crecre.base.BaseFragment
import com.crecrew.crecre.databinding.FragMyPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : BaseFragment<FragMyPageBinding, MyPageViewModel>() {

    override val layoutResID: Int
        get() = R.layout.frag_my_page
    override val viewModel: MyPageViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.vm = viewModel
    }

    companion object {
        private val TAG = "MyPageFragment"
    }

}