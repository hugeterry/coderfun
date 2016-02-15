package cn.hugeterry.coderfun.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.beans.DataResults;
import cn.hugeterry.coderfun.retrofit.CoderfunSingle;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/9 02:45
 */
public class DiscoveryFragement extends Fragment {
    private String mTitle;

    public static DiscoveryFragement getInstance(String title) {
        DiscoveryFragement sf = new DiscoveryFragement();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, null);
        TextView card_title_tv = (TextView) v.findViewById(R.id.card_title_tv);
        card_title_tv.setText(mTitle);

        CoderfunSingle.getInstance().getDataResults("Android", 3, 1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataResults>() {
                    @Override
                    public void onCompleted() {
                        Log.i("zhk-MainActivity", "onCompleted: ");
                        Toast.makeText(getActivity(),
                                "Completed",
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("zhk-MainActivity", "onError: ", e);
                        Toast.makeText(getActivity(),
                                "Error:" + e.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onNext(DataResults dataResults) {
                        Log.i("zhk-MainActivity", "onNext:" + dataResults.isError());
                        Toast.makeText(getActivity(), "do:" + dataResults.isError(), Toast.LENGTH_SHORT).show();
                    }
                });

        return v;
    }


}