## FontMetricsSample
This is a sample project for class `FontMetrics` & method `Canvas#drawText()`

## Screenshot & Sample
![FontMetrics Sample Screenshot](/ss/FontMetrics_Sample_ss.jpeg)

从这里下载 [Sample Apk](/apk/sample.apk)。

---

在绘制文字的时候，不可避免的用到 `FontMetrics` 类和 `Canvas#drawText()` 方法，这里详细介绍下。

## FontMetrics
先看下面这张图：
![FontMetrics Lines](http://omx3hkcsx.bkt.clouddn.com/static/images/android_fontmetrics_lines.jpeg?imageView2/2/w/640)

图中有五条线，自上而下分别是：
- top line: 文字可绘制区域最顶部的线；
- ascent line: 系统推荐的，文字可绘制区域顶部的线；
- baseline: 文字绘制的基线（在四线格上书写英文字母时的第三条线）；
- descent line: 系统推荐的，文字可绘制区域底部的线；
- bottom line: 文字可绘制区域最底部的线。

而 `FontMetrics` 类中有 `[top, ascent, descent, bottom, leading]` 字段，与上面 5 条线的关系是： **FontMetrics对象中的字段值 = 对应线条的 Y 坐标值 - baseline的 Y 坐标值** ，如果用 `Y(line A)` 表示线条 A 的 Y 坐标的话，那么以下等式成立：
```shell
FontMetrics::top = Y(top line) - Y(baseline);
FontMetrics::ascent = Y(ascent line) - Y(baseline);
FontMetrics::descent = Y(descent line) - Y(baseline);
FontMetrics::bottom = Y(bottom line) - Y(baseline);
```
<font color="#ff4081">需要注意的是：</font> 通常情况下，Android 中的 y 轴的正方向是沿屏幕向下的，也就是越往下 y 坐标越大，所以 `FontMetrics` 的 `top` 和 `ascent` 值是负值， `descent` 和 `bottom` 的值是正值。

余下的字段 `leading` 表示 系统推荐的行间距。

在实际开发中，通常使用 `Paint` 的 `getFontMetrics()` 获得其对应的 `FontMetrics` 对象。

## drawText()
绘制文字的时候，需要使用 `Canvas` 的 `drawText(String text, float x, float y, Paint paint)` 方法，其中的 `text` 表示 所需绘制的文字， `paint` 表示 画笔，这很好理解，那么 `x` 和 `y` 又是什么呢？

这里把 `x` 和 `y` 组成的点 `(x, y)` 称作 基点，用于控制文字绘制的基准位置，而 `Paint` 中的 `Align` 对象指定文字对齐方式。 基点 和 Align 共同作用从而成功绘制文字。特别地是：<font color="#ff4081">基点的 Y 坐标（也就是 y 值）其实就是前面提到的 baseline 的 Y 坐标</font>。

比如：上面图中 `baseline` 上的黄点就是基点，可以观察得出其对齐方式 `Align` 就是 `CENTER`。

博文链接：[Android 文字绘制中的 FontMetrics 和 drawText()](http://tianma.pro/post/3678283086/)

## 参考
- [Meaning of top, ascent, baseline, descent, bottom, and leading in Android's FontMetrics
](https://stackoverflow.com/questions/27631736/meaning-of-top-ascent-baseline-descent-bottom-and-leading-in-androids-font)
- [自定义控件之绘图篇（ 五）：drawText()详解](http://blog.csdn.net/harvic880925/article/details/50423762)