package popfisher.androiddrawclassic.circle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import popfisher.androiddrawclassic.utils.BitmapUtil;

/**
 * Created by popfisher on 2016/12/5.
 * 绘制带渐变填充颜色的椭圆（圆）
 */

public class GradientCircleView extends View {

    private Paint mPaintGradientCircle;
    private RectF mRectF;
    private LinearGradient mLinearGradient;
    private RadialGradient mRadialGradient;

    private static final int mStrokeColor = 0x80e4e4e4;

    private int mFillColors[] = new int[]{ 0x73ffffff, 0x5effffff, 0x5effffff, 0x80ffffff, 0x99ffffff, 0xe6ffffff };
    private int mFillColors2[] = new int[]{ 0xff000000, 0x10000000,0x0c000000};


    public GradientCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaintGradientCircle == null) {
            init();
        }
        // 同样的颜色数组mFillColors，下面两种方式的渐变效果不一样
        drawDirect(canvas);
//        drawWithBitmap(canvas);
    }

    /**
     * 直接绘制，推荐使用这种方式，节约内存，不用缓存bitmap对象
     * @param canvas
     */
    private void drawDirect(Canvas canvas) {
        mPaintGradientCircle.setStyle(Paint.Style.STROKE);
        mPaintGradientCircle.setShader(null);

        mPaintGradientCircle.setStrokeWidth(7);
        mPaintGradientCircle.setShader(mRadialGradient);
        canvas.drawCircle(mRectF.centerX(), mRectF.centerY() + 3, mRectF.width() / 2 + 3, mPaintGradientCircle);


        mPaintGradientCircle.setColor(mStrokeColor);
        mPaintGradientCircle.setStrokeWidth(2);
        mPaintGradientCircle.setShader(null);
        canvas.drawCircle(mRectF.centerX(), mRectF.centerY(), mRectF.width() / 2, mPaintGradientCircle);

        mPaintGradientCircle.setColor(mStrokeColor);
        mPaintGradientCircle.setStyle(Paint.Style.FILL);
        mPaintGradientCircle.setShader(mLinearGradient);

        // 画圆可以用下面的方法
        canvas.drawCircle(mRectF.centerX(), mRectF.centerY(), mRectF.width() / 2, mPaintGradientCircle);
    }

    /**
     * 先创建出一个渐变的Bitmap，然后绘制bitmap，除非特殊场景一定要使用到Bitmap对象，否则不用这种方式
     * @param canvas
     */
    private void drawWithBitmap(Canvas canvas) {
        canvas.drawBitmap(createGradientBitmap((int) mRectF.width(), (int) mRectF.height()), null, mRectF, mPaintGradientCircle);
    }

    private void init() {
        mPaintGradientCircle = new Paint();
        mPaintGradientCircle.setAntiAlias(true);
        final int padding = 50;
        mRectF = new RectF(padding, padding, getWidth() - padding, getHeight() - padding);
        mLinearGradient = new LinearGradient(mRectF.centerX(), mRectF.top, mRectF.centerX(), mRectF.bottom, mFillColors, null, Shader.TileMode.MIRROR);
        mRadialGradient = new RadialGradient(mRectF.centerX(), mRectF.centerY(), mRectF.width() / 2 + 6, mFillColors2, null, Shader.TileMode.MIRROR);
    }

    private Bitmap createGradientBitmap(int buttonBmpWidth, int buttonBmpHeight) {
        return BitmapUtil.drawableToBitmap(createGradientDrawable(), buttonBmpWidth, buttonBmpHeight);
    }

    /**
     * 生成的Drawable可以直接设置为View的背景
     * @return
     */
    private GradientDrawable createGradientDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, mFillColors);
        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setUseLevel(false);
        gradientDrawable.setStroke(1, mStrokeColor);
        return gradientDrawable;
    }
}
