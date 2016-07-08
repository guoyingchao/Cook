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
import com.xiaoguo.caipuapp.bean.RecylerEveryBean;

import java.util.List;

/**
 * Created by lenovo on 2016/6/30.
 */
public class RecommendRecylerItemAdapter extends RecyclerView.Adapter<RecommendRecylerItemAdapter.RecylerHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<RecylerEveryBean.ResultBean.DataBean.StepsBean> reList;
    public RecommendRecylerItemAdapter(Context context,List<RecylerEveryBean.ResultBean.DataBean.StepsBean> reList) {
        this.reList = reList;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }
    public RecommendRecylerItemAdapter(Context context,RecylerEveryBean recylerEveryBean) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        if (recylerEveryBean != null) {
            reList = recylerEveryBean.getResult().getData().get(0).getSteps();
        }
    }
    @Override
    public RecylerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.recyler_item_steps,null);
        return new RecylerHolder(convertView);
    }

    @Override
    public void onBindViewHolder(RecylerHolder holder, int position) {
        holder.stepsText.setText(reList.get(position).getStep());
        String url = reList.get(position).getImg();
        Log.i("222", "url: " + url);
        Picasso.with(context).load(url).config(Bitmap.Config.RGB_565).placeholder(R.drawable.test1)
                .into(holder.stepsImage);
        Log.i("222", "stepsImage: " + holder.stepsImage);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return reList == null ? 0 : reList.size();
    }

    public class RecylerHolder extends RecyclerView.ViewHolder {
        private ImageView stepsImage;
        private TextView stepsText;
        public RecylerHolder(View itemView) {
            super(itemView);
            stepsImage = (ImageView) itemView.findViewById(R.id.recyler_item_steps_image);
            stepsText = (TextView) itemView.findViewById(R.id.recyler_item_steps);
        }
    }
}
