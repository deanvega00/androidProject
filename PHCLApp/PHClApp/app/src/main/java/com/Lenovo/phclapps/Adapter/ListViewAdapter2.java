package com.Lenovo.phclapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.Lenovo.phclapps.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class ListViewAdapter2 extends BaseAdapter {

    //variables
    Context mContext;
    LayoutInflater inflater;
    List<LawModel> modellist;
    ArrayList<LawModel> arrayList;

    public interface ClickListener {
        public void onFavClick(int pos,LawModel model);

        public void onFavDeleteClick(int pos,LawModel model);
    }

    ClickListener clickListener;

    //constructor
    public ListViewAdapter2(Context context, List<LawModel> modellist, ClickListener clickListener) {
        mContext = context;
        this.clickListener = clickListener;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<LawModel>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder {
        TextView mTitleTv, mDescTv, buttonfav;
        ImageView buttonview;
        LinearLayout favlayout, viewlayout;
        ImageView hearticon;
    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int postition, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_category_details, null);

            //locate the views in row.xml
            holder.mTitleTv = view.findViewById(R.id.cat);
            holder.mDescTv = view.findViewById(R.id.cat_det);
            holder.buttonfav = view.findViewById(R.id.buttonfav);
            holder.buttonview = view.findViewById(R.id.buttonview);
            holder.hearticon = view.findViewById(R.id.hearticon);

            holder.favlayout = view.findViewById(R.id.favlayout);
            holder.viewlayout = view.findViewById(R.id.viewlayout);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (modellist.get(postition).isfavt) {
            holder.hearticon.setImageResource(R.drawable.heartred);
        } else {
            holder.hearticon.setImageResource(R.drawable.hearticon);

        }
        holder.mTitleTv.setText(modellist.get(postition).getTitle());
        holder.mDescTv.setText(modellist.get(postition).getDesc());
        holder.favlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    if (modellist.get(postition).isfavt) {
                        clickListener.onFavDeleteClick(postition,modellist.get(postition));

                    } else {
                        clickListener.onFavClick(postition,modellist.get(postition));
                    }
                }
            }
        });
        holder.viewlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                }
            }
        });

        return view;
    }

    //filter
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length() == 0) {
            modellist.addAll(arrayList);
        } else {
            for (LawModel model : arrayList) {
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
