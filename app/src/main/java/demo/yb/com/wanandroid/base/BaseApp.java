package demo.yb.com.wanandroid.base;


import com.blankj.utilcode.util.Utils;
import com.qihoo360.replugin.RePluginApplication;

/**
 * Created on 2018/2/7  17:47.
 *
 * @author yubin
 */

public class BaseApp extends RePluginApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
