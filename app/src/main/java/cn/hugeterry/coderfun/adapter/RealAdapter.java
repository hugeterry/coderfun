package cn.hugeterry.coderfun.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.hugeterry.coderfun.CoderfunKey;
import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.activity.ReadActivity;
import cn.hugeterry.coderfun.activity.WebAcitivity;
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
    public void onBindViewHolder(final RealViewHolder holder, final int position) {
        String type = real_list.get(position).get(0).getType();
        holder.title.setText(type);
        switch (type) {
            case "Android":
                holder.title_kid.setText("探索更多Android干货");
                intentToChangeViewpager(holder,0);
                break;
            case "iOS":
                holder.title_kid.setText("学习更多iOS干货");
                intentToChangeViewpager(holder,1);
                break;
            case "前端":
                holder.title_kid.setText("挖掘更多前端干货");
                intentToChangeViewpager(holder,2);
                break;
            case "拓展资源":
                holder.title_kid.setText("发现更多拓展资源");
                intentToChangeViewpager(holder,3);
                break;
        }
        setupHolderTextView(holder.t01, 0, position);
        setupHolderTextView(holder.t02, 1, position);
        setupHolderTextView(holder.t03, 2, position);

    }

    private void intentToChangeViewpager(RealViewHolder holder, final int num) {
        holder.toMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadActivity.class);
                intent.putExtra("numToSetCurrentItem", num);
                context.startActivity(intent);
            }
        });
    }

    private void setupHolderTextView(TextView textView, final int num, final int position) {
        for (int i = 0; i <= CoderfunKey.GH_NUM; i++) {
            textView.setText(real_list.get(position).get(num).getDesc());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebAcitivity.newIntent(context,
                            real_list.get(position).get(num).getUrl(),
                            real_list.get(position).get(num).getDesc());
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return real_list.size();
    }

    class RealViewHolder extends RecyclerView.ViewHolder {
        TextView title, title_kid;
        LinearLayout toMore;
        TextView t01, t02, t03;

        public RealViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            title_kid = (TextView) itemView.findViewById(R.id.tv_title_kid);
            toMore = (LinearLayout) itemView.findViewById(R.id.iwantMore);
            t01 = (TextView) itemView.findViewById(R.id.tv_t01);
            t02 = (TextView) itemView.findViewById(R.id.tv_t02);
            t03 = (TextView) itemView.findViewById(R.id.tv_t03);
        }
    }
}

