package com.Lenovo.phclapps.Activity;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Lenovo.phclapps.Adapter.Model;
import com.Lenovo.phclapps.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    //variables
    Context mContext;
    LayoutInflater inflater;
    List<Model> modellist;
    ArrayList<Model> arrayList;

    public CategoryAdapter(Context context, List<Model> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTv;
        public ViewHolder(View view) {
            super(view);
            mTitleTv = view.findViewById(R.id.button3);

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int postition) {
        holder.mTitleTv.setText(modellist.get(postition).getIcon());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CategoryDetails.class);
                    intent.putExtra("category", modellist.get(postition).getDesc());
                    // intent.putExtra("contentTv", "This is Battery detail...");
                    mContext.startActivity(intent);

                }
            });

    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    //filter
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length() == 0) {
            modellist.addAll(arrayList);
        } else {
            for (Model model : arrayList) {
                //                    if(modelCategories.get(i).getCategory().contains(keyword)|| modelCategories.get(i).getCategoryname().contains(keyword)|| modelCategories.get(i).getCatdetails().contains(keyword)){

                if (model.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText) || model.getDesc().toLowerCase(Locale.getDefault()).contains(charText)) {
                    modellist.add(model);

                }
            }
        }
        notifyDataSetChanged();
    }
}