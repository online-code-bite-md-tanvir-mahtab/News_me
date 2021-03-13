package com.tanvircodder.newsme;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tanvircodder.newsme.internet.GlideApp;
import com.tanvircodder.newsme.internet.MyGlide;
import com.tanvircodder.newsme.internet.UriParseAndHttpConnection;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {
//    now i am going to store the value to the array data..//
    private List<Util> data;
    private Context mContext;
    public AdapterList(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Util myUtil = data.get(position);
        String title = myUtil.getTitle();
        String source = myUtil.getSource();
        String image = myUtil.getImageURI();
//        for the date...//
        String date = myUtil.getPublish();
        holder.mTitleTextView.setText(title);
        holder.mPublisherTextView.setText(source);
        Glide.with(mContext)
                .load(image)
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(holder.mContentImageView);
//        now i am going to set the value to the right layot..//
        holder.mPublisDate.setText(date);
    }

    @Override
    public int getItemCount() {
        if (data == null)return 0;
        return data.size();
    }
    public void swapData(List<Util> data){
        this.data = data;
        if (data != null){
            notifyDataSetChanged();
        }
    }

    //    now ia m going to crete an class that will hold the data
//    of the view..//
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private ImageView mContentImageView;
        private TextView mPublisherTextView;
        private TextView mPublisDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContentImageView = (ImageView) itemView.findViewById(R.id.content_image);
            mTitleTextView = (TextView) itemView.findViewById(R.id.content_title);
            mPublisherTextView = (TextView) itemView.findViewById(R.id.publisher);
            mPublisDate = (TextView) itemView.findViewById(R.id.publis_date);
        }
    }
}
