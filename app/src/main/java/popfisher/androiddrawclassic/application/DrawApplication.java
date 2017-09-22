package popfisher.androiddrawclassic.application;

import android.app.Application;
import android.content.Context;

import popfisher.androiddrawclassic.AppContext;

/**
 * 自定义Application
 */

public class DrawApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.getInstance().setAppContext(getApplicationContext());
    }
}
