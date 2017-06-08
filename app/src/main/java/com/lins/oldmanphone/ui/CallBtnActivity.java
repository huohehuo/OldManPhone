package com.lins.oldmanphone.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lins.oldmanphone.HomeActivity;
import com.lins.oldmanphone.R;
import com.lins.oldmanphone.databinding.ActivityCallBtnBinding;

public class CallBtnActivity extends BaseActivity implements View.OnClickListener{
    ActivityCallBtnBinding binding;
    StringBuilder builder;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_call_btn);
        builder = new StringBuilder();
    }

    @Override
    protected void initEvent() {
        binding.btnA.setOnClickListener(this);
        binding.btnB.setOnClickListener(this);
        binding.btnC.setOnClickListener(this);
        binding.btnD.setOnClickListener(this);
        binding.btnE.setOnClickListener(this);
        binding.btnF.setOnClickListener(this);
        binding.btnG.setOnClickListener(this);
        binding.btnH.setOnClickListener(this);
        binding.btnI.setOnClickListener(this);
        binding.btnJ.setOnClickListener(this);
        binding.ivDel.setOnClickListener(this);
        binding.btnOk.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_a:
                builder.append("1");
                break;
            case R.id.btn_b:
                builder.append("2");
                break;
            case R.id.btn_c:
                builder.append("3");
                break;
            case R.id.btn_d:
                builder.append("4");
                break;
            case R.id.btn_e:
                builder.append("5");
                break;
            case R.id.btn_f:
                builder.append("6");
                break;
            case R.id.btn_g:
                builder.append("7");
                break;
            case R.id.btn_h:
                builder.append("8");
                break;
            case R.id.btn_i:
                builder.append("9");
                break;
            case R.id.btn_j:
                builder.append("0");
                Log.e("asd","dddd");

                break;
            case R.id.iv_del:
                builder.deleteCharAt(binding.etPhone.getText().toString().length()-1);
                Log.e("asd","dddd"+binding.etPhone.getText().toString().length());
                break;
            case R.id.btn_ok:
                Log.e("asd","dddd");
                Uri uri=Uri.parse("tel:"+binding.etPhone.getText().toString().trim());
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(uri);
                startActivity(intent);
                break;
        }
        binding.etPhone.setText(builder.toString());
    }
}
