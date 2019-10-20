package com.lc.testdemo.aidlTest.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lc.testdemo.R;
import com.lc.testdemo.aidlTest.gridrecycleview.GridAdapter;
import com.lc.testdemo.aidlTest.gridrecycleview.GridItemDecoration;
import com.lc.testdemo.aidlTest.gridrecycleview.GridItemDecorationB;
import com.lc.testdemo.aidlTest.gridrecycleview.ItemViewData;
import com.lc.testdemo.aidlTest.gridrecycleview.ListenGridItemDecoration;
import com.lc.testdemo.aidlTest.gridrecycleview.MySpanSizeLookUp;
import com.lc.testdemo.aidlTest.gridrecycleview.StudyGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class GridRecycleViewActivity extends AppCompatActivity {

    private RecyclerView rvGrid;
    private RadioGroup rgChange;
    private List<ItemViewData> itemViewDataList = new ArrayList<>();
    private GridAdapter gridAdapter;
    private GridLayoutManager layoutManager;
    private RecyclerView.ItemDecoration itemDecoration;
    private ArrayList<RecyclerView.ItemDecoration> list;
    private String TAG = "4Test...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recycle_view);
        rvGrid = findViewById(R.id.rvGrid);
        rgChange = findViewById(R.id.rgChange);
        layoutManager = new GridLayoutManager(this,15);
        layoutManager.setSpanSizeLookup(new MySpanSizeLookUp(itemViewDataList));
        gridAdapter = new GridAdapter(this,itemViewDataList);
        itemDecoration = new GridItemDecoration(this);
        rvGrid.setLayoutManager(layoutManager);
        rvGrid.setAdapter(gridAdapter);
        list = new ArrayList<RecyclerView.ItemDecoration>();
        list.add(new GridItemDecoration(this));
        list.add(new ListenGridItemDecoration(15,15));
        list.add(new StudyGridItemDecoration());
        list.add(new GridItemDecorationB());
        addData();

        rgChange.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG, "onCheckedChanged   checkedId: "+checkedId);
                int position = 0;
                switch (checkedId){
                    case R.id.GridDecorationB:
                        position = 3;break;
                    case R.id.GridDecoration2:
                        position = 0;break;
                    case R.id.StudyGridItemDecoration:
                        position = 1;break;
                    case R.id.ListenGridItemDecoration:
                        position = 2;break;
                }
                changeItemDecoration(position);
            }
        });
    }


    private void changeItemDecoration(int i){
        rvGrid.removeItemDecoration(itemDecoration);
        itemDecoration = (RecyclerView.ItemDecoration) list.get(i);
        rvGrid.addItemDecoration(itemDecoration);
        gridAdapter.notifyDataSetChanged();
    }


    public void addData(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++) {
                    itemViewDataList.add(new ItemViewData(0,i+""));
                }
//                itemViewDataList.add(new ItemViewData(0,"000"));
//                itemViewDataList.add(new ItemViewData(3,"333"));
//                itemViewDataList.add(new ItemViewData(0,"000"));
//                itemViewDataList.add(new ItemViewData(0,"000"));
//                itemViewDataList.add(new ItemViewData(2,"222"));
//                itemViewDataList.add(new ItemViewData(2,"222"));
//                itemViewDataList.add(new ItemViewData(2,"222"));
//                itemViewDataList.add(new ItemViewData(2,"222"));
//                itemViewDataList.add(new ItemViewData(2,"222"));
//                itemViewDataList.add(new ItemViewData(1,"111"));
                gridAdapter.notifyDataSetChanged();
                Log.d("4Test...",Thread.currentThread().getName()+"");
            }
        });
    }

    public void backToTop(View view) {
        Toast.makeText(this, "asdfadsf", Toast.LENGTH_SHORT).show();
        rvGrid.scrollBy(0,-rvGrid.computeVerticalScrollOffset());
    }
}
