package com.zhenai.gpuimagelab.widget

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition
import jp.co.cyberagent.android.gpuimage.GPUImageView

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/4 上午10:34
 * ModifyTime: 上午10:34
 * Description:
 */
class GlideGpuImageView(
    context: Context,
    var attrs: AttributeSet? = null
) : GPUImageView(context, attrs) {
    var target: ViewTarget<GlideGpuImageView, Bitmap>
    

    init {
        target = object : ViewTarget<GlideGpuImageView, Bitmap>(this) {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                getView().setImage(resource)
            }
        }
    }


}