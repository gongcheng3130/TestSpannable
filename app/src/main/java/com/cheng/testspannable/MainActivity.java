package com.cheng.testspannable;

import android.graphics.BlurMaskFilter;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.text.style.DynamicDrawableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView show_text_1 = findViewById(R.id.show_text_1);
        show_text_1.setText(MySpannerUtil.getBackgroundColorSpannable("Hello World!", ContextCompat.getColor(this, R.color.colorAccent)));

        TextView show_text_2 = findViewById(R.id.show_text_2);
        show_text_2.setText(MySpannerUtil.getForegroundColorSpan("Hello World!", ContextCompat.getColor(this, R.color.colorAccent)));

        TextView show_text_3 = findViewById(R.id.show_text_3);
        show_text_3.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        show_text_3.getPaint().setMaskFilter(MySpannerUtil.getBlurMaskFilter(20, BlurMaskFilter.Blur.SOLID));

        TextView show_text_4 = findViewById(R.id.show_text_4);
        show_text_4.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        show_text_4.getPaint().setMaskFilter(MySpannerUtil.getBlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));

        TextView show_text_5 = findViewById(R.id.show_text_5);
        show_text_5.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        show_text_5.getPaint().setMaskFilter(MySpannerUtil.getBlurMaskFilter(20, BlurMaskFilter.Blur.OUTER));

        TextView show_text_6 = findViewById(R.id.show_text_6);
        show_text_6.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        show_text_6.getPaint().setMaskFilter(MySpannerUtil.getBlurMaskFilter(20, BlurMaskFilter.Blur.INNER));

        TextView show_text_7 = findViewById(R.id.show_text_7);
        show_text_7.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        show_text_7.getPaint().setMaskFilter(MySpannerUtil.getEmbossMaskFilter(new float[]{10, 10, 10}, 0.5f, 1, 1));

        TextView show_text_8 = findViewById(R.id.show_text_8);
        show_text_8.setText(MySpannerUtil.getStrikethroughSpan("Hello World!"));

        TextView show_text_9 = findViewById(R.id.show_text_9);
        show_text_9.setText(MySpannerUtil.getUnderlineSpan("Hello World!"));

        TextView show_text_10 = findViewById(R.id.show_text_10);
        show_text_10.setText(MySpannerUtil.getAbsoluteSizeSpan("Hello World!", 20));

        TextView show_text_11 = findViewById(R.id.show_text_11);
        show_text_11.setText(MySpannerUtil.getDynamicDrawableSpan(this, "Hello World!", 2, 7, DynamicDrawableSpan.ALIGN_BASELINE, R.mipmap.ic_launcher));

        TextView show_text_12 = findViewById(R.id.show_text_12);
        show_text_12.setText(MySpannerUtil.getDynamicDrawableSpan(this, "Hello World!", 2, 7, DynamicDrawableSpan.ALIGN_BOTTOM, R.mipmap.ic_launcher));

        TextView show_text_13 = findViewById(R.id.show_text_13);
        show_text_13.setText(MySpannerUtil.getRelativeSizeSpan("Hello World!", 1.5f));

        TextView show_text_14 = findViewById(R.id.show_text_14);
        show_text_14.setText(MySpannerUtil.getScaleXSpan("Hello World!", 1.5f));

        TextView show_text_15 = findViewById(R.id.show_text_15);
        show_text_15.setText(MySpannerUtil.getStyleSpan("Hello World!", Typeface.BOLD_ITALIC));

        TextView show_text_16 = findViewById(R.id.show_text_16);
        show_text_16.setText(MySpannerUtil.getSubscriptSpan("Hello World!", 2, 8));

        TextView show_text_17 = findViewById(R.id.show_text_17);
        show_text_17.setText(MySpannerUtil.getSuperscriptSpan("Hello World!", 2, 8));

        TextView show_text_18 = findViewById(R.id.show_text_18);
        show_text_18.setText(MySpannerUtil.getTextAppearanceSpan(this, "Hello World!", android.R.style.TextAppearance_Large_Inverse));

        TextView show_text_19 = findViewById(R.id.show_text_19);
        show_text_19.setText(MySpannerUtil.getTypefaceSpan("Hello World!", "monospace"));

        TextView show_text_20 = findViewById(R.id.show_text_20);
        show_text_20.setMovementMethod(new LinkMovementMethod());
        show_text_20.setText(MySpannerUtil.getURLSpan("Hello World!", "https://www.baidu.com"));

        TextView show_text_21 = findViewById(R.id.show_text_21);
        show_text_21.setMovementMethod(new LinkMovementMethod());
        show_text_21.setText(MySpannerUtil.getClickableSpan("Hello World!", new ClickText(this, R.color.colorAccent, true, new ClickTextListener() {
            @Override
            public void ClickListener() {
                Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
            }
        })));

        TextView show_text_22 = findViewById(R.id.show_text_22);
        show_text_22.setText(MySpannerUtil.getAlignmentSpan("Hello World!", Layout.Alignment.ALIGN_NORMAL));

        TextView show_text_23 = findViewById(R.id.show_text_23);
        show_text_23.setText(MySpannerUtil.getAlignmentSpan("Hello World!", Layout.Alignment.ALIGN_CENTER));

        TextView show_text_24 = findViewById(R.id.show_text_24);
        show_text_24.setText(MySpannerUtil.getAlignmentSpan("Hello World!", Layout.Alignment.ALIGN_OPPOSITE));

        TextView show_text_25 = findViewById(R.id.show_text_25);
        show_text_25.setText(MySpannerUtil.getBulletSpan("Hello World!"));

        TextView show_text_26 = findViewById(R.id.show_text_26);
        show_text_26.setText(MySpannerUtil.getDrawableMarginSpan(this, "Hello World!", R.mipmap.ic_launcher, 50));

        TextView show_text_27 = findViewById(R.id.show_text_27);
        show_text_27.setText(MySpannerUtil.getIconMarginSpan(this, "Hello World!", R.mipmap.ic_launcher, 50));

        TextView show_text_28 = findViewById(R.id.show_text_28);
        show_text_28.setText(MySpannerUtil.getLeadingMarginSpan("Hello World!", 50));

        TextView show_text_29 = findViewById(R.id.show_text_29);
        show_text_29.setText(MySpannerUtil.getQuoteSpan("Hello World!"));

        TextView show_text_30 = findViewById(R.id.show_text_30);
        String text = "\t\tHelloWorld!HelloWorld!\n\t\tHelloWorld!HelloWorld!Hello\n\t\tWorld!HelloWorld!HelloWorld!HelloWorld!\n\t\tHelloWorld!";
        show_text_30.setText(MySpannerUtil.getTabStopSpan(text, 0));

        setShowEffects1();
        setShowEffects2();
    }

    private void setShowEffects1(){
        TextView show_text_31 = findViewById(R.id.show_text_31);
        String text_1 = "这是一段演示使用MySpannerStringBuilder的文本";
        String text_2 = "这是一段演示使用MySpannerStringBuilder的文本";
        String text_3 = "这是一段演示使用MySpannerStringBuilder的文本";
        show_text_31.setMovementMethod(new LinkMovementMethod());
        MySpannerStringBuilder builder = new MySpannerStringBuilder.Builder()
                .addSpannable(MySpannerUtil.getBackgroundColorSpannable(text_1, ContextCompat.getColor(this, R.color.colorAccent)))
                .addSpannable(MySpannerUtil.getForegroundColorSpan(text_2, ContextCompat.getColor(this, R.color.colorAccent)))
                .addSpannable(MySpannerUtil.getClickableSpan(text_3, new ClickText(this, ContextCompat.getColor(this, R.color.colorAccent), true, new ClickTextListener() {
                    @Override
                    public void ClickListener() {
                        Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
                    }
                })))
                .create();
        show_text_31.setText(builder);
    }

    private void setShowEffects2(){
        TextView show_text_32 = findViewById(R.id.show_text_32);
        show_text_32.setMovementMethod(new LinkMovementMethod());
        String all_text = "\t\t这是一段演示使用MySpannerString的文本对象！\n\t\t可以同时添加多种效果与某段文本的特殊效果，并且能附带点击事件。";
        MySpannerString builder = new MySpannerString.Builder(all_text)
                .setBackgroundColorSpannable(ContextCompat.getColor(this, R.color.colorAccent))
                .setForegroundColorSpan(ContextCompat.getColor(this, android.R.color.white))
                .setClickableSpan("点击事件", new ClickText(this, ContextCompat.getColor(this, R.color.colorPrimaryDark), true, new ClickTextListener() {
                    @Override
                    public void ClickListener() {
                        Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
                    }
                }))
                .create();
        show_text_32.setText(builder);
    }

}
