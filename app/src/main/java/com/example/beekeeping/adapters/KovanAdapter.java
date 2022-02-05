package com.example.beekeeping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beekeeping.database.Database;
import com.example.beekeeping.fragments.KovanlarFragmentDirections;
import com.example.beekeeping.models.KovanModel;
import com.example.beekeeping.R;

import java.util.ArrayList;

public class KovanAdapter extends RecyclerView.Adapter<KovanAdapter.MyViewHolder> {

    private Context mContext;
    public ArrayList<KovanModel> kovanlarList,kovanlarListAll;
    private Database db;

    public KovanAdapter() {
    }

    public KovanAdapter(Context mContext, ArrayList<KovanModel> kovanlarList, ArrayList<KovanModel> kovanlarListAll) {

        this.mContext = mContext;
        this.kovanlarList = kovanlarList;
        this.kovanlarListAll = kovanlarListAll;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_kovan,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final KovanModel kovan = kovanlarList.get(position);
        final KovanModel kovanAll = kovanlarListAll.get(position);

        db = new Database(mContext);

        setCard(holder,kovan);

        clickedCard(holder,kovanAll);
    }

    @Override
    public int getItemCount() {
        return kovanlarList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvKovanNo, tvKovanDurum, tvKovanTarih;
        private CardView kovan_row;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvKovanNo = itemView.findViewById(R.id.tvKovanNo);
            tvKovanDurum = itemView.findViewById(R.id.tvKovanDurum);
            tvKovanTarih = itemView.findViewById(R.id.tvKovanTarih);
            kovan_row = itemView.findViewById(R.id.kovan_row);
        }
    }

    private void setCard(MyViewHolder holder, KovanModel kovan){

        holder.tvKovanNo.setText(String.valueOf(kovan.getKovanNo()));
        holder.tvKovanDurum.setText(kovan.getKovanDurum());
        holder.tvKovanTarih.setText(kovan.getKovanTarih());

    }

    private void clickedCard(MyViewHolder holder, KovanModel kovanAll){

        holder.kovan_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                KovanlarFragmentDirections.ActionKovanlarToKovanDetayFragment action = KovanlarFragmentDirections.actionKovanlarToKovanDetayFragment();
                action.setKovan(kovanAll);

                Navigation.findNavController(v).navigate(action);

            }
        });
    }
}
