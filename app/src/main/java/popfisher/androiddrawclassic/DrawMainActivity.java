package popfisher.androiddrawclassic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DrawMainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.android_sys_shape_xml).setOnClickListener(this);
        findViewById(R.id.android_sys_drawable_xml).setOnClickListener(this);
        findViewById(R.id.second_order_bezier_curve).setOnClickListener(this);
        findViewById(R.id.gradient_fill_circle).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {
            case R.id.android_sys_shape_xml:
                jumpToActivity(XmlShapeActivity.class);
                break;
            case R.id.android_sys_drawable_xml:
                jumpToActivity(XmlDrawableActivity.class);
                break;
            case R.id.second_order_bezier_curve:
                jumpToActivity(BezierCurveActivity.class);
                break;
            case R.id.gradient_fill_circle:
                jumpToActivity(GradientCircleActivity.class);
                break;
        }
    }

    private void jumpToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
