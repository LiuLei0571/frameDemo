package com.lenny.framedemo.project.home.temple;

import android.view.View;

import com.lenny.framedemo.compent.base.IAct;


/**
 * Created by yh on 2016/10/27.
 */

public abstract class BaseTemplate<M extends ITemplateModel, VH extends TemplateBaseHolder>
        implements ITemplate<M, VH> {
    private IAct act;
    private View.OnClickListener mOnClick;

    public View.OnClickListener getOnClick() {
        if (mOnClick == null) {
            mOnClick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Object item = v.getTag(R.id.item);
//                    String href = "";
//                    if (item != null && item instanceof ModelPageItem) {
//                        href = ((ModelPageItem) item).getItemUrl();
//                    } else if (item != null && item instanceof Subject) {
//                        href = ((Subject) item).getOutLinkUrl();
//                        if (TextUtils.isEmpty(href)) {
//                            href = ((Subject) item).getDetailUrl();
//                        }
//                    }
//                    if (TextUtils.isEmpty(href)) {
//                        item = v.getTag(R.id.href);
//                        if (item != null && item instanceof String) {
//                            href = (String) item;
//                        }
//                    }
//                    if (!TextUtils.isEmpty(href)) {
//                    }
                }
            };
        }
        return mOnClick;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.mOnClick = listener;
    }

    public BaseTemplate(IAct act) {
        this.act = act;
    }

    @Override
    public void onResume(IAct act) {

    }

    @Override
    public void onPause(IAct act) {

    }

    public IAct getAct() {
        return act;
    }

    @Override
    public void bindCustomViewHolder(VH holder, M model) {

    }

}
