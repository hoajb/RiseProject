package vn.com.rise.project.Utils;

import android.util.SparseArray;

/**
 * Created by hoanguyen on 3/2/16.
 */
public class Constants {
    private static SparseArray<String> mapValueClass;

    public static final String WEBSITE_RISE = "http://rise.com.vn/";

    public static SparseArray<String> getMapsValueClass() {
        if (mapValueClass == null) {
            mapValueClass = new SparseArray<>();

            mapValueClass.put(0, "PK");
            mapValueClass.put(1, "K");
            mapValueClass.put(2, "S1");
            mapValueClass.put(3, "S2");
            mapValueClass.put(4, "S3");
            mapValueClass.put(5, "S4");
            mapValueClass.put(6, "S5");
        }

        return mapValueClass;
    }
}
