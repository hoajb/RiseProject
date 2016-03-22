package vn.com.rise.project.Utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by hoanguyen on 12/27/15.
 */
public class FileUtils {

    private static final String TAG = "ImageGalleryFragment";
    public static final String SD_ROOT_PATH = "/RISEVietnam";
    public static final String SD_OUTPUT_PATH = "/InfoOutput";
    public static final String FILE_PRICE_PER_SIZE = "RiseFixedInfo";
    public static final String FILE_INFO_SAVED = "RiseInfo";
    public static final String LINK_WEB_ABOUT = "http://rise.com.vn/vn/gioi-thieu";

    public static class AudioFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3"));
        }
    }

    public static class VideoFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {

            return (name.toLowerCase().endsWith(".mp4"));
        }
    }

    public static class ImageFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            String nameLowerCase = name.toLowerCase();
            return (nameLowerCase.endsWith(".jpg")
                    || nameLowerCase.endsWith(".jpeg")
                    || nameLowerCase.endsWith(".png"));
        }
    }

    public static class DocumentFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            String nameLowerCase = name.toLowerCase();
            return (nameLowerCase.endsWith(".pdf")
                    || nameLowerCase.endsWith(".txt")

                    || nameLowerCase.endsWith(".doc")
                    || nameLowerCase.endsWith(".docx"))

                    || nameLowerCase.endsWith(".xls")
                    || nameLowerCase.endsWith(".xlsx")

                    || nameLowerCase.endsWith(".ppt")
                    || nameLowerCase.endsWith(".pptx")
                    ;
        }
    }

    public static String getMIMETypeFromPath(String path) {
        String extension = getExtension(path).toLowerCase();
        if (TextUtils.isEmpty(extension)) return "";

        if (extension.equals("pdf")) {
            return "application/pdf";
        } else if (extension.equals("txt")) {
            return "text/plain";
        } else if (extension.equals("doc")) {
            return "application/msword";
        } else if (extension.equals("docx")) {
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        } else if (extension.equals("xls")) {
            return "application/vnd.ms-excel";
        } else if (extension.equals("xlsx")) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        } else if (extension.equals("ppt")) {
            return "application/mspowerpoint";
        } else if (extension.equals("pptx")) {
            return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
        }


        return "";
    }

    public static String getExtension(String fileName) {
        if (TextUtils.isEmpty(fileName)) return "";

        int lastDot = fileName.lastIndexOf('.');
        if (lastDot < 0) return "";

        return fileName.substring(lastDot + 1, fileName.length()).toLowerCase();
    }

    public static String getFileNameFromPath(String filePath) {
        if (TextUtils.isEmpty(filePath)) return "";

        int lastDot = filePath.lastIndexOf('/');
        if (lastDot < 0) return "";

        return filePath.substring(lastDot + 1, filePath.length()).toLowerCase();
    }

    public static ArrayList<String> getImagePaths(String mainPath, FilenameFilter pFilter) {
        ArrayList<String> listPaths = new ArrayList<>();
        File home = new File(mainPath);
        File[] files = home.listFiles(pFilter);
        if (files != null && files.length > 0) {
            for (File file : files) {
                listPaths.add(file.getPath());
            }
        }
        Log.d(TAG, "getImagePaths: listPaths.size(): " + listPaths.size());
        return listPaths;
    }

    /**
     * not use for now
     *
     * @param context
     * @param data
     */
    public static void writeToFile_FixedInfo(Context context, String data) {
        writeToFile(context, Environment.getExternalStorageDirectory().getPath() + FileUtils.SD_ROOT_PATH + "/" +
                FILE_PRICE_PER_SIZE, data);
    }

    public static void writeToFile(Context context, String fileName, String data) {
        try {
            String rootPath = Environment.getExternalStorageDirectory().getPath() + FileUtils.SD_ROOT_PATH;
            File myFile = new File(rootPath + "/" + fileName + ".txt");
            if (!myFile.exists()) {
                boolean newFile = myFile.createNewFile();
                if (!newFile) {
                    Log.e("Exception", "File write failed: cannot createFile");
                    return;
                }
            }
            FileOutputStream fOut = new FileOutputStream(myFile, true);
            OutputStreamWriter myOutWriter =
                    new OutputStreamWriter(fOut);
            myOutWriter.append("\n--------------------------");
            myOutWriter.append(data);
            myOutWriter.close();
            fOut.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile_FixedInfo(Context pContext) {
        return readFromFile(pContext, Environment.getExternalStorageDirectory().getPath() + FileUtils.SD_ROOT_PATH + "/" +
                FILE_PRICE_PER_SIZE);
    }

    public static String readFromFile(Context pContext, String nameFile) {

        String ret = "";

        try {
            String fullFilePath = nameFile + ".txt";
            File readFile = new File(fullFilePath);
            if (readFile.exists()) {
                FileReader fileReader = new FileReader(readFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                ret = stringBuilder.toString();
            }

        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
