package com.xiaoguo.caipuapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.bean.CategoryBean;

import java.util.List;

/**
 * Created by lenovo on 2016/7/2.
 */
public class ClassfiyEveryAdapter extends RecyclerView.Adapter<ClassfiyEveryAdapter.ClassfiyViewHolder> {
    private List<CategoryBean.ResultBean.ListBean> mList;
    private Context context;
    private LayoutInflater mInflater;

    public ClassfiyEveryAdapter(Context context,List<CategoryBean.ResultBean.ListBean> mList) {
        this.mList = mList;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public ClassfiyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.classfiy_every_id,null);
        return new ClassfiyViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ClassfiyViewHolder holder, int position) {
        holder.textButton.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ClassfiyViewHolder extends RecyclerView.ViewHolder {
        private Button textButton;
        public ClassfiyViewHolder(View itemView) {
            super(itemView);
            textButton = (Button) itemView.findViewById(R.id.classfiy_every_parentId_button);
        }
    }
}
