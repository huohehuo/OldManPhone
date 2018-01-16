package com.lins.oldmanphone.ui;

import android.databinding.DataBindingUtil;
import android.view.KeyEvent;

import com.lins.oldmanphone.Config;
import com.lins.oldmanphone.R;
import com.lins.oldmanphone.databinding.ActivityAboutBinding;

public class WebActivity extends BaseActivity {
    ActivityAboutBinding binding;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_about);
        binding.toolbar.ivTopArrow.setImageResource(R.drawable.jpush_ic_richpush_actionbar_back);
        binding.toolbar.tvTopLeft.setText("关于");
    }

    @Override
    protected void initEvent() {
//        binding.wvMine.loadUrl(Config.URL_ABOUT);
            binding.myWv.loadUrl(Config.URL_ABOUT);
//        binding.wvAbout.loadUrl(Config.URL_ABOUT);
        closeActivity(binding.toolbar.ivTopArrow);
    }

    @Override
    protected void getData() {
        //设置WebView属性，能够执行Javascript脚本
//        binding.wvAbout.getSettings().setJavaScriptEnabled(true);
//        binding.wvAbout.loadUrl(Config.URL_ABOUT);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.myWv.canGoBack()) {
            binding.myWv.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
