package demo.yb.com.wanandroid;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.yb.com.wanandroid.adapter.GankAdapter;
import demo.yb.com.wanandroid.entry.GankEntry;
import demo.yb.com.wanandroid.http.GankLoader;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    private GankLoader mGankLoader;
    private GankAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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

        addSubscription(subscription);
    }

    public static class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 20, 20);
        }
    }
}
