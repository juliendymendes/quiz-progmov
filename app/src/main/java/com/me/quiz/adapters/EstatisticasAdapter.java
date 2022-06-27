package com.me.quiz.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.me.quiz.R;
import com.me.quiz.entidades.Estatistica;

public class EstatisticasAdapter extends RecyclerView.Adapter<EstatisticasAdapter.ViewHolder> {

    Fragment context;
    Estatistica[] data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvArea;
        private final TextView tvAcertos;
        private final TextView tvTempo;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            tvArea = view.findViewById(R.id.tv_area_painel) ;
            tvAcertos = view.findViewById(R.id.tv_acertos);
            tvTempo = view.findViewById(R.id.tv_tempo);

        }


        public TextView getTvAcertos() {
            return tvAcertos;
        }

        public TextView getTvTempo() {
            return tvTempo;
        }

        public TextView getTvArea() {
            return tvArea;
        }
    }

    public EstatisticasAdapter(Fragment context, Estatistica[] estatisticas){
        this.context = context;
        this.data = estatisticas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.estatistica_painel, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.getTvArea().setText(data[position].getAreaConhecimento());
        viewHolder.getTvAcertos().setText(String.valueOf(data[position].getQtdAcertos()));
        viewHolder.getTvTempo().setText(data[position].getTempo());
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
