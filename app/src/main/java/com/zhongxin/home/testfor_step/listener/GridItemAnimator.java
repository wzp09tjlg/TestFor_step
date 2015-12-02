package com.zhongxin.home.testfor_step.listener;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * Created by Walter on 2015/10/28.
 */
public class GridItemAnimator extends DefaultItemAnimator {
    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        boolean flag =super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY);
        Animation oldItemScale = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        oldItemScale.setDuration(1000);
        Animation oldItemAlph = new AlphaAnimation(1.0f, 0.0f);
        oldItemAlph.setDuration(1000);
        AnimationSet oldItemAnimSet = new AnimationSet(true);
        oldItemAnimSet.addAnimation(oldItemScale);
        oldItemAnimSet.addAnimation(oldItemAlph);
        oldHolder.itemView.setAnimation(oldItemAnimSet);

        Animation newItemScale = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        newItemScale.setDuration(1000);
        Animation newItemAlph = new AlphaAnimation(0.3f, 1.0f);
        newItemAlph.setDuration(1000);
        AnimationSet newItemAnimSet = new AnimationSet(true);
        newItemAnimSet.addAnimation(newItemScale);
        newItemAnimSet.addAnimation(newItemAlph);
        newHolder.itemView.setAnimation(newItemAnimSet);
        return  flag;

    }
}
