package com.example.beekeeping.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beekeeping.Database.Database;
import com.example.beekeeping.fragments.NotlarFragmentDirections;
import com.example.beekeeping.Models.NotModel;
import com.example.beekeeping.R;

import java.util.ArrayList;

public class NotAdapter extends RecyclerView.Adapter<NotAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<NotModel> notlarList, notlarListTam;
    private Database db;

    public NotAdapter() {
    }

    public NotAdapter(Context mContext, ArrayList<NotModel> notlarList, ArrayList<NotModel> notlarListTam) {
        this.mContext = mContext;
        this.notlarList = notlarList;
        this.notlarListTam = notlarListTam;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_not, parent,false);

        return new NotAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final NotModel not = notlarList.get(position);
        final NotModel notAll = notlarListTam.get(position);

        db = new Database(mContext);

        setCard(holder,not);

        clickedCard(holder,notAll);
    }

    @Override
    public int getItemCount() {
        return notlarList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView notBaslik, notTarih;
        private CardView not_row;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            notBaslik = itemView.findViewById(R.id.tvNotBaslik);
            notTarih = itemView.findViewById(R.id.tvNotTarih);
            not_row = itemView.findViewById(R.id.not_row);
        }
    }

    private void setCard(NotAdapter.MyViewHolder holder, NotModel not){

        holder.notBaslik.setText(not.getNot_baslik());
        holder.notTarih.setText(not.getNot_tarih());
    }

    private void clickedCard(NotAdapter.MyViewHolder holder, NotModel notAll){

        holder.not_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotlarFragmentDirections.ActionNotlarToNotDetayFragment action = NotlarFragmentDirections.actionNotlarToNotDetayFragment();
                action.setNot(notAll);

                Navigation.findNavController(v).navigate(action);
            }
        });
    }
}
