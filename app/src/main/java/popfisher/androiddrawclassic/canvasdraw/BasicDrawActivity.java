package popfisher.androiddrawclassic.canvasdraw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import popfisher.androiddrawclassic.R;
import popfisher.androiddrawclassic.canvasdraw.text.DrawTextActivity;
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
        findViewById(R.id.basic_draw_text).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.basic_draw_line:
                jumpToActivity(DrawTextActivity.class);
                break;
            case R.id.basic_draw_text:
                jumpToActivity(DrawTextActivity.class);
                break;
        }
    }

    private void jumpToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
