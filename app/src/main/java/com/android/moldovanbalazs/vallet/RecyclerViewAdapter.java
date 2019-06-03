package com.android.moldovanbalazs.vallet;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.moldovanbalazs.vallet.domain.Wallet;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecycleViewHolder> {

    private final List<Wallet> data;

    public RecyclerViewAdapter(List<Wallet> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View recyclerRow = layoutInflater.inflate(R.layout.recycler_item, viewGroup, false);
        return new RecycleViewHolder(recyclerRow);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, int i) {


        //recycleViewHolder.id.setText(i);
        recycleViewHolder.currency.setText("currency: " + currencyAdatper(data.get(i).getCurrency()));
        recycleViewHolder.sum.setText(String.valueOf("sum: " + data.get(i).getSum()));
        recycleViewHolder.registrationDate.setText("registration date: " + data.get(i).getRegistrationDate().toLocaleString());
        recycleViewHolder.expirationDate.setText("expiration date: " + data.get(i).getExpirationDate().toLocaleString()
        );

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {

        //final TextView id;
        final TextView currency;
        final TextView sum;
        final TextView registrationDate;
        final TextView expirationDate;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.id = itemView.findViewById(R.id.number);
            this.currency = itemView.findViewById(R.id.currency);
            this.sum = itemView.findViewById(R.id.sum);
            this.registrationDate = itemView.findViewById(R.id.regDate);
            this.expirationDate = itemView.findViewById(R.id.expDate);

        }
    }

    private String currencyAdatper(Wallet.Currency currency) {
        switch(currency) {
            case LEI:
                return "LEI";
            case EURO:
                return  "EURO";
            case DOLLAR:
                return "DOLLAR";
        }
        return null;
    }
}
