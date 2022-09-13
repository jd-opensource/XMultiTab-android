package com.mumu.mutitab.demo

import android.app.Application
import com.jingdong.JdImageToolKit

/**
 * @author libinbin
 * @description:
 * @date :2022/9/7 6:22 下午
 */

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        JdImageToolKit.initialize(this);
    }
}