package com.mumu.mutitab.demo

import android.content.Context

/**
 * @author libinbin
 * @description:
 * @date :2022/9/13 6:16 下午
 */

object DPIUtil {

    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}