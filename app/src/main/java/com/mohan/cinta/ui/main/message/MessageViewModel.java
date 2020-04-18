package com.mohan.cinta.ui.main.message;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.mohan.cinta.network.ResponseData;
import com.mohan.cinta.repositories.SubmitDataRepo;

public class MessageViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public String message;
    private SubmitDataRepo submitDataRepo;
    public MessageFragmentListener messageFragmentListener;
    private String fullName, mobileno, emailid;
    private MutableLiveData<ResponseData> responseDataMutableLiveData;

    public void onNext(View view) {
        if (message == null || message.equalsIgnoreCase("")) {
            messageFragmentListener.onInValidation("Please Enter Message");
        } else {
            messageFragmentListener.onSuccess(message);

            submitDataRepo = SubmitDataRepo.getInstance();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("fullname", fullName);
            jsonObject.addProperty("mobileno", mobileno);
            jsonObject.addProperty("emailid", emailid);
            jsonObject.addProperty("message", message);

            responseDataMutableLiveData = submitDataRepo.submitDataCall(jsonObject);
            messageFragmentListener.onSubmit();
        }
    }

    public void init(String fullName, String mobileno, String emailid) {
        this.fullName = fullName;
        this.emailid = emailid;
        this.mobileno = mobileno;
    }

    public LiveData<ResponseData> getResponseData() {
        return responseDataMutableLiveData;
    }
}
