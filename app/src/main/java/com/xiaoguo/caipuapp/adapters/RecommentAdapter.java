package com.xiaoguo.caipuapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.bean.RecommendBean;
import com.xiaoguo.caipuapp.bean.SearchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/6/28.
 */
public class RecommentAdapter extends RecyclerView.Adapter<RecommentAdapter.RecylerHodler>{
//    private List<RecommendBean> data = new ArrayList<>();
    private LayoutInflater mInflater;
    private List<SearchBean.ResultBean.DataBean> itemDataList;
    private Context context;
//    private SearchBean searchBean;
public RecommentAdapter(Context context,List<SearchBean.ResultBean.DataBean> itemDataList){
//        this.data = data;
    this.context = context;
    mInflater = LayoutInflater.from(context);
    this.itemDataList=itemDataList;
   /* if (searchBean != null) {
        itemDataList = searchBean.getResult().getData();
    }*/
}

    public RecommentAdapter(Context context,SearchBean searchBean){
//        this.data = data;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        if (searchBean != null) {
            itemDataList = searchBean.getResult().getData();
        }
    }
    @Override
    public RecylerHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.recyler_item_layout,null);
        return new RecylerHodler(convertView);
    }

    @Override
    public void onBindViewHolder(RecylerHodler holder, int position) {
//        Log.i("111", "data: " + data.size());
//        Log.i("111", "content: " + holder.content);
        holder.content.setText(itemDataList.get(position).getTags());
        holder.title.setText(itemDataList.get(position).getTitle());
//        holder.image.setImageBitmap(data.get(position).getBitmap());
        String url = itemDataList.get(position).getAlbums().get(0);
        Log.i("111", "图片: " + url);
        Picasso.with(context).load(url).config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.test1).into(holder.image);
    }
    /**
     * 此方法一般不需要复写，但是如果一个RecyclerView钟不同item有不同的布局格式时，
     * 可以在此方法中返回不同的view类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return itemDataList == null ? 0 : itemDataList.size();
    }


    public class RecylerHodler extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;
        public ImageView image;
        public RecylerHodler(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.item_image);
//            ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
//            image.setLayoutParams(layoutParams);
            title = (TextView) itemView.findViewById(R.id.recommendText_title);
            content = (TextView) itemView.findViewById(R.id.item_content_text);
//            Log.i("111", "content1: " + content);

        }

    }
}
