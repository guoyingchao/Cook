package com.xiaoguo.caipuapp.listener;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoguo.caipuapp.bean.CategoryBean;

import java.util.List;

/**
 * Created by lenovo on 2016/7/3.
 */
public class ClassfiyEveryListener implements RecyclerView.OnItemTouchListener {
    private RecyclerView recyclerView;
    private GestureDetector gestureDetector;
    private List<CategoryBean.ResultBean.ListBean> mList;
    private OnEveryItemTouchListener listener;
    public ClassfiyEveryListener () {
        
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
    public interface OnEveryItemTouchListener {
        public void onItemClicked(View item,String id);
    }
}
