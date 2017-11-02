package com.kevinho.view.tvrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;



public abstract class TVRecyclerViewAdapter<VH extends TVRecyclerViewAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.itemView.setFocusable(true);
        onDataBinding(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    focusIn(v);
                } else {
                    focusOut(v);
                }
                // 重绘,使item的绘制顺序调整生效
                ViewGroup parent = (ViewGroup) v.getParent();
                if (parent != null) {
                    parent.requestLayout();
                    parent.postInvalidate();
                }
            }
        });
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 实现数据与View的绑定
     *
     * @param holder
     * @param position
     */
    protected abstract void onDataBinding(VH holder, int position);

    /**
     * 实现获取焦点时的动画
     *
     * @param v
     */
    protected abstract void focusIn(View v);

    /**
     * 实现失去焦点时的动画
     *
     * @param v
     */
    protected abstract void focusOut(View v);


    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        Context mContext;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            this.mContext = context;
            itemView.setBackground(mContext.getResources().getDrawable(R.drawable.rv_item_bg));
        }
    }

}
