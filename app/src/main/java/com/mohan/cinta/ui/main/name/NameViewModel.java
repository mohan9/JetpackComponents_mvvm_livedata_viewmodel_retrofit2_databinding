package com.mohan.cinta.ui.main.name;

import android.view.View;

import androidx.lifecycle.ViewModel;

public class NameViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public String strFullName;
    public NameFragmentListener nameFragmentListener;

    public void onNext(View view) {
        if (strFullName == null || strFullName.equalsIgnoreCase("")) {
            nameFragmentListener.onInValidation("Please Enter FullName");
        } else {
            nameFragmentListener.onSuccess(strFullName);
        }
    }

}
