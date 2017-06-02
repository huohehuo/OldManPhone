package com.lins.oldmanphone;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bigkoo.pickerview.OptionsPickerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lins.oldmanphone.adapter.MainAdapter;
import com.lins.oldmanphone.bean.AddAddress;
import com.lins.oldmanphone.bean.AddAdrMsg;
import com.lins.oldmanphone.databinding.ActivityHomeBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.lins.oldmanphone.App.getHashData;

public class HomeActivity extends BaseActivity{

    ActivityHomeBinding binding;
    private MainAdapter mainAdapter;
    private ArrayList<String> addressList;
    private OptionsPickerView adrrPick;
    public static boolean isForeground = false;
    BmobUser bmobUser;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0://更新地址列表
                    Log.e("handler","更新地址列表数据");
                    if (App.getHashData(Config.ADDRESS_LIST)!=null){
                        addressList.clear();
                        for (AddAddress adr: (List<AddAddress>)getHashData(Config.ADDRESS_LIST)
                                ) {
                            addressList.add(adr.getAddr());
                        }
                    }
                    binding.content.ryMain.setRefreshing(true);
                    binding.content.ryMain.setRefreshing(false);

                    break;
                case 1:

                    break;
            }

        }
    };
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        binding.content.toolbar.ivTopArrow.setImageResource(R.drawable.bulb);
        binding.content.toolbar.tvTopTitle.setText("首页");
        setDrawerLeftEdgeSize(this, binding.drawerLayout, 0.2f);//设置抽屉滑动响应范围


        binding.content.ryMain.setAdapter(mainAdapter = new MainAdapter(HomeActivity.this));
        binding.content.ryMain.setLayoutManager(new LinearLayoutManager(this));
//        bmobUser = BmobUser.getCurrentUser();

        addressList = new ArrayList<>();
        addressList.add("墨西哥");
        adrrPick = new OptionsPickerView(this);
//        handler.postDelayed(updataNum,2000);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.MAIN_RECEIVER_ACTION);
        registerReceiver(receiver,intentFilter);

//        sendBroadcast(new Intent(Config.RECEIVER_IN_SERVICE).putExtra("content","0"));
        //后台获取主页列表条目数
//        BackService.getMainNum(LoginActivity.this);

//        //后台获取地址列表数据
//        BmobUser bmobUser = BmobUser.getCurrentUser();
//        BackService.getAddressList(LoginActivity.this,bmobUser.getObjectId(),"addr");



    }

    @Override
    protected void initEvent() {
        navClick();
        initAddrData();
        binding.content.ryMain.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.content.toolbar.pbTopRight.setVisibility(View.VISIBLE);
//                if (getHashData(Config.ADDRESS_LIST)!=null){
//                    addressList.clear();
//                    for (AddAddress adr: (List<AddAddress>)getHashData(Config.ADDRESS_LIST)
//                            ) {
//                        addressList.add(adr.getAddr());
//                    }
//                }
//                showDialog("正在更新");
                getMainData();
            }
        });
        mainAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                BackService.getAddressList(MainActivity.this,"asdf","asdf");
//                App.setHashData(Config.ACTIVITY_SEND_DATA, mainAdapter.getAllData().get(position));
//                startActivity(ShowActivity.class);
            }
        });

        binding.content.toolbar.ivTopArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        binding.content.ivChooseAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("contenta","0");
//                sendBroadcast(intent);
                adrrPick.show();
            }
        });

        //选项卡点击回调
        adrrPick.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                App.setSharedData(Config.AT_ADDRESS,addressList.get(options1));
                getMainData();
            }
        });
    }

    private void navClick(){
//        startActivityWith(addActivity.class,binding.layoutNav.llAdd);
//        startActivityWith(AccountActivity.class,binding.layoutNav.llMe);
//        startActivityWith(EditUserInfoActivity.class,binding.layoutNav.ivNavHead);
        binding.layoutNav.llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //退出登录（将会取消自动登录）
        binding.layoutNav.llExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                App.setSharedData(Config.IS_AUTO_LOGIN,"0");
//                startActivity(LoginActivity.class);
                finish();
            }
        });
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void getData() {

        mainAdapter.add(new AddAdrMsg("moxi","address"));
    }

    @Override
    protected void onResume() {
        isForeground= true;
        super.onResume();
        getMainData();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    private void getMainData(){
//        mainAdapter.clear();
//        BmobQuery<AddAdrMsg> shuoBmobQuery = new BmobQuery<>();
//        shuoBmobQuery.addWhereEqualTo("addr",App.getSharedData(Config.AT_ADDRESS));
////        shuoBmobQuery.setLimit(App.getHashMainNum(Config.NUM_OF_MAIN));
//        shuoBmobQuery.findObjects(new FindListener<AddAdrMsg>() {
//            @Override
//            public void done(List<AddAdrMsg> list, BmobException e) {
//                if (e == null) {
//                    mainAdapter.addAll(list);
//                } else {
//                    showToast("获取数据失败");
//                }
//                binding.content.toolbar.pbTopRight.setVisibility(View.GONE);
////                closeDialog();
//            }
//        });
    }

    void initAddrData() {
        if (getHashData(Config.ADDRESS_LIST)!=null){
            for (AddAddress adr: (List<AddAddress>)getHashData(Config.ADDRESS_LIST)
                    ) {
                addressList.add(adr.getAddr());
            }
        }else{
            addressList.add("nothing");
        }

        adrrPick.setPicker(addressList);
        adrrPick.setCyclic(false);
        adrrPick.setSelectOptions(0);
    }

    //广播：用于接收相应相应的事件
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String str=intent.getStringExtra("content");
            Log.e("reciver","广播获得数据："+str);
            switch (str){
                case "0":
                    Log.e("reciver","广播：执行更新地址列表");
                    handler.sendEmptyMessage(0);
                    break;
                case "1":
                    Log.e("reciver","收到jpush数据");
                    handler.sendEmptyMessage(1);
            }
        }
    };

    @Override
    protected void onDestroy() {
//        handler.removeCallbacks(updataNum);
        unregisterReceiver(receiver);
//        ServiceUtil.stopServiceUtil(MainActivity.this);
        super.onDestroy();
    }

    /**
     * 设置全屏滑动
     *
     * @param activity
     * @param drawerLayout
     * @param displayWidthPercentage
     */
    private void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            // 找到 ViewDragHelper 并设置 Accessible 为true
            Field leftDraggerField =
                    drawerLayout.getClass().getDeclaredField("mLeftDragger");//Right
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
            // 找到 edgeSizeField 并设置 Accessible 为true
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            // 设置新的边缘大小
            Point displaySize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (displaySize.x *
                    displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }

    private long firstTime = 0;
    //双击退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                }
                showBottomToast(binding.getRoot(), "再按一次退出程序");
                firstTime = System.currentTimeMillis();
            } else {
//                BmobUser.logOut();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
