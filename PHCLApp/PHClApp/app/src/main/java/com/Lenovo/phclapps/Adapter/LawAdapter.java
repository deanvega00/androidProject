package com.Lenovo.phclapps.Adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Lenovo.phclapps.Activity.FavouriteLawActivity;
import com.Lenovo.phclapps.Activity.SingleCategoryDetails;
import com.Lenovo.phclapps.Activity.SliderActivity;
import com.Lenovo.phclapps.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LawAdapter extends RecyclerView.Adapter<LawAdapter.ViewHolder> {

    //variables
    Context mContext;
    LayoutInflater inflater;
    List<LawModel> modellist;
    ArrayList<LawModel> arrayList;
    String mSrcKey = "";

    public interface ClickListener {
        public void onFavClick(int pos, LawModel model);

        public void onFavDeleteClick(int pos, LawModel model);
    }

    ClickListener clickListener;

    public LawAdapter(Context context, List<LawModel> modellist, ClickListener clickListener) {
        mContext = context;
        this.clickListener = clickListener;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<LawModel>();
        this.arrayList.addAll(modellist);
        mSrcKey = "";
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTv, mDescTv, buttonfav;
        ImageView buttonview;
        LinearLayout favlayout, viewlayout;
        ImageView hearticon;

        public ViewHolder(View view) {
            super(view);
            mTitleTv = view.findViewById(R.id.cat);
            mDescTv = view.findViewById(R.id.cat_det);
            buttonfav = view.findViewById(R.id.buttonfav);
            buttonview = view.findViewById(R.id.buttonview);
            hearticon = view.findViewById(R.id.hearticon);

            favlayout = view.findViewById(R.id.favlayout);
            viewlayout = view.findViewById(R.id.viewlayout);
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_category_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int postition) {
        if (modellist.get(postition).isfavt) {
            holder.hearticon.setImageResource(R.drawable.heartred);
            holder.buttonfav.setText("");
        } else {
            holder.hearticon.setImageResource(R.drawable.hearticon);
            holder.buttonfav.setText("Add to Favourite");

        }
        if (mSrcKey.length() > 0){
            holder.mTitleTv.setText(highlight(modellist.get(postition).getTitle(), mSrcKey));
            holder.mDescTv.setText(highlight(modellist.get(postition).getDesc(), mSrcKey));
        }else {
            holder.mTitleTv.setText(modellist.get(postition).getTitle());
            holder.mDescTv.setText(modellist.get(postition).getDesc());
        }
        holder.favlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    if (modellist.get(postition).isfavt) {
                        clickListener.onFavDeleteClick(postition, modellist.get(postition));

                    } else {
                        clickListener.onFavClick(postition, modellist.get(postition));
                    }
                }
            }
        });
        holder.viewlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(mContext, SingleCategoryDetails.class);
                in.putExtra("lawmodel", modellist.get(postition));
                if (mSrcKey.length() > 0){
                    in.putExtra("keyword", mSrcKey);
                }
                mContext.startActivity(in);
            }
        });
    }

    public static Spannable highlight( String original, String word) {
        String nocase = original.toLowerCase();
        SpannableString span = new SpannableString(original);
        String normalized = Normalizer.normalize(original, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        int start = nocase.indexOf(word);
        if (start < 0) {
            return span;
        } else {
            Spannable highlighted = new SpannableString(original);
            while (start >= 0) {
                int spanStart = Math.min(start, original.length());
                int spanEnd = Math.min(start+word.length(), original.length());

                highlighted.setSpan(new BackgroundColorSpan(0xff3390ff), spanStart,
                        spanEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                highlighted.setSpan(new ForegroundColorSpan(0xffffffff), spanStart,
                        spanEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                start = nocase.indexOf(word, spanEnd);
            }
            return highlighted;
        }
    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    //filter
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mSrcKey = charText;
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