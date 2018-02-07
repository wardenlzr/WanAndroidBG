package demo.yb.com.wanandroid;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created on 2018/2/7  17:47.
 *
 * @author yubin
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
