package demo.yb.com.wanandroid.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {


    protected Context mContext;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (initLayoutId() != -1) {
            view = inflater.inflate(initLayoutId(), null);
            unbinder = ButterKnife.bind(this, view);
        } else if (loadView(null) != null) {
            view = loadView(null);
            unbinder = ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract int initLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData();


    public View loadView(View view) {
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 给View 设置焦点  让edittext失去焦点
     * 2016年12月15日16:54:10
     *
     * @param view
     */
    public void setEtFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    /**
     * 不带参数的跳转Activity
     * 2016年12月15日16:04:39
     */
    public void startAct(Class clazz) {
        startActivity(new Intent(mContext, clazz));
    }

}
