package com.zcq.sdk.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zcq.sdk.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyResumeActivity extends AppCompatActivity {

    @BindView(R.id.ic_search)
    ImageView icSearch;
    @BindView(R.id.list_dept)
    ListView listDept;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_dept)
    TextView tvDept;
    @BindView(R.id.list_info)
    RecyclerView listInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resume);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ic_search, R.id.tv_name, R.id.tv_age, R.id.tv_level, R.id.tv_dept})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_search:
                break;
            case R.id.tv_name:
                break;
            case R.id.tv_age:
                break;
            case R.id.tv_level:
                break;
            case R.id.tv_dept:
                break;
        }
    }
}
