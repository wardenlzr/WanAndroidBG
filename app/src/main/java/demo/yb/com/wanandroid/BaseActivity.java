package demo.yb.com.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
}
