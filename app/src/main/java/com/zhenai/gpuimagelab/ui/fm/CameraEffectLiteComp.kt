package com.zhenai.gpuimagelab.ui.fm

import android.hardware.Camera
import android.os.Bundle
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.util.RandomUtils
import com.salton123.util.RxUtils
import com.zhenai.gpuimagelab.R
import com.zhenai.gpuimagelab.bean.FilterType
import com.zhenai.gpuimagelab.business.CameraContract
import com.zhenai.gpuimagelab.business.CameraPresenter
import com.zhenai.gpuimagelab.util.camera.CameraHelper
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter
import jp.co.cyberagent.android.gpuimage.GPUImageGammaFilter
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter
import kotlinx.android.synthetic.main.comp_camera_effect_lite.*
import java.util.concurrent.TimeUnit

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/10 上午11:52
 * ModifyTime: 上午11:52
 * Description:
 */
class CameraEffectLiteComp : BaseSupportPresenterFragment<CameraPresenter>(), CameraContract.ICameraView {

    override fun setUpCamera(mCameraInstance: Camera?, orientation: Int, flipHorizontal: Boolean, b: Boolean) {
        mGPUImage.setUpCamera(mCameraInstance, orientation, flipHorizontal, b)
    }

    private lateinit var mGPUImage: GPUImage
    private lateinit var mCameraHelper: CameraHelper
    private var mEffects = mutableListOf(
        FilterType("contrast", GPUImageContrastFilter(2.0f), true),
        FilterType("gamma", GPUImageGammaFilter(2.0f), true),
        FilterType("invert", GPUImageColorInvertFilter()),
        FilterType("pixelation", GPUImagePixelationFilter()),
        FilterType("sepia", GPUImageSepiaFilter())
    )

    override fun getLayout(): Int = R.layout.comp_camera_effect_lite

    override fun initVariable(savedInstanceState: Bundle?) {
        mGPUImage = GPUImage(activity())
        mCameraHelper = CameraHelper(activity())
        mPresenter = CameraPresenter(mCameraHelper)
    }

    override fun initViewAndData() {
        mGPUImage.setGLSurfaceView(eglEffectShow)
        mDisp = Observable.interval(0, 2000, TimeUnit.MILLISECONDS)
            .compose(RxUtils.rxSchedulerHelper())
            .subscribe {
                var random = RandomUtils.getRandom(0, mEffects.size)
                mGPUImage.setFilter(mEffects[random].filter)
            }
    }

    private var mDisp: Disposable? = null

    override fun onResume() {
        super.onResume()
        mPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.onPause()
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