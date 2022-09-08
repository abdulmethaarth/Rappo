package com.affinity.rappo.ui_activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.affinity.rappo.R;
import com.affinity.rappo.model.HistoryDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryDetailsAdapter extends RecyclerView.Adapter<HistoryDetailsAdapter.CustomViewHolder>{


    private List<HistoryDetails> historyDetails;
    android.content.Context mContext;
    private Object Context;

    public HistoryDetailsAdapter(List<HistoryDetails> historyDetails ) {
        this.historyDetails = historyDetails;
        this.mContext = mContext;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        this.mContext=mContext;
        HistoryDetails data = historyDetails.get(position);
        holder.date.setText(data.getCurrentDate());
        holder.reportNo.setText(data.getServiceRNO());
        holder.name.setText(data.getCustomerName());
    }

    @Override
    public int getItemCount() {
        return historyDetails.size();
    }

    public void filterList(ArrayList<HistoryDetails> filteredList) {
        historyDetails = filteredList;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView date, reportNo, name;

        public CustomViewHolder(final View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            reportNo = (TextView) view.findViewById(R.id.reportNo);
            name = (TextView) view.findViewById(R.id.name);

        }
    }
}
