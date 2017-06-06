package com.lins.oldmanphone.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
//        binding.wvMine.loadUrl(Config.URL_ABOUT);
        closeActivity(binding.toolbar.ivTopArrow);
    }

    @Override
    protected void getData() {
        //设置WebView属性，能够执行Javascript脚本
        binding.wvAbout.getSettings().setJavaScriptEnabled(true);
        binding.wvAbout.loadUrl(Config.URL_ABOUT);
//        binding.wvAbout.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                if (newProgress == 100) {
//                    binding.pbWv.setVisibility(View.GONE);
//                } else {
//                    binding.pbWv.setVisibility(View.VISIBLE);
//                    binding.pbWv.setProgress(newProgress);
//                }
//            }
//        });
//        binding.wvAbout.setWebViewClient(new WebViewClient() {
//
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                Config.URL_ABOUT = url;
//                view.loadUrl(Config.URL_ABOUT);
//            }
//        });



    }
}
