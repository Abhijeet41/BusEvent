package com.abhi41.busevent.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi41.busevent.Events.Events;
import com.abhi41.busevent.R;
import com.abhi41.busevent.databinding.FragmentUserBinding;
import com.abhi41.busevent.utils.GlobalBus;

import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;


public class UserFragment extends Fragment {

    FragmentUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user,container,false);

        //register the evnt subscriber
        GlobalBus.getBus().register(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setClickListener(view);

    }

    private void setClickListener(@NotNull View view) {

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Events.FragmentActivityMessage fragmentActivityMessage =
                        new Events.FragmentActivityMessage(binding.editText.getText().toString());
                GlobalBus.getBus().post(fragmentActivityMessage);
            }
        });

    }

    @Subscribe
    public void getMessage(Events.ActivityFragmentMessage activityFragmentMessage) {

        binding.message.setText(
                getString(R.string.message_received) +
                        " " + activityFragmentMessage.getMessage());

        Toast.makeText(getActivity(),
                getString(R.string.message_fragment) +
                        " " + activityFragmentMessage.getMessage(),
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GlobalBus.getBus().unregister(this);
    }
}