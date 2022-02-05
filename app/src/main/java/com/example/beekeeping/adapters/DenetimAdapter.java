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
import com.example.beekeeping.fragments.DenetimlerFragmentDirections;
import com.example.beekeeping.models.DenetimModel;
import com.example.beekeeping.R;

import java.util.ArrayList;

public class DenetimAdapter extends RecyclerView.Adapter<DenetimAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<DenetimModel> denetimlerList,denetimlerListAll;
    private Database db;

    public DenetimAdapter(Context mContext, ArrayList<DenetimModel> denetimlerList,ArrayList<DenetimModel> denetimlerListAll) {

        this.mContext = mContext;
        this.denetimlerList = denetimlerList;
        this.denetimlerListAll = denetimlerListAll;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_denetim,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final DenetimModel denetim = denetimlerList.get(position);
        final DenetimModel denetimAll = denetimlerListAll.get(position);

        db = new Database(mContext);

        setCard(holder,denetim);

        clickedCard(holder,denetimAll);


    }

    @Override
    public int getItemCount() {
        return denetimlerList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView kovanNo, denetimTarih;
        private CardView rowDenetim;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            kovanNo = itemView.findViewById(R.id.tvKovanNo);
            denetimTarih = itemView.findViewById(R.id.tvDenetimTarih);
            rowDenetim = itemView.findViewById(R.id.denetim_row);
        }
    }

    private void setCard(MyViewHolder holder, DenetimModel denetim){

        holder.kovanNo.setText(String.valueOf(denetim.getKovan_no()));
        holder.denetimTarih.setText(denetim.getDenetim_tarih());
    }

    private void clickedCard(MyViewHolder holder, DenetimModel denetimAll){

        holder.rowDenetim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DenetimlerFragmentDirections.ActionDenetimlerToDenetimDetayFragment action = DenetimlerFragmentDirections.actionDenetimlerToDenetimDetayFragment();
                action.setDenetim(denetimAll);

                Navigation.findNavController(v).navigate(action);
            }
        });

    }
}
