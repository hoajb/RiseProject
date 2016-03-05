package vn.com.rise.project.activity;

import android.content.Intent;
import android.net.Uri;
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
public class GeneralClassActivity extends BaseActivity implements View.OnClickListener {
    public static final String EXTRA_TITLE = "EXTRA_TITLE";

    private TextView mTextViewTitle;
    private Button mButtonProgramTree;
    private Button mButtonSchedule;
    private Button mButtonPrice;
    private Button mButtonFormPrice;
    private Button mButtonWebsite;
    private View mButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_class);
        initView();
        setData();
    }

    private void initView() {
        mTextViewTitle = (TextView) findViewById(R.id.title_info);
        mButtonProgramTree = (Button) findViewById(R.id.program_tree);
        mButtonSchedule = (Button) findViewById(R.id.schedule);
        mButtonPrice = (Button) findViewById(R.id.price);
        mButtonFormPrice = (Button) findViewById(R.id.form_price);
        mButtonWebsite = (Button) findViewById(R.id.website);
        mButtonBack = findViewById(R.id.button_back);

        mButtonProgramTree.setOnClickListener(this);
        mButtonSchedule.setOnClickListener(this);
        mButtonPrice.setOnClickListener(this);
        mButtonFormPrice.setOnClickListener(this);
        mButtonWebsite.setOnClickListener(this);
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
        Intent intent;
        switch (v.getId()) {
            case R.id.program_tree:
                intent = new Intent(GeneralClassActivity.this, ViewListData.class);
                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_PROGRAM_TREE);
                startActivity(intent);
                break;

            case R.id.schedule:
                intent = new Intent(GeneralClassActivity.this, ViewListData.class);
                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_SCHEDULE);
                startActivity(intent);
                break;

            case R.id.price:
                intent = new Intent(GeneralClassActivity.this, ViewListData.class);
                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_PRICE);
                startActivity(intent);
                break;

            case R.id.form_price:
                intent = new Intent(GeneralClassActivity.this, PriceFromActivity.class);
                startActivity(intent);
                break;

            case R.id.website:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.WEBSITE_RISE));
                startActivity(intent);
                break;

            case R.id.button_back:
                finish();
                break;
        }
    }
}
