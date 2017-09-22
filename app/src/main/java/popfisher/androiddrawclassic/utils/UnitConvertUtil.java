package popfisher.androiddrawclassic.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by popfisher on 2017/9/22.
 */

public class UnitConvertUtil {

    public static float dp2px(Context context, float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }
}
