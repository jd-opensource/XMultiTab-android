package com.mumu.mutitab.bottom

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.mumu.mutitab.NavigationAdapter

/**
 * @author libinbin
 * @description:
 * @date :2022/9/13 3:46 下午
 */

open class SimpleNavigationAdapter<T : ITabItem>(
    private val data: List<T>?,
    private val displayImage: IDisplayImage
) :
    NavigationAdapter() {

    final override fun getCount(): Int {
        return data?.size ?: 0
    }

    override fun getSelectIndex(): Int {
        return 0
    }

    override fun createItemView(viewGroup: ViewGroup, position: Int): View {
        val itemData = data?.get(position)
        val simpleItemView = SimpleItemView(viewGroup.context)
        simpleItemView.displayImage = displayImage
        itemData?.let {
            if (it.isSpecial() && it.getSpecialTabHeight() > 0) {
                val width = if (isEqually()) 0 else ViewGroup.LayoutParams.WRAP_CONTENT
                simpleItemView.layoutParams =
                    LinearLayout.LayoutParams(width, it.getSpecialTabHeight()).apply {
                        weight = 1f
                    }
            }
            simpleItemView.setData(it)
        }
        return simpleItemView
    }


}