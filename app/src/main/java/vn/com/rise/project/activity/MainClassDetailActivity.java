package vn.com.rise.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.rise.project.R;

/**
 * Created by hoanguyen on 3/2/16.
 */
public class MainClassDetailActivity extends BaseActivity implements View.OnClickListener {
    public static final String EXTRA_TITLE = "EXTRA_TITLE";

    private TextView mTextViewTitle;
    private Button mButtonImage;
    private Button mButtonVideo;
    private Button mButtonInfo;
    private View mButtonBack;

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

        mButtonImage.setOnClickListener(this);
        mButtonVideo.setOnClickListener(this);
        mButtonInfo.setOnClickListener(this);
        mButtonBack.setOnClickListener(this);
    }

    private void setData() {
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra(EXTRA_TITLE);

            if (!TextUtils.isEmpty(title)) {
                mTextViewTitle.setText(title);
            } else {
                mTextViewTitle.setText("-.-");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image:
                Toast.makeText(MainClassDetailActivity.this, "Image", Toast.LENGTH_SHORT).show();
                break;

            case R.id.video:
                Toast.makeText(MainClassDetailActivity.this, "Video", Toast.LENGTH_SHORT).show();

                break;

            case R.id.info:
                Toast.makeText(MainClassDetailActivity.this, "Info", Toast.LENGTH_SHORT).show();

                break;

            case R.id.button_back:
                finish();

                break;
        }
    }
}
