package com.chiachen.moviecollections.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;


public abstract class MVPActivity extends BaseActivity implements BaseFragment.BackButtonHandlerInterface {
    private ArrayList<WeakReference<BaseFragment.OnBackClickListener>> backClickListenersList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    protected abstract void initUI();

    @Override
    public void addBackClickListener(BaseFragment.OnBackClickListener onBackClickListener) {
        backClickListenersList.add(new WeakReference<>(onBackClickListener));
    }

    @Override
    public void removeBackClickListener(BaseFragment.OnBackClickListener onBackClickListener) {
        Iterator<WeakReference<BaseFragment.OnBackClickListener>> iterator = backClickListenersList.iterator();
        WeakReference<BaseFragment.OnBackClickListener> weakReference;
        while (iterator.hasNext()) {
            weakReference = iterator.next();
            if (onBackClickListener == weakReference.get()) {
                iterator.remove();
            }
        }
    }

    protected boolean fragmentsBackKeyIntercept() {
        boolean isIntercept = false;
        boolean isFragmentIntercept = false;

        BaseFragment.OnBackClickListener onBackClickListener;
        for (WeakReference<BaseFragment.OnBackClickListener> weakRef : backClickListenersList) {
            onBackClickListener  = weakRef.get();
            if (null != onBackClickListener) {
                isFragmentIntercept = onBackClickListener.onBackClick();
                if (!isIntercept) isIntercept = isFragmentIntercept;
            }
        }
        return isIntercept;
    }
}
