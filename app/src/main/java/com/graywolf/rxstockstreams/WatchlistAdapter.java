package com.graywolf.rxstockstreams;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.graywolf.rxstockstreams.data.Symbol;
import com.graywolf.rxstockstreams.data.SymbolDetail;
import com.graywolf.rxstockstreams.rxstocktwits.R;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.ViewHolder> {
    public List<Symbol> mSymbols;
    public HashMap<String, SymbolDetail> mDetails;

    public WatchlistAdapter(List<Symbol> symbols, HashMap<String, SymbolDetail> details){
        mSymbols = symbols;
        mDetails = details;
    }

    @Override
    public WatchlistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.watchlist_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WatchlistAdapter.ViewHolder holder, int position) {
        Symbol s = mSymbols.get(position);

        holder.ticker.setText(s.Symbol);
        holder.title.setText(s.Title);
        if(mDetails.containsKey(s.Symbol)){
            SymbolDetail detail = mDetails.get(s.Symbol);
            holder.price.setText(String.format("$%.2f", detail.Price));
        }
    }

    @Override
    public int getItemCount() {
        return mSymbols == null ? 0 : mSymbols.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.price) TextView price;
        @Bind(R.id.ticker) TextView ticker;
        @Bind(R.id.symbolTitle) TextView title;

        public ViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
        }
    }
}
