package com.xiaoguo.caipuapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.activities.WebViewActivity;
import com.xiaoguo.caipuapp.adapters.ShopRightAdapter;
import com.xiaoguo.caipuapp.bean.FragmentBean;
import com.xiaoguo.caipuapp.bean.ShopItemBean;
import com.xiaoguo.caipuapp.listener.ShopItemItemLisener;
import com.xiaoguo.caipuapp.urls.UrlRecommend;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopRightFragment extends Fragment {
    private RecyclerView recyclerView;
    private ShopRightAdapter adapter;
    private OkHttpClient client;
    private List<ShopItemBean.CatListBean.SubListBean> mList = new ArrayList<>();
    private List<ShopItemBean.CatListBean.SubListBean.ShareListBean> itemList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_right, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.shop_right_recyler);
        client = new OkHttpClient();
        recyclerView.setLayoutManager(getGridView());
        //getJsonData(recyclerView,0);

    }
    public RecyclerView.LayoutManager getGridView() {
        return new GridLayoutManager(getActivity(),2);
    }
    @Subcriber
    public void onMainEventThread(FragmentBean fragmentBean) {
        Log.i("000", "onMainEventThread: --------------" + fragmentBean.getPosition());
        getJsonData(recyclerView,fragmentBean.getPosition());

    }
    public void getJsonData(final RecyclerView recyclerView, final int position) {
        Request request = new Request.Builder().get().url(UrlRecommend.URL_SHOP).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonData = response.body().string();
                ShopItemBean bean = new Gson().fromJson(jsonData,ShopItemBean.class);
                mList = bean.getCatList().get(5).getSubList();
                Request request1 = new Request.Builder().get().url(UrlRecommend.URL_SHOP_ITEM[position]).build();
                client.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String jsonItem = response.body().string();
                        ShopItemBean.CatListBean.SubListBean beanItem = new Gson().fromJson(jsonItem, ShopItemBean.CatListBean.SubListBean.class);
                        itemList = beanItem.getShareList();
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < itemList.size(); i++) {
                                    adapter = new ShopRightAdapter(getActivity(), itemList);
                                    recyclerView.setAdapter(adapter);
                                }
                                    recyclerView.addOnItemTouchListener(new ShopItemItemLisener(getActivity(), itemList, new ShopItemItemLisener.ShopItemItemOnClickListener() {
                                        @Override
                                        public void onItemClickListener(String url) {
                                            Log.i("888", "onItemClickListener: 进来了，url ----" + url);
                                            Intent intent = new Intent();
                                            intent.setClass(getActivity(), WebViewActivity.class);
                                            intent.putExtra("url",url);
                                            startActivity(intent);
                                        }
                                    },recyclerView));
                                }
                        });
                    }
                });

            }
        });
    }
}
