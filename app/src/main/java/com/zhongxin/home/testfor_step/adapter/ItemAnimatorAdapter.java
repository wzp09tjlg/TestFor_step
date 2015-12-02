package com.zhongxin.home.testfor_step.adapter;

import android.animation.TimeAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.bean.AnimatorBean;
import com.zhongxin.home.testfor_step.listener.OnItemClickListener;
import com.zhongxin.home.testfor_step.listener.OnItemFocusChangeListener;

import java.util.List;

/**
 * Created by Walter on 2015/10/28.
 */
public class ItemAnimatorAdapter extends RecyclerView.Adapter<ItemAnimatorAdapter.ViewHolder> {
    private final String TAG = "ItemAnimatorAdapter";
    private final int DURATION_MS = 1500;
    private List<AnimatorBean> mList;
    private boolean isAnimation = false;
    private Context mContext;
    private Animation animation;
    private OnItemClickListener<AnimatorBean> mOnItemClickListener;
    private OnItemFocusChangeListener<AnimatorBean> mOnItemFocusChangeListener;
    public ItemAnimatorAdapter(Context context,OnItemClickListener<AnimatorBean> onItemClickListener,OnItemFocusChangeListener<AnimatorBean> onItemFocusChangeListener,List<AnimatorBean> list){
        mContext = context;
        mOnItemClickListener = onItemClickListener;
        mOnItemFocusChangeListener = onItemFocusChangeListener;
        mList = list;
        animation = AnimationUtils.loadAnimation(context,R.anim.slide_right);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_itemanimator,null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder,final int position) {
       final AnimatorBean bean = mList.get(position);
        if(bean != null){
            int index = position % 8 ;
            if(isAnimation){
                FocusAnimator animator = (FocusAnimator) viewHolder.itemView
                        .getTag(R.id.lb_focus_animator);
                if (animator == null)
                {
                    animator = new FocusAnimator(viewHolder.img,viewHolder.imgCover,position % 2 + 1, DURATION_MS);
                    viewHolder.itemView.setTag(R.id.lb_focus_animator, animator);
                }
                animator.animateFocus();
                isAnimation = false;
            }

            String iconName = "icon_item"+index;
            switch (index){
                case 0:
                    viewHolder.img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item1));
                    viewHolder.imgCover.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item1));
                    break;
                case 1:
                    viewHolder.img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item2));
                    viewHolder.imgCover.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item2));
                    break;
                case 2:
                    viewHolder.img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item3));
                    viewHolder.imgCover.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item3));
                    break;
                case 3:
                    viewHolder.img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item4));
                    viewHolder.imgCover.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item4));
                    break;
                case 4:
                    viewHolder.img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item5));
                    viewHolder.imgCover.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item5));
                    break;
                case 5:
                    viewHolder.img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item6));
                    viewHolder.imgCover.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item6));
                    break;
                case 6:
                    viewHolder.img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item7));
                    viewHolder.imgCover.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item7));
                    break;
                case 7:
                    viewHolder.img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item8));
                    viewHolder.imgCover.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_item8));
                    break;
            }
           viewHolder.name.setText("" + iconName);
        }else{

        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mOnItemClickListener.onItemClickListener( v, position,bean);
            }
        });
        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mOnItemFocusChangeListener.onItemFocusChangeListener(v,position,bean,hasFocus);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void addItem(){
        mList.add(0,new AnimatorBean());
        notifyItemChanged(0);
    }

    public void deleteItem(){
        mList.remove(0);
        notifyItemChanged(0);
    }

    public void setAnimation(boolean b){
        isAnimation = b;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private ImageView imgCover;
        private TextView name;
        public ViewHolder(View view){
            super(view);
            img = (ImageView)view.findViewById(R.id.step_img_item_animator);
            imgCover = (ImageView)view.findViewById(R.id.step_img_item_cover_animator);
            name = (TextView)view.findViewById(R.id.step_text_item_animator_name);
        }

        public ImageView getImg(){
            return img;
        }
    }

    static class FocusAnimator implements TimeAnimator.TimeListener
    {
        private final String TAG = "ItemAnimatorAdapter";
        private final int mType ;
        private final View mView0;
        private final View mView;
        private final int mDuration;
        private final float mFrom = 0.1f;
        private final float mTo = 1.0f;
        private final TimeAnimator mAnimator = new TimeAnimator();
        private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
        private float mFocusLevel = 0f;
        private float mFocusLevelStart;
        private float mFocusLevelDelta;

        FocusAnimator(View view0,View view, int type,int duration){
            mView0 = view0;
            mView = view;
            mType = type;
            mDuration = duration;
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


        void animateFocus()
        {
            endAnimation();
            final float end =  1;
            mFocusLevel = 0f;
            mFocusLevelStart = mFocusLevel;
            mFocusLevelDelta = end - mFocusLevelStart;
            mAnimator.start();
        }

        float getFocusLevel()
        {
            return mFocusLevel;
        }

        void setFocusLevel(float level)
        {
            if(mType ==1){
                mFocusLevel = level;
                //针对上一个的icon做动画
//            float mAlphaDiff0 = 1.0f;
//            float alpha0 = 1.0f - mAlphaDiff0 * level;
//            mView0.setAlpha(alpha0);
                float mTranslateDiff0 = mView0.getHeight();
                float translationY0 = mTranslateDiff0 - mTranslateDiff0 * level;
                mView0.setTranslationY(translationY0);

                //针对笑一个icon做动画
//            float mAlphaDiff = 1.0f;
//            float alpha = 0f + mAlphaDiff * level;
//            mView.setAlpha(alpha);
                float mTranslateDiff = mView.getHeight();
                float translationY = 0 - mTranslateDiff * level;
                mView.setTranslationY(translationY);
            }else if(mType ==2){
                //根据类型做左右平移
                mFocusLevel = level;
                //针对上一个的icon做动画
//            float mAlphaDiff0 = 1.0f;
//            float alpha0 = 1.0f - mAlphaDiff0 * level;
//            mView0.setAlpha(alpha0);
                float mTranslateDiff0 = mView0.getWidth();
                float translationX0 = 0.0f - mTranslateDiff0 * level;
                mView0.setTranslationX(translationX0);

                //针对笑一个icon做动画
//            float mAlphaDiff = 1.0f;
//            float alpha = 0f + mAlphaDiff * level;
//            mView.setAlpha(alpha);
                float mTranslateDiff = mView0.getWidth();
                float translationX = mView0.getWidth() - mTranslateDiff * level;
                mView.setTranslationX(translationX);
            }
//            //设置旋转
////            mFocusLevel = level;
////            float mScaleDiff = 180f;
////            float scale = 0f + mScaleDiff * level;
////            mView.setRotationY(scale);
//            //设置向上平移 类似win8 的效果
//            mFocusLevel = level;
//            //针对上一个的icon做动画
////            float mAlphaDiff0 = 1.0f;
////            float alpha0 = 1.0f - mAlphaDiff0 * level;
////            mView0.setAlpha(alpha0);
//            float mTranslateDiff0 = -250.0f;
//            float translationY0 = 0.0f + mTranslateDiff0 * level;
//            mView0.setTranslationY(translationY0);
//
//            //针对笑一个icon做动画
////            float mAlphaDiff = 1.0f;
////            float alpha = 0f + mAlphaDiff * level;
////            mView.setAlpha(alpha);
//            float mTranslateDiff = -250.0f;
//            float translationY = 250 + mTranslateDiff * level;
//            mView.setTranslationY(translationY);
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
}
