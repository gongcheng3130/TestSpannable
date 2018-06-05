package com.cheng.testspannable;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * 注：这个类是用于拼接SpannableString，也即每个SpannableString中有一段文字，是这些文字拼接起来的字符串
 //TODO MySpannerStringBuilder使用示例
 TextView show_text_31 = findViewById(R.id.show_text_31);
 String text_string_1 = "这是一段演示使用MySpannerStringBuilder的文本";
 String text_string_2 = "这是一段演示使用MySpannerStringBuilder的文本";
 String text_string_3 = "这是一段演示使用MySpannerStringBuilder的文本";
 show_text_31.setMovementMethod(new LinkMovementMethod());
 MySpannerStringBuilder builder = new MySpannerStringBuilder.Builder()
 .addSpannable(MySpannerUtil.getBackgroundColorSpannable(text_string_1, ContextCompat.getColor(this, R.color.colorAccent)))
 .addSpannable(MySpannerUtil.getForegroundColorSpan(text_string_2, ContextCompat.getColor(this, R.color.colorAccent)))
 .addSpannable(MySpannerUtil.getClickableSpan(text_string_3, new ClickText(this, ContextCompat.getColor(this, R.color.colorAccent), true, new ClickTextListener() {
@Override
public void ClickListener() {
Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
}
})))
 .create();
 show_text_31.setText(builder);
 */
public class MySpannerStringBuilder extends SpannableStringBuilder {

    public static class Builder {

        private List<SpannableString> lists;

        public Builder() {
            this.lists = new ArrayList<>();
        }

        public Builder addSpannable(SpannableString spa) {
            lists.add(spa);
            return this;
        }

        public MySpannerStringBuilder create() {
            MySpannerStringBuilder spannerBuilder = new MySpannerStringBuilder();
            for (int i = 0; i < lists.size(); i++) {
                spannerBuilder.append(lists.get(i));
            }
            return spannerBuilder;
        }

    }

}
