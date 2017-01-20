package cn.hugeterry.coderfun.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.activity.ImageActivity;
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
                holder.relativeLayout.setVisibility(View.VISIBLE);
                holder.draweeView.setVisibility(View.GONE);
                holder.iv_video.setVisibility(View.VISIBLE);
                holder.textView.setText(part_list.get(position).getDesc());
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebAcitivity.newIntent(context,
                                part_list.get(position).getUrl(),
                                part_list.get(position).getDesc());
                    }
                });
                break;
            case "福利":
                holder.relativeLayout.setVisibility(View.GONE);
                holder.draweeView.setVisibility(View.VISIBLE);
                holder.iv_video.setVisibility(View.GONE);
                Uri uri = Uri.parse(part_list.get(position).getUrl());
                holder.draweeView.setImageURI(uri);
                holder.textView.setText("瞧瞧妹纸，扩展扩展视野......");
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageActivity.newIntent(context,
                                part_list.get(position).getUrl(),
                                part_list.get(position).getDesc());
                    }
                });
                break;
            default:
                holder.relativeLayout.setVisibility(View.VISIBLE);
                holder.draweeView.setVisibility(View.GONE);
                holder.iv_video.setVisibility(View.GONE);
                holder.textView.setText(part_list.get(position).getDesc());
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebAcitivity.newIntent(context,
                                part_list.get(position).getUrl(),
                                part_list.get(position).getDesc());
                    }
                });
                break;
        }
        Uri uri = null;
        switch (part_list.get(position).getType()) {
            case "Android":
                uri = Uri.parse("res:///" + R.mipmap.android_icon);
                break;
            case "iOS":
                uri = Uri.parse("res:///" + R.mipmap.ios_icon);
                break;
            case "前端":
                uri = Uri.parse("res:///" + R.mipmap.js_icon);
                break;
            case "拓展资源":
                uri = Uri.parse("res:///" + R.mipmap.other_icon);
                break;
        }
        holder.dv_icon.setImageURI(uri);
        String author = part_list.get(position).getWho();
        if (author != null) {
            holder.tv_author.setText(author);
            holder.tv_author.setTextColor(Color.parseColor("#87000000"));
        } else {
            holder.tv_author.setText("匿名");
        }
        String time = part_list.get(position).getCreatedAt();
        if (time != null) {
            holder.tv_time.setText(TimeDifferenceUtils.getTimeDifference(time));
        } else {
            holder.tv_time.setText("");
        }
        holder.tv_type.setText(type);

    }

    @Override
    public int getItemCount() {
        return part_list.size();
    }

    class PartViewHolder extends RecyclerView.ViewHolder {
        View view;
        RelativeLayout relativeLayout;
        TextView tv_author, tv_time, tv_type;
        SimpleDraweeView draweeView, dv_icon;
        ImageView iv_video;
        TextView textView;

        public PartViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.part_message);
            tv_author = (TextView) itemView.findViewById(R.id.part_tv_author);
            tv_time = (TextView) itemView.findViewById(R.id.part_tv_time);
            dv_icon = (SimpleDraweeView) itemView.findViewById(R.id.part_type_icon);
            tv_type = (TextView) itemView.findViewById(R.id.part_tv_type);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.part_iv);
            iv_video = (ImageView) itemView.findViewById(R.id.part_video_iv);
            textView = (TextView) itemView.findViewById(R.id.part_tv);

        }
    }
}
