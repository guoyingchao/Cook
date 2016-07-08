package com.xiaoguo.caipuapp.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.activities.SearchActivity;
import com.xiaoguo.caipuapp.adapters.CookbookFragemtAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CookbookFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> mList;//定义数据源
    private String[] titleTab;//定义选项卡
    private ImageView searchImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleTab = new String[] {"推荐","分类"};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cookbook, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabLayout) view.findViewById(R.id.cookbook_tab);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_Main);
        searchImage = (ImageView) view.findViewById(R.id.imageview);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
            }
        });

        //动态添加选项卡
        for (int i = 0; i < titleTab.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab().setText(titleTab[i]);

            tabLayout.setTabTextColors(Color.GRAY,Color.BLUE);
            tabLayout.addTab(tab);
            tabLayout.setSelectedTabIndicatorHeight(3);
            tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
        }
        //添加碎片
        mList = new ArrayList<>();
        mList.add(new RecommendFragment());
        mList.add(new ClassfiyFragment());
        //适配器
        CookbookFragemtAdapter adapter = new CookbookFragemtAdapter(getChildFragmentManager(),mList,titleTab);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);//将tab和viewpager进行关联
    }

}
