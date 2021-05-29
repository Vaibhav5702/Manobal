package com.example.underscore_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yinglan.shadowimageview.ShadowImageView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    ItemClick activity;
    List<Card> list;

    public interface ItemClick {
        void onItemClick(int i);
    }

    public CardAdapter(Context context, List<Card> list) {
        this.list = list;
        this.activity = (ItemClick) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBackground;
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivBackground=itemView.findViewById(R.id.ivBackground);
            this.tvName=itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(v -> CardAdapter.this.activity.onItemClick(CardAdapter.this.list.indexOf((Card) v.getTag())));
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(this.list.get(position));
        if(this.list.get(position).getBackground().equals("sleep")) {
            holder.tvName.setText("Sleep");
            holder.ivBackground.setImageResource(R.drawable.sleep);
        }
        else if(this.list.get(position).getBackground().equals("meditate")) {
            holder.tvName.setText("Meditate");
            holder.ivBackground.setImageResource(R.drawable.meditate);
        }
        else if(this.list.get(position).getBackground().equals("anxiety")) {
            holder.tvName.setText("Anxiety");
            holder.ivBackground.setImageResource(R.drawable.anxiety);
        }
        else if(this.list.get(position).getBackground().equals("heartbreak")) {
            holder.tvName.setText("Heartbreak");
            holder.ivBackground.setImageResource(R.drawable.heartbreak);
        }
        else if(this.list.get(position).getBackground().equals("exam_stress")) {
            holder.tvName.setText("Exam Stress");
            holder.ivBackground.setImageResource(R.drawable.exam_stress);
        }
        else if(this.list.get(position).getBackground().equals("lonely")) {
            holder.tvName.setText("Lonely");
            holder.ivBackground.setImageResource(R.drawable.lonely);
        }
    }

    public int getItemCount() {
        return this.list.size();
    }
}
