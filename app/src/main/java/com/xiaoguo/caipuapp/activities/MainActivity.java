package com.xiaoguo.caipuapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.fragments.CookbookFragment;
import com.xiaoguo.caipuapp.fragments.MyselfFragment;
import com.xiaoguo.caipuapp.fragments.ShopFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *  Fragment+Tab主界面的构写
 *  1.具体 List<Fragment> 集合 + RadioGroup
 *  2.List<Fragment>
 *    1.推荐 RecommandFragment
 *    2.类别 CategroyFragment
 *    3.搜索 SearchFragment
 *    4.我的 MyFragment
 */
public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private CookbookFragment cookbookFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.main_tabs);

        initFragment();

        //动态添加选项卡，从selector中选择图片
        TabLayout.Tab tabCookbook = tabLayout.newTab().setIcon(R.drawable.cookbook_selector).setText("食谱");
//        Log.i("123","sss----->");
        TabLayout.Tab tabShop = tabLayout.newTab().setIcon(R.drawable.shop_selector).setText("商城");
        TabLayout.Tab tabMyself = tabLayout.newTab().setIcon(R.drawable.myself_selector).setText("我的");

        tabLayout.addTab(tabCookbook);
        tabLayout.addTab(tabShop);
        tabLayout.addTab(tabMyself);
        tabLayout.setSelectedTabIndicatorHeight(0);//设置滚动条的高度
        tabLayout.setTabTextColors(Color.GRAY,Color.BLUE);//设置字体没选中和选中时的颜色

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {//从没选中到选中
                showFragmentById(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    //初始化碎片集合
    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new CookbookFragment());
        fragmentList.add(new ShopFragment());
        fragmentList.add(new MyselfFragment());
        showFragmentById(0);//默认选中第0个
    }
    //通过下标进行显示

    public void showFragmentById(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            Fragment fragment = fragmentList.get(i);
            if (!fragment.isAdded()) {
                transaction.add(R.id.main_fragments_container,fragment);
            }
            if (i == index) {
                transaction.show(fragmentList.get(i));
            }else {
                transaction.hide(fragmentList.get(i));
            }
        }

        transaction.commit();

    }
}
