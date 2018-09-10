package com.zhenai.gpuimagelab.ui.fm

import android.os.Bundle
import android.view.View
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.zhenai.gpuimagelab.R
import kotlinx.android.synthetic.main.cp_main.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/4 上午9:56
 * ModifyTime: 上午9:56
 * Description:
 */
class MainComp : BaseSupportFragment() {
    override fun getLayout(): Int = R.layout.cp_main
    private val mCameraEffectLiteComp by lazy { FragmentDelegate.newInstance(CameraEffectLiteComp::class.java) }
    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        loadRootFragment(R.id.flPhotoEffectContainer, FragmentDelegate.newInstance(PhotoEffectLiteComp::class.java))
        loadRootFragment(R.id.flCameraEffectContainer, mCameraEffectLiteComp)
    }

    override fun initListener() {
        setListener(tvPicEffect, flPhotoEffect, tvCameraEffect, flCameraEffect)
    }

    override fun onClick(v: View?) {
        when (v) {
            tvPicEffect, flPhotoEffect -> {
                FragmentDelegate.newInstance(PhotoEffectPopupComp::class.java)
                    .show(childFragmentManager, "PhotoEffectPopupComp")
            }
            tvCameraEffect, flCameraEffect -> {
//                FragmentDelegate.newInstance(CameraEffectPopupComp::class.java)
//                    .show(childFragmentManager, "CameraEffectPopupComp")
//                mCameraEffectLiteComp.onPause()
            }
        }
    }

}