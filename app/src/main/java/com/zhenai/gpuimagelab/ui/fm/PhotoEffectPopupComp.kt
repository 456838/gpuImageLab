package com.zhenai.gpuimagelab.ui.fm

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.SeekBar
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemClickListener
import com.salton123.base.BaseSupportPopupFragment
import com.zhenai.gpuimagelab.R
import com.zhenai.gpuimagelab.bean.FilterType
import com.zhenai.gpuimagelab.util.BrightnessAdjuster
import com.zhenai.gpuimagelab.widget.adapter.ImgFilterAdapter
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter
import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter
import jp.co.cyberagent.android.gpuimage.GPUImageEmbossFilter
import jp.co.cyberagent.android.gpuimage.GPUImageExposureFilter
import jp.co.cyberagent.android.gpuimage.GPUImageFilter
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup
import jp.co.cyberagent.android.gpuimage.GPUImageGammaFilter
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter
import jp.co.cyberagent.android.gpuimage.GPUImageHighlightShadowFilter
import jp.co.cyberagent.android.gpuimage.GPUImageHueFilter
import jp.co.cyberagent.android.gpuimage.GPUImageMonochromeFilter
import jp.co.cyberagent.android.gpuimage.GPUImageOpacityFilter
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter
import jp.co.cyberagent.android.gpuimage.GPUImagePosterizeFilter
import jp.co.cyberagent.android.gpuimage.GPUImageRGBFilter
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter
import jp.co.cyberagent.android.gpuimage.GPUImageSharpenFilter
import jp.co.cyberagent.android.gpuimage.GPUImageSobelEdgeDetection
import jp.co.cyberagent.android.gpuimage.GPUImageWhiteBalanceFilter
import kotlinx.android.synthetic.main.cp_photo_effect.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/4 上午10:04
 * ModifyTime: 上午10:04
 * Description:
 */
class PhotoEffectPopupComp : BaseSupportPopupFragment() {
    override fun getLayout(): Int = R.layout.cp_photo_effect
    private var isChecked: Boolean = false
    private val mAdapter by lazy { ImgFilterAdapter(activity()) }
    private var mFilters: ArrayList<GPUImageFilter> = ArrayList()
    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        ivEffectShow.setImage(BitmapFactory.decodeResource(resources, R.mipmap.bg_hudieguang))
        recyclerView.layoutManager = LinearLayoutManager(activity(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = mAdapter
        mAdapter.add(FilterType("contrast", GPUImageContrastFilter(2.0f), true))
        mAdapter.add(FilterType("gamma", GPUImageGammaFilter(2.0f), true))
        mAdapter.add(FilterType("invert", GPUImageColorInvertFilter()))
        mAdapter.add(FilterType("pixelation", GPUImagePixelationFilter()))
        mAdapter.add(FilterType("hue", GPUImageHueFilter(90.0f), true))
        mAdapter.add(FilterType("brightness", GPUImageBrightnessFilter(0f), true))
        mAdapter.add(FilterType("grayscale", GPUImageGrayscaleFilter()))

        mAdapter.add(FilterType("sepia", GPUImageSepiaFilter()))
        mAdapter.add(FilterType("sharpen", GPUImageSharpenFilter(2.0f), true))
        mAdapter.add(FilterType("sobel_edge_detection", GPUImageSobelEdgeDetection()))
        mAdapter.add(FilterType("emboss", GPUImageEmbossFilter()))
        mAdapter.add(FilterType("posterize", GPUImagePosterizeFilter()))
        mAdapter.add(FilterType("saturation", GPUImageSaturationFilter(1.0f), true))
        mAdapter.add(FilterType("exposure", GPUImageExposureFilter(0.0f), true))

        mAdapter.add(FilterType("highlight_shadow", GPUImageHighlightShadowFilter(0.0f, 1.0f), true))
        mAdapter.add(FilterType("monochrome", GPUImageMonochromeFilter(1.0f, floatArrayOf(0.6f, 0.45f, 0.3f, 1.0f)), true))
        mAdapter.add(FilterType("opacity", GPUImageOpacityFilter(1.0f), true))
        mAdapter.add(FilterType("exposure", GPUImageExposureFilter(0.0f), true))
        mAdapter.add(FilterType("rgb", GPUImageRGBFilter(1.0f, 1.0f, 1.0f), true))
        mAdapter.add(FilterType("white_balance", GPUImageWhiteBalanceFilter(5000.0f, 0.0f), true))
        mAdapter.notifyDataSetChanged()
    }

    override fun initListener() {
        titleBar.leftClick { dismissAllowingStateLoss() }
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                if (obj is FilterType) {
                    if (isChecked) {
                        if (mFilters.contains(obj.filter)) {
                            return
                        }
                        mFilters.add(obj.filter)
                        ivEffectShow.filter = GPUImageFilterGroup(mFilters)
                    } else {
                        ivEffectShow.filter = obj.filter
                    }
                    if (obj.hasProgress) {
                        seekBar.visibility = View.VISIBLE
                    } else {
                        seekBar.visibility = View.GONE
                    }
                }
            }
        })
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var filter = ivEffectShow.filter
                if (filter is GPUImageBrightnessFilter) {
                    BrightnessAdjuster().apply {
                        filter(filter)
                        adjust(progress)
                        ivEffectShow.filter = filter
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        cTvGroupEffect.setOnCheckedChangeListener { _, isChecked ->
            this.isChecked = isChecked
        }
    }

    override fun onClick(v: View?) {

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}