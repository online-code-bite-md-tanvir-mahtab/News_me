package com.tanvircodder.newsme;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {
//    now i am going to store the value to the array data..//
    private String[] data;
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
        holder.mTitleTextView.setText(data[position]);
        holder.mContentTextView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        if (data == null)return 0;
        return data.length;
    }
    public void swapData(String[] data){
        this.data = data;
        if (data != null){
            notifyDataSetChanged();
        }
    }

    //    now ia m going to crete an class that will hold the data
//    of the view..//
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mContentTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title);
            mContentTextView = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
