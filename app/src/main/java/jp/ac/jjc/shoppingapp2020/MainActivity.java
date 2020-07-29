package jp.ac.jjc.shoppingapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int BalanceNow;
    TextView txBalance;

    private static final int REQUESTCODE_HAMBURGERSHOP = 101;
    private static final int REQUESTCODE_RESTAURANT = 102;
    private static final int REQUESTCODE_LOTTERY = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonListener listener = new ButtonListener();

        Button btnHamburger = findViewById(R.id.hamburgerB);
        btnHamburger.setOnClickListener(listener);
        Button btnRestaurant = findViewById(R.id.restaurantB);
        btnRestaurant.setOnClickListener(listener);
        Button btnLottery = findViewById(R.id.lotteryB);
        btnLottery.setOnClickListener(listener);

        txBalance = findViewById(R.id.balance);

        /*if(txBalance.getText().equals("")) {
            txBalance.setText(String.valueOf(BalanceNow));
        }*/
        Intent intent = getIntent();
        BalanceNow = intent.getIntExtra("EXTRA_DATA", 10000);
        txBalance.setText(String.valueOf(BalanceNow));
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent;

            int id = view.getId();
            switch (id) {
                case R.id.hamburgerB:
                    intent = new Intent(MainActivity.this, HamburgerShopActivity.class);
                    intent.putExtra("EXTRA_DATA", BalanceNow);
                    startActivityForResult(intent, REQUESTCODE_HAMBURGERSHOP);
                    break;
                case R.id.restaurantB:
                    intent = new Intent(MainActivity.this, RestaurantShopActivity.class);
                    intent.putExtra("EXTRA_DATA", BalanceNow);
                    startActivityForResult(intent, REQUESTCODE_RESTAURANT);
                    break;
                case R.id.lotteryB:
                    intent = new Intent(MainActivity.this, LotteryActivity.class);
                    intent.putExtra("EXTRA_DATA", BalanceNow);
                    startActivityForResult(intent, REQUESTCODE_LOTTERY);
                    break;
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String strResult = data.getStringExtra("result");
        switch (requestCode) {
            case REQUESTCODE_HAMBURGERSHOP:
                 BalanceNow = getIntent().getIntExtra("EXTRA_DATA", HamburgerShopActivity.BalanceNow);
                 txBalance.setText(String.valueOf(BalanceNow));
                 break;
            case REQUESTCODE_RESTAURANT:
                BalanceNow = getIntent().getIntExtra("EXTRA_DATA", RestaurantShopActivity.BalanceNow);
                txBalance.setText(String.valueOf(BalanceNow));
                break;
            case REQUESTCODE_LOTTERY:
                BalanceNow = getIntent().getIntExtra("EXTRA_DATA", LotteryActivity.BalanceNow);
                txBalance.setText(String.valueOf(BalanceNow));
                break;
        }
    }
}