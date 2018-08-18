package com.xinyang.read.ui.girlphoto;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyang.read.R;
import com.xinyang.read.entity.PhotoGirl;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class PhotpAdapter extends BaseQuickAdapter<PhotoGirl, BaseViewHolder> {

    public PhotpAdapter(int layoutResId, @Nullable List<PhotoGirl> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final PhotoGirl item) {
        //标题
        //iv_photo
        ImageView imageView=helper.getView(R.id.iv_photo);
        Glide.with(mContext).load(item.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop().override(1090, 1090*3/4)
                .placeholder(R.drawable.ic_image_loading)
                .crossFade().into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PhotosDetailActivity.class);
                intent.putExtra("url",item.getUrl());
                mContext.startActivity(intent);
            }
        });



    }
}
