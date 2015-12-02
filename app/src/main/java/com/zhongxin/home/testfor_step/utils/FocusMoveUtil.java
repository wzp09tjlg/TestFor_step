package com.zhongxin.home.testfor_step.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by futuo on 2015/4/24.
 */
public class FocusMoveUtil
{
	private static Animator mCurrentAnimator;

	public static void focusMove(View floatView, View focusView, int during)
	{
		focusMove(floatView, focusView, during, 0);
	}

	public static void focusMove(final View floatView, View focusView,
	        int during, int adujest)
	{
		if (mCurrentAnimator != null)
		{
			mCurrentAnimator.cancel();
		}
		Rect startBounds = new Rect();
		Rect finalBounds = new Rect();
		floatView.getGlobalVisibleRect(startBounds);
		focusView.getGlobalVisibleRect(finalBounds);
		ValueAnimator animatorX = ValueAnimator.ofInt(startBounds.right
                - startBounds.left, finalBounds.right - finalBounds.left);
		animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
		{
			@Override
			public void onAnimationUpdate(ValueAnimator animation)
			{
				LayoutParams param = floatView.getLayoutParams();
				param.width = (Integer) animation.getAnimatedValue();
				floatView.setLayoutParams(param);
			}
		});
		ValueAnimator animatorY = ValueAnimator.ofInt(startBounds.bottom
                - startBounds.top, finalBounds.bottom - finalBounds.top);
		animatorY.addUpdateListener(new AnimatorUpdateListener()
		{
			@Override
			public void onAnimationUpdate(ValueAnimator animation)
			{
				LayoutParams param = floatView.getLayoutParams();
				param.height = (Integer) animation.getAnimatedValue();
				floatView.setLayoutParams(param);
			}
		});
		AnimatorSet set = new AnimatorSet();
		set.play(
		        ObjectAnimator.ofFloat(floatView, View.X, startBounds.left,
                        finalBounds.left))
		        .with(ObjectAnimator.ofFloat(floatView, View.Y, startBounds.top
                        - adujest, finalBounds.top - adujest)).with(animatorX)
		        .with(animatorY);
		set.setDuration(during);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd(Animator animation)
			{
				mCurrentAnimator = null;
			}

			@Override
			public void onAnimationCancel(Animator animation)
			{
				mCurrentAnimator = null;
			}
		});
		set.start();
		mCurrentAnimator = set;
	}
}
