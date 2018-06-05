package com.cheng.testspannable;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

/**
 * 注：这个类是用于对SpannableString中某段文本的操作，也即某段文本可以拥有多个属性设置
 * //TODO MySpannerString使用示例
 * TextView show_text_32 = findViewById(R.id.show_text_32);
 * show_text_32.setMovementMethod(LinkMovementMethod.getInstance());
 * String all_text = "\t\t这是一段演示使用MySpannerString的文本对象！\n\t\t可以同时添加多种效果与某段文本的特殊效果，并且能附带点击事件。";
 * MySpannerString builder = new MySpannerString.Builder(all_text)
 * .setBackgroundColorSpannable(ContextCompat.getColor(this, R.color.colorAccent))
 * .setForegroundColorSpan(ContextCompat.getColor(this, android.R.color.white))
 * .setClickableSpan("点击事件", new ClickText(this, ContextCompat.getColor(this, R.color.colorPrimaryDark), true, new ClickTextListener() {
 *
 * @Override public void ClickListener() {
 * Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
 * }
 * }))
 * .create();
 * show_text_32.setText(builder);
 */


public class MySpannerString extends SpannableString {
    public MySpannerString(CharSequence source) {
        super(source);
    }

    //构建文本设置
    public static class Builder {

        private String text;
        private SpannableString spanna;

        public Builder(String all_text) {
            this.text = all_text;
            this.spanna = new SpannableString(all_text);
        }

        public Builder addSpannable(Object obj) {
            this.spanna.setSpan(obj, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        public Builder addSpannable(Object obj, int start, int end) {
            this.spanna.setSpan(obj, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        //设置背景颜色
        public Builder setBackgroundColorSpannable(int color) {
            return setBackgroundColorSpannable(0, text.length(), color);
        }

        public Builder setBackgroundColorSpannable(String text, int color) {
            int[] textIndex = getTextIndex(text);
            if (textIndex[0] == -1) {
                return this;
            }
            return setBackgroundColorSpannable(textIndex[0], textIndex[1], color);
        }

        public Builder setBackgroundColorSpannable(int start, int end, int color) {
            BackgroundColorSpan span = new BackgroundColorSpan(color);
            this.spanna.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        //设置文字颜色
        public Builder setForegroundColorSpan(int color) {
            return setForegroundColorSpan(0, text.length(), color);
        }

        public Builder setForegroundColorSpan(String text, int color) {
            int[] textIndex = getTextIndex(text);
            if (textIndex[0] == -1) {
                return this;
            }
            return setForegroundColorSpan(textIndex[0], textIndex[1], color);
        }

        public Builder setForegroundColorSpan(int start, int end, int color) {
            ForegroundColorSpan span = new ForegroundColorSpan(color);
            this.spanna.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        //设置点击事件
        public Builder setClickableSpan(ClickText click) {
            return setClickableSpan(0, text.length(), click);
        }

        public Builder setClickableSpan(String text, ClickText click) {
            int[] textIndex = getTextIndex(text);
            if (textIndex[0] == -1) {
                return this;
            }
            return setClickableSpan(textIndex[0], textIndex[1], click);
        }

        public Builder setClickableSpan(int start, int end, ClickText click) {
            this.spanna.setSpan(click, start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        //TODO 需要方法自己根据MySpannerUtil里面的方法加

        public MySpannerString create() {
            MySpannerString spannerBuilder = new MySpannerString(spanna);
            return spannerBuilder;
        }

        public int[] getTextIndex(String text) {
            int[] inc = new int[2];
            if (text.equals(this.text)) {
                inc[0] = 0;
                inc[1] = text.length();
            } else if (this.text.contains(text)) {
                inc[0] = this.text.indexOf(text);
                inc[1] = this.text.indexOf(text) + text.length();
            } else {
                inc[0] = -1;
                inc[1] = -1;
            }
            return inc;
        }

    }

}
