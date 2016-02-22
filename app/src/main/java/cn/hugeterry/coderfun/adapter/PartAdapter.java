package cn.hugeterry.coderfun.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.activity.WebAcitivity;
import cn.hugeterry.coderfun.model.beans.Results;
import cn.hugeterry.coderfun.utils.TimeDifferenceUtils;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/19 02:57
 */
public class PartAdapter extends RecyclerView.Adapter<PartAdapter.PartViewHolder> {

    private Context context;
    private List<Results> part_list = new ArrayList<>();

    public List<Results> getResults() {
        return part_list;
    }

    public PartAdapter(Context context, List<Results> part_list) {
        this.context = context;
        if (part_list != null) {
            this.part_list = part_list;
        }
    }

    @Override
    public PartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PartViewHolder holder = new PartViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_part, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(PartViewHolder holder, final int position) {
        String type = part_list.get(position).getType();
        switch (type) {
            case "休息视频":
                holder.iv_video.setVisibility(View.VISIBLE);
                holder.tv_time.setVisibility(View.GONE);
                holder.tv_author.setText("看看视频，休息一下吧......");
                holder.tv_author.setTextColor(Color.parseColor("#41b94d"));
                holder.textView.setText(part_list.get(position).getDesc());
                holder.tv_time.setText(part_list.get(position).getPublishedAt());
                break;
            case "福利":
                holder.draweeView.setVisibility(View.VISIBLE);
                holder.iv_video.setVisibility(View.GONE);
                holder.textView.setVisibility(View.GONE);
                holder.tv_time.setVisibility(View.GONE);
                holder.tv_author.setText("瞧瞧妹纸，扩展扩展视野......");
                holder.tv_author.setTextColor(Color.parseColor("#ffff4444"));
                Uri uri = Uri.parse(part_list.get(position).getUrl());
                holder.draweeView.setImageURI(uri);
                break;
            default:
                holder.draweeView.setVisibility(View.GONE);
                holder.iv_video.setVisibility(View.GONE);
                holder.textView.setVisibility(View.VISIBLE);
                holder.tv_time.setVisibility(View.VISIBLE);
                holder.tv_author.setText(part_list.get(position).getWho());
                holder.tv_author.setTextColor(Color.parseColor("#87000000"));
                holder.tv_time.setText(part_list.get(position).getPublishedAt());
                holder.textView.setText(part_list.get(position).getDesc());
                break;
        }

        holder.tv_time.setText(TimeDifferenceUtils.getTimeDifference(
                part_list.get(position).getPublishedAt()));
        holder.tv_type.setText(type);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebAcitivity.class);
                intent.putExtra("url", part_list.get(position).getUrl());
                intent.putExtra("desc", part_list.get(position).getDesc());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return part_list.size();
    }

    class PartViewHolder extends RecyclerView.ViewHolder {
        View view;
        SimpleDraweeView draweeView;
        ImageView iv_video;
        TextView textView;
        TextView tv_author, tv_time, tv_type;

        public PartViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.part_iv);
            iv_video = (ImageView) itemView.findViewById(R.id.part_video_iv);
            textView = (TextView) itemView.findViewById(R.id.part_tv);
            tv_author = (TextView) itemView.findViewById(R.id.part_tv_author);
            tv_time = (TextView) itemView.findViewById(R.id.part_tv_time);
            tv_type = (TextView) itemView.findViewById(R.id.part_tv_type);
        }
    }
}
