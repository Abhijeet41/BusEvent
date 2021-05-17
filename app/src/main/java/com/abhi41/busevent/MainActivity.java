package com.abhi41.busevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abhi41.busevent.Events.Events;
import com.abhi41.busevent.databinding.ActivityMainBinding;
import com.abhi41.busevent.fragment.UserFragment;
import com.abhi41.busevent.utils.GlobalBus;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        addFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Register this fragment to listen to event.
        GlobalBus.getBus().register(this);
    }

    private void addFragment() {

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer,new UserFragment())
                .commit();
    }

    public void sendMessageToFragment(View view) {

        Events.ActivityFragmentMessage activityFragmentMessageEvent =
                new Events.ActivityFragmentMessage(String.valueOf(binding.activityData.getText()));

        GlobalBus.getBus().post(activityFragmentMessageEvent);
    }

    @Subscribe
    public void getMessage(Events.FragmentActivityMessage fragmentActivityMessage) {

        binding.message.setText(getString(R.string.message_received) + " " + fragmentActivityMessage.getMessage());

        Toast.makeText(getApplicationContext(),
                getString(R.string.message_main_activity) + " " + fragmentActivityMessage.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }
}