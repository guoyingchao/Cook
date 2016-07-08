package com.xiaoguo.caipuapp.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoguo.caipuapp.bean.ShopItemBean;

import java.util.List;

/**
 * Created by lenovo on 2016/7/5.
 */
public class ShopItemItemLisener implements RecyclerView.OnItemTouchListener {
    private RecyclerView recyclerView;
    private GestureDetector gestureDetector;
    private ShopItemItemOnClickListener listener;
    private List<ShopItemBean.CatListBean.SubListBean.ShareListBean> mList;

    public ShopItemItemLisener (Context context,List<ShopItemBean.CatListBean.SubListBean.ShareListBean> mList
    ,ShopItemItemOnClickListener listener,RecyclerView recyclerView){
        this.mList = mList;
        this.listener = listener;
        this.recyclerView = recyclerView;
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if (gestureDetector.onTouchEvent(e) && listener != null) {
            View itemView = recyclerView.findChildViewUnder(e.getX(),e.getY());
            int position = recyclerView.getChildAdapterPosition(itemView);
            ShopItemBean.CatListBean.SubListBean.ShareListBean bean = mList.get(position);
            listener.onItemClickListener(bean.getGoods_url());
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
    public interface ShopItemItemOnClickListener{
        public void onItemClickListener(String url);
    }
}
