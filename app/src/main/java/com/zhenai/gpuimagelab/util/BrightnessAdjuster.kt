package com.zhenai.gpuimagelab.util

import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/4 下午4:41
 * ModifyTime: 下午4:41
 * Description:
 */
class BrightnessAdjuster : Adjuster<GPUImageBrightnessFilter>() {
    override fun adjust(percentage: Int) {
        var range = range(percentage, -1.0f, 1.0f)
        println("range:$range")
        filter.setBrightness(range)
    }
}