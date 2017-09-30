package popfisher.androiddrawclassic.samples.beziercurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by popfisher on 2016/12/2.
 * 二阶贝塞尔曲线
 */

public class SecondOrderBezierCurve extends View {

    private Paint mPaint;
    private Path mPath;

    private Rect mBottomRect;
    private Point mStartPoint;
    private Point mEndPoint;
    private Point mControlPoint;

    public SecondOrderBezierCurve(Context context) {
        super(context);
        init();
    }

    public SecondOrderBezierCurve(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setBackgroundColor(0xffffffff);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mPaint == null) {
            mPaint = new Paint();
            mPath = new Path();
            final int topMargin = getHeight() / 3;
            mStartPoint = new Point(0, topMargin);
            mEndPoint = new Point(getWidth(), topMargin);
            mControlPoint = new Point(getWidth() / 2, -topMargin);
            mPaint.setAntiAlias(true);
            mBottomRect = new Rect(0, topMargin, getWidth(), getHeight());
            mPaint.setColor(0xffade5ff); //画笔颜色
            mPaint.setStrokeWidth(0); //画笔宽度
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
        mPath.reset();
        //起点
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        //mPath
        mPath.quadTo(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y);
        //画path
        canvas.drawPath(mPath, mPaint);
        canvas.drawRect(mBottomRect, mPaint);
    }

}
