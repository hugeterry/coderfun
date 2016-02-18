package cn.hugeterry.coderfun.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.adapter.GirlyAdapter;
import cn.hugeterry.coderfun.adapter.PartAdapter;
import cn.hugeterry.coderfun.adapter.RealAdapter;
import cn.hugeterry.coderfun.beans.DataResults;
import cn.hugeterry.coderfun.beans.Results;
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
    private RealAdapter realAdapter;
    private PartAdapter partAdapter;

    private static final String ARG_TITLE = "title";
    private String mTitle;

    private List<Results> ganhuo_list;
    private List<List<Results>> ganhuo_real_list = new ArrayList<>();

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
        switch (mTitle) {
            case "首页":
                loadData("all", 15, 1);
                break;
            case "干货":
                loadData("Android", 3, 1);
                loadData("iOS", 3, 1);
                loadData("前端", 3, 1);
                loadData("拓展资源", 3, 1);
                break;
//            case "妹纸":
            default:
                loadData("福利", 45, 1);
                break;
        }


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
//            case "妹纸":
            default:
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                girlyAdapter = new GirlyAdapter(getActivity(), null);
                recyclerview.setAdapter(girlyAdapter);
                break;
        }

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
                        switch (mTitle) {
                            case "首页":
                                swipyRefreshLayout.setRefreshing(false);
                                partAdapter.getResults().clear();
                                partAdapter.getResults().addAll(dataResults.getResults());
                                partAdapter.notifyDataSetChanged();
                                break;
                            case "干货":
                                ganhuo_list = new ArrayList<>();
                                ganhuo_list.addAll(dataResults.getResults());
                                ganhuo_real_list.add(ganhuo_list);
                                realAdapter.getRealResults().clear();
                                realAdapter.getRealResults().addAll(ganhuo_real_list);
                                realAdapter.notifyDataSetChanged();
                                break;
//                            case "妹纸":
                            default:
                                swipyRefreshLayout.setRefreshing(false);
                                girlyAdapter.getResults().clear();
                                girlyAdapter.getResults().addAll(dataResults.getResults());
                                girlyAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                });
    }
}