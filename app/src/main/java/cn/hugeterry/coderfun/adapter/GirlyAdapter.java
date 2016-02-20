package cn.hugeterry.coderfun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import cn.hugeterry.coderfun.R;

import cn.hugeterry.coderfun.model.beans.Results;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/16 23:05
 */
public class GirlyAdapter extends RecyclerView.Adapter<GirlyAdapter.GirlyViewHolder> {

    private Context context;
    private List<Results> girly_list = new ArrayList<>();

    public List<Results> getResults() {
        return girly_list;
    }

    public GirlyAdapter(Context context, List<Results> girly_list) {
        this.context = context;
        if (girly_list != null) {
            this.girly_list = girly_list;
        }
    }

    @Override
    public GirlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GirlyViewHolder holder = new GirlyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_girly, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(GirlyViewHolder holder, int position) {
        Glide.with(context)
                .load(girly_list.get(position).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return girly_list.size();
    }


    class GirlyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public GirlyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_girly);
        }
    }

}