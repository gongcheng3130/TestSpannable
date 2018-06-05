package com.cheng.testspannable;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class ClickText extends ClickableSpan {

    private Context context; // 上下文
    private int color; // 字体颜色，需要的是色值不是资源id
    private boolean underline; // 是否设置下划线默认false为没有下划线
    private ClickTextListener clickListener; // 传入点击事件接口实现。

    public ClickText(Context context, int color, ClickTextListener clickListener) {
        this.context = context;
        this.color = color;
        this.clickListener = clickListener;
    }

    public ClickText(Context context, int color, boolean underline, ClickTextListener clickListener) {
        this.context = context;
        this.color = color;
        this.underline = underline;
        this.clickListener = clickListener;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(color);
        ds.setUnderlineText(underline);
    }

    @Override
    public void onClick(View widget) {
        clickListener.ClickListener();
    }

}
