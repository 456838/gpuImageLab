package com.zhenai.gpuimagelab.util.camera;

import android.hardware.Camera;

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/10 下午2:48
 * ModifyTime: 下午2:48
 * Description:
 */
public interface CameraHelperImpl {
    int getNumberOfCameras();

    Camera openCamera(int id);

    Camera openDefaultCamera();

    Camera openCameraFacing(int facing);

    boolean hasCamera(int cameraFacingFront);

    void getCameraInfo(int cameraId, CameraHelper.CameraInfo2 cameraInfo);
}
