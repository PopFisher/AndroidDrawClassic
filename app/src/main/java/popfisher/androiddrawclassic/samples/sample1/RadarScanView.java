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

    private RadarScanHelper mRadarScanHelper;

    public RadarScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRadarScanHelper == null) {
            mRadarScanHelper = new RadarScanHelper(this);
        }
        mRadarScanHelper.draw(canvas, getWidth(), getHeight());
    }

    @Override
    public void invalidateView() {
        invalidate();
    }
}
