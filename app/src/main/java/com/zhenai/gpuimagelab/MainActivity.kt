package com.zhenai.gpuimagelab

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.salton123.base.BaseSupportActivity
import com.salton123.base.FragmentDelegate
import com.zhenai.gpuimagelab.ui.fm.MainComp
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem

class MainActivity : BaseSupportActivity() {
    private var mImmersionBar: ImmersionBar? = null
    internal var isSetup = false
    override fun getLayout(): Int = R.layout.salton_fm_container

    override fun initVariable(savedInstanceState: Bundle?) {
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true)
        mImmersionBar?.apply { init() }
    }

    override fun initViewAndData() {
        checkPermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar?.apply { destroy() }
    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission() {
        val permissionItems = mutableListOf<PermissionItem>()
        permissionItems.add(PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "读取存储空间", R.drawable.permission_ic_storage))
        permissionItems.add(PermissionItem(Manifest.permission.CAMERA, "写入存储空间", R.drawable.permission_ic_camera))
        HiPermission.create(this)
            .title("亲爱的上帝")
            .msg("为了能够正常使用，请开启这些权限吧！")
            .permissions(permissionItems)
            .style(R.style.PermissionDefaultBlueStyle)
            .animStyle(R.style.PermissionAnimScale)
            .checkMutiPermission(object : PermissionCallback {
                override fun onClose() {
                    Log.i("aa", "permission_onClose")
                    Toast.makeText(applicationContext, "用户关闭了权限", Toast.LENGTH_LONG).show()
                }

                override fun onFinish() {
                    Toast.makeText(applicationContext, "初始化完毕", Toast.LENGTH_LONG).show()
                    if (!isSetup) {
                        setup()
                    }
                }

                override fun onDeny(s: String, i: Int) {
                    Log.i("aa", "permission_onDeny")
                }

                override fun onGuarantee(s: String, i: Int) {
                    Log.i("aa", "permission_onGuarantee")
                    if (!isSetup) {
                        setup()
                    }
                }
            })
    }

    private fun setup() {
        isSetup = true
        if (findFragment(MainComp::class.java) == null) {
            loadRootFragment(R.id.fl_container, FragmentDelegate.newInstance(MainComp::class.java))
        }
    }
}
