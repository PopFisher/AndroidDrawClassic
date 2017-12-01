package popfisher.androiddrawclassic.canvasdraw.triangle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import popfisher.androiddrawclassic.R;

public class DrawTriangleActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_triangle);
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
}
