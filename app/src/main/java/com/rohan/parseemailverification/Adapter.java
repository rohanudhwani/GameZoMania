package com.rohan.parseemailverification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Modelclass> bars;
    Context context;

    Adapter(Context c, List<Modelclass> list )
    {
        bars=list;
        context = c;
    }

    @NonNull
    @NotNull
    @Override
    @SuppressLint("ResourceType")
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bars,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        // Getting the color for every position
        String color = bars.get(position).getColor();

        // Set the color to the bar
        if (color.equals("Yellow"))
        {
            holder.linearLayout.setBackgroundTintList(context.getResources().getColorStateList(R.color.themeYellow));
        }
        else
        {
            holder.linearLayout.setBackgroundTintList(context.getResources().getColorStateList(R.color.Red));
        }
    }

    @Override
    public int getItemCount() {
        return bars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.barlayout);
        }
    }
}
