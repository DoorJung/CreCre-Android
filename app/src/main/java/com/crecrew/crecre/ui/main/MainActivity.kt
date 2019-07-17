package com.crecrew.crecre.ui.main

import android.os.Bundle
import com.crecrew.crecre.R
import com.crecrew.crecre.base.BaseActivity
import com.crecrew.crecre.databinding.ActMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActMainBinding, MainViewModel>() {

    override val layoutResID: Int
        get() = R.layout.act_main
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm = viewModel
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
