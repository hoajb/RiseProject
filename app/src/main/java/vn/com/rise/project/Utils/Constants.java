package vn.com.rise.project.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import vn.com.rise.project.R;

/**
 * Created by hoanguyen on 3/2/16.
 */
public class Constants {
    private static SparseArray<String> mapValueClass;
    private static SparseArray<String> mapValueMainClass;
    private static SparseArray<String> mapValueGeneralClass;

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

    public static SparseArray<String> getMapsValueMainClass(Context pContext) {
        if (mapValueMainClass == null) {
            mapValueMainClass = new SparseArray<>();

            Resources resources = pContext.getResources();
            mapValueMainClass.put(0, resources.getString(R.string.class_main_detail_image));
            mapValueMainClass.put(1, resources.getString(R.string.class_main_detail_video));
            mapValueMainClass.put(2, resources.getString(R.string.class_main_detail_info));
        }

        return mapValueMainClass;
    }

    public static SparseArray<String> getMapsValueGeneralClass(Context pContext) {
        if (mapValueGeneralClass == null) {
            mapValueGeneralClass = new SparseArray<>();

            Resources resources = pContext.getResources();
            mapValueGeneralClass.put(0, resources.getString(R.string.class_general_program_tree));
            mapValueGeneralClass.put(1, resources.getString(R.string.class_general_schedule));
            mapValueGeneralClass.put(2, resources.getString(R.string.class_general_price));
            mapValueGeneralClass.put(3, resources.getString(R.string.class_general_form_price));
            mapValueGeneralClass.put(4, resources.getString(R.string.class_general_website));
        }

        return mapValueGeneralClass;
    }

    private static List<Integer> listAge;
    private static List<String> listDiscountTime;
    private static List<String> listDiscountBro;

    public static List<Integer> getListAge() {
        if (listAge == null) {
            listAge = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                listAge.add(i);
            }
        }
        return listAge;
    }

    public static List<String> getListDiscountTime(Context context) {
        if (listDiscountTime == null) {
            listDiscountTime = new ArrayList<>();
            Resources res = context.getResources();
            listDiscountTime.add(res.getString(R.string.discount_none));
            listDiscountTime.add(res.getString(R.string.discount_whole_year));
            listDiscountTime.add(res.getString(R.string.discount_half_year));
        }
        return listDiscountTime;
    }

    public static List<String> getListDiscountBro(Context context) {
        if (listDiscountBro == null) {
            listDiscountBro = new ArrayList<>();
            Resources res = context.getResources();
            listDiscountBro.add(res.getString(R.string.discount_none));
            listDiscountBro.add(res.getString(R.string.discount_bro));
            listDiscountBro.add(res.getString(R.string.discount_other));
        }
        return listDiscountBro;
    }


}
