package popfisher.androiddrawclassic.canvasdraw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import popfisher.androiddrawclassic.R;
import popfisher.androiddrawclassic.canvasdraw.line.DrawLineActivity;
import popfisher.androiddrawclassic.canvasdraw.path.DrawPathActivity;
import popfisher.androiddrawclassic.canvasdraw.text.DrawTextActivity;
import popfisher.androiddrawclassic.canvasdraw.triangle.DrawTriangleActivity;
import popfisher.androiddrawclassic.samples.sample1.Sample1Activity;

public class BasicDrawActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_draw);
        initView();
    }

    private void initView() {
        findViewById(R.id.basic_draw_line).setOnClickListener(this);
        findViewById(R.id.basic_draw_path).setOnClickListener(this);
        findViewById(R.id.basic_draw_triangle).setOnClickListener(this);
        findViewById(R.id.basic_draw_text).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.basic_draw_line:
                jumpToActivity(DrawLineActivity.class);
                break;
            case R.id.basic_draw_text:
                jumpToActivity(DrawTextActivity.class);
                break;
            case R.id.basic_draw_path:
                jumpToActivity(DrawPathActivity.class);
                break;
            case R.id.basic_draw_triangle:
                jumpToActivity(DrawTriangleActivity.class);
                break;
        }
    }

    private void jumpToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
