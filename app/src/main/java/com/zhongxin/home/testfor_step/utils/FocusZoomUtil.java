package com.zhongxin.home.testfor_step.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.content.res.Resources;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.zhongxin.home.testfor_step.R;

/**
 * Created by zhang on 2015/4/24. 获得焦点放大工具类
 */
public class FocusZoomUtil
{
	public static final int ZOOM_FACTOR_NONE = 0;
	public static final int ZOOM_FACTOR_SMALL = 1;
	public static final int ZOOM_FACTOR_MEDIUM = 2;
	public static final int ZOOM_FACTOR_LARGE = 3;
	public static final int ZOOM_FACTOR_LLARGE = 4;

	private static final int DURATION_MS = 150;

	private static float[] sScaleFactor = new float[5];

	private int mScaleIndex;

	public FocusZoomUtil(int zoomIndex)
	{
		mScaleIndex = (zoomIndex >= 0 && zoomIndex < sScaleFactor.length)
		        ? zoomIndex : ZOOM_FACTOR_MEDIUM;
	}

	private static void lazyInit(Resources resources)
	{
		if (sScaleFactor[ZOOM_FACTOR_NONE] == 0f)
		{
			sScaleFactor[ZOOM_FACTOR_NONE] = 1f;
			sScaleFactor[ZOOM_FACTOR_SMALL] = resources
			        .getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1);
			sScaleFactor[ZOOM_FACTOR_MEDIUM] = resources
			        .getFraction(R.fraction.lb_focus_zoom_factor_medium, 1, 1);
			sScaleFactor[ZOOM_FACTOR_LARGE] = resources
			        .getFraction(R.fraction.lb_focus_zoom_factor_large, 1, 1);
			sScaleFactor[ZOOM_FACTOR_LLARGE] = resources
			        .getFraction(R.fraction.lb_focus_zoom_factor_llarge, 1, 1);
		}
	}

	private float getScale(View view)
	{
		lazyInit(view.getResources());
		return sScaleFactor[mScaleIndex];
	}

	private void viewFocused(View view, boolean hasFocus)
	{
		view.setSelected(hasFocus);
		FocusAnimator animator = (FocusAnimator) view
		        .getTag(R.id.lb_focus_animator);
		if (animator == null)
		{
			animator = new FocusAnimator(view, getScale(view), DURATION_MS);
			view.setTag(R.id.lb_focus_animator, animator);
		}
		animator.animateFocus(hasFocus, false);
	}

	public void onItemFocused(View view, boolean hasFocus)
	{
		viewFocused(view, hasFocus);
	}

	static class FocusAnimator implements TimeAnimator.TimeListener
	{
		private final View mView;
		private final int mDuration;
		private final float mScaleDiff;
		private final TimeAnimator mAnimator = new TimeAnimator();
		private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
		private float mFocusLevel = 0f;
		private float mFocusLevelStart;
		private float mFocusLevelDelta;

		FocusAnimator(View view, float scale, int duration)
		{
			mView = view;
			mDuration = duration;
			mScaleDiff = scale - 1f;
			mAnimator.setTimeListener(this);
		}

		void animateFocus(boolean select, boolean immediate)
		{
			endAnimation();
			final float end = select ? 1 : 0;
			if (immediate)
			{
				setFocusLevel(end);
			}
			else if (mFocusLevel != end)
			{
				mFocusLevelStart = mFocusLevel;
				mFocusLevelDelta = end - mFocusLevelStart;
				mAnimator.start();
			}
		}

		float getFocusLevel()
		{
			return mFocusLevel;
		}

		void setFocusLevel(float level)
		{
			mFocusLevel = level;
			float scale = 1f + mScaleDiff * level;
			mView.setScaleX(scale);
			mView.setScaleY(scale);
		}

		void endAnimation()
		{
			mAnimator.end();
		}

		@Override
		public void onTimeUpdate(TimeAnimator animation, long totalTime,
		        long deltaTime)
		{
			float fraction;
			if (totalTime >= mDuration)
			{
				fraction = 1;
				mAnimator.end();
			}
			else
			{
				fraction = (float) (totalTime / (double) mDuration);
			}
			if (mInterpolator != null)
			{
				fraction = mInterpolator.getInterpolation(fraction);
			}
			setFocusLevel(mFocusLevelStart + fraction * mFocusLevelDelta);
		}
	}

	/**
	 * 键盘点击缩放效果
	 * 
	 * @param view
	 */
	public static void clickScaleAnimation(final View view)
	{
		view.setEnabled(false);
		float scaleX = view.getScaleX();
		float scaleY = view.getScaleY();
		ObjectAnimator ox = ObjectAnimator.ofFloat(view, "scaleX", scaleX, 0.9f,
                scaleX);
		ObjectAnimator oy = ObjectAnimator.ofFloat(view, "scaleY", scaleY, 0.9f,
                scaleY);
		AnimatorSet set = new AnimatorSet();
		set.setDuration(200);
		set.playTogether(ox, oy);
		set.addListener(new Animator.AnimatorListener()
		{
			public void onAnimationStart(Animator animation)
			{
			}

			public void onAnimationRepeat(Animator animation)
			{
			}

			public void onAnimationEnd(Animator animation)
			{
				view.setEnabled(true);
				// 解决失去焦点动画和点击动画同时执行的问题
				if (!view.isFocused())
				{
					view.setScaleX(1);
					view.setScaleY(1);
				}
			}

			public void onAnimationCancel(Animator animation)
			{
			}
		});
		set.start();
	}
	
}
