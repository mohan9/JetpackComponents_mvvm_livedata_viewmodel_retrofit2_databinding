package com.mohan.cinta.ui.main.mobile;

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
import com.mohan.cinta.databinding.MobileFragmentBinding;
import com.mohan.cinta.utils.Util;

public class MobileFragment extends Fragment implements MobileFragmentListener {

    private MobileViewModel mViewModel;
    private MobileFragmentBinding mobileFragmentBinding;
    private String fullName;

    public static MobileFragment newInstance() {
        return new MobileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mobileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.mobile_fragment, container, false);
        View root = mobileFragmentBinding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fullName = getArguments().getString("fullName");

        mViewModel = new ViewModelProvider(this).get(MobileViewModel.class);
        // TODO: Use the ViewModel
        mobileFragmentBinding.setMobileViewModel(mViewModel);
        mViewModel.mobileFragmentListener = this;
        mobileFragmentBinding.setLifecycleOwner(this);
    }

    @Override
    public void onInValidation(String message) {
        Util.showMessageDialog(getActivity(), message);
    }

    @Override
    public void onSuccess(String mobileno) {
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName);
        bundle.putString("mobileno", mobileno);
        Navigation.findNavController(mobileFragmentBinding.btnNext).navigate(R.id.action_mobileFragment_to_emailFragment, bundle);
    }
}
