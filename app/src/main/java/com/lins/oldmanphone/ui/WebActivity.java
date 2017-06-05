package com.lins.oldmanphone.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lins.oldmanphone.Config;
import com.lins.oldmanphone.R;
import com.lins.oldmanphone.databinding.ActivityWebBinding;

import static cn.jpush.android.api.b.j;

public class WebActivity extends BaseActivity {
    ActivityWebBinding binding;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_web);
        binding.toolbar.ivTopArrow.setImageResource(R.drawable.jpush_ic_richpush_actionbar_back);
        binding.toolbar.tvTopLeft.setText("关于");
    }

    @Override
    protected void initEvent() {
        binding.wvMine.loadUrl(Config.URL_ABOUT);
        closeActivity(binding.toolbar.ivTopArrow);
    }

    @Override
    protected void getData() {

    }
}
