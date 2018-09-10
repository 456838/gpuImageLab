package com.zhenai.gpuimagelab.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.salton123.util.ViewUtils;
import com.zhenai.gpuimagelab.R;


public class EmuiTitleBar extends RelativeLayout {
    private static final String TAG = "EmuiTitleBar";

    private ImageView title_left_iv, title_right_iv, title_title_iv;

    public EmuiTitleBar(Context context) {
        super(context);
        init();
    }

    public EmuiTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmuiTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_title, this);
        title_left_iv = ViewUtils.f(this, R.id.title_left_iv);
        title_right_iv = ViewUtils.f(this, R.id.title_right_iv);
        title_title_iv = ViewUtils.f(this, R.id.title_title_iv);
        title_left_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() instanceof Activity) {
                    Activity activity = (Activity) getContext();
                    activity.finish();
                }
            }
        });
        title_right_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() instanceof Activity) {
                    // showMenuDialog((Activity) getContext());
                }
            }
        });
    }

    public EmuiTitleBar leftClick(OnClickListener listener) {
        title_left_iv.setOnClickListener(listener);
        return this;
    }

    public EmuiTitleBar rightClick(OnClickListener listener) {
        title_right_iv.setOnClickListener(listener);
        return this;
    }

    // public void showMenuDialog(Activity activity) {
    //     try {
    //         Class cls = Class.forName("com.salton123.opan.view.widget.MenuDialog");
    //         Constructor<?> csr = cls.getConstructor(Context.class, boolean.class);  //调用有参构造
    //         Object obj = csr.newInstance(activity, false);
    //         Method method = cls.getMethod("show");
    //         method.invoke(obj);
    //         // Method m = cls.getDeclaredMethod("show", new Class[]{int.class, String.class});
    //         // m.invoke(cls.newInstance(), 20, "chb");
    //     } catch (Exception e) {
    //         MLog.error(TAG, "[showMenuDialog] ex", e);
    //     }
    // }

}
