package com.graywolf.rxstockstreams;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.graywolf.rxstockstreams.data.Symbol;
import com.graywolf.rxstockstreams.data.SymbolDetail;
import com.graywolf.rxstockstreams.data.SymbolDetailResponse;
import com.graywolf.rxstockstreams.rxstocktwits.R;
import com.graywolf.rxstockstreams.service.StreamService;
import com.graywolf.rxstockstreams.ui.DividerItemDecoration;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WatchlistActivity extends AppCompatActivity {
    private StreamService mService;
    private List<Symbol> mSymbols;
    private String mQueryParam;
    private Context mContext;

    @Bind(R.id.watchlist) RecyclerView mWatchlistRecycler;

    @SuppressWarnings("unused")
    @OnClick(R.id.button) void goToStream(){
        startActivity(new Intent(this, StreamActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);
        ButterKnife.bind(this);
        mContext = this;

        mWatchlistRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mWatchlistRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mService = new StreamService();
        mService.getApi().getWatchlist()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(wl -> createQueryString(wl.Watchlist.Symbols))
                .flatMap(symbols -> mService.getApi().getSymbolDetails(mQueryParam))
                .subscribe(new Observer<SymbolDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(SymbolDetailResponse sdr) {
                        prepareAdapter(sdr.Prices);
                    }
                });
    }

    private void prepareAdapter(HashMap<String, SymbolDetail> details){
        WatchlistAdapter adapter = new WatchlistAdapter(mSymbols, details);
        runOnUiThread(() -> mWatchlistRecycler.setAdapter(adapter));
    }

    private void createQueryString(List<Symbol> s){
        mSymbols = s;

        ArrayList<String> tickers = new ArrayList<>();
        for(Symbol sym : mSymbols){
            tickers.add(sym.Symbol.replace(".","-"));
        }

        mQueryParam = StringUtils.join(tickers, ",");
    }
}