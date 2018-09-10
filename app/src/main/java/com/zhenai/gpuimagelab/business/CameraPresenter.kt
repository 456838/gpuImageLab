package com.zhenai.gpuimagelab.business

import android.app.Activity
import android.hardware.Camera
import android.support.v4.app.Fragment
import com.salton123.mvp.presenter.RxPresenter
import com.zhenai.gpuimagelab.util.camera.CameraHelper

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/10 下午3:12
 * ModifyTime: 下午3:12
 * Description:
 */
class CameraPresenter(var mCameraHelper: CameraHelper) : RxPresenter<CameraContract.ICameraView>(),
    CameraContract.ICameraPresenter {

    public var mCurrentCameraId = 0
    private var mCameraInstance: Camera? = null

    fun onResume() {
        setUpCamera(mCurrentCameraId)
    }

    fun onPause() {
        releaseCamera()
    }

    fun switchCamera() {
        releaseCamera()
        mCurrentCameraId = (mCurrentCameraId + 1) % mCameraHelper.numberOfCameras
        setUpCamera(mCurrentCameraId)
    }

    private fun setUpCamera(id: Int) {
        mCameraInstance = getCameraInstance(id)
        val parameters = mCameraInstance!!.parameters
        // TODO adjust by getting supportedPreviewSizes and then choosing
        // the best one for screen size (best fill screen)
        if (parameters.supportedFocusModes.contains(
                Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
        }
        mCameraInstance!!.parameters = parameters

        val orientation = mCameraHelper.getCameraDisplayOrientation(
            activity()!!, mCurrentCameraId)
        val cameraInfo = CameraHelper.CameraInfo2()
        mCameraHelper.getCameraInfo(mCurrentCameraId, cameraInfo)
        val flipHorizontal = cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT
        mView.setUpCamera(mCameraInstance, orientation, flipHorizontal, false)
    }

    /**
     * A safe way to get an instance of the Camera object.
     */
    private fun getCameraInstance(id: Int): Camera? {
        var c: Camera? = null
        try {
            c = mCameraHelper.openCamera(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return c
    }

    private fun releaseCamera() {
        mCameraInstance!!.setPreviewCallback(null)
        mCameraInstance!!.release()
        mCameraInstance = null
    }


    fun activity(): Activity? {
        if (mView is Activity) {
            return mView as Activity
        } else if (mView is Fragment) {
            return (mView as Fragment).activity
        } else {
            return null
        }
    }

}
