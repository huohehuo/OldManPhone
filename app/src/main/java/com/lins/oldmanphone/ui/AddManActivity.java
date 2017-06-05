package com.lins.oldmanphone.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lins.oldmanphone.DataUtils.MainBeanManager;
import com.lins.oldmanphone.R;
import com.lins.oldmanphone.bean.MainBean;
import com.lins.oldmanphone.databinding.ActivityAddmanBinding;
import com.lins.oldmanphone.ui.BaseActivity;

public class AddManActivity extends BaseActivity {
    ActivityAddmanBinding binding;
    MainBeanManager mainBeanManager;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addman);
        binding.toolbar.ivTopArrow.setImageResource(R.drawable.jpush_ic_richpush_actionbar_back);
        binding.toolbar.tvTopLeft.setText("添加");
        mainBeanManager = new MainBeanManager();
    }

    @Override
    protected void initEvent() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainBean mainBean = new MainBean();
                mainBean.setName(binding.etName.getText().toString());
                mainBean.setPhone(binding.etPhone.getText().toString());
                mainBeanManager.insert(mainBean);
            }
        });
    }

    @Override
    protected void getData() {

    }
}
