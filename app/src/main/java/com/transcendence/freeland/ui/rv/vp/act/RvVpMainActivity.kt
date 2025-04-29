package com.transcendence.freeland.ui.rv.vp.act

import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.LayoutInflater.from
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.transcendence.freeland.R
import com.transcendence.freeland.ui.rv.vp.adapter.ViewPagerAdapter
import com.transcendence.freeland.ui.rv.vp.fragment.RvVpFragment
import com.transcendence.freeland.ui.rv.vp.ui.CoordinatorScrollview
import com.transcendence.freeland.ui.rv.vp.utils.StatusBarUtil


class RvVpMainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    lateinit var coordinatorScrollView : CoordinatorScrollview
    lateinit var titleBar : LinearLayout
    lateinit var topLayout : LinearLayout

    //屏幕宽
    var screenWidth = 0

    //屏幕高
    var screenHeight = 0

    //tabLayout的文本和图片
    private val tabLayoutData = arrayOf("常用药品", "夜间送药", "隐形眼镜", "成人用品", "医疗器械", "全部商家")
    private val tabIconData = arrayOf(
        R.mipmap.activity_ui_rv_vp_tab_icon,
        R.mipmap.activity_ui_rv_vp_tab_icon,
        R.mipmap.activity_ui_rv_vp_tab_icon,
        R.mipmap.activity_ui_rv_vp_tab_icon,
        R.mipmap.activity_ui_rv_vp_tab_icon,
        R.mipmap.activity_ui_rv_vp_tab_icon
    )
    private var fragmentData = mutableListOf<Fragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_rv_vp_main)

        initView()
        initData()
    }

    private fun initView() {
        viewPager = findViewById(R.id.vp)
        tabLayout = findViewById(R.id.tab_layout)
        coordinatorScrollView = findViewById(R.id.sv_coordinator)
        titleBar = findViewById(R.id.ll_top_bar)
        topLayout = findViewById(R.id.ll_top)
        //获取屏幕宽高
        val resources: Resources = this.resources
        val dm: DisplayMetrics = resources.displayMetrics
        screenWidth = dm.widthPixels
        screenHeight = dm.heightPixels

        //状态栏沉浸
        StatusBarUtil.immersive(this)

        //titleBar填充
        StatusBarUtil.setPaddingSmart(this, titleBar)

        //状态栏字体颜色设置为黑色
        StatusBarUtil.darkMode(this)

        //动态设置ViewPager高度
        coordinatorScrollView.post {
            val layoutParams = viewPager.layoutParams
            layoutParams.width = screenWidth
            layoutParams.height = coordinatorScrollView.height - tabLayout.height
            viewPager.layoutParams = layoutParams
        }

    }

    private fun initData() {

        //我模拟在头部动态添加三个布局，就用图片代替了，要设置的图片高度都是我提前算好的，根据屏幕的比例来计算的
        val titleView1 = getTitleView(screenWidth * 0.42F, R.mipmap.activity_ui_rv_vp_title1)
        val titleView2 = getTitleView(screenWidth * 0.262F, R.mipmap.activity_ui_rv_vp_title2)
        topLayout.addView(titleView1)
        topLayout.addView(titleView2)

        //设置最大滑动距离
        topLayout.post {
            coordinatorScrollView.setMaxScrollY(topLayout.height)
        }

        //用于请求网络后动态添加子布局
        Handler().postDelayed({
            val titleView3 = getTitleView(screenWidth * 0.589F, R.mipmap.activity_ui_rv_vp_title3)
            topLayout.addView(titleView3)

            //再次设置最大滑动距离
            topLayout.post {
                coordinatorScrollView.setMaxScrollY(topLayout.height)
            }

        }, 200)

        //添加TabLayout
        for (i in tabLayoutData.indices) {
            tabLayout.addTab(tabLayout.newTab())
            tabLayout.getTabAt(i)!!.setText(tabLayoutData[i]).setIcon(tabIconData[i])

            //添加Fragment
            fragmentData.add(RvVpFragment.newInstance(tabLayoutData[i]))
        }

        //Fragment ViewPager
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, fragmentData)

        //TabLayout关联ViewPager
        tabLayout.setupWithViewPager(viewPager)

        //设置TabLayout数据
        for (i in tabLayoutData.indices) {
            tabLayout.getTabAt(i)!!.setText(tabLayoutData[i]).setIcon(tabIconData[i])
        }
    }

    /**
     * 获取一个title布局
     * 我这里就用三张图片模拟的
     *
     * @height 要设置的图片高度
     */
    private fun getTitleView(height: Float, res: Int): View {
        val inflate = from(this).inflate(R.layout.activity_ui_rv_vp_title_layout, null, false)
        var titleImage = inflate.findViewById<ImageView>(R.id.titleImage)
        val layoutParams = titleImage.layoutParams
        layoutParams.width = screenWidth
        layoutParams.height = height.toInt()
        titleImage.setImageResource(res)
        return inflate
    }
}