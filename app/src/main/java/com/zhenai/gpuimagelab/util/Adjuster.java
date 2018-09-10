package com.zhenai.gpuimagelab.util;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/4 下午4:40
 * ModifyTime: 下午4:40
 * Description:
 */
public abstract class Adjuster<T extends GPUImageFilter> {
    private T filter;

    @SuppressWarnings("unchecked")
    public Adjuster<T> filter(final GPUImageFilter filter) {
        this.filter = (T) filter;
        return this;
    }

    public T getFilter() {
        return filter;
    }

    public abstract void adjust(int percentage);

    protected float range(final int percentage, final float start, final float end) {
        return (end - start) * percentage / 100.0f + start;
    }

    protected int range(final int percentage, final int start, final int end) {
        return (end - start) * percentage / 100 + start;
    }
}