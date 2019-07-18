package com.crecrew.crecre.ui.main.community.board

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.crecrew.crecre.BR
import com.crecrew.crecre.R
import com.crecrew.crecre.base.BaseActivity
import com.crecrew.crecre.base.BaseRecyclerViewAdapter
import com.crecrew.crecre.data.model.posts.Posts
import com.crecrew.crecre.databinding.ActBoardBinding
import com.crecrew.crecre.databinding.ItemPostsBinding
import com.crecrew.crecre.util.view.NonScrollLinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class BoardActivity : BaseActivity<ActBoardBinding, BoardViewModel>(), BaseRecyclerViewAdapter.OnItemClickListener {

    override val layoutResID: Int
        get() = R.layout.act_board
    override val viewModel: BoardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //dataBinding
        viewDataBinding.vm = viewModel
        //ui
        setToolbar()
        setRecyclerView()
        setContentsByIntent()
        //util
        navigator()
    }

    //region viewSetting
    private fun setToolbar() {
        //Toolbar
        setSupportActionBar(viewDataBinding.actBoardTb)
        supportActionBar!!.run {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.btn_back_white)
        }
        viewDataBinding.actBoardTb.run {
            layoutParams.height += getStatusBarHeight()
            setPadding(0, getStatusBarHeight(), 0, 0)
        }
    }

    private fun setRecyclerView() {
        viewDataBinding.actBoardRv.apply {
            adapter =
                object : BaseRecyclerViewAdapter<Posts, ItemPostsBinding>() {
                    override val layoutResID: Int
                        get() = R.layout.item_posts
                    override val bindingVariableId: Int
                        get() = BR.posts
                    override val listener: OnItemClickListener?
                        get() = this@BoardActivity
                }
        }
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0)
            result = resources.getDimensionPixelSize(resourceId)

        return result
    }
    //endregion

    private fun setContentsByIntent() {
        val boardIdx = intent.getIntExtra("boardIdx", 0)
        val title = intent.getStringExtra("title")

        viewModel.getPosts(boardIdx)
        viewDataBinding.actBoardTvTitle.text = title
    }

    private fun navigator() {
        viewModel.activityToStart.observe(this, Observer { value ->
            val intent = Intent(this@BoardActivity, value.first.java)
            value.second?.let {
                intent.putExtras(it)
            }
            startActivity(intent)
        })
    }

    override fun onItemClicked(item: Any?) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
