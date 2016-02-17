package cn.hugeterry.coderfun.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.adapter.GirlyAdapter;
import cn.hugeterry.coderfun.beans.DataResults;
import cn.hugeterry.coderfun.retrofit.CoderfunSingle;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/9 02:45
 */
public class DiscoveryFragment extends Fragment {
    private SwipyRefreshLayout swipyRefreshLayout;
    private RecyclerView recyclerview;
    private GirlyAdapter girlyAdapter;

    private static final String ARG_TITLE = "title";
    private String mTitle;

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
        Toast.makeText(getActivity(), mTitle, Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_girly_list, container, false);
        initRecyclerView(v);
        initSwipyRefreshLayout(v);
        loadData("福利", 45, 1);
        return v;
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
                loadData("福利", 45, 1);

            }
        });

    }

    private void initRecyclerView(View v) {
        recyclerview = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        girlyAdapter = new GirlyAdapter(getActivity(), null);
        recyclerview.setAdapter(girlyAdapter);
    }

    private void loadData(String type, int number, int page) {
        swipyRefreshLayout.setRefreshing(true);
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
//                        switch (mTitle) {
//                            case "妹纸":
                                swipyRefreshLayout.setRefreshing(false);
                                girlyAdapter.getResults().clear();
                                girlyAdapter.getResults().addAll(dataResults.getResults());
                                girlyAdapter.notifyDataSetChanged();
//                                break;
//                        }
                    }
                });
    }
}