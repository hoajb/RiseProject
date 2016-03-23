package vn.com.rise.project.Utils;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.FilenameFilter;
import java.util.List;

import vn.com.rise.project.activity.BaseActivity;
import vn.com.rise.project.activity.ViewListData;

/**
 * Created by hoanguyen on 3/22/16.
 */
public class LoadingDataAsyncTask extends AsyncTask<Void, Void, List<String>> {
    private int mPos;
    private int mTypeData;
    private BaseActivity mBaseActivity;
    private PostExecuteListener mPostExecuteListener;

    public LoadingDataAsyncTask(BaseActivity pBaseActivity, int dataType, int pos, PostExecuteListener pListener) {
        mBaseActivity = pBaseActivity;
        mTypeData = dataType;
        mPos = pos;
        mPostExecuteListener = pListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mBaseActivity.showProgressDialog();
    }

    @Override
    protected List<String> doInBackground(Void... params) {
        String pathView = Environment.getExternalStorageDirectory().getPath() + FileUtils.SD_ROOT_PATH;

        FilenameFilter filter = null;

        if (mTypeData >= 0) {
            //Main detail
            String nameClass = Constants.getMapsValueClass().get(mPos);

            switch (mTypeData) {
                case ViewListData.TYPE_IMAGE:
                    pathView += "/" + nameClass + "/Hinh anh";
                    filter = new FileUtils.ImageFilter();
                    break;

                case ViewListData.TYPE_VIDEO:
                    pathView += "/" + nameClass + "/Video";
                    filter = new FileUtils.VideoFilter();
                    break;

                case ViewListData.TYPE_INFO:
                    pathView += "/" + nameClass + "/Thong tin chi tiet";
                    filter = new FileUtils.DocumentFilter();
                    break;
            }
        } else {
            //General
            String nameClass = "Tong Quat";

            switch (mTypeData) {
                case ViewListData.TYPE_PRICE:
                    pathView += "/" + nameClass + "/Bieu Phi";
                    break;

                case ViewListData.TYPE_PROGRAM_TREE:
                    pathView += "/" + nameClass + "/Cay chuong trinh";
                    break;

                case ViewListData.TYPE_SCHEDULE:
                    pathView += "/" + nameClass + "/Lich hoc";
                    break;
            }

            filter = new FileUtils.DocumentFilter();
        }

        return FileUtils.getImagePaths(pathView, filter);
    }

    @Override
    protected void onPostExecute(List<String> result) {
        super.onPostExecute(result);
        if (mPostExecuteListener != null) {
            mPostExecuteListener.onPostExecuteListener(result);
        }
//        handleLoadedData(result);
        mBaseActivity.dismissProgressDialog();
    }

    public interface PostExecuteListener {
        void onPostExecuteListener(List<String> result);
    }

}
