package vn.com.rise.project.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import vn.com.rise.project.R;
import vn.com.rise.project.Utils.FileUtils;
import vn.com.rise.project.Utils.LoadingDataAsyncTask;

/**
 * Created by hoanguyen on 3/2/16.
 */
public class ViewListData extends BaseActivity implements View.OnClickListener {
    public static final String EXTRA_TYPE_DATA = "EXTRA_TYPE_DATA";
    public static final String EXTRA_POS = "EXTRA_POS";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final int REQUEST_CODE = 500;
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_VIDEO = 1;
    public static final int TYPE_INFO = 2;
    public static final int TYPE_PROGRAM_TREE = -3;
    public static final int TYPE_SCHEDULE = -4;
    public static final int TYPE_PRICE = -5;
    private static final String TAG = "ViewListData";
    private ListView mListView;
    private int mTypeData;
    private List<String> mListPaths;
    private ListAdapter mListAdapter;
    private int mPos;

    private View mButtonBack;
    private View mButtonHome;
    private TextView mTextViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_data);

        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }

        mTypeData = intent.getIntExtra(EXTRA_TYPE_DATA, 0);
        mPos = intent.getIntExtra(EXTRA_POS, 0);
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setEmptyView(findViewById(R.id.empty_view));

        mButtonBack = findViewById(R.id.button_back);
        mButtonHome = findViewById(R.id.button_home);
        mTextViewTitle = (TextView) findViewById(R.id.title_info);
        mTextViewTitle.setText(intent.getStringExtra(EXTRA_TITLE));
        mButtonBack.setOnClickListener(this);
        mButtonHome.setOnClickListener(this);
        loadData();
    }

    private void loadData() {
//        new LoadingDataAsyncTask().execute();
        new LoadingDataAsyncTask(this, mTypeData, mPos, new LoadingDataAsyncTask.PostExecuteListener() {
            @Override
            public void onPostExecuteListener(List<String> result) {
                handleLoadedData(result);
            }
        }).execute();
    }

    private void bindingData() {
        if (mListAdapter == null) {
            mListAdapter = new ListAdapter();
            mListView.setAdapter(mListAdapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        File file = new File(mListPaths.get(position));
                        String type = "";
                        switch (mTypeData) {
                            case TYPE_IMAGE:
                                type = "image/*";
                                break;
                            case TYPE_VIDEO:
                                type = "video/*";
                                break;
                            case TYPE_INFO:
                            case TYPE_PRICE:
                            case TYPE_PROGRAM_TREE:
                            case TYPE_SCHEDULE:
                                type = FileUtils.getMIMETypeFromPath(file.getPath());
                                break;
                        }
                        intent.setDataAndType(Uri.fromFile(file), type);
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        String showText = "Can't open this file - Please, download 3rd app to open ";
                        switch (mTypeData) {
                            case TYPE_IMAGE:
                                showText += "IMAGE";
                                break;
                            case TYPE_VIDEO:
                                showText += "VIDEO";
                                break;
                            case TYPE_INFO:
                            case TYPE_PRICE:
                            case TYPE_PROGRAM_TREE:
                            case TYPE_SCHEDULE:
                                showText += "DOCUMENT";
                                break;
                        }
                        showText += " files!!!";
                        Toast.makeText(ViewListData.this, showText, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            mListAdapter.notifyDataSetChanged();
        }
    }

    private class ListAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;

        ListAdapter() {
            layoutInflater = LayoutInflater.from(ViewListData.this);
        }

        @Override
        public int getCount() {
            return mListPaths != null ? mListPaths.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return mListPaths != null ? mListPaths.get(position) : "";
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
                viewHolder = new ViewHolder();

                viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageIcon);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //----
            switch (mTypeData) {
                case TYPE_IMAGE:
                    viewHolder.imageView.setImageResource(R.drawable.icon_image2);
                    break;
                case TYPE_VIDEO:
                    viewHolder.imageView.setImageResource(R.drawable.icon_video);
                    break;
                case TYPE_INFO:
                case TYPE_PRICE:
                case TYPE_PROGRAM_TREE:
                case TYPE_SCHEDULE:
                    viewHolder.imageView.setImageResource(R.drawable.icon_document);
                    break;
            }

            viewHolder.textView.setText(FileUtils.getFileNameFromPath(mListPaths.get(position)));

            if (position % 2 == 0) {
                convertView.setBackgroundColor(getResources().getColor(R.color.DarkGray));
            } else {
                convertView.setBackgroundColor(getResources().getColor(R.color.LightSlateGray));
            }

            return convertView;
        }

        private class ViewHolder {
            TextView textView;
            ImageView imageView;
        }
    }

//    private  class LoadingDataAsyncTask extends AsyncTask<Void, Void, List<String>> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            showProgressDialog();
//        }
//
//        @Override
//        protected List<String> doInBackground(Void... params) {
//            String pathView = Environment.getExternalStorageDirectory().getPath() + FileUtils.SD_ROOT_PATH;
//
//            FilenameFilter filter = null;
//
//            if (mTypeData >= 0) {
//                //Main detail
//                String nameClass = Constants.getMapsValueClass().get(mPos);
//
//                switch (mTypeData) {
//                    case TYPE_IMAGE:
//                        pathView += "/" + nameClass + "/Hinh anh";
//                        filter = new FileUtils.ImageFilter();
//                        break;
//
//                    case TYPE_VIDEO:
//                        pathView += "/" + nameClass + "/Video";
//                        filter = new FileUtils.VideoFilter();
//                        break;
//
//                    case TYPE_INFO:
//                        pathView += "/" + nameClass + "/Thong tin chi tiet";
//                        filter = new FileUtils.DocumentFilter();
//                        break;
//                }
//            } else {
//                //General
//                String nameClass = "Tong Quat";
//
//                switch (mTypeData) {
//                    case TYPE_PRICE:
//                        pathView += "/" + nameClass + "/Bieu Phi";
//                        break;
//
//                    case TYPE_PROGRAM_TREE:
//                        pathView += "/" + nameClass + "/Cay chuong trinh";
//                        break;
//
//                    case TYPE_SCHEDULE:
//                        pathView += "/" + nameClass + "/Lich hoc";
//                        break;
//                }
//
//                filter = new FileUtils.DocumentFilter();
//            }
//
//            return FileUtils.getImagePaths(pathView, filter);
//        }
//
//        @Override
//        protected void onPostExecute(List<String> result) {
//            super.onPostExecute(result);
//            handleLoadedData(result);
//            dismissProgressDialog();
//        }
//    }

    private void handleLoadedData(List<String> pResult) {
        mListPaths = pResult;
        bindingData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_back:
                finish();
                break;
            case R.id.button_home:
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}
