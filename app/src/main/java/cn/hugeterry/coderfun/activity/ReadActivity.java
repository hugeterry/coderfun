package cn.hugeterry.coderfun.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import cn.hugeterry.coderfun.CoderfunCache;
import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.adapter.MyPagerAdapter;
import cn.hugeterry.coderfun.fragment.ReadFragment;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/23 21:53
 */
public class ReadActivity extends AppCompatActivity {
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private ViewPager vp;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"Android", "iOS", "前端", "拓展资源"};
    private int numToSetCurrentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_read);
        CoderfunCache.isBackFromWebOrImage = true;
        numToSetCurrentItem = getIntent().getIntExtra("numToSetCurrentItem", 0);

        initFragments();
        initViewPager();
        mImageArray = new int[]{R.mipmap.bg_android,
                R.mipmap.bg_ios,
                R.mipmap.bg_js,
                R.mipmap.bg_other};
        mColorArray = new int[]{android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light};

        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.setTitle("分类阅读")
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(vp);

        vp.setCurrentItem(numToSetCurrentItem);
    }


    private void initFragments() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(ReadFragment.getInstance(title));
        }
    }

    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
