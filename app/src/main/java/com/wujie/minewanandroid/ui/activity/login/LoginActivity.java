package com.wujie.minewanandroid.ui.activity.login;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jaeger.library.StatusBarUtil;
import com.jakewharton.rxbinding3.InitialValueObservable;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.wujie.minewanandroid.BaseActivity;
import com.wujie.minewanandroid.R;
import com.wujie.minewanandroid.util.ARouterUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

@Route(path = ARouterUtils.LoginPath)
public class LoginActivity extends BaseActivity<LoginPresenter, LoginContact.View> implements LoginContact.View {


    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void init() {
        StatusBarUtil.setColor(this, Color.BLACK);
        StatusBarUtil.setDarkMode(this); //白字
        StatusBarUtil.setLightMode(this); //黑字
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        InitialValueObservable<CharSequence> nameObservable = RxTextView.textChanges(mEtName);
        InitialValueObservable<CharSequence> pwdObservable = RxTextView.textChanges(mEtPwd);
        addDisposable(Observable.combineLatest(nameObservable, pwdObservable, (charSequence, charSequence2) -> TextUtils.isEmpty(charSequence)&&TextUtils.isEmpty(charSequence2)).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    Toast.makeText(mContext, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.login(getText(mEtName), getText(mEtPwd));
            }
        }));
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
