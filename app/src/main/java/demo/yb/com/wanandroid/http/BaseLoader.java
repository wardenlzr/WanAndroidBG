package demo.yb.com.wanandroid.http;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BaseLoader {
    /**
     * @param observable
     * @param <T>
     * @return
     */
    protected <T> Observable<T> observe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
