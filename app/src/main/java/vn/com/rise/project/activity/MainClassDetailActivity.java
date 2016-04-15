package vn.com.rise.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import vn.com.rise.project.R;
import vn.com.rise.project.Utils.Constants;
import vn.com.rise.project.views.CircleMenuLayout;

/**
 * Created by hoanguyen on 3/2/16.
 */
public class MainClassDetailActivity extends BaseActivity implements View.OnClickListener {
    //    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_POS = "EXTRA_POS";

    private CircleMenuLayout mCircleMenuLayout;

    private TextView mTextViewTitle;
    //    private Button mButtonImage;
//    private Button mButtonVideo;
//    private Button mButtonInfo;
    private View mButtonBack;
    private View mButtonHome;
    private int mPosItemClick;
    private String[] mItemTexts;

    private int[] mItemImgs = new int[]{
            R.drawable.icon_image_circle,
            R.drawable.icon_video_circle,
            R.drawable.icon_info_circle,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class_detail_v2);
        initView();
        setData();
    }

    private void initView() {
        mTextViewTitle = (TextView) findViewById(R.id.title_info);
//        mButtonImage = (Button) findViewById(R.id.image);
//        mButtonVideo = (Button) findViewById(R.id.video);
//        mButtonInfo = (Button) findViewById(R.id.info);
        mButtonBack = findViewById(R.id.button_back);
        mButtonHome = findViewById(R.id.button_home);

//        mButtonImage.setOnClickListener(this);
//        mButtonVideo.setOnClickListener(this);
//        mButtonInfo.setOnClickListener(this);
        mButtonBack.setOnClickListener(this);
        mButtonHome.setOnClickListener(this);

        int size = Constants.getMapsValueClass().size();
        mItemTexts = new String[size];

        for (int i = 0; i < size; i++) {
            mItemTexts[i] = Constants.getMapsValueMainClass(this).get(i);
        }
        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setHideItemText(true);
        mCircleMenuLayout.setHideMenuCenterItem(true);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {

            @Override
            public void itemClick(View view, int pos) {

                //TODO chống doubleclick ở đây
                Intent intent;
                switch (pos) {
                    case 0:
                        intent = new Intent(MainClassDetailActivity.this, ImageGalleryViewActivity.class);
                        intent.putExtra(ViewListData.EXTRA_POS, mPosItemClick);
                        startActivityForResult(intent, ViewListData.REQUEST_CODE);
                        break;

                    case 1:
                        intent = new Intent(MainClassDetailActivity.this, ViewListData.class);
                        intent.putExtra(ViewListData.EXTRA_POS, mPosItemClick);
                        intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_VIDEO);
                        intent.putExtra(ViewListData.EXTRA_TITLE, mTextViewTitle.getText().toString() + " - " + getString(R.string.class_main_detail_video));
                        startActivityForResult(intent, ViewListData.REQUEST_CODE);
                        break;

                    case 2:
                        intent = new Intent(MainClassDetailActivity.this, ViewListData.class);
                        intent.putExtra(ViewListData.EXTRA_POS, mPosItemClick);
                        intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_INFO);
                        intent.putExtra(ViewListData.EXTRA_TITLE, mTextViewTitle.getText().toString() + " - " + getString(R.string.class_main_detail_info));
                        startActivityForResult(intent, ViewListData.REQUEST_CODE);
                        break;
                }
            }

            @Override
            public void itemCenterClick(View view) {
                //TODO
            }
        });
    }

    private void setData() {
        Intent intent = getIntent();
        if (intent != null) {
            mPosItemClick = intent.getIntExtra(EXTRA_POS, 0);
            String title = Constants.getMapsValueClass().get(mPosItemClick);

            if (!TextUtils.isEmpty(title)) {
                mTextViewTitle.setText(title);
            } else {
                mTextViewTitle.setText("-.-");
            }
        }
    }

    @Override
    public void onClick(View v) {
//        Intent intent;

        switch (v.getId()) {

//            case R.id.image:
////                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_IMAGE);
////                intent.putExtra(ViewListData.EXTRA_TITLE, mTextViewTitle.getText().toString() + " - " + getString(R.string.class_main_detail_image));
////                startActivityForResult(intent, ViewListData.REQUEST_CODE);
//
//                intent = new Intent(MainClassDetailActivity.this, ImageGalleryViewActivity.class);
//                intent.putExtra(ViewListData.EXTRA_POS, mPosItemClick);
//
////                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_IMAGE);
////                intent.putExtra(ViewListData.EXTRA_TITLE, mTextViewTitle.getText().toString() + " - " + getString(R.string.class_main_detail_image));
//                startActivityForResult(intent, ViewListData.REQUEST_CODE);
//
//
//                break;
//
//            case R.id.video:
//                intent = new Intent(MainClassDetailActivity.this, ViewListData.class);
//                intent.putExtra(ViewListData.EXTRA_POS, mPosItemClick);
//                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_VIDEO);
//                intent.putExtra(ViewListData.EXTRA_TITLE, mTextViewTitle.getText().toString() + " - " + getString(R.string.class_main_detail_video));
//                startActivityForResult(intent, ViewListData.REQUEST_CODE);
//                break;
//
//            case R.id.info:
//                intent = new Intent(MainClassDetailActivity.this, ViewListData.class);
//                intent.putExtra(ViewListData.EXTRA_POS, mPosItemClick);
//                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_INFO);
//                intent.putExtra(ViewListData.EXTRA_TITLE, mTextViewTitle.getText().toString() + " - " + getString(R.string.class_main_detail_info));
//                startActivityForResult(intent, ViewListData.REQUEST_CODE);
//                break;

            case R.id.button_back:
            case R.id.button_home:
                finish();

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ViewListData.REQUEST_CODE && resultCode == RESULT_OK) {
            finish();
        }
    }
}
