package vn.com.rise.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import vn.com.rise.project.R;
import vn.com.rise.project.views.CircleMenuLayout;

public class HomeActivity extends BaseActivity {

    private CircleMenuLayout mCircleMenuLayout;

    private String[] mItemTexts = new String[]{"PreK ", "K", "S1",
            "S2", "S3", "S4"};
    private int[] mItemImgs = new int[]{R.drawable.icon__linkedin_circle_color,
            R.drawable.icon_facebook_circle_color, R.drawable.icon_google_circle,
            R.drawable.icon_instagram_circle_color, R.drawable.icon_skype,
            R.drawable.icon_youtube_circle_color};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);


        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {

            @Override
            public void itemClick(View view, int pos) {
                Toast.makeText(HomeActivity.this, mItemTexts[pos],
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void itemCenterClick(View view) {
                Toast.makeText(HomeActivity.this,
                        "you can do something just like ccb  ",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

}
