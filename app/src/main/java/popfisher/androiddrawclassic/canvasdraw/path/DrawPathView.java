package popfisher.androiddrawclassic.canvasdraw.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by popfisher on 2017/12/1.
 */

public class DrawPathView extends View {
    private Paint mPaint;
    private Path mPath;
    private float mPhase;

    private float mLineStrokeSize       = 10;         // 线边框大小
    private float mDashLineStrokeSize   = 10;     // 虚线边框大小
    private float mDashLineLength       = 20;         // 一小段虚线的长度
    private float mDashWidth            = 20;              // 虚线中间间隔的长度

    public DrawPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(0xffff0000);
            mPath = new Path();
        }
        mPaint.setPathEffect(null);
        mPaint.setStrokeWidth(mLineStrokeSize);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.reset();
        // 用Path画一条直线
        mPath.moveTo(0, 100);
        mPath.lineTo(getWidth(), 100);
        canvas.drawPath(mPath, mPaint);

        // 用Path两天不连续条直线
        mPath.moveTo(0, 200);
        mPath.lineTo(getWidth() * 1 / 3, 200);
        mPath.moveTo(getWidth() * 2 / 3, 200);
        mPath.lineTo(getWidth(), 200);
        canvas.drawPath(mPath, mPaint);

        // 用Path画一条虚线
        mPaint.setStrokeWidth(mDashLineStrokeSize);
        mPath.reset();
        mPath.moveTo(0, 300);
        mPath.lineTo(getWidth(), 300);
        mPaint.setPathEffect(new DashPathEffect(new float[]{mDashLineLength, mDashWidth}, 0));
        canvas.drawPath(mPath, mPaint);

        // 用Path画一条虚线，自动水平滚动
        mPaint.setStrokeWidth(mDashLineStrokeSize);
        mPath.reset();
        mPath.moveTo(0, 400);
        mPath.lineTo(getWidth(), 400);
        mPaint.setPathEffect(new DashPathEffect(new float[]{mDashLineLength, mDashWidth}, mPhase++));
        canvas.drawPath(mPath, mPaint);
        invalidate();
    }
}
