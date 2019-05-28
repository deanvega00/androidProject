package com.Lenovo.phclapps.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.Lenovo.phclapps.Adapter.LawAdapter;
import com.Lenovo.phclapps.Adapter.LawModel;
import com.Lenovo.phclapps.R;

public class SingleCategoryDetails extends AppCompatActivity {
    LawModel lawModel;
    TextView mTitleTv, mDescTv, buttonfav, catName;

    String mKeyword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlecategory_details);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Law Detail");
        mTitleTv = findViewById(R.id.cat);
        mDescTv = findViewById(R.id.cat_det);
        catName = findViewById(R.id.catName);
        lawModel = (LawModel) getIntent().getSerializableExtra("lawmodel");
        mKeyword = getIntent().getStringExtra("keyword");
        if (mKeyword != null && mKeyword.length() > 0){
            mTitleTv.setText(LawAdapter.highlight(lawModel.getTitle(), mKeyword));
            mDescTv.setText(LawAdapter.highlight(lawModel.getDesc(), mKeyword));

        }else {
            mTitleTv.setText(lawModel.getTitle());
            mDescTv.setText(lawModel.getDesc());

        }
        String lawId = lawModel.getId();
        for (int i = 0; i < Config.cateList.size(); i++) {
            if (lawId.equals(Config.cateList.get(i).getDesc())) {
                catName.setText(Config.cateList.get(i).getIcon());
                break;
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
