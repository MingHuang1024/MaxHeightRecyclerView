package com.example.maxheightrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /** 随机数 */
    private int r = 15;

    private boolean isMaxHeight_a = true;

    MaxHeightRecyclerView maxRecyclerView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        maxRecyclerView = findViewById(R.id.maxRecyclerView);
        maxRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter(generateData());
        maxRecyclerView.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 重新设置列表高度
                int max;
                if (isMaxHeight_a) {
                    max = getResources().getDimensionPixelOffset(R.dimen.max_height_b);
                    isMaxHeight_a = false;
                    ((Button)view).setText("最大高度(400dp)，点击修改");
                } else {
                    max = getResources().getDimensionPixelOffset(R.dimen.max_height_a);
                    isMaxHeight_a = true;
                    ((Button)view).setText("最大高度(300dp)，点击修改");
                }
                maxRecyclerView.setMaxHeight(max);

            }
        });

        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 刷新列表中的数据
                Random random = new Random();
                if (r <= 6) {
                    r = random.nextInt(6) + 20;
                } else {
                    r = random.nextInt(6) + 1;
                }
                adapter.refresh(generateData());
            }
        });
    }

    private List<String> generateData() {
        Log.d("数据条数", "r=" + r);
        List a = new ArrayList();
        for (int i = 0; i < r; i++) {
            a.add("item" + i);
        }
        return a;
    }
}
