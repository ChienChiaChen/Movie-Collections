package com.chiachen.moviecollections.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by jianjiacheng on 15/05/2018.
 */

public abstract class MVPFragment extends BaseFragment implements BaseFragment.OnBackClickListener {
    private BackButtonHandlerInterface mBackButtonHandler;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    protected abstract void initUI();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBackButtonHandler = (BackButtonHandlerInterface) mActivity;
        mBackButtonHandler.addBackClickListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBackButtonHandler.removeBackClickListener(this);
        mBackButtonHandler = null;
    }
}
