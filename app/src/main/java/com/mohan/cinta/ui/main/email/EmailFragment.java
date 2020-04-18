package com.mohan.cinta.ui.main.email;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mohan.cinta.R;
import com.mohan.cinta.databinding.EmailFragmentBinding;
import com.mohan.cinta.utils.Util;

public class EmailFragment extends Fragment implements EmailFragmentListener {

    private EmailViewModel mViewModel;
    private EmailFragmentBinding emailFragmentBinding;
    private String fullName, mobileno;

    public static EmailFragment newInstance() {
        return new EmailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        emailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.email_fragment, container, false);
        View root = emailFragmentBinding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fullName = getArguments().getString("fullName");
        mobileno = getArguments().getString("mobileno");

        mViewModel = new ViewModelProvider(this).get(EmailViewModel.class);
        // TODO: Use the ViewModel

        emailFragmentBinding.setEmailViewModel(mViewModel);
        mViewModel.emailFragmentListener = this;
        emailFragmentBinding.setLifecycleOwner(this);
    }

    @Override
    public void onInValidation(String message) {
        Util.showMessageDialog(getActivity(), message);
    }

    @Override
    public void onSuccess(String emailid) {
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName);
        bundle.putString("mobileno", mobileno);
        bundle.putString("emailid", emailid);
        Navigation.findNavController(emailFragmentBinding.btnNext).navigate(R.id.action_emailFragment_to_messageFragment, bundle);
    }
}
