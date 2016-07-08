package com.xiaoguo.caipuapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.bean.ShopItemBean;

import java.util.List;

/**
 * Created by lenovo on 2016/7/4.
 */
public class ShopRightAdapter extends RecyclerView.Adapter<ShopRightAdapter.ShopRightViewHolder> {
    private List<ShopItemBean.CatListBean.SubListBean.ShareListBean> mList;
    private LayoutInflater mInflater;
    private Context context;

    public ShopRightAdapter(Context context,List<ShopItemBean.CatListBean.SubListBean.ShareListBean> mList) {
        this.context = context;
        this.mList = mList;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public ShopRightAdapter.ShopRightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.shop_right_item,null);
        return new ShopRightViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ShopRightAdapter.ShopRightViewHolder holder, int position) {

        if (mList.get(position).getSource().equals("taobao")) {
            holder.tianmao.setVisibility(View.INVISIBLE);
        }
        holder.tianmao.setImageResource(R.drawable.face);
        holder.title.setText(mList.get(position).getName());
        holder.price.setText("¥" + mList.get(position).getPrice());
        if (mList.get(position).getIs_free_shipping() != 1) {
            holder.shipping.setVisibility(View.INVISIBLE);
        }
        holder.shipping.setText("【包邮】");
        holder.heart.setImageResource(R.drawable.heart);
        holder.likes.setText(mList.get(position).getLike());
        String url = mList.get(position).getIcon();
        Picasso.with(context).load(url).config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.normallogo).into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ShopRightViewHolder extends RecyclerView.ViewHolder {
        private ImageView logo;
        private ImageView tianmao;
        private TextView title;
        private TextView price;
        private TextView shipping;
        private ImageView heart;
        private TextView likes;

        public ShopRightViewHolder(View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.shop_right_image);
            tianmao = (ImageView) itemView.findViewById(R.id.tianmao_image);
            title = (TextView) itemView.findViewById(R.id.right_item_title);
            price = (TextView) itemView.findViewById(R.id.price);
            shipping = (TextView) itemView.findViewById(R.id.shipping);
            heart = (ImageView) itemView.findViewById(R.id.shop_item_like);
            likes = (TextView) itemView.findViewById(R.id.likes_number);
        }
    }
}
