package com.wildma.bigworld.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.wildma.bigworld.R;
import com.wildma.bigworld.bean.HotspotNewsItemBean;
import com.wildma.bigworld.global.MyApplication;
import com.wildma.bigworld.widget.GlideRoundImage;

import java.util.ArrayList;

/**
 * Author       wildma
 * Date         2017/12/12
 * Desc	        ${热点新闻适配器}
 */
public class HotspotNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<HotspotNewsItemBean> mHotspotNewsList = new ArrayList<>();
    private Context mContext;

    public HotspotNewsAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new HotspotNewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_hotspot_news, parent, false));
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        HotspotNewsViewHolder hotspotNewsViewHolder = (HotspotNewsViewHolder) holder;
        final HotspotNewsItemBean hotspotNewsItemBean = mHotspotNewsList.get(holder.getAdapterPosition());
        hotspotNewsViewHolder.textView.setTextColor(Color.BLACK);
        hotspotNewsViewHolder.textView.setText(hotspotNewsItemBean.getTitle());
        Glide.with(mContext)
                .load(mHotspotNewsList.get(position).getImages()[0])
                .asBitmap()
                .transform(new CenterCrop(MyApplication.getContext()), new GlideRoundImage(MyApplication.getContext(), 4))
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(hotspotNewsViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mHotspotNewsList.size();
    }

    public void addItems(ArrayList<HotspotNewsItemBean> list) {
        mHotspotNewsList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearData() {
        mHotspotNewsList.clear();
        notifyDataSetChanged();
    }

    class HotspotNewsViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;
        ImageView imageView;

        HotspotNewsViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_image);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
