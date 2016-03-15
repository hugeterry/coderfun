package cn.hugeterry.coderfun.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.hugeterry.coderfun.CoderfunCache;
import cn.hugeterry.coderfun.CoderfunKey;
import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.adapter.GirlyAdapter;
import cn.hugeterry.coderfun.adapter.PartAdapter;
import cn.hugeterry.coderfun.adapter.RealAdapter;
import cn.hugeterry.coderfun.model.beans.DataResults;
import cn.hugeterry.coderfun.model.beans.Results;
import cn.hugeterry.coderfun.retrofit.CoderfunSingle;
import cn.hugeterry.coderfun.utils.FunDao;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/9 02:45
 */
public class DiscoveryFragment extends Fragment {
    private SwipyRefreshLayout swipyRefreshLayout;
    private RecyclerView recyclerview;
    private GirlyAdapter girlyAdapter;
    private RealAdapter realAdapter;
    private PartAdapter partAdapter;

    private static final String ARG_TITLE = "title";
    private String mTitle;
    private static int FRESH_GANHUO_TIME = 4;
    private static int fi_num = CoderfunKey.FI_NUM;
    private static int gh_num = CoderfunKey.GH_NUM;
    private static int mz_num = CoderfunKey.MZ_NUM;
    private static int NOW_PAGE_FI = 1;
    private static int NOW_PAGE_GH = 1;
    private static int NOW_PAGE_MZ = 1;
    private List<Results> part_list = new ArrayList<>();
    private List<Results> ganhuo_list;
    private List<List<Results>> ganhuo_real_list = new ArrayList<>();
    private List<Results> girly_list = new ArrayList<>();


    public static DiscoveryFragment getInstance(String title) {
        DiscoveryFragment fra = new DiscoveryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
        CoderfunCache.isBackFromWebOrImage = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        initRecyclerView(v);
        initSwipyRefreshLayout(v);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDbData();
        if (!CoderfunCache.isBackFromWebOrImage) {
            loadData(true);
        }
    }


    private void loadData(Boolean isTop) {
        switch (mTitle) {
            case "首页":
                if (isTop) {
                    NOW_PAGE_FI = 1;
                }
                getDataResults("all", fi_num, NOW_PAGE_FI, isTop);
                break;
            case "干货":
                FRESH_GANHUO_TIME = 0;
                ganhuo_real_list.clear();
                getDataResults("Android", gh_num, NOW_PAGE_GH, isTop);
                getDataResults("iOS", gh_num, NOW_PAGE_GH, isTop);
                getDataResults("前端", gh_num, NOW_PAGE_GH, isTop);
                getDataResults("拓展资源", gh_num, NOW_PAGE_GH, isTop);
                break;
            case "妹纸":
                if (isTop) {
                    NOW_PAGE_MZ = 1;
                }
                getDataResults("福利", mz_num, NOW_PAGE_MZ, isTop);
                break;
        }
    }

    private void loadDbData() {
        clearAdapterResults();
        switch (mTitle) {
            case "首页":
                part_list.clear();
                part_list.addAll(FunDao.getFun_Part());
                break;
            case "干货":
                FRESH_GANHUO_TIME = 4;
                ganhuo_real_list.clear();
                ganhuo_real_list.addAll(FunDao.getFun_Real());
                break;
            case "妹纸":
                girly_list.clear();
                girly_list.addAll(FunDao.getFun_Girly());
                break;
        }
        dealWithDataInRecyclerView(part_list, ganhuo_real_list, girly_list);
    }

    private void initSwipyRefreshLayout(View v) {
        swipyRefreshLayout = (SwipyRefreshLayout) v.findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);

        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                Log.d("MainActivity", "Refresh triggered at "
                        + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));
                Observable.timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                swipyRefreshLayout.setRefreshing(false);
                            }
                        });
                loadData(direction == SwipyRefreshLayoutDirection.TOP ? true : false);

            }
        });

    }

    private void initRecyclerView(View v) {
        recyclerview = (RecyclerView) v.findViewById(R.id.recyclerView);
        switch (mTitle) {
            case "首页":
                recyclerview.setLayoutManager(new LinearLayoutManager(recyclerview.getContext()));
                partAdapter = new PartAdapter(getActivity(), null);
                recyclerview.setAdapter(partAdapter);
                break;
            case "干货":
                recyclerview.setLayoutManager(new LinearLayoutManager(recyclerview.getContext()));
                realAdapter = new RealAdapter(getActivity(), null);
                recyclerview.setAdapter(realAdapter);
                break;
            case "妹纸":
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                girlyAdapter = new GirlyAdapter(getActivity(), null);
                recyclerview.setAdapter(girlyAdapter);
                break;
        }

    }

    private void getDataResults(final String type, int number, int page, final boolean isTop) {
        CoderfunSingle.getInstance().getDataResults(type, number, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataResults>() {
                    @Override
                    public void onCompleted() {
                        Log.i("frag", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("frag", "onError: " + e.getMessage(), e);
                        Snackbar.make(recyclerview,"网络不顺畅嘞,更新不了数据啦",Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(DataResults dataResults) {
                        if (dataResults.isError()) {
                            Snackbar.make(recyclerview,"啊擦，服务器出问题啦",Snackbar.LENGTH_SHORT).show();
                        } else {
                            if (mTitle.equals("干货")) {
                                ganhuo_list = new ArrayList<>();
                                ganhuo_list.addAll(dataResults.getResults());
                                ganhuo_real_list.add(ganhuo_list);
                                FRESH_GANHUO_TIME++;
                            }

                            if (isTop) {
                                saveDataInDb(dataResults.getResults(), ganhuo_real_list);
                                clearAdapterResults();
                            }
                            dealWithDataInRecyclerView(dataResults.getResults(), ganhuo_real_list, dataResults.getResults());
                        }
                    }
                });
    }

    private void clearAdapterResults() {
        switch (mTitle) {
            case "首页":
                partAdapter.getResults().clear();
                break;
            case "妹纸":
                girlyAdapter.getResults().clear();
                break;
        }
    }

    private void dealWithDataInRecyclerView(List<Results> part_list, List<List<Results>> ganhuo_real_list, List<Results> girly_list) {
        switch (mTitle) {
            case "首页":
                partAdapter.getResults().addAll(part_list);
                partAdapter.notifyDataSetChanged();
                NOW_PAGE_FI++;
                break;
            case "干货":
                if (FRESH_GANHUO_TIME == 4) {
                    realAdapter.getRealResults().clear();
                    realAdapter.getRealResults().addAll(ganhuo_real_list);
                    realAdapter.notifyDataSetChanged();
                    ganhuo_real_list.clear();
                }
                break;
            case "妹纸":
                girlyAdapter.getResults().addAll(girly_list);
                girlyAdapter.notifyDataSetChanged();
                NOW_PAGE_MZ++;
                break;
        }
    }

    private void saveDataInDb(List<Results> results, List<List<Results>> ganhuo_real_list) {
        switch (mTitle) {
            case "首页":
                FunDao.addFun_Part(results);
                break;
            case "干货":
                if (FRESH_GANHUO_TIME == 4) {
                    FunDao.addFun_Real(ganhuo_real_list);
                }
                break;
            case "妹纸":
                FunDao.addFun_Girly(results);
                break;
        }
    }
}