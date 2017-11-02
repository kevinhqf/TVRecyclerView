package com.kevinho.view.tvrecyclerviewdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevinho.view.tvrecyclerview.TVRecyclerViewAdapter;



public class SimpleTVRecyclerViewAdapter extends TVRecyclerViewAdapter<SimpleTVRecyclerViewAdapter.VH> {
    private static final String TAG = "SimpleAdapter";
    Context mContext;

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
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_card, parent, false));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected void focusOut(View v) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.1f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.1f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.start();
    }

    @Override
    protected void focusIn(View v) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.1f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.start();
    }

    @Override
    protected void onDataBinding(VH holder, int position) {
        holder.tv.setText("item " + position);
    }

    @Override
    public int getItemCount() {
        return 200;
    }

    class VH extends TVRecyclerViewAdapter.ViewHolder {
        TextView tv;
        ImageView iv;

        public VH(Context context, View itemView) {
            super(context, itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
