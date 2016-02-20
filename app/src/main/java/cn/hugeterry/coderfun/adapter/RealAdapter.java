package cn.hugeterry.coderfun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.model.beans.Results;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/17 21:53
 */
public class RealAdapter extends RecyclerView.Adapter<RealAdapter.RealViewHolder> {

    private Context context;
    private List<List<Results>> real_list = new ArrayList<>();

    public List<List<Results>> getRealResults() {
        return real_list;
    }

    public RealAdapter(Context context, List<List<Results>> real_list) {
        this.context = context;
        if (real_list != null) {
            this.real_list = real_list;
        }
    }

    @Override
    public RealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RealViewHolder holder = new RealViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_real, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RealViewHolder holder, int position) {
        String type = real_list.get(position).get(0).getType();
        holder.title.setText(type);
        switch (type) {
            case "Android":
                holder.title_kid.setText("探索更多Android干货");
                break;
            case "iOS":
                holder.title_kid.setText("学习更多iOS干货");
                break;
            case "前端":
                holder.title_kid.setText("挖掘更多前端干货");
                break;
            case "拓展资源":
                holder.title_kid.setText("发现更多拓展资源");
                break;
        }
        holder.t01.setText(real_list.get(position).get(0).getDesc());
        holder.t02.setText(real_list.get(position).get(1).getDesc());
        holder.t03.setText(real_list.get(position).get(2).getDesc());

    }

    @Override
    public int getItemCount() {
        return real_list.size();
    }

    class RealViewHolder extends RecyclerView.ViewHolder {
        TextView title, title_kid;
        TextView toMore;
        TextView t01, t02, t03;

        public RealViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            title_kid = (TextView) itemView.findViewById(R.id.tv_title_kid);
            toMore = (TextView) itemView.findViewById(R.id.iwantMore);
            t01 = (TextView) itemView.findViewById(R.id.tv_t01);
            t02 = (TextView) itemView.findViewById(R.id.tv_t02);
            t03 = (TextView) itemView.findViewById(R.id.tv_t03);
        }
    }
}

