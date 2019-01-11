package com.wujie.minewanandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wujie.minewanandroid.widget.StatusLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingActivity extends AppCompatActivity {

    @BindView(R.id.status_layout)
    StatusLayout mStatusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);

        mStatusLayout.setError();
        Toast.makeText(this, "谈吐上", Toast.LENGTH_SHORT).show();
        mStatusLayout.setOnRetryClickListener(new StatusLayout.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                Toast.makeText(LoadingActivity.this, "重新连接", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
