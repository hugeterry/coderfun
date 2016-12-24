package cn.hugeterry.coderfun.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.umeng.analytics.MobclickAgent;

import cn.hugeterry.coderfun.CoderfunKey;
import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.adapter.MyPagerAdapter;
import cn.hugeterry.coderfun.fragment.DiscoveryFragment;
import cn.hugeterry.updatefun.UpdateFunGO;
import cn.hugeterry.updatefun.config.UpdateKey;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/9 02:48
 */

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager vp;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"首页", "干货", "妹纸"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initFragments();
        initViewPager();
        initTabLayout();
        setupUpdate();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(DiscoveryFragment.getInstance(title));
        }
    }

    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }

    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_read:
                Intent intent_read = new Intent(this, ReadActivity.class);
                startActivity(intent_read);
                break;
            case R.id.action_about_me:
                WebAcitivity.newIntent(this, "http://www.hugeterry.cn/about", "HugeTerry");
                break;
            case R.id.action_about:
                Intent intent_about = new Intent(this, AboutActivity.class);
                startActivity(intent_about);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupUpdate() {
        UpdateKey.API_TOKEN = CoderfunKey.API_TOKEN;
        UpdateKey.APP_ID = CoderfunKey.APP_ID;
        UpdateFunGO.init(this);
    }

    public void onResume() {
        super.onResume();
        UpdateFunGO.onResume(this);
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        UpdateFunGO.onStop(this);
        MobclickAgent.onPause(this);
    }

}
