package popfisher.androiddrawclassic.canvasdraw.line;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by HUAWEI on 2017/12/3.
 */

public class DrawLineHelper {

    private Paint mPaint;
    private float mLineStrokeSize       = 10;       // 线边框大小
    private float mDashLineStrokeSize   = 10;       // 虚线边框大小
    private float mDashLineLength       = 20;       // 一小段虚线的长度
    private float mDashWidth            = 20;       // 虚线中间间隔的长度

    public DrawLineHelper() {
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(0xffff0000);
        }
    }
    
    public void draw(Canvas canvas, int hostHeight, int hostWidth) {
        mPaint.setStrokeWidth(mLineStrokeSize);
        // 画实线
        mPaint.setStyle(Paint.Style.STROKE);
        // 画水平直线
        canvas.drawLine(0, hostHeight / 2, hostWidth,hostHeight / 2, mPaint);
        // 画垂直直线
        canvas.drawLine(hostWidth / 2, 0, hostWidth / 2, hostHeight, mPaint);

        mPaint.setStrokeWidth(mDashLineStrokeSize);

        //在画横向虚线之前需要先把画布向下平移办个线段高度的位置，目的就是为了防止线段只画出一半的高度
        drawHorizontalDashLine(canvas, mDashLineStrokeSize / 2, 0, hostWidth, mDashLineLength, mDashWidth);
        drawHorizontalDashLine(canvas, hostHeight - mDashLineStrokeSize / 2, 0, hostWidth, mDashLineLength, mDashWidth);

        drawVerticalDashLine(canvas, mDashLineStrokeSize / 2, 0,hostHeight, mDashLineLength, mDashWidth);
        drawVerticalDashLine(canvas, hostWidth - mDashLineStrokeSize / 2, 0, hostHeight, mDashLineLength, mDashWidth);
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
