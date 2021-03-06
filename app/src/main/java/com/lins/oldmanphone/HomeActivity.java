package com.lins.oldmanphone;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lins.oldmanphone.DataUtils.MainBeanManager;
import com.lins.oldmanphone.adapter.MainAdapter;
import com.lins.oldmanphone.bean.MainBean;
import com.lins.oldmanphone.databinding.ActivityHomeBinding;
import com.lins.oldmanphone.ui.AddManActivity;
import com.lins.oldmanphone.ui.BaseActivity;
import com.lins.oldmanphone.ui.WebActivity;
import com.lins.oldmanphone.utils.GreenDaoManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private static final int REQUEST_CODE = 0;//请求码
    ActivityHomeBinding binding;
    private MainAdapter mainAdapter;
    private MainBeanManager mainBeanManager;
    private List<MainBean> list = new ArrayList<MainBean>();
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setDrawerLeftEdgeSize(this, binding.drawerLayout, 0.2f);//设置抽屉滑动响应范围
        mainBeanManager = new MainBeanManager();

        binding.content.ryMain.setAdapter(mainAdapter = new MainAdapter(HomeActivity.this));
        binding.content.ryMain.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    protected void initEvent() {
        navClick();
        binding.content.ryMain.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        mainAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MainBean mainBean = mainAdapter.getAllData().get(position);
                Uri uri=Uri.parse("tel:"+mainBean.getPhone());
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(uri);
                HomeActivity.this.startActivity(intent);
//                updataList(mainBeanManager.delete(mainBean));
            }
        });

        mainAdapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final int position) {
                final MainBean mainBean = mainAdapter.getAllData().get(position);
                final AlertDialog.Builder dialog =new AlertDialog.Builder(HomeActivity.this);
                dialog.setTitle("提示")
                        .setMessage("是否删除")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                updataList(mainBeanManager.delete(mainBean));
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                            Toast.makeText(MainActivity.this, "继续使用", Toast.LENGTH_SHORT).show();
//                                moveTaskToBack(true);
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
                return false;
            }
        });

        binding.content.toolbar.ivMainHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });



        binding.content.toolbar.ivMainAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AddManActivity.class);
            }
        });



    }

    private void navClick() {
        startActivityWith(WebActivity.class,binding.layoutNav.llAbout);
//        startActivityWith(addActivity.class,binding.layoutNav.llAdd);
//        startActivityWith(AccountActivity.class,binding.layoutNav.llMe);
//        startActivityWith(EditUserInfoActivity.class,binding.layoutNav.ivNavHead);

        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = GreenDaoManager.getInstance().getSession().getMainBeanDao().queryBuilder().build().list();
        if (list!=null&&list.size()!=0){
            binding.content.tvNodata.setVisibility(View.GONE);
        }else{
            binding.content.tvNodata.setVisibility(View.VISIBLE);
        }
        if (mainAdapter !=null){
            mainAdapter.clear();
            for (MainBean mb : list) {
                mainAdapter.add(mb);
            }
            mainAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void getData() {
        list = GreenDaoManager.getInstance().getSession().getMainBeanDao().queryBuilder().build().list();
        if (mainAdapter !=null){
            mainAdapter.clear();
            for (MainBean mb : list) {
                mainAdapter.add(mb);
            }
            mainAdapter.notifyDataSetChanged();
        }
        binding.content.ryMain.setRefreshing(false);
    }


    //更新列表数据
    private void updataList(List<MainBean> mainBean){
        list.clear();
        mainAdapter.clear();
        list.addAll(mainBean);
        for (MainBean mb : list) {
            mainAdapter.add(mb);
        }
        mainAdapter.notifyDataSetChanged();
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
