package com.mumu.mutitab

import com.mumu.mutitab.ISpecialTab

/**
 * @author libinbin
 * @description:底部导航数据
 * @date :2022/3/31 3:10 下午
 *
 * 接口--给通用底部导航赋值
 */

interface ITabItem : ISpecialTab {


    /**
     * 非选中图片
     */
    fun getUnSelectImageUrl(): String?

    /**
     * 选中图片
     */
    fun getSelectImageUrl(): String?

    /***
     * Tab 名称
     */
    fun getName(): String?
}