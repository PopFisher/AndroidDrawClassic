package popfisher.androiddrawclassic.canvasdraw.text;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by popfisher on 2017/9/26.
 */

public class DrawTextView extends View {

    private TextDrawHelper mTextDrawHelper;

    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTextDrawHelper = new TextDrawHelper();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mTextDrawHelper.draw(canvas, getWidth(), getHeight());
    }
}
