package popfisher.androiddrawclassic.canvasdraw.triangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by popfisher on 2017/12/1.
 */

public class DrawTriangleView extends View {
    private Paint mPaint;
    private Path mPath;
    private RectF mTargetRectF;
    private float mLineStrokeSize = 0;         // 线边框大小

    public DrawTriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(0xffff0000);
            mPath = new Path();
            mTargetRectF = new RectF();
        }
        mPaint.setStrokeWidth(mLineStrokeSize);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mTargetRectF.set(10, 50, 110, 150);
        drawLsoscelesTriangle(canvas, mTargetRectF, ARROW_UP);

        mTargetRectF.offset(150, 0);
        drawLsoscelesTriangle(canvas, mTargetRectF, ARROW_DOWN);

        mTargetRectF.offset(150, 0);
        drawLsoscelesTriangle(canvas, mTargetRectF, ARROW_LEFT);

        mTargetRectF.offset(150, 0);
        drawLsoscelesTriangle(canvas, mTargetRectF, ARROW_RIGHT);
    }

    public static final int ARROW_UP = 1;
    public static final int ARROW_DOWN = 2;
    public static final int ARROW_LEFT = 3;
    public static final int ARROW_RIGHT = 4;

    private void drawLsoscelesTriangle(Canvas canvas, RectF targetRectF, int arrowOrientation) {
        mPath.reset();
        // 用Path画一个等腰三角形箭头，为了方便立即，画Path采用顺时针的进行
        // 箭头向上
        switch (arrowOrientation) {
            case ARROW_UP:
                mPath.moveTo(targetRectF.centerX(), targetRectF.top);
                mPath.lineTo(targetRectF.right, targetRectF.bottom);
                mPath.lineTo(targetRectF.left, targetRectF.bottom);
                break;
            case ARROW_DOWN:
                mPath.moveTo(targetRectF.centerX(), targetRectF.bottom);
                mPath.lineTo(targetRectF.left, targetRectF.top);
                mPath.lineTo(targetRectF.right, targetRectF.top);
                break;
            case ARROW_LEFT:
                mPath.moveTo(targetRectF.left, targetRectF.centerY());
                mPath.lineTo(targetRectF.right, targetRectF.top);
                mPath.lineTo(targetRectF.right, targetRectF.bottom);
                break;
            case ARROW_RIGHT:
                mPath.moveTo(targetRectF.right, targetRectF.centerY());
                mPath.lineTo(targetRectF.left, targetRectF.bottom);
                mPath.lineTo(targetRectF.left, targetRectF.top);
                break;
        }
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}
