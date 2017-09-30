package popfisher.androiddrawclassic.canvasdraw.text;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * 文本绘制
 */

public class TextDrawHelper {

    private Paint mPaint;

    private void initData() {
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        }
    }

    public void draw(Canvas canvas, int hostWidth, int hostHeight) {
        initData();

        mPaint.setTextSize(40);
        mPaint.setColor(Color.BLACK);
        mPaint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));
        canvas.drawText("Android", 0, 100, mPaint);

        mPaint.setTextSize(30);
        canvas.drawText("已关闭", 0, 150, mPaint);
    }

}
