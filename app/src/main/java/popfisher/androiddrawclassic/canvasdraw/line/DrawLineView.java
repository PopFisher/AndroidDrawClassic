package popfisher.androiddrawclassic.canvasdraw.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by popfisher on 2017/12/1.
 */

public class DrawLineView extends View {
    private Paint mPaint;
    private float mLineStrokeSize       = 10;       // 线边框大小
    private float mDashLineStrokeSize   = 10;       // 虚线边框大小
    private float mDashLineLength       = 20;       // 一小段虚线的长度
    private float mDashWidth            = 20;       // 虚线中间间隔的长度

    public DrawLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

            mPaint.setColor(0xffff0000);
        }
        mPaint.setStrokeWidth(mLineStrokeSize);
        // 画实线
        mPaint.setStyle(Paint.Style.STROKE);
        // 画水平直线
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mPaint);
        // 画垂直直线
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mPaint);

        mPaint.setStrokeWidth(mDashLineStrokeSize);

        //在画横向虚线之前需要先把画布向下平移办个线段高度的位置，目的就是为了防止线段只画出一半的高度
        drawHorizontalDashLine(canvas, mDashLineStrokeSize / 2, 0, getWidth(), mDashLineLength, mDashWidth);
        drawHorizontalDashLine(canvas, getHeight() - mDashLineStrokeSize / 2, 0, getWidth(), mDashLineLength, mDashWidth);

        drawVerticalDashLine(canvas, mDashLineStrokeSize / 2, 0, getHeight(), mDashLineLength, mDashWidth);
        drawVerticalDashLine(canvas, getWidth() - mDashLineStrokeSize / 2, 0, getHeight(), mDashLineLength, mDashWidth);
    }

    /**
     * 画水平方向虚线
     * @param canvas
     */
    public void drawHorizontalDashLine(Canvas canvas, float yPos, float dashStartX, float dashStopX, float dashLineLength, float dashWidth){
        float totalWidth = 0;
        float dashTotalLength = dashStopX - dashStartX;
        canvas.save();
        float[] pts = {0, 0, dashLineLength, 0};
        canvas.translate(dashStartX, yPos);
        while (totalWidth <= dashTotalLength) {
            canvas.drawLines(pts, mPaint);
            canvas.translate(dashLineLength + dashWidth, 0);
            totalWidth += dashLineLength + dashWidth;
        }
        canvas.restore();
    }

    /**
     * 画竖直方向虚线
     * @param canvas
     */
    public void drawVerticalDashLine(Canvas canvas, float xPos, float dashStartY, float dashStopY, float dashLineLength, float dashWidth){
        float totalWidth = 0;
        float dashTotalLength = dashStopY - dashStartY;
        canvas.save();
        float[] pts = {0, 0, 0, dashLineLength};
        canvas.translate(xPos, dashStartY);
        while (totalWidth <= dashTotalLength) {
            canvas.drawLines(pts, mPaint);
            canvas.translate(0, dashLineLength + dashWidth);
            totalWidth += dashLineLength + dashWidth;
        }
        canvas.restore();
    }
}
