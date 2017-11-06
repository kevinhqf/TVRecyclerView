# TVRecyclerView

TVRecyclerView is an Android RecyclerView library for TV. It resolves the focus problem
on the TV end(not for all layout manager).

### Usage
1. First you need to add gradle dependency in your project:
```gradle
dependencies {
    ......
    compile 'kevinho.view.dev:tvrecyclerview:0.1.1'
}
```

2. Add a tvrecyclerview tag in your layout XML file:
```xml
<com.kevinho.view.tvrecyclerview.TVRecyclerView
        android:id="@+id/rv_list"
        android:padding="16dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

3. Then new a Adapter Class that extends TVRecyclerViewAdapter,and then you need
to implement the methods below :
```java
    public class SimpleTVRecyclerViewAdapter extends TVRecyclerViewAdapter<TVRecyclerViewAdapter.ViewHolder>{
        .....
        
        @Override
        public long getItemId(int position) {
            return position;
        }
        
        /* 
        implement to tell the view how to do 
        when its state changing from onFocus to lose focus
        */
        @Override
        protected void focusOut(View v) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.05f, 1.0f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.05f, 1.0f);
            AnimatorSet set = new AnimatorSet();
            set.play(scaleX).with(scaleY);
            set.start();
        }
    
        /* 
        implement to tell the view how to do 
        when its state changing from unFocus to get focus
        */
        @Override
        protected void focusIn(View v) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.05f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.05f);
            AnimatorSet set = new AnimatorSet();
            set.play(scaleX).with(scaleY);
            set.start();
        }
    
        /**
        * to bind data to the item view
        */
        @Override
        protected void onDataBinding(TVRecyclerViewAdapter.ViewHolder holder, int position) {
            if (holder instanceof OneVH) {
                ((OneVH) holder).tv.setText("" + position);
            }
        }
    
        .....
    }
```

4. Finally use the TVRecyclerView just like the default recyclerview:
```java
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
        // set the layout manager
        rv.setLayoutManager(manager);
        // set the adapter
        rv.setAdapter(new SimpleTVRecyclerViewAdapter(this));
        // add a decoration
        rv.addItemDecoration(new DefaultItemDecoration(5,5, 0, 0));
```

You can download the code to see how the TVRecyclerView works and there is a
Demo too.

### ScreenShot
![screenshot](http://o7x6n1hmo.bkt.clouddn.com/image/2017-11-06_100814.png)


