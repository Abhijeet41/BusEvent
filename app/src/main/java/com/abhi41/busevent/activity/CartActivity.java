package com.abhi41.busevent.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.abhi41.busevent.Events.CartEvent;
import com.abhi41.busevent.R;
import com.abhi41.busevent.databinding.ActivityCartBinding;
import com.abhi41.busevent.utils.GlobalBus;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    List<CartEvent> cartEventList = new ArrayList<>();
    private static final String TAG = "CartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
    }

    @Subscribe
    public void onCartItemAdd(CartEvent cartEvent) {
        cartEventList.add(cartEvent);
        String cartTotalItems = "Cart Items: " + cartEventList.size();
        binding.cartTextView.setText(cartTotalItems);

        for (int i = 0; i <cartEventList.size(); i++) {
            Log.d(TAG, cartEventList.get(i).cartItem);
        }

        Toast.makeText(this, "Item added to cart.", Toast.LENGTH_SHORT).show();
    }

    public void open(View view) {
        Intent intent = new Intent(CartActivity.this, ListItemsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!GlobalBus.getBus().isRegistered(this))
            GlobalBus.getBus().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalBus.getBus().unregister(this);
    }
}