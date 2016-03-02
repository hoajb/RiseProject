package vn.com.rise.project.Utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by hoanguyen on 12/27/15.
 */
public class FileUtils {

    private static final String TAG = "ImageGalleryFragment";
    public static final String SD_ROOT_PATH = "/RiseFolder";
    public static final String FILE_NAME_SAVE = "Rise_Info";
    public static final String LINK_WEB_ABOUT = "http://rise.com.vn/vn/gioi-thieu";

    public static class AudioFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3"));
        }
    }

    public static class VideoFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp4"));
        }
    }

    public static class ImageFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".jpg")
                    || name.endsWith(".jpeg")
                    || name.endsWith(".png"));
        }
    }

    public static class DocumentFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".pdf")
                    || name.endsWith(".txt")

                    || name.endsWith(".doc")
                    || name.endsWith(".docx"))

                    || name.endsWith(".xls")
                    || name.endsWith(".xlsx")

                    || name.endsWith(".ppt")
                    || name.endsWith(".pptx")
                    ;
        }
    }

    public static String getMIMETypeFromPath(String path) {
        String extension = getExtension(path);
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

    public static void writeToFile(Context context, String data) {
        writeToFile(context, FILE_NAME_SAVE, data);
    }

    public static void writeToFile(Context context, String fileName, String data) {
        try {
            String rootPath = Environment.getExternalStorageDirectory().getPath() + FileUtils.SD_ROOT_PATH;
            File myFile = new File(rootPath + "/" + fileName + ".txt");
            if (!myFile.exists())
                myFile.createNewFile();
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

    private String readFromFile(Context pContext) {

        return readFromFile(pContext, FILE_NAME_SAVE);
    }

    private String readFromFile(Context pContext, String nameFile) {

        String ret = "";

        try {
            InputStream inputStream = pContext.openFileInput(nameFile + ".txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
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
