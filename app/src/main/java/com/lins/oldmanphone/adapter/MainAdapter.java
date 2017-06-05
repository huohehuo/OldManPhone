package com.lins.oldmanphone.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lins.oldmanphone.R;
import com.lins.oldmanphone.bean.MainBean;

import java.util.List;


/**
 * Created by LINS on 2017/5/2.
 */

public class MainAdapter extends RecyclerArrayAdapter<MainBean> {
    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainHolder(parent);
    }

    class MainHolder extends BaseViewHolder<MainBean> {

        private TextView name;
        private ImageView imageView;

        public MainHolder(ViewGroup parent) {
            super(parent, R.layout.item_main);
            name = $(R.id.tv_main_name);
            imageView = $(R.id.iv_main_pic);
        }


        @Override
        public void setData(MainBean data) {
            super.setData(data);
            name.setText(data.getName());

            if (data.getBgurl() != null) {
                Log.e("asd","asdfasdf");
                Glide.with(getContext())
                        .load(data.getBgurl())
                        .placeholder(R.drawable.camera)
                        .centerCrop()
                        .into(imageView);
            }else{
                Glide.with(getContext()).load(R.drawable.bg_adapter).into(imageView);
                Log.e("asd","-------");
            }


        }
    }
}
