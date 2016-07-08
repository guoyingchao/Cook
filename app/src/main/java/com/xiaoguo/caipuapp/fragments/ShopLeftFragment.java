package com.xiaoguo.caipuapp.fragments;


import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.bean.FragmentBean;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopLeftFragment extends Fragment {

    private String[] tabs = {"全部","海鲜","鲜肉","生禽","鲜蛋","熟食"};
    private ListView leftListView;
    private List<String> mList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_left, container, false);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        leftListView = (ListView) view.findViewById(R.id.shop_left_listView);
        for (int i = 0; i < tabs.length; i++) {
            mList.add(tabs[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.shop_left_item,R.id.shop_left_item_text,mList);
       View views=getActivity().getLayoutInflater().inflate(R.layout.shop_left_item,null);


        leftListView.setAdapter(adapter);
        adapter.getView(0,views,null).setSelected(true);
        Log.i("123","123-------------->"+adapter.getView(0,views,null));
        Drawable drawable=getActivity().getResources().getDrawable(R.drawable.shop_left_selector);
        adapter.getView(0,views,null).setBackground(drawable);

//        leftListView.getChildAt(0).setSelected(true);
//        showBackground(0-leftListView.getFirstVisiblePosition());
       /* leftListView.setSelected(true);
        leftListView.setSelection(0);
        leftListView.setItemChecked(0,true);
        leftListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);*/
        //adapter.getView(0,null,leftListView).setBackgroundColor(Color.GRAY);
//        Log.i("000", "onViewCreated: "+ ((TextView) adapter.getView(0, null, leftListView)).getText());

        leftListView.post(new Runnable() {
            @Override
            public void run() {
              /*  Log.i("1111", "run: "+((TextView) leftListView.getChildAt(1)).getText());
               leftListView.getChildAt(1).setSelected(true);*/
         /*        leftListView.setSelected(true);
        leftListView.setSelection(0);
        leftListView.setItemChecked(0,true);
        leftListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);*/
            //   leftListView.getChildAt(0).setBackgroundColor(Color.GRAY);
                EventBus.getDefault().post(new FragmentBean(0));
            }
        });

       /* leftListView.post(new Runnable() {
            @Override
        public void run() {
            leftListView.getChildAt(0).setBackgroundColor();
        }
    });*/
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(new FragmentBean(position));
                showBackground(position);
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void showBackground(int position){
        Drawable drawable=getActivity().getResources().getDrawable(R.drawable.shop_left_selector);
        for (int i = 0; i <mList.size(); i++) {
            if (position==i){
                leftListView.getChildAt(position).setSelected(true);
            }else {
                leftListView.getChildAt(i).setSelected(false);
            }
        }
        leftListView.getChildAt(position).setBackground(drawable);
    }
}
