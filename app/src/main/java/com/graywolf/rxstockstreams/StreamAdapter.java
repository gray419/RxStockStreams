package com.graywolf.rxstockstreams;

import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.graywolf.rxstockstreams.data.Message;
import com.graywolf.rxstockstreams.rxstocktwits.R;
import com.graywolf.rxstockstreams.utils.TimeUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.ViewHolder> {
    public List<Message> mMessages;

    public StreamAdapter(List<Message> msgs){
        mMessages = msgs;
    }

    @Override
    public StreamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StreamAdapter.ViewHolder holder, int position) {
        Message msg = mMessages.get(position);

        holder.timestamp.setText(TimeUtils.getFormattedTimeStamp(msg.CreatedAt));
        holder.username.setText(msg.User.UserName);
        holder.body.setText(msg.Body);
        Linkify.addLinks(holder.body, Linkify.WEB_URLS);
    }

    @Override
    public int getItemCount() {
        return mMessages == null ? 0 : mMessages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.username) TextView username;
        @Bind(R.id.timestamp) TextView timestamp;
        @Bind(R.id.body) TextView body;

        public ViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
        }
    }
}
