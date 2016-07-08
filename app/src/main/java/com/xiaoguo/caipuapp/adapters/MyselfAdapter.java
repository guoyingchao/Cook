package com.xiaoguo.caipuapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.bean.MyselfItemBean;

import java.util.List;

/**
 * Created by lenovo on 2016/7/4.
 */
public class MyselfAdapter extends BaseAdapter {
    private Context context;
    private List<MyselfItemBean> mList;
    private LayoutInflater mInflater;
    public MyselfAdapter (Context context,List<MyselfItemBean> mList) {
        this.context = context;
        this.mList = mList;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.myself_item,null);
            viewHolder = new ViewHolder(convertView) ;
            viewHolder.v = convertView.findViewById(R.id.horizontal_line3);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.myself_image);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.myself_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MyselfItemBean bean = mList.get(position);
        viewHolder.textView.setText(bean.getText());
        viewHolder.imageView.setImageResource(bean.getImageId());
        return convertView;
    }
    public class ViewHolder {
        private View view;
        private ImageView imageView;
        private TextView textView;
        private View v;
        private ViewHolder(View view) {
            this.view = view;
        }
    }
}
