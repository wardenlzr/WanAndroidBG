package demo.yb.com.wanandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.yb.com.wanandroid.R;
import demo.yb.com.wanandroid.base.BaseActivity;

public class ImgDetailActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Picasso.with(mContext).load(url).into(image);
    }

    @OnClick(R.id.image)
    public void onClick() {
        finish();
    }
}
