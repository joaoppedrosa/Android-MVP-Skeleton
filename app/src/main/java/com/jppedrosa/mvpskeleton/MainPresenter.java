package com.jppedrosa.mvpskeleton;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jppedrosa.mvpskeleton.core.BasePresenter;

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 07/09/2022.
 */
public class MainPresenter extends BasePresenter<MainView> {

    protected MainPresenter(@NonNull MainView view,
                            @NonNull Context mContext) {
        super(view, mContext);
    }
}
