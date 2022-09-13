package com.mumu.mutitab

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * @author libinbin
 * @description:
 * @date :2022/9/13 3:10 下午
 */

abstract class NavigationAdapter {


    /**
     * 导航可见View数量
     */
    abstract fun getCount(): Int

    /**
     * 默认选中位置
     */
    abstract fun getSelectIndex(): Int

    /**
     * 实例化单个TabView
     *
     * @param viewGroup The containing View .
     * @param position The  position to be instantiated.
     * @return View
     *
     */
    fun instantiateItem(viewGroup: ViewGroup, position: Int): View {
        val itemView = createItemView(viewGroup, position)
        if (isEqually()) {
            resetLayoutParams(itemView)
        }
        return itemView
    }

    /**
     * 实例化单个TabView
     *
     * @param viewGroup The containing View .
     * @param position The  position to be instantiated.
     * @return View
     *
     */
    abstract fun createItemView(viewGroup: ViewGroup, position: Int): View

    /**
     * 导航条背景色
     */
    open fun getBackgroundColor(): Int {
        return Color.parseColor("#ffffff")
    }

    /**
     * 重置Layout，宽度等分，高度充满
     */
    open fun resetLayoutParams(itemView: View) {
        val layoutParams = itemView.layoutParams
        if (layoutParams is LinearLayout.LayoutParams) {
            layoutParams.width = 0
            layoutParams.weight = 1f
        }
    }

    /**
     * 是的等分，默认宽度等分，高度充满
     */
    open fun isEqually(): Boolean {
        return true
    }

    /**
     * 选中状态改变调用
     * @param checked 选中true，反选false
     */
    open fun setChecked(itemView: View, checked: Boolean) {

    }
}