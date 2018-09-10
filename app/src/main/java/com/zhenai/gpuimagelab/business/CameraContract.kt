package com.zhenai.gpuimagelab.business

import android.hardware.Camera
import com.salton123.mvp.presenter.BasePresenter
import com.salton123.mvp.view.BaseView

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/10 下午3:11
 * ModifyTime: 下午3:11
 * Description:
 */

interface CameraContract {
    interface ICameraPresenter : BasePresenter<ICameraView> {

    }

    interface ICameraView : BaseView {
        fun setUpCamera(mCameraInstance: Camera?, orientation: Int, flipHorizontal: Boolean, b: Boolean)
    }
}