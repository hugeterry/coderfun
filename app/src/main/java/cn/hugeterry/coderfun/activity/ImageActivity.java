package cn.hugeterry.coderfun.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;

import cn.hugeterry.coderfun.CoderfunCache;
import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.utils.SaveImageUtils;
import cn.hugeterry.coderfun.utils.ShareUtils;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/23 02:19
 */
public class ImageActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SimpleDraweeView simpleDraweeView;
    private String url, desc;

    public static final String EXTRA_IMAGE_URL = "url";
    public static final String EXTRA_IMAGE_DESC = "desc";

    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(ImageActivity.EXTRA_IMAGE_URL, url);
        intent.putExtra(ImageActivity.EXTRA_IMAGE_DESC, desc);
        context.startActivity(intent);
        return intent;
    }

    private void parseIntent() {
        url = getIntent().getStringExtra(ImageActivity.EXTRA_IMAGE_URL);
        desc = getIntent().getStringExtra(ImageActivity.EXTRA_IMAGE_DESC);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        CoderfunCache.isBackFromWebOrImage = true;
        parseIntent();
        initToolbar();
        initSimpleDraweeView();
    }

    private void initSimpleDraweeView() {
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.draweeview);
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
        hierarchy.setProgressBarImage(new ProgressBarDrawable());
        Uri uri = Uri.parse(url);
        simpleDraweeView.setImageURI(uri);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle("beautiful girls");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                ShareUtils.shareImage(this, SaveImageUtils.saveImage(url, desc, this));
                break;
            case R.id.action_save:
                SaveImageUtils.saveImage(url, desc, this);
                Toast.makeText(this, "已经保存图片啦", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
