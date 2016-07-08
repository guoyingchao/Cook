package com.xiaoguo.caipuapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.adapters.ClassfiyEveryAdapter;
import com.xiaoguo.caipuapp.bean.CategoryBean;
import com.xiaoguo.caipuapp.urls.UrlRecommend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClassfiyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OkHttpClient client;
    private ClassfiyEveryAdapter adapter;
    private String parentId;
    private TextView textTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classfiy);
        textTitle = (TextView) findViewById(R.id.classfiy_every_title);
        client = new OkHttpClient();
        Intent intent = getIntent();
        parentId = intent.getStringExtra("parentId");
        recyclerView = (RecyclerView) findViewById(R.id.classfiy_every_parentId);
        recyclerView.setLayoutManager(getDridView());
        getJsonData(recyclerView,UrlRecommend.URL_CATEROY);
    }
    public RecyclerView.LayoutManager getDridView() {
        return new GridLayoutManager(this,4);//4列
    }

    public void getJsonData(final RecyclerView recyclerView, String website) {
//        Log.i("555", "-----------------------: ajgjzskljgklajs" );
        final Request request = new Request.Builder().get().url(website).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                CategoryBean bean = new Gson().fromJson(jsonData,CategoryBean.class);
                final List<CategoryBean.ResultBean> result = bean.getResult();
               // mList.addAll(bean.getList());
             //   Log.i("555", "mList: " + mList.size());
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getParentId() .equals(parentId) ) {
                        final int j=i;
                        Log.i("555", "bean: " + bean);
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                textTitle.setText(result.get(j).getName());
                                adapter = new ClassfiyEveryAdapter(ClassfiyActivity.this,result.get(j).getList());
                                recyclerView.setAdapter(adapter);
                            }
                        });
                    }
                }

            }
        });
    }
}
