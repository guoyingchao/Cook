package com.xiaoguo.caipuapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.adapters.RecommendRecylerItemAdapter;
import com.xiaoguo.caipuapp.bean.RecylerEveryBean;
import com.xiaoguo.caipuapp.urls.UrlRecommend;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RecylerItemActivity extends AppCompatActivity {

//    private OkHttpClient client;
    private ImageView topImage;
    private TextView textNetTitle;
//    private TextView textType;
    private TextView textNetType;
//    private TextView textImtro;
    private TextView textNetImtro;
//    private TextView textIngtedients;
    private TextView textNetIngtedients;
//    private TextView textBurden;
    private TextView textNetBurden;
//    private TextView textStups;
//    private View view;
    private RequestQueue requestQueue;
//    private String newUrl;
    private RecommendRecylerItemAdapter adapter;
    private List<RecylerEveryBean.ResultBean.DataBean.StepsBean> mList;
    private RecyclerView recylerView;
    private RecylerEveryBean.ResultBean.DataBean.StepsBean stepsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_item);
        initView();
        requestQueue = Volley.newRequestQueue(this);
        recylerView = (RecyclerView) findViewById(R.id.recyler_item_recylerView);
        recylerView.setLayoutManager(getLinearLayout());
        mList = new ArrayList<>();

//        client=new OkHttpClient();
        /**
         * 接收传过来的id
         */
        Intent intent = getIntent();
//        int id=Integer.parseInt(intent.getStringExtra("id"));
         String id = intent.getStringExtra("id");
//        Toast.makeText(RecylerItemActivity.this, "id:" + id, Toast.LENGTH_SHORT).show();
        String newUrl = UrlRecommend.URL_EVERY + id;
        Log.i("111", "newUrl: " + newUrl);
      //  getStepsJsonData(newUrl);
        initVolley(newUrl);
    }
    public void initView () {
        topImage = (ImageView) findViewById(R.id.recyler_item_image);
        textNetTitle = (TextView) findViewById(R.id.recyler_item_title);
        textNetType = (TextView) findViewById(R.id.recyler_item_tags);
        textNetImtro = (TextView) findViewById(R.id.recyler_item_imtro);
//        textIngtedients = (TextView) findViewById(R.id.ingtedients);
        textNetIngtedients = (TextView) findViewById(R.id.recyler_item_ingredients);
//        textBurden = (TextView) findViewById(R.id.burden);
        textNetBurden = (TextView) findViewById(R.id.recyler_item_burden);
//        textStups = (TextView) findViewById(R.id.stups);
//        recyclerView = (RecyclerView) findViewById(R.id.recyler_item_recylerView);
//        view = findViewById(R.id.recyler_item_view);
    }
    public RecyclerView.LayoutManager getLinearLayout () {
        return new LinearLayoutManager(RecylerItemActivity.this,LinearLayoutManager.VERTICAL,false);
    }

    public void initVolley(String web){
        StringRequest stringRequest = new StringRequest(web, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                RecylerEveryBean reBean = new Gson().fromJson(response,RecylerEveryBean.class);
                Log.i("111", "reBean: " + reBean);

                String topPicUrl = reBean.getResult().getData().get(0).getAlbums().get(0);
                Picasso.with(RecylerItemActivity.this).load(topPicUrl).config(Bitmap.Config.RGB_565).into(topImage);
                textNetTitle.setText(reBean.getResult().getData().get(0).getTitle());
                textNetType.setText(reBean.getResult().getData().get(0).getTags());
                textNetImtro.setText(reBean.getResult().getData().get(0).getImtro());
                Log.i("222", "textNetImtro: " + textNetImtro);
                textNetIngtedients.setText(reBean.getResult().getData().get(0).getIngredients());
                textNetBurden.setText(reBean.getResult().getData().get(0).getBurden());
                Log.i("222", "textNetIngtedients: " + textNetIngtedients);
                for (int i = 0; i < reBean.getResult().getData().get(0).getSteps().size(); i++) {
//                    mList.add(reBean.getResult().getData().get(0).getSteps().get(i).);
                    stepsBean = new RecylerEveryBean.ResultBean.DataBean.StepsBean();
                    stepsBean.setImg(reBean.getResult().getData().get(0).getSteps().get(i).getImg());
                    stepsBean.setStep(reBean.getResult().getData().get(0).getSteps().get(i).getStep());
                    mList.add(stepsBean);
                }


                adapter = new RecommendRecylerItemAdapter(RecylerItemActivity.this,mList);
                Log.i("tag123",mList.toString());
//                recylerView.setLayoutManager(getLinearLayout());
                Log.i("222", "adapter: " + adapter);
                recylerView.setAdapter(adapter);
                Log.i("222", "recyler: " + recylerView);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }
        );
        requestQueue.add(stringRequest);
    }
}
