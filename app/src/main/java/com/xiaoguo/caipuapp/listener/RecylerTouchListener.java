package com.xiaoguo.caipuapp.listener;

import android.content.Context;
import android.content.Intent;
import android.renderscript.Int2;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoguo.caipuapp.activities.RecylerItemActivity;
import com.xiaoguo.caipuapp.bean.SearchBean;

import java.util.List;

/**
 * Created by lenovo on 2016/6/28.
 */
public class RecylerTouchListener implements RecyclerView.OnItemTouchListener {

    private RecyclerView recyclerView;
    private GestureDetector gestureDetector;
    private RecylerItemClickListener listener;
    private List<SearchBean.ResultBean.DataBean> list;

    public RecylerTouchListener(final Context context, List<SearchBean.ResultBean.DataBean> list,RecyclerView recyclerView, RecylerItemClickListener listener) {
        this.listener = listener;
        this.recyclerView = recyclerView;
        this.list=list;
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
            View item = recyclerView.findChildViewUnder(e.getX(),e.getY());
            int position = recyclerView.getChildAdapterPosition(item);
            SearchBean.ResultBean.DataBean dataBean = list.get(position);
            listener.onItemClicked(item,dataBean.getId());
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
    public interface RecylerItemClickListener {
        public void onItemClicked(View item,String id);
    }
}
