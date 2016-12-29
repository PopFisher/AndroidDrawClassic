# Android XML shape 标签使用详解

&emsp;&emsp;我相信，一个android开发者肯定懂得使用xml定义一个Drawable，比如定义一个 rect 或者 circle 作为一个View的背景，但是，也肯定也有人选择使用一张png图（或者是一张.9图）作为View的背景。首先提出几个问题？

**备注：**本文所说的Drawable都特指shape标签定义的Drawable

- shape 标签定义的 Drawable 是哪种类型的 Drawable?
- 使用 Drawable 有什么好处？
- 什么情况下选择使用 Drawable，而不是使用一张图，反之呢？
- shape 标签能定义多少种类型的 Drawable？（这是本文的重点，方便我这种懒惰的程序员直接拷贝代码修改）

下面依次回答上面几个问题
## shape标签定义的Drawable是哪种类型的Drawable?
&emsp;&emsp;shape 标签定义的 Drawable 类型对应 **GradientDrawable**

&emsp;&emsp;这里可能会认为是 ShapeDrawale ，我一开始也是这样认为的，因为我看到官方文档上说 ShapeDrawable 也是使用 shape 标签定义的，可是去看 GradientDrawable 的时候也是同样的解释，简直懵逼了，后面经过代码实际检验，shape 标签定义的 Drawable 能直接强制转换为 GradientDrawable，而不能转换为 ShapeDrawable，这个时候只能认为是 ShapeDrawable 的文档解释有点问题了，可能文档错了吧。

&emsp;&emsp;ShapeDrawable 与 GradientDrawale 确实有很多相似之处，具体情况后续单独写文章来说明，本文不涉及 ShapeDrawable 的其他内容。

## 使用Drawable有什么好处？ ##

- 很方便得到一个矩形，圆，椭圆，圆环，很容易维护和修改
- 很方便实现圆角，渐变（线性渐变，径向渐变，扫描渐变）
- 代替图片作为View的背景，减少apk的体积（减少apk体积最明显最有效的步骤就是去掉图片）
- 大图片耗内存，使用 Drawable 节省内存，Android本身对 Drawable 做了很好的优化（内存优化需要考虑）


## 什么情况下选择使用Drawable，而不是使用一张图，反之呢？ ##
 
- 理论上能用 Drawable 的地方就用 Drawable
- 如果能够通过 shape 标签就能定义的几何图形就能满足需求，就不用图片来表示
- 渐变类型的背景也尽量使用 shape 来实现
- 不规则的，复杂的图形还是只能使用图片，比如要一个表示手机的图标，一个人的头像
- 有些特殊拉升效果需要使用 .9.png 图片（尽可能的小吧，越小越省内存）

## shape标签能定义多少种类型的Drawable？ ##
&emsp;&emsp;**shape 可以定义四种类型的几何图形，由 android:shape 属性指定**

&emsp;&emsp;line      --> 线

&emsp;&emsp;rectangle --> 矩形（圆角矩形）

&emsp;&emsp;oval	  --> 椭圆，圆

&emsp;&emsp;ring      --> 圆环

&emsp;&emsp;**shape 可以定义边框属性**

&emsp;&emsp;有边框，无边框，虚线边框，实线边框

&emsp;&emsp;**shape 可以实现矩形圆角效果**

&emsp;&emsp;可以指定其中一个角或者多个角设置圆角效果

&emsp;&emsp;指定圆角半径设置圆角的大小

&emsp;&emsp;**shape 可以实现三种渐变，由子标签 gradient 实现**

&emsp;&emsp;linear	--> 线性渐变（水平，垂直，对角线三个渐变）

&emsp;&emsp;sweep	--> 扫描渐变（只支持顺时针方向，其实颜色反过来就跟逆时针一样的了）

&emsp;&emsp;radial	--> 径向渐变（由指定的中心点开始向外渐变，指定半径）

&emsp;&emsp;xml 实现只支持三个颜色，startColor，CenterColor，endColor

&emsp;&emsp;更多更详细关于 shape 的介绍请移步  [Android GradientDrawable(shape标签定义) 静态使用和动态使用(圆角，渐变实现)](http://www.cnblogs.com/popfisher/p/5606690.html "Android GradientDrawable(shape标签定义) 静态使用和动态使用")

&emsp;&emsp;**由上面的组合可以定义很多 Drawable，下面依次进行介**绍：

### 线（实线+虚线） ###

&emsp;&emsp;![](/docpic/line.png "实线，虚线")

### 矩形（边框+填充） ###

&emsp;&emsp;![](/docpic/rect.png "边框与填充的组合")

### 圆角矩形 ###

&emsp;&emsp;![](/docpic/round_rect.png "圆角矩形")

### 渐变效果 ###

&emsp;&emsp;![](/docpic/gradient.png "渐变效果")

### 圆（边框+填充+渐变） ###

&emsp;&emsp;![](/docpic/circle.png "圆")

### 椭圆（边框+填充+渐变） ###

&emsp;&emsp;![](/docpic/oval.png "椭圆")

### 圆环（边框+填充+渐变） ###

&emsp;&emsp;![](/docpic/ring.png "圆环")