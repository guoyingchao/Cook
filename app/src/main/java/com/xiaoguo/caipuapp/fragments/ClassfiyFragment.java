package com.xiaoguo.caipuapp.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.activities.ClassfiyActivity;
import com.xiaoguo.caipuapp.adapters.ClassfiyAdapter;
import com.xiaoguo.caipuapp.bean.CategoryBean;
import com.xiaoguo.caipuapp.bean.ClassfiyImageAndTitleBean;
import com.xiaoguo.caipuapp.urls.ClassfiyImageUrl;
import com.xiaoguo.caipuapp.urls.UrlRecommend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassfiyFragment extends Fragment {

    private GridView gridView;
    private Request request;
    private OkHttpClient client;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classfiy, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.classfiy_gridView);
        client = new OkHttpClient();
        getClassfiyData(UrlRecommend.URL_CATEROY);

    }
    public void getClassfiyData( String website) {
        request = new Request.Builder().get().url(website).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String jsonData=response.body().string();
                final CategoryBean bean=new Gson().fromJson(jsonData,CategoryBean.class);
                final List<CategoryBean.ResultBean> data=new ArrayList<CategoryBean.ResultBean>();
                    data.addAll(bean.getResult());
                Log.i("TAG","-------------->"+data.get(21).getName());
                gridView.post(new Runnable() {
                    @Override
                    public void run() {
                       ClassfiyAdapter adapter=new ClassfiyAdapter(getActivity(),data, ClassfiyImageUrl.urlImage);
                        gridView.setAdapter(adapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent();
                                intent.setClass(getActivity(),ClassfiyActivity.class);
                                intent.putExtra("parentId", bean.getResult().get(position).getParentId());
                                Log.i("555", "bean.getResult().get(position).getParentId(): "+bean.getResult().get(position).getParentId());
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.classfiyin,R.anim.classfiyout);
                            }
                        });
                    }
                });

            }
        });
    }
}
