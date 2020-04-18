package com.mohan.cinta.ui.main.name;

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
import com.mohan.cinta.databinding.MainFragmentBinding;
import com.mohan.cinta.utils.Util;

public class NameFragment extends Fragment implements NameFragmentListener {

    private NameViewModel mViewModel;
    private MainFragmentBinding mainFragmentBinding;

    public static NameFragment newInstance() {
        return new NameFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        View root = mainFragmentBinding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NameViewModel.class);
        // TODO: Use the ViewModel

        mainFragmentBinding.setNameViewModel(mViewModel);
        mViewModel.nameFragmentListener = this;
        mainFragmentBinding.setLifecycleOwner(this);

    }

    @Override
    public void onInValidation(String message) {
        Util.showMessageDialog(getActivity(), message);
    }

    @Override
    public void onSuccess(String fullName) {
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName);
        Navigation.findNavController(mainFragmentBinding.btnNext).navigate(R.id.action_mainFragment_to_mobileFragment, bundle);
    }
}