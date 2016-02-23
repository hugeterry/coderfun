package cn.hugeterry.coderfun.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.hugeterry.coderfun.CoderfunCache;
import cn.hugeterry.coderfun.CoderfunKey;
import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.adapter.ReadAdapter;
import cn.hugeterry.coderfun.model.beans.DataResults;
import cn.hugeterry.coderfun.model.beans.Results;
import cn.hugeterry.coderfun.retrofit.CoderfunSingle;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/9 02:45
 */
public class ReadFragment extends Fragment {
    private SwipyRefreshLayout swipyRefreshLayout;
    private RecyclerView recyclerview;
    private ReadAdapter readAdapter;

    private static final String ARG_TITLE = "title";
    private String mTitle;
    private static int read_num = CoderfunKey.READ_NUM;
    private static int NOW_PAGE_READ = 1;

    public static ReadFragment getInstance(String title) {
        ReadFragment fra = new ReadFragment();
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
        loadData(true);
    }


    private void loadData(Boolean isTop) {
        if (isTop == true) {
            NOW_PAGE_READ = 1;
        }
        getDataResults(mTitle, read_num, NOW_PAGE_READ, isTop);

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

        recyclerview.setLayoutManager(new LinearLayoutManager(recyclerview.getContext()));
        readAdapter = new ReadAdapter(getActivity(), null);
        recyclerview.setAdapter(readAdapter);
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
                        Toast.makeText(getActivity(), "网络不顺畅嘞", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(DataResults dataResults) {
                        if (dataResults.isError() == true) {
                            Toast.makeText(getActivity(), "啊擦，服务器出问题啦", Toast.LENGTH_SHORT).show();
                        } else {

                            if (isTop == true) {
                                clearAdapterResults();
                            }
                            dealWithDataInRecyclerView(dataResults.getResults());
                        }
                    }
                });
    }

    private void clearAdapterResults() {
        readAdapter.getResults().clear();
    }

    private void dealWithDataInRecyclerView(List<Results> read_list) {
        readAdapter.getResults().addAll(read_list);
        readAdapter.notifyDataSetChanged();
        NOW_PAGE_READ++;
    }
}