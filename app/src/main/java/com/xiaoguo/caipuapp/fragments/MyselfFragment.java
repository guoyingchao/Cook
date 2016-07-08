package com.xiaoguo.caipuapp.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.adapters.MyselfAdapter;
import com.xiaoguo.caipuapp.bean.MyselfItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyselfFragment extends Fragment {
    private String[] text = {"我的收藏","意见反馈","我的订单","设置","关于我们"};
    private ListView listView;
    private MyselfAdapter adapter;
    private List<MyselfItemBean> mList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myself, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.myself);
        for (int i = 0; i < text.length; i++) {
            MyselfItemBean bean = new MyselfItemBean();
            bean.setImageId(R.drawable.next);
            bean.setText(text[i]);
            mList.add(bean);
        }
        Log.i("777", "mlist: " + mList.size());
        adapter = new MyselfAdapter(getActivity(),mList);
        Log.i("777", "adapter 调用了: ");
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("友情提示");
                builder.setMessage("亲，您还没有登录呢，哈哈哈～～");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
    }
}
