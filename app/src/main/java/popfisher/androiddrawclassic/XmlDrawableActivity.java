package popfisher.androiddrawclassic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class XmlDrawableActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_drawable_xml);
        initViews();
    }

    private void initViews() {
        findViewById(R.id.drawable_selector_1).setOnClickListener(this);
        findViewById(R.id.drawable_selector_2).setOnClickListener(this);
        ((ImageView) findViewById(R.id.drawable_level_1)).setImageLevel(5);
    }

    @Override
    public void onClick(View v) {

    }
}
