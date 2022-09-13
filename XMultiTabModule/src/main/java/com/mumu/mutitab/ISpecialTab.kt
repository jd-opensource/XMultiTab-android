package com.mumu.mutitab

/**
 * @author libinbin
 * @description: 是否是异型Tab，异性Tab默认在原高度的基础上增加10dp
 * 1、需要设置android:clipChildren="false"才能生效
 * 2、也可以自定义layout修改Tab的高度
 * @date :2022/9/13 2:45 下午
 */

interface ISpecialTab {

    /**
     * 是否是异形Tab
     */
    fun isSpecial(): Boolean

    /**
     *  是否是异形Tab高度
     */
    fun getSpecialTabHeight(): Int

}