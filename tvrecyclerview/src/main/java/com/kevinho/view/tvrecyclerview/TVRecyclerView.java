package com.kevinho.view.tvrecyclerview;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;

/**
 * @author HeQinfu
 * @copyright 康佳集团股份有限公司保留所有版权。
 * @description
 * @created on 2017-11-1。
 */

public class TVRecyclerView extends RecyclerView {
    private static final String TAG = "TVRecyclerView";

    public TVRecyclerView(Context context) {
        this(context, null);
    }

    public TVRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TVRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        //setHasFixedSize(true);
        //setWillNotDraw(true);
        setChildrenDrawingOrderEnabled(true);

        setClipChildren(false);
        setClipToPadding(false);

        setItemAnimator(null);
    }

    private OnInterceptListener mOnInterceptListener;

    public void setOnInterceptListener(OnInterceptListener onInterceptListener) {
        mOnInterceptListener = onInterceptListener;
    }

    private boolean keyCodeOfDPAD(KeyEvent event) {
        return event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT
                || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT
                || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN
                || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mOnInterceptListener != null && mOnInterceptListener.onIntercept(event)) {
            return true;
        }
        boolean result = super.dispatchKeyEvent(event);
        View focusView = this.getFocusedChild();
        if (focusView == null) {
            return result;
        } else {
            int dy = 0;
            int dx = 0;
            if (getChildCount() > 0) {
                View firstView = this.getChildAt(0);
                dy = firstView.getHeight();
                dx = firstView.getWidth();
            }
            if (event.getAction() == KeyEvent.ACTION_UP
                    && keyCodeOfDPAD(event)) {
                return true;
            } else {
                switch (event.getKeyCode()) {
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        return findNextFocusView(focusView, new Point(dx, 0), View.FOCUS_RIGHT);
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        return findNextFocusView(focusView, new Point(-dx, 0), View.FOCUS_LEFT);
                    case KeyEvent.KEYCODE_DPAD_DOWN:
                        return findNextFocusView(focusView, new Point(0, dy), View.FOCUS_DOWN);
                    case KeyEvent.KEYCODE_DPAD_UP:
                        return findNextFocusView(focusView, new Point(0, -dy), View.FOCUS_UP);
                }
            }
        }


        return result;
    }

    /**
     * 找到下一个焦点的itemView
     *
     * @param focusView
     * @param vector
     * @param direction
     * @return
     */
    private boolean findNextFocusView(View focusView, Point vector, int direction) {
        View nextFocus = FocusFinder.getInstance()
                .findNextFocus(this, focusView, direction);
        //  Log.i(TAG, "right is null:" + (rightView == null));
        if (nextFocus != null) {
            nextFocus.requestFocus();
            return true;
        } else {
            this.smoothScrollBy(vector.x, vector.y);
            return true;
        }
    }

    /**
     * 修改item的绘制顺序,让获取焦点的item在最后进行绘制
     *
     * @param childCount
     * @param i
     * @return
     */
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        View view = getFocusedChild();

        int position = indexOfChild(view);
        if (position < 0) {
            return i;
        } else {
            if (i == childCount - 1) {//这是最后一个需要刷新的item
                return position;
            }
            if (i == position) {
                return childCount - 1;
            }
        }
        return i;//正常次序的item
    }

    /**
     * 可以实现此接口来实现自己的KeyEvent逻辑
     */
    public interface OnInterceptListener {
        boolean onIntercept(KeyEvent event);
    }

}
