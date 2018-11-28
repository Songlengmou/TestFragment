package com.anningtex.testfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPage;

    private ArrayList<Fragment> frags;
    private ArrayList<String> titles;
    private Myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        viewPage = findViewById(R.id.viewPage);
        tabLayout = findViewById(R.id.tabLayout);

        //tab名的列表
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        frags = new ArrayList<>();
        frags.add(new OneFragment());
        frags.add(new TwoFragment());

        titles = new ArrayList<>();
        titles.add("库存");
        titles.add("设置");

        tabLayout.setupWithViewPager(viewPage);
        adapter = new Myadapter(getSupportFragmentManager());
        //联动
        viewPage.setAdapter(adapter);
        viewPage.setCurrentItem(0);
        tabLayout.getTabAt(0).select();
    }

    //适配器
    public class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getfragment(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        public Fragment currentFragment;

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            currentFragment = (Fragment) object;
        }
    }

    //动态创建Fragment的方法
    public Fragment getfragment(int position) {
        Fragment fg = frags.get(position);
        if (fg != null) {
        } else {//返回一个默认的fragment
            fg = new OneFragment();
        }
        return fg;
    }
}
