package com.xiaoguo.caipuapp.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.activities.RecylerItemActivity;
import com.xiaoguo.caipuapp.activities.SearchActivity;
import com.xiaoguo.caipuapp.adapters.RecommentAdapter;
import com.xiaoguo.caipuapp.bean.RecommendBean;
import com.xiaoguo.caipuapp.bean.SearchBean;
import com.xiaoguo.caipuapp.listener.RecylerTouchListener;
import com.xiaoguo.caipuapp.urls.UrlRecommend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecommendFragment extends Fragment {

    private RecyclerView recylerView;
//    private List<SearchBean> searchBeenList =new ArrayList<>();
    private RecommentAdapter adpter;
    public static final String KEY = "key";
    private int position;
    private OkHttpClient client;
    private String jsonData;
//    private int index;
    public static RecommendFragment newInstance(int position) {
        RecommendFragment rf = new RecommendFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY,position);
        rf.setArguments(bundle);
        return rf;
    }
    private List<SearchBean.ResultBean.DataBean> list=new ArrayList<>();
    private ViewPager viewPager;
    private List<View> mList;
    private int pagerCount;
    private static final int MSG_TURN = 0x01;
    public static final int  MSG_TURN_STOP = 0x12;
//    public static final int  MSG_BACK_RESET = 0x99;
    public static final long DURATION_TURN = 2000;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_TURN://开始轮播
                    pagerCount = viewPager.getCurrentItem();
                    Log.i("123","");
                    pagerCount++;
                    viewPager.setCurrentItem(pagerCount);
                    handler.sendEmptyMessageDelayed(MSG_TURN,DURATION_TURN);

                break;
                case MSG_TURN_STOP://停止轮播
                    handler.removeMessages(MSG_TURN);//移除轮播消息
                    break;
            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new OkHttpClient();
        if (getArguments() != null) {
            position = getArguments().getInt(KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * 内容单行的实现
         */
        recylerView = (RecyclerView) view.findViewById(R.id.recylerView_recommend);
//        initItemData();
//        adpter = new RecommentAdapter(getActivity(),rbList);
        recylerView.setLayoutManager(getLinearLayout());
//        recylerView.setAdapter(adpter);

        /**
         * 轮播图的实现
         */
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_recommend);
        initData();
        viewPager.setAdapter(new MyPagerAdapter());
        handler.sendEmptyMessageDelayed(MSG_TURN,DURATION_TURN);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                pagerCount=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {//滑动状态判断
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE://停止滑动状态
                        if (!handler.hasMessages(MSG_TURN)) {
                            handler.sendEmptyMessageDelayed(MSG_TURN,DURATION_TURN);//重新轮播
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING://手指滑动状态
                        handler.hasMessages(MSG_TURN_STOP);//发送停止的消息
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        break;
                }
            }
        });
        viewPager.setCurrentItem(Integer.MAX_VALUE/2);

        /**
         * 区分网络请求的类型
         */
        switch (position) {
            case 0:
                getRecommendJsonData(recylerView, UrlRecommend.URL_RCOMMEND);
                break;
            case 1:
                getRecommendJsonData(recylerView, UrlRecommend.URL_CATEROY);
                break;
        }

    }
    public RecyclerView.LayoutManager getLinearLayout () {
        return new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
    }
//    private void initItemData(String url) {
//        for (int i = 0; i < 30; i++) {
//            RecommendBean bean = new RecommendBean();
//            bean.setTitle("1");
//            bean.setContent("红烧肉与鱼香肉丝" + i);
//            Drawable drawable = getResources().getDrawable(R.drawable.pic2);
//            BitmapDrawable bd = (BitmapDrawable) drawable;
//            Bitmap bm = bd.getBitmap();
//            bean.setBitmap(bm);
//            rbList.add(bean);
//        }
//    }

    //此时是本地的图片
    private void initData() {//加载数据源
        int[] pic = {R.drawable.pager1,R.drawable.pager2,R.drawable.pager3,R.drawable.pager4};
        mList = new ArrayList<>();
        for (int i = 0; i < pic.length; i++) {//为每个imageview动态添加图片
            //动态添加imageview
            ImageView image = new ImageView(getActivity());
            //设置宽度和高度
            image.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            image.setImageResource(pic[i]);//图片源
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            mList.add(image);
        }
    }

    /**
     * 获取网络数据
     */
    public void getRecommendJsonData(final RecyclerView recylerView, String website) {
//        SearchBean bean;
        for (int i = 1; i < 6; i++) {
            final int j=i;
            Request request = new Request.Builder().get().url(website+j).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //这是失败的情况
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    jsonData = response.body().string();
                    if (jsonData != null) {
                         final SearchBean bean = new Gson().fromJson(jsonData, SearchBean.class);
                        Log.i("111", "bean: "+bean);
                        list.addAll(bean.getResult().getData());
                        if(j==5){
                            recylerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    adpter = new RecommentAdapter(getActivity(), list);
                                    recylerView.setAdapter(adpter);
                                    recylerView.addOnItemTouchListener(new RecylerTouchListener(getActivity(), list,recylerView, new RecylerTouchListener.RecylerItemClickListener() {
                                        @Override
                                        public void onItemClicked(View item, String id) {

                                        Intent intent = new Intent();
                                        intent.setClass(getActivity(), RecylerItemActivity.class);
//                                            Toast.makeText(getActivity(), list.get(position).getId(), Toast.LENGTH_SHORT).show();
                                        intent.putExtra("id",id);
                                        getActivity().startActivity(intent);
                                        }
                                    }));
                                }
                            });

                        }

                       /* recylerView.post(new Runnable() {
                            @Override
                            public void run() {
                                adpter = new RecommentAdapter(getActivity(), bean);
                                recylerView.setAdapter(adpter);
                            }
                        });*/
                    }

                }

            });

        }
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {//数据源大小
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        /**
         * 视图对象动态绘制 (目的是节省内存的开销,一般情况加载的图片资源是比较耗内存的) 返回的是Object数据源的对象
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int index = position % mList.size();
            ImageView v = (ImageView) mList.get(index);
            container.addView(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SearchActivity.class);
                    startActivity(intent);
                }
            });
            return v;
        }

        /**
         * 视图对象动态删除
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            int index = position % mList.size();
            View v = mList.get(index);
            container.removeView(v);
        }
    }
}
