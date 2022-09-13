package com.mumu.mutitab.bottom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.Checkable
import android.widget.LinearLayout
import com.mumu.mutitab.NavigationAdapter
import com.mumu.mutitab.OnTabChangedListener

/**
 * @author libinbin
 * @description:
 * @date :2022/9/7 3:15 下午
 */
class BottomNavigationLayout
@JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    /**
     * 是否允许重复回调--预留
     * true：已选中Tab点击会再次回调
     */
    private var mEnableRepeatCallBack = false

    /**
     * 底部导航View
     */
    private var mTabChangedListener: OnTabChangedListener? = null

    /**
     * 选中的位置索引
     */
    private var mCheckedIndex = -1

    private var mAdapter: NavigationAdapter? = null

    init {
        orientation = HORIZONTAL
    }

    fun setAdapter(adapter: NavigationAdapter) {
        if (mAdapter != null) {
            removeAllViews()
        }
        mCheckedIndex = -1
        mAdapter = adapter
        init()

    }

    fun setEnableRepeatCallBack(enableRepeatCallBack: Boolean) {
        this.mEnableRepeatCallBack = enableRepeatCallBack
    }

    fun setTabChangedListener(tabChangedListener: OnTabChangedListener) {
        this.mTabChangedListener = tabChangedListener
    }

    private fun init() {
        val adapter = mAdapter
        adapter ?: return
        setBackgroundColor(adapter.getBackgroundColor())
        val count = adapter.getCount()
        if (count <= 0) {
            return
        }
        if (adapter.isEqually()) {
            weightSum = count.toFloat()
        }
        val selectIndex = adapter.getSelectIndex()
        addItemView(count, adapter, selectIndex)
        onTabChanged(selectIndex, false)
    }

    private fun addItemView(count: Int, adapter: NavigationAdapter, selectIndex: Int) {
        for (position in 0 until count) {
            val itemView = adapter.instantiateItem(this, position)
            itemView.setOnClickListener {
                childTabChangedOnClick(position)
            }
            addView(itemView)
            setChecked(itemView, selectIndex == position)
        }
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT).apply {
            weight = 1f
        }
    }

    private fun childTabChangedOnClick(position: Int) {
        if (mCheckedIndex == position) {
            if (mEnableRepeatCallBack) {
                onTabChanged(position, true)
            }
            return
        }
        setChecked(getChildItemView(position), true)
        setChecked(getChildItemView(mCheckedIndex), false)
        onTabChanged(position, true)
    }

    private fun onTabChanged(position: Int, isFromClick: Boolean) {
        mCheckedIndex = position
        mTabChangedListener?.onChanged(position, isFromClick)
    }

    private fun setChecked(childItemView: View?, checked: Boolean) {
        childItemView?.let {
            mAdapter?.setChecked(it, checked)
            if (it is Checkable) {
                it.isChecked = checked
            }
        }
    }

    private fun getChildItemView(position: Int): View? {
        val childCount = childCount
        if (position in 0 until childCount) {
            return getChildAt(position)
        }
        return null
    }
}