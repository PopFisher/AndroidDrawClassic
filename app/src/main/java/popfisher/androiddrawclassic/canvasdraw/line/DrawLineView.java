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

    private DrawLineHelper mDrawLineHelper = new DrawLineHelper();

    public DrawLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDrawLineHelper.draw(canvas, getHeight(), getWidth());
    }

}
