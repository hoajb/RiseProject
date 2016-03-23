package vn.com.rise.project.activity;

import android.content.Intent;
import android.os.Bundle;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;

import java.util.ArrayList;
import java.util.List;

import vn.com.rise.project.R;
import vn.com.rise.project.Utils.LoadingDataAsyncTask;
import vn.com.rise.project.Utils.PicassoImageLoader;

/**
 * Created by hoanguyen on 3/22/16.
 */
public class ImageGalleryViewActivity extends BaseActivity {

    private ScrollGalleryView scrollGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery_view);
        initView();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        int mPos = intent.getIntExtra(ViewListData.EXTRA_POS, 0);
        new LoadingDataAsyncTask(this, ViewListData.TYPE_IMAGE, mPos, new LoadingDataAsyncTask.PostExecuteListener() {
            @Override
            public void onPostExecuteListener(List<String> result) {

                List<MediaInfo> infos = new ArrayList<>(result.size());
                for (String url : result)
                    infos.add(MediaInfo.mediaLoader(new PicassoImageLoader(url)));

                scrollGalleryView
                        .setThumbnailSize(100)
                        .setZoom(true)
                        .setFragmentManager(getSupportFragmentManager())
//                .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.drawable.wallpaper1)))
//                .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(toBitmap(R.drawable.wallpaper7))))
//                .addMedia(MediaInfo.mediaLoader(new MediaLoader() {
//                    @Override public boolean isImage() {
//                        return true;
//                    }
//
//                    @Override public void loadMedia(Context context, ImageView imageView,
//                                                    MediaLoader.SuccessCallback callback) {
//                        imageView.setImageBitmap(toBitmap(R.drawable.wallpaper3));
//                        callback.onSuccess();
//                    }
//
//                    @Override public void loadThumbnail(Context context, ImageView thumbnailView,
//                                                        MediaLoader.SuccessCallback callback) {
//                        thumbnailView.setImageBitmap(toBitmap(R.drawable.wallpaper3));
//                        callback.onSuccess();
//                    }
//                }))
//                .addMedia(MediaInfo.mediaLoader(new DefaultVideoLoader(movieUrl, R.mipmap.default_video)))
                        .addMedia(infos);
            }
        }).execute();
    }

    private void initView() {
        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);
    }
}
