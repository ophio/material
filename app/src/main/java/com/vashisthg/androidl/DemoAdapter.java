package com.vashisthg.androidl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    List<DemoEntity> demoList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @InjectView(R.id.item_text) TextView textView;
        @InjectView(R.id.item_image) ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DemoAdapter(List<DemoEntity> demoList) {
        if(demoList == null) {
            demoList = new ArrayList<DemoEntity>();
        }
        this.demoList = demoList;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public DemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_demo_adapter, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        DemoEntity demoEntity = demoList.get(position);
        holder.textView.setText(demoEntity.text);
        holder.imageView.setImageResource(demoEntity.imageDrawable);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return demoList.size();
    }
}