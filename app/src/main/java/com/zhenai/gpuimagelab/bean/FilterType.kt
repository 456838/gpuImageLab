package com.zhenai.gpuimagelab.bean

import jp.co.cyberagent.android.gpuimage.GPUImageFilter

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/4 下午3:55
 * ModifyTime: 下午3:55
 * Description:
 */
data class FilterType(var name: String, var filter: GPUImageFilter, var hasProgress: Boolean = false)