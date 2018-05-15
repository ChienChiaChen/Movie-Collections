package com.chiachen.moviecollections.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chiachen.moviecollections.R;

/**
 * Created by jianjiacheng on 15/05/2018.
 */

public class BaseFragment extends Fragment {
    public Activity mActivity;
    public View mBaseView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBaseView = view;
    }

    public Toolbar initToolBar(View view, String title) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        return toolbar;
    }


    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public interface OnBackClickListener {
        boolean onBackClick();
    }

    public interface BackButtonHandlerInterface {
        void addBackClickListener(OnBackClickListener onBackClickListener);
        void removeBackClickListener(OnBackClickListener onBackClickListener);
    }
}