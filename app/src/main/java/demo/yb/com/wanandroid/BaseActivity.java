package demo.yb.com.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity {

    public BaseActivity mContext;
    private static final String TAG ="BaseActivity" ;
    /**
     *
     */
    private CompositeSubscription sCompositeSubscription ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(sCompositeSubscription == null || sCompositeSubscription.isUnsubscribed()){
            sCompositeSubscription = new CompositeSubscription();
        }
        mContext = this;
    }

    /**
     * 添加Subscription
     * @param subscription
     */
    public void addSubscription(Subscription subscription){
        Log.d(TAG,"add subscription");
        sCompositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sCompositeSubscription!=null){
            Log.d(TAG,"base activity unscbscribe");
            sCompositeSubscription.unsubscribe();
        }
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
