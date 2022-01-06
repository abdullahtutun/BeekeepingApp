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
import com.example.beekeeping.Fragments.GorevlerFragmentDirections;
import com.example.beekeeping.Models.GorevModel;
import com.example.beekeeping.R;

import java.util.ArrayList;

public class GorevAdapter extends RecyclerView.Adapter<GorevAdapter.MyViewHolder> {

    private Context mContext;
    public ArrayList<GorevModel> gorevlerList,gorevlerListAll;
    private Database db;

    public GorevAdapter() {
    }

    public GorevAdapter(Context mContext, ArrayList<GorevModel> gorevlerList, ArrayList<GorevModel> gorevlerListAll) {
        this.mContext = mContext;
        this.gorevlerList = gorevlerList;
        this.gorevlerListAll = gorevlerListAll;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gorev,parent,false);

        return new GorevAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final GorevModel gorev = gorevlerList.get(position);
        final GorevModel gorevAll = gorevlerListAll.get(position);

        db = new Database(mContext);

        setCard(holder,gorev);

        clickedCard(holder,gorevAll);
    }

    @Override
    public int getItemCount() {
        return gorevlerList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvGorevBaslik, tvGorevTarih;
        private CardView gorev_row;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGorevBaslik = itemView.findViewById(R.id.tvGorevBaslik);
            tvGorevTarih = itemView.findViewById(R.id.tvGorevTarih);
            gorev_row = itemView.findViewById(R.id.gorev_row);
        }
    }

    private void setCard(GorevAdapter.MyViewHolder holder, GorevModel gorev){

        holder.tvGorevBaslik.setText(String.valueOf(gorev.getGorev_baslik()));
        holder.tvGorevTarih.setText(gorev.getTamamlanma_tarih());
    }

    private void clickedCard(GorevAdapter.MyViewHolder holder, GorevModel gorevAll){

        holder.gorev_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GorevlerFragmentDirections.ActionGorevlerToGorevDetayFragment action = GorevlerFragmentDirections.actionGorevlerToGorevDetayFragment();
                action.setGorev(gorevAll);

                Navigation.findNavController(v).navigate(action);

            }
        });
    }
}
