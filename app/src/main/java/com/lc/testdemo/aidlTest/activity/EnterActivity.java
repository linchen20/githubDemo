package com.lc.testdemo.aidlTest.activity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lc.testdemo.R;
import com.lc.testdemo.aidlTest.AIDLActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.pm.PackageManager.MATCH_DEFAULT_ONLY;

/**
 *
 * Created by Administrator on 2019/3/3.
 */

public class EnterActivity extends AppCompatActivity {

    private static final String TAG = "EnterActivity ==> ssssss";
    private List<Item> itemList = Collections.synchronizedList(new ArrayList<Item>());

    private RecyclerView rvEnterActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        initMap();
        rvEnterActivity = findViewById(R.id.rvEnterActivity);
        rvEnterActivity.setLayoutManager(new LinearLayoutManager(this));
        rvEnterActivity.setAdapter(new Adapter());

    }

    private void initMap() {
        itemList.add(new Item("AIDL", AIDLActivity.class,MyAction.ACTION_AIDL));
        itemList.add(new Item("Messenger组件间通信", Messenger.class,MyAction.ACTION_MESSENGER));
        itemList.add(new Item("自定义动画框架", DIYViewAnimationActivity.class,MyAction.ACTION_DIY_ANIMATION));
        itemList.add(new Item("RecycleView用占比实现不同条目", GridRecycleViewActivity.class,MyAction.ACTION_GRID));
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(EnterActivity.this).inflate(R.layout.item_enter_activity,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindView(position);
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemDiyAnimation;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItemDiyAnimation = itemView.findViewById(R.id.tvItemDiyAnimation);
        }

        public void bindView(int position){
            final Item item = itemList.get(position);
            tvItemDiyAnimation.setText(item.mItemName);
            tvItemDiyAnimation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(item.action);
                    ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent,MATCH_DEFAULT_ONLY);
                    Log.d(TAG, "onClick: "+resolveInfo);
                    startActivity(intent);
                }
            });
        }
    }

    private class Item{
        protected String mItemName;
        protected Class mClazz;
        protected String action;

        public Item( String mItemName,Class mClazz, String action) {
            this.mClazz = mClazz;
            this.mItemName = mItemName;
            this.action = action;
        }
    }
}
