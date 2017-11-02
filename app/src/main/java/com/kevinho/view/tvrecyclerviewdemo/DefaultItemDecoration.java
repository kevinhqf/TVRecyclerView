package com.kevinho.view.tvrecyclerviewdemo;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

/**
 * the recyclerView decoration
 */
public class DefaultItemDecoration extends RecyclerView.ItemDecoration {

    int left = 0;
    int top = 0;
    int right = 0;
    int bottom = 0;

    public DefaultItemDecoration(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {

        outRect.set(left, top, right, bottom);

    }
}
