package com.kevinho.view.tvrecyclerviewdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kevinho.view.tvrecyclerview.TVRecyclerViewAdapter;

import java.util.Random;


public class SimpleTVRecyclerViewAdapter extends TVRecyclerViewAdapter<TVRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "SimpleAdapter";
    Context mContext;

    public static final String[] COLORS = {
            "#0793ea",
    "#07eabd",
    "#ea0756",
    "#e2ea07",
    "#07ea5a",
    "#ea8b07",
   "#b907ea",
    "#5f30ec",
    "#ec55f4",
    "#f14616"
    };

    public SimpleTVRecyclerViewAdapter(Context context) {
        mContext = context;
        setHasStableIds(true);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Log.e(TAG, "onItemClick:" + pos);
            }
        });
    }

    @Override
    public TVRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new OneVH(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_one, parent, false));
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected void focusOut(View v,int pos) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.05f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.05f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.start();
    }

    @Override
    protected void focusIn(View v,int pos) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.05f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.05f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.start();
    }

    @Override
    protected void onDataBinding(TVRecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder instanceof OneVH) {
            ((OneVH) holder).tv.setText("" + position);
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class OneVH extends TVRecyclerViewAdapter.ViewHolder {
        TextView tv;
        FrameLayout fl;

        public OneVH(Context context, View itemView) {
            super(context, itemView);
            tv = (TextView) itemView.findViewById(R.id.tv1);
            fl = (FrameLayout) itemView.findViewById(R.id.fl);
            int i = new Random().nextInt(COLORS.length);
            fl.setBackgroundColor(Color.parseColor(COLORS[i]));
        }
    }

}
