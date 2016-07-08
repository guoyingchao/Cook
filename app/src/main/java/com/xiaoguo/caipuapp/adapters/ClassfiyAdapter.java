package com.xiaoguo.caipuapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.bean.CategoryBean;
import com.xiaoguo.caipuapp.bean.ClassfiyImageAndTitleBean;
import com.xiaoguo.caipuapp.urls.ClassfiyImageUrl;

import java.util.List;

/**
 * Created by lenovo on 2016/7/2.
 */
public class ClassfiyAdapter extends BaseAdapter {
    private String[]arr;
    private LayoutInflater mIflater;
    private List<CategoryBean.ResultBean> mData;
    private Context context;
    public ClassfiyAdapter(Context context,List<CategoryBean.ResultBean> mData,String[]arr) {
        this.context = context;
        this.arr=arr;
        this.mData=mData;
        mIflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mIflater.inflate(R.layout.classfiy_every_item,null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.classfiy_every_image);
            viewHolder.textTitle = (TextView) convertView.findViewById(R.id.classfiy_every_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CategoryBean.ResultBean bean = mData.get(position);

        Picasso.with(context).load(arr[position]).config(Bitmap.Config.RGB_565).placeholder(R.drawable.icon).resize(80,80)
                .centerCrop().into(viewHolder.image);
        viewHolder.textTitle.setText(bean.getName());
        return convertView;
    }
    public class ViewHolder {
        private TextView textTitle;
        private ImageView image;
        private View view;
        public ViewHolder (View view) {
            this.view = view;
        }
    }
}
