package com.mohan.cinta.ui.main.email;

import android.view.View;

import androidx.lifecycle.ViewModel;

public class EmailViewModel extends ViewModel {

    public String email;
    // TODO: Implement the ViewModel

    public EmailFragmentListener emailFragmentListener;

    public void onNext(View view) {
        if (email == null || email.equalsIgnoreCase("")) {
            emailFragmentListener.onInValidation("Please Enter Email Id.");
        } else if (!isValidEmail(email)) {
            emailFragmentListener.onInValidation("Please Enter Valid Email Id.");
        } else {
            emailFragmentListener.onSuccess(email);
        }
    }

    private Boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
    }
}
