package com.hhly.mypullrefreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int ON_REFRESH = 1;
    private static final int ON_LOAD_MORE = 2;


    private RecyclerView mRecyclerView;
    private SmartPullableLayout mSmartPullableLayout;
    private ArrayList<String> recyclerData = new ArrayList<>();
    private RecycleViewAdapter mRvAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ON_REFRESH:
                    recyclerData.add(0, "这是全新的数据");
                    mRvAdapter.notifyDataSetChanged();
                    mSmartPullableLayout.stopPullBehavior();
                    break;
                case ON_LOAD_MORE:
                    recyclerData.add("这是加载出来的数据");
                    mRvAdapter.notifyDataSetChanged();
                    mSmartPullableLayout.stopPullBehavior();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mSmartPullableLayout = (SmartPullableLayout) findViewById(R.id.layout_pullable);
        for (int i = 0;i<30;i++){
            recyclerData.add(i+"hahah");
        }

        mRvAdapter = new RecycleViewAdapter(recyclerData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRvAdapter);


        mSmartPullableLayout.setOnPullListener(new SmartPullableLayout.OnPullListener() {
            @Override
            public void onPullDown() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(ON_REFRESH);
                    }
                }).start();
            }

            @Override
            public void onPullUp() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(ON_LOAD_MORE);
                    }
                }).start();
            }

        });
    }
}
