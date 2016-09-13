package com.hankkin.library;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

public class MyImageLoader  {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static class MyImageLoaderHolder {
        private static final MyImageLoader INSTANCE = new MyImageLoader();
    }

    private MyImageLoader() {
    }

    public static final MyImageLoader getInstance() {
        return MyImageLoaderHolder.INSTANCE;
    }

    public void displayImage(Context context, String url, ImageView imageView){
        Glide
                .with(context)
                .load(url)
                .fitCenter()
//                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    //直接加载网络图片
    public void displayImage(final Context context, String url, ImageView imageView, final ProgressBar progressBar) {
        Glide
                .with(context)
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        if (progressBar !=null){
                            progressBar.setVisibility(View.GONE);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (progressBar !=null){
                            progressBar.setVisibility(View.GONE);
                        }
                        return false;
                    }
                })
                .fitCenter()
//                .centerCrop()
                .crossFade()
                .into(new GlideDrawableImageViewTarget(imageView){
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        if (progressBar !=null){
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }


    //加载SD卡图片
    public void displayImage(Context context, File file, ImageView imageView) {
        Glide
                .with(context)
                .load(file)
                .centerCrop()
                .into(imageView);

    }

    //加载SD卡图片并设置大小
    public void displayImage(Context context, File file, ImageView imageView, int width, int height) {
        Glide
                .with(context)
                .load(file)
                .override(width, height)
                .centerCrop()
                .into(imageView);

    }

    //加载网络图片并设置大小
    public void displayImage(final Context context, String url, final ImageView imageView, int width, int height, final ProgressBar progressBar) {
        Glide
                .with(context)
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        if (progressBar !=null){
                            progressBar.setVisibility(View.GONE);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (progressBar !=null){
                            progressBar.setVisibility(View.GONE);
                        }
                        return false;
                    }
                })
                .centerCrop()
                .override(width, height)
                .crossFade()
                .into(imageView);
    }

    //加载网络图片并设置大小
    public void displayImage(Context context, String url, ImageView imageView, int width, int height) {
        Glide
                .with(context)
                .load(url)
                .override(width, height)
                .crossFade()
                .into(imageView);
    }

    public void displayImageFitCenter(Context context, String url, ImageView imageView, int width, int height) {
        Glide
                .with(context)
                .load(url)
                .fitCenter()
                .override(width, height)
                .crossFade()
                .into(imageView);
    }

    public void displayImageCen(Context context, String url, ImageView imageView, int width, int height) {
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .override(width, height)
                .crossFade()
                .into(imageView);
    }

    //加载drawable图片
    public void displayImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .centerCrop()
                .crossFade()
                .into(imageView);
    }


    //将资源ID转为Uri
    public Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }


    public void displayImage(Context context, Uri uri, ImageView imageView){
        Glide.with(context)
                .load(uri)
                .centerCrop()
                .into(imageView);
    }

}