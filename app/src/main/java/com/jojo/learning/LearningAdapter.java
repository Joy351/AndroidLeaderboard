package com.zikozee.learning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.zikozee.R;

import java.util.ArrayList;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.LearningViewHolder> {

    ArrayList<Learning> mLearnings;

    public LearningAdapter(ArrayList<Learning> learnings){
        this.mLearnings =learnings;
    }

    @NonNull
    @Override
    public LearningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_learning, parent, false);
        return new LearningViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningViewHolder holder, int position) {
        Learning learning  = mLearnings.get(position);
        holder.bind(learning);
    }

    @Override
    public int getItemCount() {
        return mLearnings.size();
    }

    public class LearningViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView hours;
        ImageView imageView;

        public LearningViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.learner_id);
            hours = itemView.findViewById(R.id.learner_score_id);
            imageView = itemView.findViewById(R.id.img_learning);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Learning learning){
            name.setText(learning.getName());
            hours.setText(learning.getHours() +" learning hours, " + learning.getCountry());
            Picasso.get().setLoggingEnabled(true);
            Picasso.get()
                    .load(learning.getUrl())
                    .placeholder(R.drawable.ic_baseline_outlined_flag_24)
                    .into(imageView);
        }
    }
}
