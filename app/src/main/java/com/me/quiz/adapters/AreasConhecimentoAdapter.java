package com.me.quiz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.me.quiz.R;

public class AreasConhecimentoAdapter extends RecyclerView.Adapter<AreasConhecimentoAdapter.ViewHolder> {
    Fragment context;
    String[] data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView textView;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = (TextView) view.findViewById(R.id.tv_area) ;
            cardView = (CardView) view.findViewById(R.id.cv_areas_item);
        }

        public CardView getCardView() {
            return cardView;
        }

        public TextView getTextView() {
            return textView;
        }
    }


    public AreasConhecimentoAdapter(Fragment context, String[] dataSet) {
        this.context = context;
        this.data = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.areas_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(data[position]);
        viewHolder.getCardView().setOnClickListener(view -> {
            NavHostFragment.findNavController(context).navigate(R.id.action_homeFragment_to_quizFragment);
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.length;
    }
}
