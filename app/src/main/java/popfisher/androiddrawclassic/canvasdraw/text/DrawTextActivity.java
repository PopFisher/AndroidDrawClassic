package popfisher.androiddrawclassic.canvasdraw.text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import popfisher.androiddrawclassic.R;

public class DrawTextActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_text);
        initView();
    }

    private void initView() {
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
        }
    }

    private void jumpToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
