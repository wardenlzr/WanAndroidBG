package demo.yb.com.wanandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import demo.yb.com.wanandroid.R;
import demo.yb.com.wanandroid.entry.GankEntry;


public class GankAdapter extends RecyclerView.Adapter {
    private List<GankEntry> mGankEntries;
    public Context mContext;

    public GankAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<GankEntry> gankEntries) {
        mGankEntries = gankEntries;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        GankEntry entry = mGankEntries.get(position);

        Picasso.with(mContext).load(entry.url).into(viewHolder.mImageView);
        viewHolder.descText.setText(entry.desc);
        viewHolder.authorText.setText(entry.who);

    }

    @Override
    public int getItemCount() {
        return mGankEntries == null ? 0 : mGankEntries.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView descText;
        public TextView authorText;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.gank_iamge);
            descText = (TextView) itemView.findViewById(R.id.desc);
            authorText = (TextView) itemView.findViewById(R.id.author);
        }
    }
}
