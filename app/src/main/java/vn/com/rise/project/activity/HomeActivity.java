package vn.com.rise.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import vn.com.rise.project.R;
import vn.com.rise.project.Utils.Constants;
import vn.com.rise.project.views.CircleMenuLayout;

public class HomeActivity extends BaseActivity {

    private CircleMenuLayout mCircleMenuLayout;

    private String[] mItemTexts;
//    private int[] mItemImgs = new int[]{R.drawable.icon__linkedin_circle_color,
//            R.drawable.icon_facebook_circle_color, R.drawable.icon_google_circle,
//            R.drawable.icon_instagram_circle_color, R.drawable.icon_skype,
//            R.drawable.icon_youtube_circle_color};

    private int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal,
            R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
            R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
            R.drawable.home_mbank_6_normal};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        int size = Constants.getMapsValueClass().size();
        mItemTexts = new String[size];

        for (int i = 0; i < size; i++) {
            mItemTexts[i] = Constants.getMapsValueClass().get(i);
        }
        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {

            @Override
            public void itemClick(View view, int pos) {
//                String title = mItemTexts[pos];

                Intent intent = new Intent(HomeActivity.this, MainClassDetailActivity.class);
                intent.putExtra(MainClassDetailActivity.EXTRA_POS, pos);

                startActivity(intent);

                //TODO chống doubleclick ở đây
            }

            @Override
            public void itemCenterClick(View view) {
                String title = getString(R.string.class_general_title);

                Intent intent = new Intent(HomeActivity.this, GeneralClassActivity.class);
                intent.putExtra(GeneralClassActivity.EXTRA_TITLE, title);

                startActivity(intent);
            }
        });

    }

}
