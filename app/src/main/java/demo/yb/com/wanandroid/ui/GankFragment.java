package demo.yb.com.wanandroid.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import demo.yb.com.wanandroid.BaseFragment;
import demo.yb.com.wanandroid.MainActivity;
import demo.yb.com.wanandroid.R;
import demo.yb.com.wanandroid.adapter.GankAdapter;
import demo.yb.com.wanandroid.entry.GankEntry;
import demo.yb.com.wanandroid.http.GankLoader;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created on 2018/2/7  17:27.
 *
 * @author yubin
 */

public class GankFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    private GankLoader mGankLoader;
    private GankAdapter mAdapter;
    @Override
    protected int initLayoutId() {
        return R.layout.gank_fragment;
    }
    @Override
    protected void initView(View view) {
        mGankLoader = new GankLoader();
        initView();
        getGankList();
    }
    private void initView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new MyItemDecoration());
        mAdapter = new GankAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void getGankList() {
        Subscription subscription = mGankLoader.getGankList().subscribe(new Action1<List<GankEntry>>() {
            @Override
            public void call(List<GankEntry> gankEntries) {
                Log.i("BG", "gank size:" + gankEntries.size());
                mAdapter.setData(gankEntries);
                mAdapter.notifyDataSetChanged();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        ((MainActivity)getActivity()).addSubscription(subscription);
    }

    public static class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 20, 20);
        }
    }


    @Override
    protected void initData() {

    }
}
