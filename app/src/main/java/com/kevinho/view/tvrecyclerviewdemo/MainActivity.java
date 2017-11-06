package com.kevinho.view.tvrecyclerviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.kevinho.view.tvrecyclerview.TVRecyclerView;

public class MainActivity extends Activity {


    TVRecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rv = (TVRecyclerView) findViewById(R.id.rv_list);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 5 == 0)
                    return 2;
                return 1;
            }
        });
        rv.setLayoutManager(manager);
        rv.setAdapter(new SimpleTVRecyclerViewAdapter(this));
        rv.addItemDecoration(new DefaultItemDecoration(5,5, 0, 0));
    }


}
