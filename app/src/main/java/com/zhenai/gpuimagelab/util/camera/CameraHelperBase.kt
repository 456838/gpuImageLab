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

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera

class CameraHelperBase(private val mContext: Context) : CameraHelperImpl {

    override fun getNumberOfCameras(): Int {
        return if (hasCameraSupport()) 1 else 0
    }

    override fun openCamera(id: Int): Camera {
        return Camera.open()
    }

    override fun openDefaultCamera(): Camera {
        return Camera.open()
    }

    override fun hasCamera(facing: Int): Boolean {
        return if (facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            hasCameraSupport()
        } else false
    }

    override fun openCameraFacing(facing: Int): Camera? {
        return if (facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            Camera.open()
        } else null
    }

    override fun getCameraInfo(cameraId: Int, cameraInfo: CameraHelper.CameraInfo2) {
        cameraInfo.facing = Camera.CameraInfo.CAMERA_FACING_BACK
        cameraInfo.orientation = 90
    }

    private fun hasCameraSupport(): Boolean {
        return mContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
    }
}
