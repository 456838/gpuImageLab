package com.zhenai.gpuimagelab.ui.fm

import android.graphics.BitmapFactory
import android.os.Bundle
import com.salton123.base.BaseSupportFragment
import com.salton123.util.RandomUtils
import com.salton123.util.RxUtils
import com.zhenai.gpuimagelab.R
import com.zhenai.gpuimagelab.bean.FilterType
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter
import jp.co.cyberagent.android.gpuimage.GPUImageGammaFilter
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter
import kotlinx.android.synthetic.main.comp_photo_effect_lite.*
import java.util.concurrent.TimeUnit

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/10 上午11:52
 * ModifyTime: 上午11:52
 * Description:
 */
class PhotoEffectLiteComp : BaseSupportFragment() {
    private var mEffects = mutableListOf(
        FilterType("contrast", GPUImageContrastFilter(2.0f), true),
        FilterType("gamma", GPUImageGammaFilter(2.0f), true),
        FilterType("invert", GPUImageColorInvertFilter()),
        FilterType("pixelation", GPUImagePixelationFilter()),
        FilterType("sepia", GPUImageSepiaFilter())
    )

    override fun getLayout(): Int = R.layout.comp_photo_effect_lite

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    private var mDisp: Disposable? = null
    override fun initViewAndData() {
        ivEffectShow.setImage(BitmapFactory.decodeResource(resources, R.mipmap.bg_hudieguang))
        mDisp = Observable.interval(0, 2000, TimeUnit.MILLISECONDS)
            .compose(RxUtils.rxSchedulerHelper())
            .subscribe {
                var random = RandomUtils.getRandom(0, mEffects.size)
                ivEffectShow.filter = mEffects[random].filter
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisp?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}