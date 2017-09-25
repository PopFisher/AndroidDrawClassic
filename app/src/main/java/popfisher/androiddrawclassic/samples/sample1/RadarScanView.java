package popfisher.androiddrawclassic.samples.sample1;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import popfisher.androiddrawclassic.samples.IViewNotifyListener;

/**
 * Created by popfisher on 2017/9/22.
 */

public class RadarScanView extends View implements IViewNotifyListener {

    private RadarScanDrawHelper mRadarScanDrawHelper;

    public RadarScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRadarScanDrawHelper == null) {
            mRadarScanDrawHelper = new RadarScanDrawHelper(this);
        }
        mRadarScanDrawHelper.draw(canvas, getWidth(), getHeight());
    }

    @Override
    public void invalidateView() {
        invalidate();
    }
}
