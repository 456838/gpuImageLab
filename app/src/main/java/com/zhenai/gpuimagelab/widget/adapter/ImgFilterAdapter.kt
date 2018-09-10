package com.zhenai.gpuimagelab.widget.adapter

import android.content.Context
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.base.recyclerview.adapter.CommonAdapter
import com.zhenai.gpuimagelab.R
import com.zhenai.gpuimagelab.bean.FilterType

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/4 下午3:54
 * ModifyTime: 下午3:54
 * Description:
 */
class ImgFilterAdapter(context: Context) : CommonAdapter<FilterType>(context, R.layout.adapter_item_img_filter) {
    override fun bindData(holder: ViewHolder, data: FilterType, position: Int) {
        holder.setText(R.id.tvName, "" + data.name)
    }
}