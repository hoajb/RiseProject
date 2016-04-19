package vn.com.rise.project.activity;

import android.content.Intent;
import android.net.Uri;
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
public class GeneralClassActivity extends BaseActivity implements View.OnClickListener {
    public static final String EXTRA_TITLE = "EXTRA_TITLE";

    private CircleMenuLayout mCircleMenuLayout;
    private TextView mTextViewTitle;
    //    private Button mButtonProgramTree;
//    private Button mButtonSchedule;
//    private Button mButtonPrice;
//    private Button mButtonFormPrice;
//    private Button mButtonWebsite;
    private View mButtonBack;
    private View mButtonHome;
    private String[] mItemTexts;

    private int[] mItemImgs = new int[]{

            R.drawable.icon_circle_programe,
            R.drawable.icon_circle_schedule,
            R.drawable.icon_circle_money2,
            R.drawable.icon_circle_form_save,
            R.drawable.icon_circle_logo_rise,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_class_v2);
        initView();
        setData();
    }

    private void initView() {
        mTextViewTitle = (TextView) findViewById(R.id.title_info);
//        mButtonProgramTree = (Button) findViewById(R.id.program_tree);
//        mButtonSchedule = (Button) findViewById(R.id.schedule);
//        mButtonPrice = (Button) findViewById(R.id.price);
//        mButtonFormPrice = (Button) findViewById(R.id.form_price);
//        mButtonWebsite = (Button) findViewById(R.id.website);
        mButtonBack = findViewById(R.id.button_back);
        mButtonHome = findViewById(R.id.button_home);

//        mButtonProgramTree.setOnClickListener(this);
//        mButtonSchedule.setOnClickListener(this);
//        mButtonPrice.setOnClickListener(this);
//        mButtonFormPrice.setOnClickListener(this);
//        mButtonWebsite.setOnClickListener(this);
        mButtonBack.setOnClickListener(this);
        mButtonHome.setOnClickListener(this);

        int size = Constants.getMapsValueClass().size();
        mItemTexts = new String[size];

        for (int i = 0; i < size; i++) {
            mItemTexts[i] = Constants.getMapsValueMainClass(this).get(i);
        }
        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setHideItemText(true);
//        mCircleMenuLayout.setHideMenuCenterItem(true);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {

            @Override
            public void itemClick(View view, int pos) {

                //TODO chống doubleclick ở đây
                Intent intent;
                switch (pos) {
                    case 0:
                        intent = new Intent(GeneralClassActivity.this, ViewListData.class);
                        intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_PROGRAM_TREE);
                        intent.putExtra(ViewListData.EXTRA_TITLE, getString(R.string.class_general_program_tree));
                        startActivityForResult(intent, ViewListData.REQUEST_CODE);
                        break;

                    case 1:

                        intent = new Intent(GeneralClassActivity.this, ViewListData.class);
                        intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_SCHEDULE);
                        intent.putExtra(ViewListData.EXTRA_TITLE, getString(R.string.class_general_schedule));
                        startActivityForResult(intent, ViewListData.REQUEST_CODE);
                        break;

                    case 2:
                        intent = new Intent(GeneralClassActivity.this, ViewListData.class);
                        intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_PRICE);
                        intent.putExtra(ViewListData.EXTRA_TITLE, getString(R.string.class_general_price));
                        startActivityForResult(intent, ViewListData.REQUEST_CODE);
                        break;

                    case 3:
                        intent = new Intent(GeneralClassActivity.this, PriceFromActivity.class);
                        startActivityForResult(intent, ViewListData.REQUEST_CODE);
                        break;

                    case 4:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.WEBSITE_RISE));
                        startActivityForResult(intent, ViewListData.REQUEST_CODE);
                        break;
                }
            }

            @Override
            public void itemCenterClick(View view) {
                finish();
            }
        });
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
//            case R.id.program_tree:
//                intent = new Intent(GeneralClassActivity.this, ViewListData.class);
//                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_PROGRAM_TREE);
//                intent.putExtra(ViewListData.EXTRA_TITLE, getString(R.string.class_general_program_tree));
//                startActivityForResult(intent, ViewListData.REQUEST_CODE);
//                break;
//
//            case R.id.schedule:
//                intent = new Intent(GeneralClassActivity.this, ViewListData.class);
//                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_SCHEDULE);
//                intent.putExtra(ViewListData.EXTRA_TITLE, getString(R.string.class_general_schedule));
//                startActivityForResult(intent, ViewListData.REQUEST_CODE);
//                break;
//
//            case R.id.price:
//                intent = new Intent(GeneralClassActivity.this, ViewListData.class);
//                intent.putExtra(ViewListData.EXTRA_TYPE_DATA, ViewListData.TYPE_PRICE);
//                intent.putExtra(ViewListData.EXTRA_TITLE, getString(R.string.class_general_price));
//                startActivityForResult(intent, ViewListData.REQUEST_CODE);
//                break;
//
//            case R.id.form_price:
//                intent = new Intent(GeneralClassActivity.this, PriceFromActivity.class);
//                startActivityForResult(intent, ViewListData.REQUEST_CODE);
//                break;
//
//            case R.id.website:
//                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.WEBSITE_RISE));
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
