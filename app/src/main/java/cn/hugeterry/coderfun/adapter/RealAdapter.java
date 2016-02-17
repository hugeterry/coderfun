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
import cn.hugeterry.coderfun.beans.Results;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/17 21:53
 */
public class RealAdapter extends RecyclerView.Adapter<RealAdapter.RealViewHolder> {

    private Context context;
    private List<Results> list = new ArrayList<>();

    private List<Results> getResults() {
        return list;
    }

    public RealAdapter(Context context, List<Results> list) {
        this.context = context;
        if (list != null) {
            this.list = list;
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
        holder.title.setText(list.get(position).getType());
        holder.t01.setText(list.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return list.size();
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

