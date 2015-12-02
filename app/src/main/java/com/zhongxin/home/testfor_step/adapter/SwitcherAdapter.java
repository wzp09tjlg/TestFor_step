package com.zhongxin.home.testfor_step.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.zhongxin.home.testfor_step.R;

/**
 * Created by Walter on 2015/11/6.
 */
public class SwitcherAdapter extends RecyclerView.Adapter<SwitcherAdapter.ViewHolder> {
    private final String TAG = "SwitcherAdapter";
    private Context mContext;
    private int[] mIcons;
    private ViewSwitcher.ViewFactory mViewFactory;
    public SwitcherAdapter(Context context,ViewSwitcher.ViewFactory viewFactory){
        mContext = context;
        mViewFactory = viewFactory;
        getData();
    }

    private void getData(){
        mIcons = new int[]{R.drawable.category_subject,R.drawable.category_anim,
                R.drawable.category_children,R.drawable.category_life,
                R.drawable.category_3d,R.drawable.category_music,
                R.drawable.category_record};
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_switcher,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
      final int icon = mIcons[i];
//      holder.switcher.setImageDrawable(mContext.getResources().getDrawable(icon));
//        holder.img.setImageResource(icon);
//        holder.switcher.setFactory(mViewFactory);
        holder.switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView image = new ImageView(mContext);
//        image.setMinimumHeight(200);
//        image.setMinimumWidth(200);
                image.setScaleType(ImageView.ScaleType.FIT_CENTER);
                image.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return image;
            }
        });
//        holder.switcher.setInAnimation(mContext, R.anim.item_in);
//        holder.switcher.setOutAnimation(mContext, R.anim.item_out);
        holder.switcher.setImageDrawable(mContext.getResources().getDrawable(icon));
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageSwitcher switcher;
        private ImageView img;
        public ViewHolder(View view){
            super(view);
            switcher = (ImageSwitcher)view.findViewById(R.id.step_img_switcher);
//            img      = (ImageView)view.findViewById(R.id.step_img_switcher_img);
        }
    }
}
