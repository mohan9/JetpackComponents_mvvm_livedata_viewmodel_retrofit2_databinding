package com.mohan.cinta.ui.main.mobile;

import android.view.View;

import androidx.lifecycle.ViewModel;


public class MobileViewModel extends ViewModel {

    public String mobileno;
    // TODO: Implement the ViewModel

    public MobileFragmentListener mobileFragmentListener;

    public void onNext(View view) {
        if (mobileno == null || mobileno.equalsIgnoreCase("")) {
            mobileFragmentListener.onInValidation("Please Enter Mobile No.");
        } else if (!isValidMobileNo(mobileno)) {
            mobileFragmentListener.onInValidation("Please Enter Valid Mobile No.");
        } else {
            mobileFragmentListener.onSuccess(mobileno);
        }
    }

    private Boolean isValidMobileNo(String phone) {
        return phone.matches("^[+]?[0-9]{10}$");
    }
}
