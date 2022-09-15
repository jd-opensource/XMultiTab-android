package com.mumu.mutitab

import android.view.View

/**
 * @author libinbin
 * @description:
 * @date :2022/9/13 2:43 下午
 */

interface IDisplayImage {

    /**
     * 加载图片
     */
    fun displayImage(imageUrl: String?, view: View)
}