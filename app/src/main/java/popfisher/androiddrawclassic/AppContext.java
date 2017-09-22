package popfisher.androiddrawclassic;

import android.content.Context;

/**
 * 提供获取ApplicationContext的公共方法
 * 以及跟App相关的全局数据
 */

public class AppContext {

    private Context mAppContext;

    private AppContext() {
    }

    public static AppContext getInstance() {
        return ContextCreator.appContext;
    }

    public static class ContextCreator {
        public static final AppContext appContext = new AppContext();
    }

    public void setAppContext(Context context) {
        mAppContext = context;
    }

    public Context getAppContext() {
        return mAppContext;
    }
}
