package vn.com.rise.project.iohelper;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import vn.com.rise.project.R;
import vn.com.rise.project.Utils.FileUtils;
import vn.com.rise.project.model.InfoData;

/**
 * Created by hoanguyen on 3/6/16.
 */
public class IOHelper {

    private static final String TAG = IOHelper.class.getSimpleName();

    private static final String SHEET_NAME = "INFO";
    private static final int COLUMN_NUMBER = 0;
    private static final int COLUMN_TITLE = 1;
    private static final int COLUMN_VALUE = 2;
    private static IOHelper _instance;

    private static SimpleDateFormat sSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static IOHelper instance() {
        if (_instance == null) {
            _instance = new IOHelper();
        }
        return _instance;
    }

    public boolean saveExcelFile(Context context, InfoData data) {

        // check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly() || data == null) {
            Log.e(TAG, "Storage not available or read only");
            return false;
        }

        String pathRise = Environment.getExternalStorageDirectory().getPath() + FileUtils.SD_ROOT_PATH + FileUtils.SD_OUTPUT_PATH;
        File folderOutput = new File(pathRise);
        if (!folderOutput.exists()) {
            boolean mkdir = folderOutput.mkdir();
            if (!mkdir) {
                Log.e(TAG, "Storage not available or read only");
                return false;
            }
        }

        File fileOutput = new File(pathRise + "/" + FileUtils.FILE_INFO_SAVED + ".xls");

        FileInputStream lFin = null;
        HSSFWorkbook wb = null;
        HSSFSheet sheetInfo = null;
        FileOutputStream lFout = null;
        POIFSFileSystem lPOIfs = null;
        boolean isAppend = false;

        if (fileOutput.exists()) {
            try {
                lFout = new FileOutputStream(fileOutput, true);
                lFin = new FileInputStream(fileOutput);
//                lPOIfs = new POIFSFileSystem(lFin);
//                wb = new HSSFWorkbook(lPOIfs);
                wb = new HSSFWorkbook(lFin);
                lFout.close();
                sheetInfo = wb.getSheet(SHEET_NAME);
                isAppend = true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            //New Workbook
            wb = new HSSFWorkbook();
            //New Sheet
            sheetInfo = wb.createSheet(SHEET_NAME);
        }

        boolean success = false;

        //Cell style for header row
//        CellStyle cs = wb.createCellStyle();
//        cs.setFillForegroundColor(HSSFColor.LIME.index);
//        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


        int jump = 0;
        Row row;
        Cell c;
        Resources res = context.getResources();

        if (!isAppend) {
            row = sheetInfo.createRow(0);
            c = row.createCell(jump);
            c.setCellValue(res.getString(R.string.number));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_name));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_age));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.english));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_nameFa));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_phoneFa));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_emailFa));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_nameMo));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_phoneMo));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_emailMo));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_address));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_hour));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_discount1));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_discount2));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_discount3));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_total));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.gift));

            c = row.createCell(++jump);
            c.setCellValue(res.getString(R.string.hint_note));

            sheetInfo.setColumnWidth(0, (5 * 500));
            for (int i = 1; i < jump; i++) {
                sheetInfo.setColumnWidth(i, (15 * 500));
            }
        }

        jump = 0;
//        int count = -1;
//        for (Row rowInfo : sheetInfo) {
//            count++;
//            if (rowInfo.getCell(0).getStringCellValue().isEmpty()) {
//                break;
//            }
//        }
        //**** No. *****
        int num = sheetInfo.getLastRowNum() + 1;
        row = sheetInfo.getRow(num);
        if (row == null)
            row = sheetInfo.createRow(num);

        c = row.createCell(jump);
        c.setCellValue(String.valueOf(num));

        //**** Name *****
        c = row.createCell(++jump);
        c.setCellValue(data.getName());

        //**** Age *****
        c = row.createCell(++jump);
        c.setCellValue(data.getAge());

        //**** Study English *****
        c = row.createCell(++jump);
        c.setCellValue(data.isStudyEng() ? res.getString(R.string.yes) : res.getString(R.string.no));

        //**** Father's Name *****
        c = row.createCell(++jump);
        c.setCellValue(data.getNameFa());

        //**** Father's Phone *****
        c = row.createCell(++jump);
        c.setCellValue(data.getPhoneFa());

        //**** Father's Email *****
        c = row.createCell(++jump);
        c.setCellValue(data.getEmailFa());

        //**** Mother's Name *****
        c = row.createCell(++jump);
        c.setCellValue(data.getNameMo());

        //**** Mother's Phone *****
        c = row.createCell(++jump);
        c.setCellValue(data.getPhoneMo());

        //**** Mother's Email *****
        c = row.createCell(++jump);
        c.setCellValue(data.getEmailMo());

        //**** Address *****
        c = row.createCell(++jump);
        c.setCellValue(data.getAddress());

        //**** Hour *****
        c = row.createCell(++jump);
        c.setCellValue(data.getHour());

        //**** Discount 1 *****
        c = row.createCell(++jump);
        c.setCellValue(data.getDiscount());

        //**** Discount 2 *****
        c = row.createCell(++jump);
        c.setCellValue(data.getDiscountTime());

        //**** Discount 3 *****
        c = row.createCell(++jump);
        c.setCellValue(data.getDiscountBro());

        //**** Total *****
        c = row.createCell(++jump);
        c.setCellValue(data.getTotal());

        //**** Gift *****
        c = row.createCell(++jump);
        String value = "";
        if (data.isGiftBackpack())
            value += res.getString(R.string.backpack);

        if (data.isGiftShirt()) {
            if (value.length() > 0)
                value += ",";
            value += res.getString(R.string.shirt);
        }

        if (data.isGiftVoucher()) {
            if (value.length() > 0)
                value += ",";
            value += res.getString(R.string.voucher);
        }

        if (data.getOther().length() > 0) {
            if (value.length() > 0)
                value += ",";
            value += data.getOther();
        }
        c.setCellValue(value);

        //**** Note *****
        c = row.createCell(++jump);
        c.setCellValue(data.getNote());

        try {
            lFout = new FileOutputStream(fileOutput);
            wb.write(lFout);
            lFout.flush();

            Log.w("FileUtils", "Writing file" + fileOutput);
            success = true;
        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + fileOutput, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != lFout)
                    lFout.close();
                if (null != lFin)
                    lFin.close();
            } catch (Exception ex) {
                //nothing
            }
        }
        return success;
    }

    public void readExcelFile(Context context, String filename) {

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e(TAG, "Storage not available or read only");
            return;
        }

        try {
            // Creating Input Stream
            File file = new File(context.getExternalFilesDir(null), filename);
            FileInputStream myInput = new FileInputStream(file);

            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            /** We now need something to iterate through the cells.**/
            Iterator rowIter = mySheet.rowIterator();

            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    Log.d(TAG, "Cell Value: " + myCell.toString());
                    Toast.makeText(context, "cell Value: " + myCell.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCurrTime() {
        return sSimpleDateFormat.format(new Date());
    }

    public static boolean isExternalStorageReadOnly() {
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState());
    }

    public static boolean isExternalStorageAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }


    /////

/*
    ///-------------
    //**** Name *****
    row = sheetInfo.createRow(jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_name));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getName());

    //**** Age *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_age));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getAge());

    //**** Study English *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.english));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.isStudyEng() ? "Yes" : "No");

    //**** Father's Name *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_nameFa));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getNameFa());

    //**** Father's Phone *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_phoneFa));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getPhoneFa());

    //**** Father's Email *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_emailFa));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getEmailFa());

    //**** Mother's Email *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_nameMo));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getNameMo());

    //**** Mother's Phone *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_phoneMo));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getPhoneMo());

    //**** Mother's Email *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_emailMo));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getEmailMo());

    //**** Address *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_address));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getAddress());

    //**** Hour *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_hour));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getHour());

    //**** Money/Hour *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_money_hour));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getMoneyPerHour());

    //**** Discount *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_discount));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getDiscount());

    //**** Discount Time Label*****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_discount1));
    c = row.createCell(COLUMN_VALUE);
    String value2 = data.getDiscountTimeLabel();
    if (value2.toLowerCase().equals(R.string.hint_discount)) {
        value2 = res.getString(R.string.discount_none);
    }
    c.setCellValue(value2);

    //**** Discount Time Value*****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_discount1_value));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getDiscountTime());

    //**** Discount Bro Label*****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_discount2));
    c = row.createCell(COLUMN_VALUE);
    String value22 = data.getDiscountBroLabel();
    if (value22.toLowerCase().equals(R.string.hint_discount)) {
        value22 = res.getString(R.string.discount_none);
    }
    c.setCellValue(value22);

    //**** Discount Bro Value*****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_discount2_value));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getDiscountBro());

    //**** Total *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_total));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getTotal());

    //**** Gift *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.gift));
    c = row.createCell(COLUMN_VALUE);
    String value = "";
    if (data.isGiftBackpack())
    value += res.getString(R.string.backpack);

    if (data.isGiftShirt()) {
        if (value.length() > 0)
            value += ",";
        value += res.getString(R.string.shirt);
    }

    if (data.isGiftVoucher()) {
        if (value.length() > 0)
            value += ",";
        value += res.getString(R.string.voucher);
    }

    if (data.getOther().length() > 0) {
        if (value.length() > 0)
            value += ",";
        value += data.getOther();
    }
    c.setCellValue(value);

    //**** Note *****
    row = sheetInfo.createRow(++jump);
    c = row.createCell(COLUMN_NUMBER);
    c.setCellValue(jump + 1);
    c = row.createCell(COLUMN_TITLE);
    c.setCellValue(res.getString(R.string.hint_note));
    c = row.createCell(COLUMN_VALUE);
    c.setCellValue(data.getNote());
    */
}
