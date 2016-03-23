package vn.com.rise.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.com.rise.project.R;
import vn.com.rise.project.Utils.Constants;

/**
 * Created by hoanguyen on 3/2/16.
 */
public class MainClassDetailActivity extends BaseActivity implements View.OnClickListener {
    //    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_POS = "EXTRA_POS";

    private TextView mTextViewTitle;
    private Button mButtonImage;
    private Button mButtonVideo;
    private Button mButtonInfo;
    private View mButtonBack;
    private View mButtonHome;
    private int mPosItemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class_detail);
        initView();
        setData();
    }

    private void initView() {
        mTextViewTitle = (TextView) findViewById(R.id.title_info);
        mButtonImage = (Button) findViewById(R.id.image);
        mButtonVideo = (Button) findViewById(R.id.video);
        mButtonInfo = (Button) findViewById(R.id.info);
        mButtonBack = findViewById(R.id.button_back);
        mButtonHome = findViewById(R.id.button_home);

        mButtonImage.setOnClickListener(this);
        mButtonVideo.setOnClickListener(this);
        mButtonInfo.setOnClickListener(this);
        mButtonBack.setOnClickListener(this);
        mButtonHome.setOnClickListener(this);
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
        Intent intent;

        switch (v.getId()) {

            case R.id.image:
//                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_IMAGE);
//                intent.putExtra(ViewListData.EXTRA_TITLE, mTextViewTitle.getText().toString() + " - " + getString(R.string.class_main_detail_image));
//                startActivityForResult(intent, ViewListData.REQUEST_CODE);

                intent = new Intent(MainClassDetailActivity.this, ImageGalleryViewActivity.class);
                intent.putExtra(ViewListData.EXTRA_POS, mPosItemClick);

//                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_IMAGE);
//                intent.putExtra(ViewListData.EXTRA_TITLE, mTextViewTitle.getText().toString() + " - " + getString(R.string.class_main_detail_image));
                startActivityForResult(intent, ViewListData.REQUEST_CODE);


                break;

            case R.id.video:
                intent = new Intent(MainClassDetailActivity.this, ViewListData.class);
                intent.putExtra(ViewListData.EXTRA_POS, mPosItemClick);
                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_VIDEO);
                intent.putExtra(ViewListData.EXTRA_TITLE, mTextViewTitle.getText().toString() + " - " + getString(R.string.class_main_detail_video));
                startActivityForResult(intent, ViewListData.REQUEST_CODE);
                break;

            case R.id.info:
                intent = new Intent(MainClassDetailActivity.this, ViewListData.class);
                intent.putExtra(ViewListData.EXTRA_POS, mPosItemClick);
                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_INFO);
                intent.putExtra(ViewListData.EXTRA_TITLE, mTextViewTitle.getText().toString() + " - " + getString(R.string.class_main_detail_info));
                startActivityForResult(intent, ViewListData.REQUEST_CODE);
                break;

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
