package com.example.vincent.recyclerviewdemo;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerview)
    android.support.v7.widget.RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwiperefreshlayout;

    private ArrayList<String> mList = new ArrayList<>();

    private int[] mStraggeredIcons = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R
            .mipmap.p4, R.mipmap.p5, R.mipmap.p6, R.mipmap.p7, R.mipmap.p8, R.mipmap.p9, R
            .mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p13, R.mipmap.p14, R.mipmap
            .p15, R.mipmap.p16, R.mipmap.p17, R.mipmap.p18, R.mipmap.p19, R.mipmap.p20, R
            .mipmap.p21, R.mipmap.p22, R.mipmap.p23, R.mipmap.p24, R.mipmap.p25, R.mipmap
            .p26, R.mipmap.p27, R.mipmap.p28, R.mipmap.p29, R.mipmap.p30, R.mipmap.p31, R
            .mipmap.p32, R.mipmap.p33, R.mipmap.p34, R.mipmap.p35, R.mipmap.p36, R.mipmap
            .p37, R.mipmap.p38, R.mipmap.p39, R.mipmap.p40, R.mipmap.p41, R.mipmap.p42, R
            .mipmap.p43, R.mipmap.p44};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initEvent();
        initData();

    }

    private void initData() {
        for (int i = 0; i < mStraggeredIcons.length; i++) {
            mList.add("当前的数据 :" + i);
        }

        //int orientation,这个指定方法,boolean reverseLayout)//这个指定排序ture倒着排 flase顺着排
//        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
//        RecyclerView.LayoutManager layout = new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,true);
        RecyclerView.LayoutManager layout = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layout);

        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = View.inflate(parent.getContext(), R.layout.show_item, null);
                MyViewHodler myViewHodler = new MyViewHodler(view);
                return myViewHodler;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                MyViewHodler myViewHodler = (MyViewHodler) holder;

                //设置数据
                myViewHodler.item_straggered_iv.setImageResource(mStraggeredIcons[position]);
                myViewHodler.item_straggered_tv.setText(mList.get(position));
            }

            @Override
            public int getItemCount() {
                return mList.size();
            }
        });

    }

    private void initEvent() {
        mSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("当前下拉刷新了");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //模拟加载
                        SystemClock.sleep(2000);

                        //停止
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mSwiperefreshlayout.setRefreshing(false);
                            }
                        });

                    }
                }).start();

            }
        });
    }

    class MyViewHodler extends RecyclerView.ViewHolder {


        public ImageView item_straggered_iv;
        public TextView item_straggered_tv;

        public MyViewHodler(View itemView) {
            super(itemView);

            item_straggered_iv = (ImageView) itemView.findViewById(R.id.item_straggered_iv);
            item_straggered_tv = (TextView) itemView.findViewById(R.id.item_straggered_tv);
        }
    }

}
