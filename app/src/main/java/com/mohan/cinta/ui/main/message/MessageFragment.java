package com.mohan.cinta.ui.main.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mohan.cinta.R;
import com.mohan.cinta.databinding.MessageFragmentBinding;
import com.mohan.cinta.network.ResponseData;
import com.mohan.cinta.utils.Util;

public class MessageFragment extends Fragment implements MessageFragmentListener {

    private MessageViewModel mViewModel;
    private MessageFragmentBinding messageFragmentBinding;
    private String fullName, mobileno, emailid;

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        messageFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.message_fragment, container, false);
        View root = messageFragmentBinding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fullName = getArguments().getString("fullName");
        mobileno = getArguments().getString("mobileno");
        emailid = getArguments().getString("emailid");

        mViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        // TODO: Use the ViewModel

        messageFragmentBinding.setMessageViewModel(mViewModel);
        mViewModel.messageFragmentListener = this;
        messageFragmentBinding.setLifecycleOwner(this);
        messageFragmentBinding.progressBar.hide();
    }

    @Override
    public void onInValidation(String message) {
        Util.showMessageDialog(getActivity(), message);
    }

    @Override
    public void onSuccess(String fullName) {
        mViewModel.init(fullName, mobileno, emailid);
    }

    @Override
    public void onSubmit() {
        messageFragmentBinding.progressBar.show();
        mViewModel.getResponseData().observe(getViewLifecycleOwner(), new Observer<ResponseData>() {
            @Override
            public void onChanged(ResponseData responseData) {
                messageFragmentBinding.progressBar.hide();
                if (responseData.getErrormessage() != null) {
                    Toast.makeText(getActivity(), responseData.getErrormessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Successfully saved...", Toast.LENGTH_LONG).show();
                    Navigation.findNavController(messageFragmentBinding.btnNext).navigate(R.id.action_messageFragment_to_mainFragment);
                }
            }
        });
    }
}
