package popfisher.androiddrawclassic.canvasdraw.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by popfisher on 2017/12/1.
 */

public class DrawPathView extends View {
    private Paint mPaint;
    private Path mPath;

    private float mLineStrokeSize       = 10;       // 线边框大小
    private float mDashLineStrokeSize   = 10;       // 虚线边框大小
    private float mDashLineLength       = 20;       // 一小段虚线的长度
    private float mDashWidth            = 20;       // 虚线中间间隔的长度

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
        mPaint.setStrokeWidth(mLineStrokeSize);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath.moveTo(0, getHeight() / 2);
        mPath.lineTo(getWidth(), getHeight() /2);
        canvas.drawPath(mPath, mPaint);
    }
}
