package com.cheng.testspannable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.DrawableMarginSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.IconMarginSpan;
import android.text.style.ImageSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TabStopSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;

/**
 * SpannableStringBuilder和SpannableString主要通过使用setSpan(Object what, int start, int end, int flags)改变文本样式。
 * 对应的参数：
 *
 * start： 指定Span的开始位置
 *
 * end： 指定Span的结束位置，并不包括这个位置
 *
 * flags：取值有如下四个
 Spannable.SPAN_EXCLUSIVE_INCLUSIVE：在 Span前面输入的字符不应用 Span的效果，在后面输入的字符应用Span效果。
 Spannable.SPAN_INCLUSIVE_EXCLUSIVE：在 Span前面输入的字符应用 Span 的效果，在后面输入的字符不应用Span效果。
 Spannable.SPAN_INCUJSIVE_INCLUSIVE：在 Span前后输入的字符都应用 Span 的效果。
 Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括。
 *
 * 注：start包括这个下标位置，end不包括这个下标位置，flag表示应用在start到end这段字符中的设置是否同步设置到前后内容上
 * ，一般来说只需要使用Spannable.SPAN_EXCLUSIVE_EXCLUSIVE就行
 *
 * what： 对应的各种Span，不同的Span对应不同的样式。已知的可用类有：
 BackgroundColorSpan : 文本背景色
 ForegroundColorSpan : 文本颜色
 MaskFilterSpan : 修饰效果，(模糊BlurMaskFilter、浮雕EmbossMaskFilter)
 RasterizerSpan : 光栅效果 玛德渣渣。貌似没这个方法。百度都找不到。
 StrikethroughSpan : 删除线 效果等同于textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
 SuggestionSpan : 相当于占位符 智商低了。没看懂咋搞。。。
 UnderlineSpan : 下划线 效果等同于textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
 AbsoluteSizeSpan : 文本字体（绝对大小）
 DynamicDrawableSpan : 设置图片，基于文本基线或底部对齐。
 ImageSpan : 图片 DynamicDrawableSpan子类
 RelativeSizeSpan : 相对大小（文本字体）
 ScaleXSpan : 横向缩放样式，将字体按比例进行横向缩放
 StyleSpan : 字体样式：粗体、斜体等
 SubscriptSpan : 脚注样式，下标（数学公式会用到）
 SuperscriptSpan : 上标（数学公式会用到）
 TextAppearanceSpan : 文本外貌（包括字体、大小、样式和颜色）
 TypefaceSpan : 文本字体
 URLSpan : 文本超链接
 ClickableSpan : 点击事件
 AlignmentSpan : 对齐方式
 BulletSpan : 着重样式，加一个小圆点 颜色与文本颜色一致
 DrawableMarginSpan : 图片Margin样式
 IconMarginSpan : 图片Margin样式
 LeadingMarginSpan : 文本缩进的样式
 QuoteSpan : 引用样式，在文本左侧添加一条表示引用的竖线
 TabStopSpan : 制表位偏移样式，距离每行的leading margin的偏移量，在首行加入制表符时才产生效果

 //TODO 使用示例
 show_text_1.setText(MySpannerUtil.getBackgroundColorSpannable("Hello World!", ContextCompat.getColor(this, R.color.colorAccent)));

 show_text_2.setText(MySpannerUtil.getForegroundColorSpan("Hello World!", ContextCompat.getColor(this, R.color.colorAccent)));

 show_text_3.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
 show_text_3.getPaint().setMaskFilter(MySpannerUtil.getBlurMaskFilter(20, BlurMaskFilter.Blur.SOLID));

 show_text_4.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
 show_text_4.getPaint().setMaskFilter(MySpannerUtil.getBlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));

 show_text_5.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
 show_text_5.getPaint().setMaskFilter(MySpannerUtil.getBlurMaskFilter(20, BlurMaskFilter.Blur.OUTER));

 show_text_6.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
 show_text_6.getPaint().setMaskFilter(MySpannerUtil.getBlurMaskFilter(20, BlurMaskFilter.Blur.INNER));

 show_text_7.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
 show_text_7.getPaint().setMaskFilter(MySpannerUtil.getEmbossMaskFilter(new float[]{10, 10, 10}, 0.5f, 1, 1));

 show_text_8.setText(MySpannerUtil.getStrikethroughSpan("Hello World!"));

 show_text_9.setText(MySpannerUtil.getUnderlineSpan("Hello World!"));

 show_text_10.setText(MySpannerUtil.getAbsoluteSizeSpan("Hello World!", 20));

 show_text_11.setText(MySpannerUtil.getDynamicDrawableSpan(this, "Hello World!", 2, 7, DynamicDrawableSpan.ALIGN_BASELINE, R.mipmap.ic_launcher));

 show_text_12.setText(MySpannerUtil.getDynamicDrawableSpan(this, "Hello World!", 2, 7, DynamicDrawableSpan.ALIGN_BOTTOM, R.mipmap.ic_launcher));

 show_text_13.setText(MySpannerUtil.getRelativeSizeSpan("Hello World!", 1.5f));

 show_text_14.setText(MySpannerUtil.getScaleXSpan("Hello World!", 1.5f));

 show_text_15.setText(MySpannerUtil.getStyleSpan("Hello World!", Typeface.BOLD_ITALIC));

 show_text_16.setText(MySpannerUtil.getSubscriptSpan("Hello World!", 2, 8));

 show_text_17.setText(MySpannerUtil.getSuperscriptSpan("Hello World!", 2, 8));

 show_text_18.setText(MySpannerUtil.getTextAppearanceSpan(this, "Hello World!", android.R.style.TextAppearance_Large_Inverse));

 show_text_19.setText(MySpannerUtil.getTypefaceSpan("Hello World!", "monospace"));

 show_text_20.setMovementMethod(new LinkMovementMethod());
 show_text_20.setText(MySpannerUtil.getURLSpan("Hello World!", "https://www.baidu.com"));

 show_text_21.setMovementMethod(new LinkMovementMethod());
 show_text_21.setText(MySpannerUtil.getClickableSpan("Hello World!", new ClickText(this, R.color.colorAccent, true, new ClickTextListener() {
@Override
public void ClickListener() {
Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
}
})));

 show_text_22.setText(MySpannerUtil.getAlignmentSpan("Hello World!", Layout.Alignment.ALIGN_NORMAL));

 show_text_23.setText(MySpannerUtil.getAlignmentSpan("Hello World!", Layout.Alignment.ALIGN_CENTER));

 show_text_24.setText(MySpannerUtil.getAlignmentSpan("Hello World!", Layout.Alignment.ALIGN_OPPOSITE));

 show_text_25.setText(MySpannerUtil.getBulletSpan("Hello World!"));

 show_text_26.setText(MySpannerUtil.getDrawableMarginSpan(this, "Hello World!", R.mipmap.ic_launcher, 50));

 show_text_27.setText(MySpannerUtil.getIconMarginSpan(this, "Hello World!", R.mipmap.ic_launcher, 50));

 show_text_28.setText(MySpannerUtil.getLeadingMarginSpan("Hello World!", 50));

 show_text_29.setText(MySpannerUtil.getQuoteSpan("Hello World!"));

 String text = "\t\tHelloWorld!HelloWorld!\n\t\tHelloWorld!HelloWorld!Hello\n\t\tWorld!HelloWorld!HelloWorld!HelloWorld!\n\t\tHelloWorld!";
 show_text_30.setText(MySpannerUtil.getTabStopSpan(text, 0));
 */
public class MySpannerUtil extends SpannableStringBuilder {

    public MySpannerUtil(Context context) {

    }

    /**
     * 获得这段带背景颜色设置的SpannableString
     * @param text 设置的文本
     * @param color 设置的颜色(注：传入的是颜色色值，不要传资源id)
     * @return SpannableString
     */
    public static SpannableString getBackgroundColorSpannable(String text, int color) {
        return getBackgroundColorSpannable(text, 0, text.length(), color);
    }

    /**
     * 获得这段带背景颜色设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param color 设置的颜色(注：传入的是颜色色值，不要传资源id)
     * @return SpannableString
     */
    public static SpannableString getBackgroundColorSpannable(String text, int start, int end, int color) {
        SpannableString spannableString = new SpannableString(text);
        BackgroundColorSpan span = new BackgroundColorSpan(color);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段带字体颜色设置的SpannableString
     * @param text 设置的文本
     * @param color 设置的颜色(注：传入的是颜色色值，不要传资源id)
     * @return SpannableString
     */
    public static SpannableString getForegroundColorSpan(String text, int color) {
        return getForegroundColorSpan(text, 0, text.length(), color);
    }

    /**
     * 获得这段带字体颜色设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param color 设置的颜色(注：传入的是颜色色值，不要传资源id)
     * @return SpannableString
     */
    public static SpannableString getForegroundColorSpan(String text, int start, int end, int color) {
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获取一个模糊设置的BlurMaskFilter
     * 注：需关闭硬件加速 setLayerType(View.LAYER_TYPE_SOFTWARE, null)
     * @param radius 值越大阴影越扩散，其实就是阴影范围
     * @param style BlurMaskFilter.Blur中的值
     * SOLID 在图像的Alpha边界外产生一层与Paint颜色一致的阴影效果而不影响图像本身
     * NORMAL 会将整个图像模糊掉
     * OUTER 会在Alpha边界外产生一层阴影且会将原本的图像变透明
     * INNER 会在图像内部产生模糊，可以看见阴影在色块的内部，有种淡淡的浮雕感
     * @return BlurMaskFilter
     */
    public static BlurMaskFilter getBlurMaskFilter(int radius, BlurMaskFilter.Blur style) {
        BlurMaskFilter maskFilter = new BlurMaskFilter(radius, style);
        return maskFilter;
    }

    /**
     * 获取一个浮雕设置的EmbossMaskFilter
     * 注：需关闭硬件加速 setLayerType(View.LAYER_TYPE_SOFTWARE, null)
     * @param direction 是float数组，定义长度为3的数组标量[x,y,z] 理解：来指定光源的方向，以控件为坐标原点，光源在三轴上的坐标
     * @param ambient 取值在0到1之间，定义背景光 或者说是周围光 理解：光照强度，越大显示越亮
     * @param specular 定义镜面反射系数 理解：反光度，值越大越不反光，最后变黑
     * @param blurRadius 模糊半径 理解：明暗度，值越大越暗
     * @return EmbossMaskFilter
     */
    public static EmbossMaskFilter getEmbossMaskFilter(float[] direction, float ambient, float specular, float blurRadius) {
        EmbossMaskFilter maskFilter = new EmbossMaskFilter(direction, ambient, specular, blurRadius);
        return maskFilter;
    }

    /**
     * 获得这段带删除线设置的SpannableString
     * @param text 设置的文本
     * @return SpannableString
     */
    public static SpannableString getStrikethroughSpan(String text) {
        return getStrikethroughSpan(text, 0, text.length());
    }

    /**
     * 获得这段带删除线设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @return SpannableString
     */
    public static SpannableString getStrikethroughSpan(String text, int start, int end) {
        SpannableString spannableString = new SpannableString(text);
        StrikethroughSpan span = new StrikethroughSpan();
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段带下划线设置的SpannableString
     * @param text 设置的文本
     * @return SpannableString
     */
    public static SpannableString getUnderlineSpan(String text) {
        return getUnderlineSpan(text, 0, text.length());
    }

    /**
     * 获得这段带下划线设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @return SpannableString
     */
    public static SpannableString getUnderlineSpan(String text, int start, int end) {
        SpannableString spannableString = new SpannableString(text);
        UnderlineSpan span = new UnderlineSpan();
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段字体大小设置的SpannableString
     * @param text 设置的文本
     * @param size 字体大小
     * @return SpannableString
     */
    public static SpannableString getAbsoluteSizeSpan(String text, int size) {
        return getAbsoluteSizeSpan(text, 0, text.length(), size);
    }

    /**
     * 获得这段字体大小设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param size 字体大小
     * @return SpannableString
     */
    public static SpannableString getAbsoluteSizeSpan(String text, int start, int end, int size) {
        SpannableString spannableString = new SpannableString(text);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(size);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段图片插入设置的SpannableString
     * @param context 上下文
     * @param text 设置的文本
     * @param style DynamicDrawableSpan.ALIGN_BASELINE 基于文本基线对齐  DynamicDrawableSpan.ALIGN_BOTTOM 基于控件底部对齐
     * @return SpannableString
     */
    public static SpannableString getDynamicDrawableSpan(final Context context, String text, int style, int resource) {
        return getDynamicDrawableSpan(context, text, 0, text.length(), style, resource);
    }

    /**
     * 获得这段图片插入设置的SpannableString
     * @param context 上下文
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param style DynamicDrawableSpan.ALIGN_BASELINE 基于文本基线对齐  DynamicDrawableSpan.ALIGN_BOTTOM 基于底部对齐
     * @return SpannableString
     */
    public static SpannableString getDynamicDrawableSpan(final Context context, String text, int start, int end, int style, final int resource) {
        SpannableString spannableString = new SpannableString(text);
        DynamicDrawableSpan span = new DynamicDrawableSpan(style) {
            @Override
            public Drawable getDrawable() {
                Drawable d = context.getResources().getDrawable(resource);
                d.setBounds(0, 0, 50, 50);
                return d;
            }
        };
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段图片插入设置的SpannableString
     * @param context 上下文
     * @param text 设置的文本
     * @param resource 本地资源
     * @param style DynamicDrawableSpan.ALIGN_BASELINE 基于文本基线对齐  DynamicDrawableSpan.ALIGN_BOTTOM 基于控件底部对齐
     * @return SpannableString
     */
    public static SpannableString getImageSpan(final Context context, String text, int style, int resource) {
        return getImageSpan(context, text, resource, 0, text.length(), style);
    }

    /**
     * 获得这段图片插入设置的SpannableString
     * ImageSpan构建有多种重载方法，根据需要使用，效果跟DynamicDrawableSpan一样
     * @param context 上下文
     * @param text 设置的文本
     * @param resource 本地资源
     * @param start 开始下标
     * @param end 结束下标
     * @param style DynamicDrawableSpan.ALIGN_BASELINE 基于文本基线对齐  DynamicDrawableSpan.ALIGN_BOTTOM 基于底部对齐
     * @return SpannableString
     */
    public static SpannableString getImageSpan(Context context, String text, int start, int end, int style, int resource) {
        SpannableString spannableString = new SpannableString(text);
        Drawable draw = context.getResources().getDrawable(resource);
        ImageSpan span = new ImageSpan(draw, style);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段字体大小设置的SpannableString
     * 效果与AbsoluteSizeSpan一样，区别设置字体大小的单位不同，这里是以控件本身的宽高为基准，所以一般来说0.5到1.5之间的数值就够了
     * @param text 设置的文本
     * @param size 字体大小
     * @return SpannableString
     */
    public static SpannableString getRelativeSizeSpan(String text, float size) {
        return getRelativeSizeSpan(text, 0, text.length(), size);
    }

    /**
     * 获得这段字体大小设置的SpannableString
     * 效果与AbsoluteSizeSpan一样，区别设置字体大小的单位不同，这里是以控件本身的宽高为基准，所以一般来说0.5到1.5之间的数值就够了
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param size 字体大小
     * @return SpannableString
     */
    public static SpannableString getRelativeSizeSpan(String text, int start, int end, float size) {
        SpannableString spannableString = new SpannableString(text);
        RelativeSizeSpan span = new RelativeSizeSpan(size);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段横向缩放设置的SpannableString
     * @param text 设置的文本
     * @param size 缩放量
     * @return SpannableString
     */
    public static SpannableString getScaleXSpan(String text, float size) {
        return getScaleXSpan(text, 0, text.length(), size);
    }

    /**
     * 获得这段横向缩放设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param size 缩放量
     * @return SpannableString
     */
    public static SpannableString getScaleXSpan(String text, int start, int end, float size) {
        SpannableString spannableString = new SpannableString(text);
        ScaleXSpan span = new ScaleXSpan(size);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段字体样式设置的SpannableString
     * @param text 设置的文本
     * @param style Typeface.BOLD 粗体 Typeface.ITALIC 倾斜 Typeface.BOLD_ITALIC 加粗倾斜
     * @return SpannableString
     */
    public static SpannableString getStyleSpan(String text, int style) {
        return getStyleSpan(text, 0, text.length(), style);
    }

    /**
     * 获得这段字体样式设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param style Typeface.BOLD 粗体 Typeface.ITALIC 倾斜 Typeface.BOLD_ITALIC 加粗倾斜
     * @return SpannableString
     */
    public static SpannableString getStyleSpan(String text, int start, int end, int style) {
        SpannableString spannableString = new SpannableString(text);
        StyleSpan span = new StyleSpan(style);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段带下标设置的SpannableString
     * @param text 设置的文本
     * @return SpannableString
     */
    public static SpannableString getSubscriptSpan(String text) {
        return getSubscriptSpan(text, 0, text.length());
    }

    /**
     * 获得这段带下标设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @return SpannableString
     */
    public static SpannableString getSubscriptSpan(String text, int start, int end) {
        SpannableString spannableString = new SpannableString(text);
        SubscriptSpan span = new SubscriptSpan();
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段带上标设置的SpannableString
     * @param text 设置的文本
     * @return SpannableString
     */
    public static SpannableString getSuperscriptSpan(String text) {
        return getSuperscriptSpan(text, 0, text.length());
    }

    /**
     * 获得这段带上标设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @return SpannableString
     */
    public static SpannableString getSuperscriptSpan(String text, int start, int end) {
        SpannableString spannableString = new SpannableString(text);
        SuperscriptSpan span = new SuperscriptSpan();
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段文本属性设置的SpannableString
     * @param context 上下文
     * @param text 设置的文本
     * @param style 样式设置
     * @return SpannableString
     */
    public static SpannableString getTextAppearanceSpan(Context context, String text, int style) {
        return getTextAppearanceSpan(context, text, 0, text.length(), style);
    }

    /**
     * 获得这段文本属性设置的SpannableString
     * @param context 上下文
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param style 样式设置
     * @return SpannableString
     */
    public static SpannableString getTextAppearanceSpan(Context context, String text, int start, int end, int style) {
        SpannableString spannableString = new SpannableString(text);
        TextAppearanceSpan span = new TextAppearanceSpan(context, style);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段字体设置的SpannableString
     * @param text 设置的文本
     * @param format 字体 可以理解为宋体、楷书、隶书之类的字体类型，不能用中文
     * @return SpannableString
     */
    public static SpannableString getTypefaceSpan(String text, String format) {
        return getTypefaceSpan(text, 0, text.length(), format);
    }

    /**
     * 获得这段字体设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param format 字体 可以理解为宋体、楷书、隶书之类的字体类型，不能用中文
     * @return SpannableString
     */
    public static SpannableString getTypefaceSpan(String text, int start, int end, String format) {
        SpannableString spannableString = new SpannableString(text);
        TypefaceSpan span = new TypefaceSpan(format);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段超链接设置的SpannableString
     * 注：如果需要直接点击超链接产生效果，需设置控件可点击 setMovementMethod(new LinkMovementMethod())
     * @param text 设置的文本
     * @param link 链接
     * @return SpannableString
     */
    public static SpannableString getURLSpan(String text, String link) {
        return getURLSpan(text, 0, text.length(), link);
    }

    /**
     * 获得这段超链接设置的SpannableString
     * 注：如果需要直接点击超链接产生效果，需设置控件可点击 setMovementMethod(new LinkMovementMethod())
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param link 链接
     * @return SpannableString
     */
    public static SpannableString getURLSpan(String text, int start, int end, String link) {
        SpannableString spannableString = new SpannableString(text);
        URLSpan span = new URLSpan(link);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置字符串中某区间字符颜色并且可点击
     * 注：如果需要直接点击产生效果，需设置控件可点击 setMovementMethod(new LinkMovementMethod())
     * @param text 设置的文本
     * @param click 自定义的点击效果
     * @return SpannableString
     */
    public static SpannableString getClickableSpan(String text, ClickText click) {
        return getClickableSpan(text, 0, text.length(), click);
    }

    /**
     * 设置字符串中某区间字符颜色并且可点击
     * 注：如果需要直接点击产生效果，需设置控件可点击 setMovementMethod(new LinkMovementMethod())
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param click 自定义的点击效果
     * @return SpannableString
     */
    public static SpannableString getClickableSpan(String text, int start, int end, ClickText click) {
        SpannableString spanStr = new SpannableString(text);
        spanStr.setSpan(click, start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanStr;
    }

    /**
     * 获得这段位置设置的SpannableString
     * @param text 设置的文本
     * @param alignment 链接
     * @return SpannableString
     */
    public static SpannableString getAlignmentSpan(String text, Layout.Alignment alignment) {
        return getAlignmentSpan(text, 0, text.length(), alignment);
    }

    /**
     * 获得这段位置设置的SpannableString
     * @param text 设置的文本
     * @param start 开始下标
     * @param end 结束下标
     * @param alignment ALIGN_CENTER 居中 ALIGN_NORMAL 正常 ALIGN_OPPOSITE 底部
     * @return SpannableString
     */
    public static SpannableString getAlignmentSpan(String text, int start, int end, Layout.Alignment alignment) {
        SpannableString spannableString = new SpannableString(text);
        AlignmentSpan.Standard span = new AlignmentSpan.Standard(alignment);
        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段加重点设置的SpannableString
     * 注：只能设置在文本前面
     * @param text 设置的文本
     * @return SpannableString
     */
    public static SpannableString getBulletSpan(String text) {
        SpannableString spannableString = new SpannableString(text);
        BulletSpan span = new BulletSpan();//有三个重载方法，可以设置原点颜色与间距
        spannableString.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段图片边距设置的SpannableString
     * 注：与getDynamicDrawableSpan或getImageSpan不同，这个宽高使用图片的宽高，一般不常用
     * @param context 上下文
     * @param text 设置的文本
     * @param resource 本地资源
     * @param pad 图片与文本之间的距离
     * @return SpannableString
     */
    public static SpannableString getDrawableMarginSpan(Context context, String text, int resource, int pad) {
        SpannableString spannableString = new SpannableString(text);
        Drawable draw = context.getResources().getDrawable(resource);
        DrawableMarginSpan span = new DrawableMarginSpan(draw, pad);
        spannableString.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段图片边距设置的SpannableString
     * 注：使用与DrawableMarginSpan效果一样
     * @param context 上下文
     * @param text 设置的文本
     * @param resource 本地资源
     * @param pad 图片与文本之间的距离
     * @return SpannableString
     */
    public static SpannableString getIconMarginSpan(Context context, String text, int resource, int pad) {
        SpannableString spannableString = new SpannableString(text);
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), resource);
        IconMarginSpan span = new IconMarginSpan(bmp, pad);
        spannableString.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段文本缩进设置的SpannableString
     * @param text 设置的文本
     * @param first 首行缩进像素
     * @return SpannableString
     */
    public static SpannableString getLeadingMarginSpan(String text, int first) {
        SpannableString spannableString = new SpannableString(text);
        LeadingMarginSpan span = new LeadingMarginSpan.Standard(first);
        spannableString.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段引用设置的SpannableString
     * @param text 设置的文本
     * @return SpannableString
     */
    public static SpannableString getQuoteSpan(String text) {
        SpannableString spannableString = new SpannableString(text);
        QuoteSpan span = new QuoteSpan();//还有一个传颜色的重载方法
        spannableString.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获得这段文本缩进设置的SpannableString
     * 注：一般使用需配合\n与\t这两个特殊符号使用，\t会替换成一个字体的空格，一般用于段落使用
     * @param text 设置的文本
     * @param pad 间距
     * @return SpannableString
     */
    public static SpannableString getTabStopSpan(String text, int pad) {
        SpannableString spannableString = new SpannableString(text);
        TabStopSpan span = new TabStopSpan.Standard(0);
        spannableString.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}
