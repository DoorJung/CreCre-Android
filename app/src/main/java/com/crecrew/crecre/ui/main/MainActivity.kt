package com.crecrew.crecre.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.crecrew.crecre.R
import com.crecrew.crecre.base.BaseActivity
import com.crecrew.crecre.base.BasePagerAdapter
import com.crecrew.crecre.data.local.pref.PreferenceManager
import com.crecrew.crecre.databinding.ActMainBinding
import com.crecrew.crecre.ui.main.community.CommunityFragment
import com.crecrew.crecre.ui.main.home.HomeFragment
import com.crecrew.crecre.ui.main.myPage.MyPageFragment
import com.crecrew.crecre.ui.main.rank.RankFragment
import com.crecrew.crecre.ui.main.vote.VoteFragment
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActMainBinding, MainViewModel>() {

    override val layoutResID: Int
        get() = R.layout.act_main
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferenceManager(this).putPreference("TOKEN", getString(R.string.test_token))
        //dataBinding
        viewDataBinding.vm = viewModel
        //ui
        setToolbar()
        setViewPager()
        setTabLayout()
        //util
        navigator()
    }

    //region viewSetting
    private fun setToolbar() {
        //Toolbar
        setSupportActionBar(viewDataBinding.actMainTb)
        supportActionBar!!.title = ""
        viewDataBinding.actMainTb.run {
            layoutParams.height += getStatusBarHeight()
            setPadding(0, getStatusBarHeight(), 0, 0)
        }
    }

    private fun setViewPager() {
        //ViewPager
        viewDataBinding.actMainVp.run {
            adapter = BasePagerAdapter(supportFragmentManager).apply {
                addFragment(HomeFragment())
                addFragment(RankFragment())
                addFragment(VoteFragment())
                addFragment(CommunityFragment())
                addFragment(MyPageFragment())
            }
            offscreenPageLimit = 4
            //Refresh menu
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    invalidateOptionsMenu()
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
        }
    }

    private fun setTabLayout() {
        //TabLayout
        viewDataBinding.actMainTl.run {
            //SetupWithViewPager
            setupWithViewPager(viewDataBinding.actMainVp)
            //Set tab text and icon
            getTabAt(0)!!.run { text = "홈"; setIcon(R.drawable.selector_home) }
            getTabAt(1)!!.run { text = "랭크"; setIcon(R.drawable.selector_rank) }
            getTabAt(2)!!.run { text = "투표"; setIcon(R.drawable.selector_vote) }
            getTabAt(3)!!.run { text = "게시판"; setIcon(R.drawable.selector_community) }
            getTabAt(4)!!.run { text = "MY"; setIcon(R.drawable.selector_my_page) }
            //TabSelectedListener
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    when (tab.position) {
                        0 -> {
                            viewDataBinding.actMainTb.visibility = View.GONE
                        }
                        1 -> {
                            viewDataBinding.actMainTb.visibility = View.VISIBLE
                            viewDataBinding.actMainTvTitle.run {
                                text = "랭킹"; setTextColor(resources.getColor(R.color.white))
                            }
                        }
                        2 -> {
                            viewDataBinding.actMainTb.visibility = View.VISIBLE
                            viewDataBinding.actMainTvTitle.run {
                                text = "투표"; setTextColor(resources.getColor(R.color.white))
                            }
                        }
                        3 -> {
                            viewDataBinding.actMainTb.visibility = View.VISIBLE
                            viewDataBinding.actMainTvTitle.run {
                                text = "커뮤니티"; setTextColor(resources.getColor(R.color.white))
                            }
                        }
                        4 -> {
                            viewDataBinding.actMainTb.visibility = View.VISIBLE
                            viewDataBinding.actMainTvTitle.run {
                                text = "MY"; setTextColor(resources.getColor(R.color.white))
                            }
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
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

    private fun navigator() {
        viewModel.activityToStart.observe(this, Observer { value ->
            val intent = Intent(this@MainActivity, value.first.java)
            value.second?.let {
                intent.putExtras(it)
            }
            startActivity(intent)
        })
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
