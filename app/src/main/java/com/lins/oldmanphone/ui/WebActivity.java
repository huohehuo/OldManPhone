package com.lins.oldmanphone.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lins.oldmanphone.R;
import com.lins.oldmanphone.databinding.ActivityWebBinding;

public class WebActivity extends BaseActivity {
    ActivityWebBinding binding;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_web);

    }

    @Override
    protected void initEvent() {
        binding.wvMine.loadUrl("http://lins.baiduux.com/h5/610f1dbb-4715-1db6-ec6e-da4fa6872e92.html");
    }

    @Override
    protected void getData() {

    }
}
