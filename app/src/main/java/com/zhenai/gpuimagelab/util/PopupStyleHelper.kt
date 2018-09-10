package com.zhenai.gpuimagelab.util

import android.app.Dialog
import android.view.Gravity
import android.view.View
import android.view.WindowManager

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/4 上午10:06
 * ModifyTime: 上午10:06
 * Description:
 */
object PopupStyleHelper {

    fun fullScreenStyle(dialog: Dialog): Dialog {
        val dialogWindow = dialog.window
        val uiOptions = (
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE)
        dialogWindow.decorView.systemUiVisibility = uiOptions
        val lp = dialogWindow.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        lp.gravity = Gravity.CENTER
        dialogWindow.attributes = lp
        return dialog
    }
}