package com.mumu.mutitab

/**
 * @author libinbin
 * @description: Tab切换监听
 * @date :2022/4/1 11:42 上午
 *
 */

interface OnTabChangedListener {

    /**
     * Tab切换
     * @param isFromClick 是否是点击切换（首次进入选中，则为false）
     */
    fun onChanged(position: Int, isFromClick: Boolean)

}