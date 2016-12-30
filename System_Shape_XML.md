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

</br>

&emsp;&emsp;**shape 可以定义边框属性**

&emsp;&emsp;有边框，无边框，虚线边框，实线边框

</br>

&emsp;&emsp;**shape 可以实现矩形圆角效果**

&emsp;&emsp;可以指定其中一个角或者多个角设置圆角效果

&emsp;&emsp;指定圆角半径设置圆角的大小

</br>

&emsp;&emsp;**shape 可以实现三种渐变，由子标签 gradient 实现**

&emsp;&emsp;linear	--> 线性渐变（水平，垂直，对角线三个渐变）

&emsp;&emsp;sweep	--> 扫描渐变（只支持顺时针方向，其实颜色反过来就跟逆时针一样的了）

&emsp;&emsp;radial	--> 径向渐变（由指定的中心点开始向外渐变，指定半径）

&emsp;&emsp;xml 实现只支持三个颜色，startColor，CenterColor，endColor

&emsp;&emsp;更多更详细关于 shape 的介绍请移步  [Android GradientDrawable(shape标签定义) 静态使用和动态使用(圆角，渐变实现)](http://www.cnblogs.com/popfisher/p/5606690.html "Android GradientDrawable(shape标签定义) 静态使用和动态使用")

&emsp;&emsp;**由上面的组合可以定义很多 Drawable，下面依次进行介**绍：

</br>

### 线（实线+虚线） ###

&emsp;&emsp;![](/docpic/line.png "实线，虚线")

**实线：line\_solid.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 实线 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
    	android:shape="line"
    	android:useLevel="true">

    <stroke
        android:width="2dp"
        android:color="#ffff0000" />

	</shape>

**虚线：line\_dashed.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!--虚线
	    设置类型会line
	    需要关闭硬件加速虚线才能绘制出来，布局文件中使用的时候需要设置android:layerType="software"
	    android:width 线宽，布局文件中的View的高度需要比这个值大才可以绘制出来
	    android:dashWidth 每段破折线的长度
	    android:dashGap="5dp"每段破折线之间的间隔-->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="line"
	    android:useLevel="true">
	
	    <stroke
	        android:width="2dp"
	        android:dashGap="5dp"
	        android:dashWidth="10dp"
	        android:color="#ffff0000" />
	
	</shape>


### 矩形（边框+填充） ###

&emsp;&emsp;![](/docpic/rect.png "边框与填充的组合")

**矩形实线边框内部无填充：rect\_solid\_border.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 实线边框 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff0000" />
	
	</shape>

**矩形虚线边框内部无填充：rect\_dashed\_border.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 虚线边框 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff0000"
	        android:dashGap="5dp"
	        android:dashWidth="10dp" />
	
	</shape>

**矩形实线边框-内部填充：rect\_solid\_border\_and\_fill.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 实线边框+内部填充 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff0000" />
	
	    <solid android:color="#ff00ffff" />
	
	</shape>

**矩形虚线边框-内部填充：rect\_dashed\_border\_and\_fill.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 虚线边框+内部填充 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff0000"
	        android:dashGap="5dp"
	        android:dashWidth="10dp" />
	
	    <solid android:color="#ff00ffff" />
	</shape>

### 圆角矩形 ###

&emsp;&emsp;![](/docpic/round_rect.png "圆角矩形")

**圆角矩形-只有边框：rect\_rounded\_border.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形边框圆角 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size android:height="100dp"
	        android:width="100dp"/>
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff0000" />
	
	    <corners android:bottomLeftRadius="2dp"
	        android:bottomRightRadius="2dp"
	        android:topLeftRadius="2dp"
	        android:topRightRadius="2dp" />
	
	</shape>

**圆角矩形-只有内部填充：rect\_rounded\_fill.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆角矩形 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size android:height="100dp"
	        android:width="100dp"/>
	
	    <solid android:color="#8000ff00" />
	
	    <corners android:bottomLeftRadius="2dp"
	        android:bottomRightRadius="2dp"
	        android:topLeftRadius="2dp"
	        android:topRightRadius="2dp" />
	
	</shape>

**圆角矩形-有边框有填充:rect\_rounded\_border\_and\_fill.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形边框+填充 圆角 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size android:height="100dp"
	        android:width="100dp"/>
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff0000" />
	
	    <solid android:color="#8000ff00" />
	
	    <corners android:bottomLeftRadius="2dp"
	        android:bottomRightRadius="2dp"
	        android:topLeftRadius="2dp"
	        android:topRightRadius="2dp" />
	
	</shape>

**圆角矩形-左边圆角为一个半圆弧：rect\_rounded\_left\_arc.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形圆角+左右两边为一个圆弧 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size
	        android:width="50dp"
	        android:height="10dp" />
	
	    <solid android:color="#8000ff00" />
	
	    <!-- 圆角半径是高度的一般就是一个圆弧了 -->
	    <corners
	        android:bottomLeftRadius="20dp"
	        android:topLeftRadius="20dp" />
	
	</shape>

**圆角矩形-左右两边都是半圆弧:rect\_rounded\_left\_right\_arc.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形圆角+左右两边为一个圆弧 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size
	        android:width="50dp"
	        android:height="10dp" />
	
	    <solid android:color="#8000ff00" />
	
	    <!-- 圆角半径是高度的一般就是一个圆弧了 -->
	    <corners android:radius="20dp" />
	
	</shape>

**圆角矩形-左右两边都是半圆弧-带边框：rect\_rounded\_left\_right\_arc\_border.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形圆角+左右两边为一个圆弧 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size
	        android:width="50dp"
	        android:height="10dp" />
	
	    <stroke android:color="#ffff0000"
	        android:width="2dp"/>
	
	    <solid android:color="#8000ff00" />
	
	    <!-- 圆角半径是高度的一般就是一个圆弧了 -->
	    <corners android:radius="20dp" />
	
	</shape>

**圆角矩形-圆：rect\_rounded\_arc.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形圆角+圆出一个圆弧 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size android:height="10dp"
	        android:width="10dp"/>
	
	    <solid android:color="#8000ff00" />
	
	    <corners android:radius="20dp" />
	
	</shape>

**圆角矩形-上下两边半圆弧:rect\_rounded\_top\_bottom\_arc.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形圆角+左右两边为一个圆弧 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size
	        android:width="10dp"
	        android:height="60dp" />
	
	    <solid android:color="#8000ff00" />
	
	    <!-- 圆角半径是高度的一般就是一个圆弧了 -->
	    <corners android:radius="10dp" />
	
	</shape>

### 渐变效果（以矩形为例） ###

&emsp;&emsp;![](/docpic/gradient.png "渐变效果")

**垂直线性渐变：rect\_gradient\_linear\_vertical.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形内部填充-线性垂直渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size
	        android:width="@dimen/shape_size"
	        android:height="@dimen/shape_size" />
	
	    <stroke
	        android:width="1px"
	        android:color="#ffff00ff" />
	
	    <!-- 调整angle实现水平渐变，垂直渐变或者对角渐变 -->
	    <gradient
	        android:angle="-45"
	        android:centerX="0.5"
	        android:centerY="0.4"
	        android:centerColor="#8000ff00"
	        android:endColor="#1000ff00"
	        android:startColor="#ff00ff00"
	        android:type="linear" />
	</shape>

**水平线性渐变:rect\_gradient\_linear\_horizon.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形内部填充-线性水平渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size
	        android:width="@dimen/shape_size"
	        android:height="@dimen/shape_size" />
	
	    <stroke
	        android:width="1px"
	        android:color="#ffff00ff" />
	
	    <!-- 调整angle实现水平渐变，垂直渐变或者对角渐变 -->
	    <gradient
	        android:angle="0"
	        android:centerX="0.5"
	        android:centerY="0.5"
	        android:centerColor="#8000ff00"
	        android:endColor="#ff00ff00"
	        android:startColor="#1000ff00"
	        android:type="linear" />
	</shape>

**对角线线性渐变：rect\_gradient\_linear\_diagonal.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形内部填充-线性对角线渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size
	        android:width="@dimen/shape_size"
	        android:height="@dimen/shape_size" />
	
	    <stroke
	        android:width="1px"
	        android:color="#ffff00ff" />
	
	    <!-- 调整angle实现水平渐变，垂直渐变或者对角渐变 -->
	    <gradient
	        android:angle="45"
	        android:centerX="0.5"
	        android:centerY="0.5"
	        android:centerColor="#8000ff00"
	        android:endColor="#1000ff00"
	        android:startColor="#ff00ff00"
	        android:type="linear" />
	</shape>

**径向渐变：rect\_gradient\_radial.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形内部填充-径向渐变,一般不用在rect上，用到圆或者椭圆上 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	
	    <size
	        android:width="@dimen/shape_size"
	        android:height="@dimen/shape_size" />
	
	    <stroke
	        android:width="1px"
	        android:color="#ffff00ff" />
	
	    <!-- 径向渐变angle无效 -->
	    <gradient
	        android:angle="0"
	        android:centerX="0.5"
	        android:centerY="0.5"
	        android:startColor="#0000ff00"
	        android:endColor="#ff00ff00"
	        android:gradientRadius="40dp"
	        android:type="radial" />
	</shape>

**扫描渐变：rect\_gradient\_sweep.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 矩形内部填充-扫描渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle"
	    android:useLevel="true">
	    <!--如果布局中没有设置View的大小，会size设置的大小为默认值-->
	    <size
	        android:width="20dp"
	        android:height="20dp" />
	
	    <stroke
	        android:width="1px"
	        android:color="#ffff00ff" />
	
	    <!--调整angle不能实现角度变化
	        centerX,centerY是中心点的位置，这里用的是百分比值（0-1）
	        在rect中gradientRadius无效-->
	    <gradient
	        android:angle="0"
	        android:centerX="0.5"
	        android:centerY="0.5"
	        android:startColor="#ff00ff00"
	        android:gradientRadius="20dp"
	        android:type="sweep" />
	</shape>


### 圆（边框+填充+渐变） ###

&emsp;&emsp;![](/docpic/circle.png "圆")

**圆-边框：circle\_border.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆形边框 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true">
	
	    <size
	        android:width="80dp"
	        android:height="80dp" />
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff0000" />
	
	</shape>

**圆-填充：circle\_fill.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆形边框 + 填充 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true">
	
	    <size
	        android:width="80dp"
	        android:height="80dp" />
	
	    <solid android:color="#800000ff" />
	
	</shape>

**圆-边框填充：circle\_border\_and\_fill.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆形边框 + 填充 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true">
	
	    <size
	        android:width="80dp"
	        android:height="80dp" />
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff0000" />
	
	    <solid android:color="#800000ff" />
	
	</shape>

**线性渐变：circle\_gradient\_linear.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆形内部填充-线性渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true">
	
	    <size
	        android:width="@dimen/shape_size"
	        android:height="@dimen/shape_size" />
	
	    <!-- angle调整渐变角度，只能是45的倍数，centerX, centerY是百分百（0-1） -->
	    <gradient
	        android:angle="-90"
	        android:centerX="0.5"
	        android:centerY="0.8"
	        android:centerColor="#80ff0000"
	        android:endColor="#ffff0000"
	        android:startColor="#00ff0000"
	        android:type="linear" />
	
	</shape>

**径向渐变：circle\_gradient\_radial.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆形内部填充-径向渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true">
	
	    <size
	        android:width="40dp"
	        android:height="40dp" />
	
	    <!-- centerX, centerY是百分百（0-1） -->
	    <gradient
	        android:centerX="0.5"
	        android:centerY="0.5"
	        android:startColor="#ffff0000"
	        android:centerColor="#80ff0000"
	        android:endColor="#10ff0000"
	        android:gradientRadius="30dp"
	        android:type="radial" />
	
	</shape>

**扫描渐变：circle\_gradient\_sweep.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆形内部填充-扫描渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true">
	
	    <size
	        android:width="@dimen/shape_size"
	        android:height="@dimen/shape_size" />
	
	    <!-- sweep类型angle,gradientRadius无效，centerX, centerY是百分百（0-1） -->
	    <gradient
	        android:centerX="0.5"
	        android:centerY="0.6"
	        android:startColor="#ffff0000"
	        android:centerColor="#80ff0000"
	        android:endColor="#20ff0000"
	        android:gradientRadius="20dp"
	        android:type="sweep" />
	
	</shape>

### 椭圆（边框+填充+渐变） ###

&emsp;&emsp;![](/docpic/oval.png "椭圆")

**边框：oval\_border.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 椭圆边框 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true">
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff0000" />
	
	</shape>

**填充：oval\_fill.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 椭圆填充-->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true">
	
	    <solid android:color="#800000ff" />
	
	</shape>

**边框+填充：oval\_border\_and\_fill.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 椭圆边框 + 填充-->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true">
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff0000" />
	
	    <solid android:color="#800000ff" />
	
	</shape>

**线性渐变：oval\_gradient\_linear.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 椭圆内部填充-线性渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true" >
	
	    <size
	        android:width="80dp"
	        android:height="60dp" />
	
	    <gradient
	        android:angle="45"
	        android:centerX="0.5"
	        android:centerY="0.7"
	        android:centerColor="#80ff0000"
	        android:endColor="#ffff0000"
	        android:startColor="#00ff0000"
	        android:type="linear" />
	
	</shape>

**径向渐变：oval\_gradient\_radial.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 椭圆内部填充-径向渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true" >
	
	    <size
	        android:width="80dp"
	        android:height="60dp" />
	
	    <gradient
	        android:centerX="0.5"
	        android:centerY="0.5"
	        android:centerColor="#80ff0000"
	        android:endColor="#00ff0000"
	        android:startColor="#ffff0000"
	        android:gradientRadius="40dp"
	        android:type="radial" />
	
	</shape>

**扫描渐变：oval\_gradient\_sweep.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 椭圆内部填充-扫描渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="oval"
	    android:useLevel="true" >
	
	    <size
	        android:width="80dp"
	        android:height="60dp" />
	
	    <gradient
	        android:centerX="0.5"
	        android:centerY="0.5"
	        android:centerColor="#80ff0000"
	        android:endColor="#ffff0000"
	        android:startColor="#00ff0000"
	        android:type="sweep" />
	
	</shape>

### 圆环（边框+填充+渐变） ###

&emsp;&emsp;![](/docpic/ring.png "圆环")

**环内填充：ring\_fill.xml**

	<?xml version="1.0" encoding="utf-8"?><!-- 圆环 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:innerRadiusRatio="4"
	    android:shape="ring"
	    android:thicknessRatio="4"
	    android:useLevel="false">
	    <!--android:useLevel="false"必须是false-->
	    <size
	        android:width="80dp"
	        android:height="80dp" />
	
	    <solid android:color="#80ff0000" />
	
	</shape>

**圆环边框:ring\_border.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆环-仅有边框 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:innerRadius="20dp"
	    android:shape="ring"
	    android:thickness="16dp"
	    android:useLevel="false">
	    <!--android:useLevel="false"必须是false-->
	
	    <size
	        android:width="80dp"
	        android:height="80dp" />
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff00ff" />
	</shape>

**边框+填充：ring\_border\_and\_fill.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆环 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:innerRadius="20dp"
	    android:shape="ring"
	    android:thickness="16dp"
	    android:useLevel="false">
	    <!--android:useLevel="false"必须是false-->
	
	    <size
	        android:width="80dp"
	        android:height="80dp" />
	
	    <solid android:color="#80ff0000" />
	
	    <stroke
	        android:width="2dp"
	        android:color="#ffff00ff" />
	</shape>


**线性渐变：ring\_gradient\_linear.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆环-线性渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="ring"
	    android:innerRadius="10dp"
	    android:thickness="30dp"
	    android:useLevel="false">
	    <!--android:useLevel="false"必须是false-->
	
	    <size
	        android:width="80dp"
	        android:height="80dp" />
	
	    <gradient
	        android:angle="45"
	        android:centerColor="#80ff0000"
	        android:endColor="#ffff0000"
	        android:startColor="#00ff0000"
	        android:type="linear" />
	
	</shape>

**径向渐变：ring\_gradient\_radial.xml**
	
	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆环-径向渐变渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="ring"
	    android:innerRadius="10dp"
	    android:thickness="30dp"
	    android:useLevel="false">
	    <!--android:useLevel="false"必须是false-->
	
	    <size
	        android:width="80dp"
	        android:height="80dp" />
	
	    <!--设置径向渐变半径，渐变从圆心开始-->
	    <gradient
	        android:centerX="0.5"
	        android:centerY="0.5"
	        android:centerColor="#80ff0000"
	        android:endColor="#00ff0000"
	        android:startColor="#ffff0000"
	        android:gradientRadius="40dp"
	        android:type="radial" />
	
	</shape>

**扫描渐变:ring\_gradient\_sweep.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<!-- 圆环-线性渐变 -->
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="ring"
	    android:innerRadius="10dp"
	    android:thickness="30dp"
	    android:useLevel="false">
	    <!--android:useLevel="false"必须是false-->
	
	    <size
	        android:width="80dp"
	        android:height="80dp" />
	
	    <!--扫描渐变shape不能设置角度-->
	    <gradient
	        android:centerColor="#80ff0000"
	        android:endColor="#ffff0000"
	        android:startColor="#00ff0000"
	        android:type="sweep" />
	
	</shape>


## 总结 ##

&emsp;&emsp;熟练掌握好 shape 标签的使用，能够构建出多种多样的 Drawable，这些 Drawable 可以直接作为背景设置给 View，代码通过 R.drawable 方式使用，xml 文件通过 @drawabl/nmae 使用。跟图片的使用一模一样，关键是使用系统 Drawable 可以减少 apk 的体积，减少内存占用等好处，所以，不要觉得简单就不系统学习，越简单的东西越要熟练掌握。后面还会陆续出一些 Drawable 的文章，比如除了shape标签定义的 Drawable，其他还有哪些 xml 标签能定义 Drawable？ Drawable怎么通过代码的方式创建并使用？