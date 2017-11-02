package com.kevinho.view.tvrecyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

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
        rv = findViewById(R.id.rv_list);
        rv.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        rv.setAdapter(new SimpleTVRecyclerViewAdapter(this));
        rv.addItemDecoration(new DefaultItemDecoration(15, 15, 15, 15));
    }


}
