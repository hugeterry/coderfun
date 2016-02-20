package cn.hugeterry.coderfun.views;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.ArrayList;

import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.adapter.MyPagerAdapter;
import cn.hugeterry.coderfun.fragment.DiscoveryFragment;
import cn.hugeterry.coderfun.utils.ViewFindUtils;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/9 02:48
 */

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private View decorView;
    private ViewPager vp;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"首页", "干货", "妹纸"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        FlowManager.init(this);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initFragments();
        initDecorView();
        initViewPager();
        initTabLayout();

    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(DiscoveryFragment.getInstance(title));
        }
    }

    private void initDecorView() {
        decorView = getWindow().getDecorView();
    }

    private void initViewPager() {
        vp = ViewFindUtils.find(decorView, R.id.vp);
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }

    private void initTabLayout() {
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp);
    }

}
