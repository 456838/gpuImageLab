package com.zhenai.gpuimagelab.util

import jp.co.cyberagent.android.gpuimage.GPUImageLevelsFilter

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/10 上午11:28
 * ModifyTime: 上午11:28
 * Description:
 */
class LevelsMinMidAdjuster : Adjuster<GPUImageLevelsFilter>() {
    override fun adjust(percentage: Int) {
        filter.setMin(0.0f, range(percentage, 0.0f, 1.0f), 1.0f)
    }
}