package com.melihkarakilinc.sondepremler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;


public class DepremAdapter extends RecyclerView.Adapter<DepremAdapter.ViewHolder> {

    ArrayList<DepremInf> depremInf;
    Context context;

    public DepremAdapter(ArrayList<DepremInf> depremInf, Context context){

        this.depremInf= depremInf;
        this.context=context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_tarih,txt_saat,txt_yer,txt_siddet;
        CardView cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_tarih = itemView.findViewById(R.id.txt_tarih);
            txt_saat = itemView.findViewById(R.id.txt_saat);
            txt_yer = itemView.findViewById(R.id.txt_yer);
            txt_siddet = itemView.findViewById(R.id.txt_siddet);
            cardview=itemView.findViewById(R.id.cardview);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt_tarih.setText(depremInf.get(position).getTarih());
        holder.txt_saat.setText(depremInf.get(position).getSaat().toString());
        holder.txt_yer.setText(depremInf.get(position).getYer().toString());
        holder.txt_siddet.setText(depremInf.get(position).getSiddet().toString());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,MapsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("name_of_extra",depremInf);
                i.putExtra("position",position);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return depremInf.size();
    }


}
