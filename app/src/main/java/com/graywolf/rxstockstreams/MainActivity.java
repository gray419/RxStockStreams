package com.graywolf.rxstockstreams;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.graywolf.rxstockstreams.data.Message;
import com.graywolf.rxstockstreams.data.StreamResponse;
import com.graywolf.rxstockstreams.rxstocktwits.R;
import com.graywolf.rxstockstreams.service.StreamService;
import com.graywolf.rxstockstreams.ui.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private StreamService mStreamService;
    private StreamAdapter mAdapter;
    private List<Message> mMessages;

    private final static String TAG = "ReactiveEvents";

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
                .subscribe(sr -> setListAdapter(sr));

        test();
        test2();
        test3();
        test4();
        test5();
    }

    private void setListAdapter(StreamResponse sr) {
        mMessages = sr.getMessages();
        mAdapter = new StreamAdapter(mMessages);
        mStreamList.setAdapter(mAdapter);
    }

    private void test(){
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
            }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };

        myObservable.subscribe(mySubscriber);
    }

    private void test2(){
        Observable<String> myObservable =
                Observable.just("Hello, world 2!");

        Action1<String> onNextAction = s -> Log.i(TAG, s);
        myObservable.subscribe(onNextAction);
    }

    private void test3(){
        Observable.just("Hello, World 3!").subscribe(s -> {
            Log.i(TAG, s);
        });
    }

    private void test4(){
        Observable.just("Hello, world 4!")
                .subscribe(s -> Log.i(TAG, s));
    }

    private void test5(){
        Observable.just("Hello, World 5!")
                .map(s -> s + " - Unknown")
                .subscribe(s -> Log.i(TAG, s));
    }
}