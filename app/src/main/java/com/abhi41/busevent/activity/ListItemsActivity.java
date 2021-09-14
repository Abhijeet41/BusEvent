package com.abhi41.busevent.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.abhi41.busevent.Events.CartEvent;
import com.abhi41.busevent.R;
import com.abhi41.busevent.databinding.ActivityListItemsBinding;
import com.abhi41.busevent.utils.GlobalBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ListItemsActivity extends AppCompatActivity {

    ActivityListItemsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_list_items);
    }

    public void addItemToCart(View view) {
        EventBus.getDefault().post(new CartEvent("new Cart Item"));
    }


}