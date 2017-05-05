package com.lenny.framedemo.compent.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenny.framedemo.R;
import com.lenny.framedemo.common.utils.lang.Strings;
import com.lenny.framedemo.compent.base.BaseDialog;
import com.lenny.framedemo.compent.base.IDialog;
import com.lenny.framedemo.compent.cdi.cmp.DialogComponent;
import com.lenny.framedemo.compent.helper.UIHelper;

import butterknife.Bind;

/**
 * 通用对话框
 * Created by yh on 2016/5/6.
 */
public class CommonDialog extends BaseDialog implements AdapterView.OnItemClickListener {
    public static final String tag = "common_dialog";
    @Bind(R.id.dialog_title)
    TextView titleView;
    @Bind(R.id.dialog_btns)
    LinearLayout buttons;

    @Bind(R.id.singleBtnViewStub)
    ViewStub singleBtnViewStub;
    @Bind(R.id.twoBtnViewStub)
    ViewStub twoBtnViewStub;
    @Bind(R.id.textContent)
    ViewStub textContent;
    @Bind(R.id.listViewContent)
    ViewStub listViewContent;
    TextView contentView;
    private Builder builder;

    public CommonDialog(Context context) {
        super(context);
    }

    public static Builder builder(FragmentActivity mActivity) {
        return new Builder(mActivity);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (builder.onItemClickListener != null) {
            boolean isClose = builder.onItemClickListener.onItemClick(this, parent, position);
            if (isClose) {
                dismiss();
            }
        }
    }

    public static class Builder {

        private FragmentActivity mActivity;
        private CharSequence content = Strings.EMPTY;
        private String title;
        private int gravity = Gravity.NO_GRAVITY;
        private DialogBtn[] btns;
        private BaseAdapter adapter;
        private OnItemClickListener onItemClickListener;
        private OnCancelListener onCancelListener;

        private Builder(FragmentActivity activity) {
            this.mActivity = activity;
        }

        /**
         * 设置普通文本对话框
         *
         * @param content
         * @return
         */
        public Builder setContent(CharSequence content) {
            this.content = content;
            return this;
        }

        /**
         * 设置为 列表对话框
         *
         * @param adapter
         * @param onItemClickListener
         * @return
         */
        public Builder setListContent(BaseAdapter adapter, OnItemClickListener onItemClickListener) {
            this.adapter = adapter;
            this.onItemClickListener = onItemClickListener;
            return this;
        }

        public Builder setTitle(String spanned) {
            this.title = spanned;
            return this;
        }

        public Builder setTitle(int resId) {
            this.title = UIHelper.getString(resId);
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setBtns(DialogBtn... btns) {
            this.btns = btns;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.onCancelListener = onCancelListener;
            return this;
        }

        public CommonDialog show() {
            CommonDialog impl = new CommonDialog(mActivity);
            impl.builder = this;
            impl.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (onCancelListener != null) {
                        onCancelListener.onCancel(dialog);
                    }
                }
            });
            impl.show();
            return impl;
        }

    }


    @Override
    public void afterViewBind(Bundle savedInstanceState) {
        super.afterViewBind(savedInstanceState);

        DialogBtn[] btns = builder.btns;
        if (btns != null) {
            if (btns.length == 1) {
                final DialogBtn btn = btns[0];
                View singleBtnView = singleBtnViewStub.inflate();
                Button allButton = (Button) singleBtnView.findViewById(R.id.dialog_btn);
                allButton.setText(btn.getBtnText());
                allButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean act = btn.onBtnClick(CommonDialog.this);
                        if (act) {
                            cancelOrDismiss(btn);
                        }
                    }
                });
            } else if (btns.length == 2) {
                View twoBtnView = twoBtnViewStub.inflate();
                final DialogBtn btn0 = btns[0];
                Button leftButton = (Button) twoBtnView.findViewById(R.id.dialog_btn_left);
                leftButton.setText(btn0.getBtnText());
                leftButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean act = btn0.onBtnClick(CommonDialog.this);
                        if (act) {
                            cancelOrDismiss(btn0);
                        }
                    }
                });

                final DialogBtn btn1 = btns[1];
                Button rightBtn = (Button) twoBtnView.findViewById(R.id.dialog_btn_right);
                rightBtn.setText(btn1.getBtnText());
                rightBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean act = btn1.onBtnClick(CommonDialog.this);
                        if (act) {
                            cancelOrDismiss(btn1);
                        }
                    }
                });
            }
        }
        if (!TextUtils.isEmpty(builder.title)) {
            titleView.setText(builder.title);
        } else {
            titleView.setVisibility(View.GONE);
        }
        View view = textContent.inflate();
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(Html.fromHtml(builder.content.toString()));
        textView.setGravity(builder.gravity);
    }

    @Override
    protected void doInject(DialogComponent baseDialog) {
          baseDialog.plus(this);
    }


    @Override
    protected int getRootView() {
        return R.layout.dialog_common2;
    }

    private void cancelOrDismiss(DialogBtn btn) {
        if (btn instanceof CancelDialogBtn) {
            cancel();
        } else {
            dismiss();
        }
    }

    public interface DialogBtn {
        String getBtnText();

        /**
         * @param dialog
         * @return返回true表示点击后对话框关闭
         */
        boolean onBtnClick(IDialog dialog);

        /**
         * 是否需要统计
         *
         * @return
         */
        boolean needTrack();
    }

    public interface OnItemClickListener {
        /**
         * @param dialog
         * @return返回true表示点击后对话框关闭
         */
        boolean onItemClick(IDialog dialog, AdapterView<?> parent, int position);
    }

    public interface OnCancelListener {
        void onCancel(DialogInterface dialog);
    }

    public static class CustomerDialogBtn implements DialogBtn {
        private String buttonText;

        public CustomerDialogBtn(@StringRes int textId) {
            buttonText = UIHelper.getString(textId);
        }

        public CustomerDialogBtn(String text) {
            buttonText = text;
        }

        @Override
        public final String getBtnText() {
            return buttonText;
        }


        @Override
        public boolean onBtnClick(IDialog dialog) {
            return true;
        }

        @Override
        public boolean needTrack() {
            return false;
        }
    }

    /**
     * 确认
     */
    public static class ConfirmDialogBtn extends CustomerDialogBtn {
        public ConfirmDialogBtn() {
            super("确定");
        }

        @Override
        public boolean onBtnClick(IDialog dialog) {
            return true;
        }
    }

    /**
     * 取消
     */
    public static class CancelDialogBtn extends CustomerDialogBtn {
        public CancelDialogBtn() {
            super("取消");
        }

        @Override
        public boolean onBtnClick(IDialog dialog) {
            return true;
        }
    }

}
