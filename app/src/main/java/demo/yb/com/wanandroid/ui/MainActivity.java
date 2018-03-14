package demo.yb.com.wanandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qihoo360.replugin.RePlugin;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.yb.com.wanandroid.Config;
import demo.yb.com.wanandroid.R;
import demo.yb.com.wanandroid.base.BaseActivity;
import demo.yb.com.wanandroid.entry.TabEntity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.tl)
    CommonTabLayout mTabLayout_2;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"首页"/*, "体系", "项目", "收藏"*/};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect/*, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect*/};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select/*, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select*/};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RePlugin.preload("app");
        SPUtils spUtils = SPUtils.getInstance();
        boolean study = spUtils.getBoolean("study");
        if (study) {
            try {
                Intent intent = RePlugin.createIntent("top.jowanxu.wanandroidclient", "top.jowanxu.wanandroidclient.ui.activity.MainActivity");
                RePlugin.startActivity(MainActivity.this,intent);
                spUtils.put("study", false);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showShort("插件出错！找360大大吧！");
            }
        }else {
            spUtils.put("study", true);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("玩什么Android，看妹纸吧！");
        ToastUtils.showShort(Config.hjText);
        initGirlPage();
    }

    private void initGirlPage() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
            mFragments.add(new GankFragment());
        }
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTabLayout_2.showMsg(0, new Random().nextInt(100) + 1);
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }


}
