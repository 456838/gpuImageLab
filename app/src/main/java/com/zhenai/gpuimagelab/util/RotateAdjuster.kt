package com.zhenai.gpuimagelab.util

import android.opengl.Matrix
import jp.co.cyberagent.android.gpuimage.GPUImageTransformFilter

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/10 上午11:16
 * ModifyTime: 上午11:16
 * Description:
 */
class RotateAdjuster : Adjuster<GPUImageTransformFilter>() {
    override fun adjust(percentage: Int) {
        val transform = FloatArray(16)
        Matrix.setRotateM(transform, 0, (360 * percentage / 100).toFloat(), 0f, 0f, 1.0f)
        filter.transform3D = transform
    }
}