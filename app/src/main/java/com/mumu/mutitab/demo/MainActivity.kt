package com.mumu.mutitab.demo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jd.mobile.image.JDImageLoader
import com.mumu.mutitab.OnTabChangedListener
import com.mumu.mutitab.bottom.BottomNavigationLayout
import com.mumu.mutitab.bottom.IDisplayImage
import com.mumu.mutitab.bottom.ITabItem
import com.mumu.mutitab.bottom.SimpleNavigationAdapter

class MainActivity : AppCompatActivity() {

    private val mContentText: TextView by lazy { findViewById<TextView>(R.id.contentText) }
    private val mBottomLayout: BottomNavigationLayout by lazy {
        findViewById<BottomNavigationLayout>(
            R.id.bottomLayout
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
    }

    private fun initData() {
        val data = ArrayList<TabData>().apply {
            add(
                TabData(
                    "https://m.360buyimg.com/seckillcms/jfs/t1/194427/36/16568/9102/610a481cE3e5f45ec/0a874f171c573b68.png.webp",
                    "https://m.360buyimg.com/seckillcms/jfs/t1/189907/34/16617/10267/610a4821E73668436/84b1bc60383cedd1.png.webp"
                )
            )
            add(
                TabData(
                    "https://m.360buyimg.com/seckillcms/jfs/t1/166859/40/24674/30058/61cc1adaE7673232e/1f7862401a22cedd.png.webp",
                    "https://m.360buyimg.com/seckillcms/jfs/t1/126415/9/21134/16872/61cc1ae7Ea9ab80e7/927e500d72dd6aaf.png.webp"
                )
            )
            add(
                TabData(
                    "https://m.360buyimg.com/seckillcms/jfs/t1/218751/34/21050/85583/631b099aE494e677a/80d7b6a2de113511.gif",
                    "https://m.360buyimg.com/seckillcms/jfs/t1/146133/9/30162/85583/631b09beEe844b9d7/52d131299c316d84.gif",
                    "",
                    true,
                    DPIUtil.dp2px(this@MainActivity, 50f)
                )
            )
            add(
                TabData(
                    "https://m.360buyimg.com/seckillcms/jfs/t1/177517/30/17677/15184/610a4836E67c959ff/0dcd6f0f52c535ba.png.webp",
                    "https://m.360buyimg.com/seckillcms/jfs/t1/182058/37/17697/15111/610a483eE98dcb54e/ffefee158823b272.png.webp"
                )
            )
            add(
                TabData(
                    "https://m.360buyimg.com/seckillcms/jfs/t1/180025/3/28661/18041/631a9af3E169148bf/63c53c5b21e7b7f1.png.webp",
                    "https://m.360buyimg.com/seckillcms/jfs/t1/110887/40/29850/21585/631a9afbE1448d319/06a8965a4dac66ce.png.webp"
                )
            )
        }
        mBottomLayout.setTabChangedListener(object : OnTabChangedListener {
            override fun onChanged(position: Int, isFromClick: Boolean) {
                mContentText.text = position.toString()
            }

        })
        val display = object : IDisplayImage {
            override fun displayImage(imageUrl: String?, view: View) {
                JDImageLoader.display(imageUrl, view)
            }
        }
        val adapter = object : SimpleNavigationAdapter<TabData>(data, display) {
            override fun getSelectIndex(): Int {
                return 1
            }
        }
        mBottomLayout.setAdapter(adapter)
    }


    class TabData
    @JvmOverloads constructor(
        private val selectUrl: String,
        private val unSelectUrl: String,
        private val tabName: String? = null,
        private val specialTab: Boolean = false,
        private val specialHeight: Int = 0
    ) : ITabItem {
        override fun getUnSelectImageUrl(): String? {
            return unSelectUrl
        }

        override fun getSelectImageUrl(): String? {
            return selectUrl
        }

        override fun getName(): String? {
            return tabName
        }

        override fun isSpecial(): Boolean {
            return specialTab
        }

        override fun getSpecialTabHeight(): Int {
            return specialHeight
        }


    }


}