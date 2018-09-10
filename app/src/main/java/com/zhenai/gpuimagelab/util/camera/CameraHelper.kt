/*
 * Copyright (C) 2012 CyberAgent
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhenai.gpuimagelab.util.camera

import android.app.Activity
import android.content.Context
import android.hardware.Camera
import android.hardware.Camera.CameraInfo
import android.view.Surface

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.GINGERBREAD

class CameraHelper(context: Context) {
    private val mImpl: CameraHelperImpl = if (SDK_INT >= GINGERBREAD) {
        CameraHelperGB()
    } else {
        CameraHelperBase(context)
    }

    val numberOfCameras: Int
        get() = mImpl.numberOfCameras

    fun openCamera(id: Int): Camera {
        return mImpl.openCamera(id)
    }

    fun openDefaultCamera(): Camera {
        return mImpl.openDefaultCamera()
    }

    fun openFrontCamera(): Camera {
        return mImpl.openCameraFacing(CameraInfo.CAMERA_FACING_FRONT)
    }

    fun openBackCamera(): Camera {
        return mImpl.openCameraFacing(CameraInfo.CAMERA_FACING_BACK)
    }

    fun hasFrontCamera(): Boolean {
        return mImpl.hasCamera(CameraInfo.CAMERA_FACING_FRONT)
    }

    fun hasBackCamera(): Boolean {
        return mImpl.hasCamera(CameraInfo.CAMERA_FACING_BACK)
    }

    fun getCameraInfo(cameraId: Int, cameraInfo: CameraInfo2) {
        mImpl.getCameraInfo(cameraId, cameraInfo)
    }

    fun setCameraDisplayOrientation(activity: Activity,
                                    cameraId: Int, camera: Camera) {
        val result = getCameraDisplayOrientation(activity, cameraId)
        camera.setDisplayOrientation(result)
    }

    fun getCameraDisplayOrientation(activity: Activity, cameraId: Int): Int {
        val rotation = activity.windowManager.defaultDisplay
            .rotation
        var degrees = 0
        when (rotation) {
            Surface.ROTATION_0 -> degrees = 0
            Surface.ROTATION_90 -> degrees = 90
            Surface.ROTATION_180 -> degrees = 180
            Surface.ROTATION_270 -> degrees = 270
        }

        val result: Int
        val info = CameraInfo2()
        getCameraInfo(cameraId, info)
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360
        }
        return result
    }

    class CameraInfo2 {
        var facing: Int = 0
        var orientation: Int = 0
    }


}
