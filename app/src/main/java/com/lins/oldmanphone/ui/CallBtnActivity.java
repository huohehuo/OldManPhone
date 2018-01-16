package com.lins.oldmanphone.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.lins.oldmanphone.R;
import com.lins.oldmanphone.databinding.ActivityCallBtnBinding;

public class CallBtnActivity extends BaseActivity implements View.OnClickListener{
    ActivityCallBtnBinding binding;
    StringBuilder builder;
//    private SoundPool soundPool;//声明一个SoundPool
    private int soundID;//创建某个声音对应的音频ID
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_call_btn);
        binding.toolbar.ivTopArrow.setImageResource(R.drawable.jpush_ic_richpush_actionbar_back);
        binding.toolbar.tvTopLeft.setText("拨号");
        builder = new StringBuilder();
        initSound();
    }

    @SuppressLint("NewApi")
    private void initSound() {
//        soundPool = new SoundPool.Builder().build();
//        soundID = soundPool.load(this, R.raw.water, 1);
    }
    private void playSound() {
//        soundPool.play(
//                soundID,
//                0.1f,   //左耳道音量【0~1】
//                0.5f,   //右耳道音量【0~1】
//                1,     //播放优先级【0表示最低优先级】
//                0,     //循环模式【0表示循环一次，-1表示一直循环，其他表示数字+1表示当前数字对应的循环次数】
//                1     //播放速度【1是正常，范围从0~2】
//        );
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

        closeActivity(binding.toolbar.ivTopArrow);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View view) {
//        playSound();
        switch (view.getId()){
            case R.id.btn_a:
                setPhoneNum("1");
                break;
            case R.id.btn_b:
                setPhoneNum("2");
                break;
            case R.id.btn_c:
                setPhoneNum("3");
                break;
            case R.id.btn_d:
                setPhoneNum("4");
                break;
            case R.id.btn_e:
                setPhoneNum("5");
                break;
            case R.id.btn_f:
                setPhoneNum("6");
                break;
            case R.id.btn_g:
                setPhoneNum("7");
                break;
            case R.id.btn_h:
                setPhoneNum("8");
                break;
            case R.id.btn_i:
                setPhoneNum("9");
                break;
            case R.id.btn_j:
                setPhoneNum("0");

                break;
            case R.id.iv_del:
                if (binding.etPhone.getText().toString().length()==0){
                    break;
                }
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

    private void setPhoneNum(String number){
        if (builder.length()<13){
            builder.append(number);
            if (builder.length()==3){
                builder.append("-");
            }
            if (builder.length()==8){
                builder.append("-");
            }
        }else{
            showToast("号码已超出限制长度");
        }

    }
}
