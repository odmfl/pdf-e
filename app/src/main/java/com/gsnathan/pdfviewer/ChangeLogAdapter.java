package com.gsnathan.pdfviewer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChangeLogAdapter extends RecyclerView.Adapter<ChangeLogAdapter.ViewHolder> {

    private List<ChangeLog> changeLogs;

    public ChangeLogAdapter(List<ChangeLog> changeLogs) {
        this.changeLogs = changeLogs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_change_log, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChangeLog changeLog = changeLogs.get(position);
        holder.getIcon().setImageResource(changeLog.getIconRes());
        holder.getTitle().setText(changeLog.getTitle());
        holder.getDescription().setText(changeLog.getDescription());
    }

    @Override
    public int getItemCount() {
        return changeLogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView title;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.itemImageView);
            title = itemView.findViewById(R.id.itemTitleView);
            description = itemView.findViewById(R.id.itemDescView);
        }

        public ImageView getIcon() {
            return icon;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDescription() {
            return description;
        }
    }
}
