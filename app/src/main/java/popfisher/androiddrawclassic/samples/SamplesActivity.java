package popfisher.androiddrawclassic.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import popfisher.androiddrawclassic.R;
import popfisher.androiddrawclassic.samples.sample1.Sample1Activity;

public class SamplesActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples);
        initView();
    }

    private void initView() {
        findViewById(R.id.sample1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.sample1:
                jumpToActivity(Sample1Activity.class);
                break;
        }
    }

    private void jumpToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
