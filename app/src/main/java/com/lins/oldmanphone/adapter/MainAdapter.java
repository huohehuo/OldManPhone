package com.lins.oldmanphone.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

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

        private TextView title,word;
        public MainHolder(ViewGroup parent) {
            super(parent, R.layout.item_main);
            title = $(R.id.tv_main_title);
            word = $(R.id.tv_main_word);
        }



        @Override
        public void setData(MainBean data) {
            super.setData(data);
                title.setText(data.getName());
                word.setText(data.getPhone());

//            Glide.with(getContext())
//                    .load(data.getPic())
//                    .placeholder(R.mipmap.ic_launcher)
//                    .centerCrop()
//                    .into(imageView);

        }
    }
}
