package com.graywolf.rxstockstreams;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.graywolf.rxstockstreams.data.Message;
import com.graywolf.rxstockstreams.data.StreamResponse;
import com.graywolf.rxstockstreams.rxstocktwits.R;
import com.graywolf.rxstockstreams.service.StreamService;
import com.graywolf.rxstockstreams.ui.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private StreamService mStreamService;
    private StreamAdapter mAdapter;
    private List<Message> mMessages;

    @Bind(R.id.stream) RecyclerView mStreamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mStreamList.setLayoutManager(new LinearLayoutManager(this));
        mStreamList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mStreamService = new StreamService();
        mStreamService.getApi()
                .getMessages()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StreamResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StreamResponse streamResponse) {
                        mMessages = streamResponse.getMessages();
                        mAdapter = new StreamAdapter(mMessages);
                        mStreamList.setAdapter(mAdapter);
                    }
                });
    }
}