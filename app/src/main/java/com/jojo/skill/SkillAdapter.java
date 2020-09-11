package com.zikozee.skill;

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
import com.zikozee.learning.Learning;

import java.util.List;


public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {

    List<Skill> mData;

    public SkillAdapter(List<Skill> data) {
        mData = data;
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView= LayoutInflater.from(context)
                .inflate(R.layout.item_skill, parent, false);

        return  new SkillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        Skill skill = mData.get(position);
        holder.bind(skill);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static  class SkillViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView score;
        ImageView imageView;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.skill_id);
            score = itemView.findViewById(R.id.skill_score_id);
            imageView = itemView.findViewById(R.id.img_skill);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Skill skill){
            name.setText(skill.getName());
            score.setText(skill.getScore() +" skill IQ Score, " + skill.getCountry());
            Picasso.get().setLoggingEnabled(true);
            Picasso.get()
                    .load(skill.getUrl())
                    .placeholder(R.drawable.ic_baseline_outlined_flag_24)
                    .into(imageView);
        }
    }

}
