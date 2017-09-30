package popfisher.androiddrawclassic.samples.sample1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;

import popfisher.androiddrawclassic.AppContext;
import popfisher.androiddrawclassic.R;
import popfisher.androiddrawclassic.samples.IViewNotifyListener;
import popfisher.androiddrawclassic.utils.UnitConvertUtil;

/**
 * 实现一个雷达扫描控件，分两层绘制
 * bottom层：绘制盘面，静止不动的部分
 * top层：绘制旋转的部分，一个扇形在旋转
 */

public class RadarScanDrawHelper {

    private Context mAppContext = AppContext.getInstance().getAppContext();

    private int mHostWidth = 0;
    private int mHostHeight = 0;
    private int mCenterX = 0;
    private int mCenterY = 0;

    private Paint mPaint;
    private boolean isHasInit = false;
    private static final int CIRCLE1_RADIUS = 67;   // dp
    private static final int CIRCLE2_RADIUS = 89;   // dp
    private static final int CIRCLE3_RADIUS = 113;  // dp
    private static final int STROKE_WIDTH = 1;  // dp
    private static final float SECTOR_WIDTH = 1.5f;  // dp
    private static final float START_DEGREES = -90;

    private float mCircle1Radius = 0.0f;
    private float mCircle2Radius = 0.0f;
    private float mCircle3Radius = 0.0f;
    private float mStrokeWidth = 0.0f;
    private float mSectorWidth = 0.0f;

    private int mCircleBorderColor = 0x80ffffff;
    private int mCircleDiameterColor = 0x33ffffff;

    private IViewNotifyListener mViewNotifyListener;

    public RadarScanDrawHelper(IViewNotifyListener viewNotifyListener) {
        mViewNotifyListener = viewNotifyListener;
    }

    public void draw(Canvas canvas, int hostWidth, int hostHeight) {
        initParams(hostWidth, hostHeight);
        drawTarget(canvas);
    }

    private void initParams(int hostWidth, int hostHeight) {
        if (mHostWidth == hostWidth && mHostHeight == hostHeight) {
            return;
        }
        mHostWidth = hostWidth;
        mHostHeight = hostHeight;
        mCenterX = hostWidth / 2;
        mCenterY = hostHeight / 2;
        if (isHasInit) {
            return;
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mCircle1Radius = UnitConvertUtil.dp2px(mAppContext, CIRCLE1_RADIUS);
        mCircle2Radius = UnitConvertUtil.dp2px(mAppContext, CIRCLE2_RADIUS);
        mCircle3Radius = UnitConvertUtil.dp2px(mAppContext, CIRCLE3_RADIUS);
        mStrokeWidth = UnitConvertUtil.dp2px(mAppContext, STROKE_WIDTH);
        mSectorWidth = UnitConvertUtil.dp2px(mAppContext, SECTOR_WIDTH);
    }

    private void drawTarget(Canvas canvas) {
        drawBottomLayer(canvas);
        drawTopLayer(canvas);
        if (mViewNotifyListener != null) {
            mRotateAngle += 2;
            mViewNotifyListener.invalidateView();
        }
    }

    private void drawBottomLayer(Canvas canvas) {
        mPaint.setShader(null);
        mPaint.setColor(mCircleBorderColor);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mCenterX, mCenterY, mCircle1Radius, mPaint);
        canvas.drawCircle(mCenterX, mCenterY, mCircle2Radius, mPaint);
        canvas.drawCircle(mCenterX, mCenterY, mCircle3Radius, mPaint);

        mPaint.setColor(mCircleDiameterColor);
        canvas.drawLine(mCenterX - mCircle3Radius, mCenterY, mCenterX + mCircle3Radius, mCenterY, mPaint);
        canvas.drawLine(mCenterX, mCenterY - mCircle3Radius, mCenterX, mCenterY + mCircle3Radius, mPaint);

        drawLogoIcon(canvas);
    }

    private Bitmap mLogoBitmap;
    private static final int mLogoResId = R.drawable.ic_launcher;

    private void drawLogoIcon(Canvas canvas) {
        mPaint.setAlpha(255);   // 这里透明度要设置为不透明，因为透明度会被setColor修改掉
        if (mLogoBitmap == null) {
            mLogoBitmap = BitmapFactory.decodeResource(AppContext.getInstance().getResources(), mLogoResId);
        }
        canvas.drawBitmap(mLogoBitmap, mCenterX - mLogoBitmap.getWidth() / 2, mCenterY - mLogoBitmap.getHeight() / 2, mPaint);
    }

    private SweepGradient mSweepGradient;
    private Matrix mMatrix = new Matrix();
    private int mColors[] = {0x00000000, 0x40ffffff};
    private float mPositions[] = {0.75f, 1.0f};

    private void drawTopLayer(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        if (mSweepGradient == null) {
            mSweepGradient = new SweepGradient(mCenterX, mCenterY, mColors, mPositions);
        }
        mMatrix.setRotate(START_DEGREES + mRotateAngle, mCenterX, mCenterY);
        mSweepGradient.setLocalMatrix(mMatrix);
        mPaint.setShader(mSweepGradient);
        canvas.drawCircle(mCenterX, mCenterY, mCircle3Radius, mPaint);
        drawLine(canvas);
    }

    private final static int[] LINE_COLORS = {0x00ffffff, 0xffffffff};     // 圆心到头部圆球的线颜色

    private void drawLine(Canvas canvas) {
        float[] positions = calculateCirclePtXY();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mSectorWidth);
        mPaint.setShader(new LinearGradient(mCenterX, mCenterY, positions[0], positions[1], LINE_COLORS, null, Shader.TileMode.CLAMP));
        canvas.drawLine(mCenterX, mCenterY, positions[0], positions[1], mPaint);
    }

    private float mRotateAngle = 0.0f;

    /**
     * 计算圆上任一点的坐标，起始点从顶部开始，按顺时针方向旋转
     * @return
     */
    private float[] calculateCirclePtXY() {
        double circlePointRadian = Math.PI / 180f * mRotateAngle;                                    // 角度转弧度
        float circleX = (float) (mCenterX + mCircle3Radius * Math.sin(circlePointRadian));
        float circleY = (float) (mCenterY - mCircle3Radius * Math.cos(circlePointRadian));           // 手机屏幕中，y轴是从上到下，所这里是减
        float[] positions = {circleX, circleY};
        return positions;
    }
}
