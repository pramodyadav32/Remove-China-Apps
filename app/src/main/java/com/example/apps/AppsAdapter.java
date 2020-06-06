package com.example.apps;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.apps.AppsEvent.HEADER_TYPE;
import static com.example.apps.AppsEvent.ITEM_TYPE;

public class AppsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AppsEvent> mList;
    boolean isSelectedAll;

    public AppsAdapter(List<AppsEvent> list) {
        this.mList = list;
    }

    ArrayList<String> appsList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case HEADER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apps_header, parent, false);
                return new HeaderViewHolder(view);
            case ITEM_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apps_item, parent, false);
                return new EventViewHolder(view);
        }
        return null;
    }

    @SuppressLint("Assert")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AppsEvent object = mList.get(position);
        if (object != null) {
            switch (object.getType()) {
                case HEADER_TYPE:
                    ((HeaderViewHolder) holder).mTitle.setText(object.getName());
                    setAnimation(holder.itemView, position);
                    break;
                case ITEM_TYPE:
                    ((EventViewHolder) holder).img_app.setImageResource(object.getImage());
                    ((EventViewHolder) holder).text_name.setText(object.getName());
                    if (!isSelectedAll) {
                        ((EventViewHolder) holder).checkbox.setChecked(false);
                    } else {
                        ((EventViewHolder) holder).checkbox.setChecked(true);
                    }
                    setAnimation(holder.itemView, position);
                    ((EventViewHolder) holder).checkbox.setTag(object.getPath());
                    ((EventViewHolder) holder).checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            try {
                                if (b) {
                                    appsList.add((String) compoundButton.getTag());
                                } else {
                                    appsList.remove(compoundButton.getTag());
                                }
                                Log.d("list", appsList.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    break;
            }

        }
    }

    public void selectAll() {
        isSelectedAll = true;
        notifyDataSetChanged();
    }

    public void unSelectAll() {
        isSelectedAll = false;
        notifyDataSetChanged();
    }

    private int lastPosition = -1;

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            AppsEvent object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.titleTextView);
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView img_app;
        private TextView text_name;
        private CheckBox checkbox;

        public EventViewHolder(View itemView) {
            super(itemView);
            img_app = itemView.findViewById(R.id.img_app);
            text_name = itemView.findViewById(R.id.text_name);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }
}