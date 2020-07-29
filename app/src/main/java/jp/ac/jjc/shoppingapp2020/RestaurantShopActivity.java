package jp.ac.jjc.shoppingapp2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class RestaurantShopActivity extends AppCompatActivity {

    public static int BalanceNow;
    AlertDialog.Builder builder;

    final int menu00 = 800;
    final int menu01 = 680;
    final int menu02 = 1980;
    final int menu03 = 980;
    final String title = "ご注文の確認";
    final String confirmation = "本当に注文しますがよろしいですか！？";
    final String notEnough = "お金が足りません。";
    final String thanks = "ご注文ありがとうございました！";
    final String canceled = "キャンセルしました。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_shop_actibvity);

        Intent intent = getIntent();
        BalanceNow = intent.getIntExtra("EXTRA_DATA", MainActivity.BalanceNow);

        ReturnButtonListener listener = new ReturnButtonListener();
        Button btnReturn = findViewById(R.id.backB);
        btnReturn.setOnClickListener(listener);

        ImageButtonListener listener2 = new ImageButtonListener();

        ImageButton imb00 = findViewById(R.id.menu_00);
        imb00.setOnClickListener(listener2);
        ImageButton imb01 = findViewById(R.id.menu_01);
        imb01.setOnClickListener(listener2);
        ImageButton imb02 = findViewById(R.id.menu_02);
        imb02.setOnClickListener(listener2);
        ImageButton imb03 = findViewById(R.id.menu_03);
        imb03.setOnClickListener(listener2);

        builder = new AlertDialog.Builder(this);
    }

    private class ImageButtonListener implements View.OnClickListener{
        @Override
        public void onClick(final View view){
            int id = view.getId();
            int price = 0;
            String show = "";

            builder.setTitle(title);
            builder.setMessage(confirmation);
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String show = canceled;
                    Toast.makeText(RestaurantShopActivity.this, show, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int id = view.getId();
                    int price;
                    String show = "";
                    switch (id){
                        case R.id.menu_00:
                            price = menu00;
                            if(BalanceNow>=price){
                                BalanceNow -= price;
                                show = thanks;
                            }else show = notEnough;
                            break;
                        case R.id.menu_01:
                            price = menu01;
                            if(BalanceNow>=price){
                                BalanceNow -= price;
                                show = thanks;
                            }else show = notEnough;
                            break;
                        case R.id.menu_02:
                            price = menu02;
                            if(BalanceNow>=price){
                                BalanceNow -= price;
                                show = thanks;
                            }else show = notEnough;
                            break;
                        case R.id.menu_03:
                            price = menu03;
                            if(BalanceNow>=price){
                                BalanceNow -= price;
                                show = thanks;
                            }else show = notEnough;
                            break;
                    }
                    Toast.makeText(RestaurantShopActivity.this, show, Toast.LENGTH_LONG).show();
                 }
            });
            builder.show();
            }
    }

    private class ReturnButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent = new Intent();
            intent.putExtra("result", "102");
            intent.putExtra("EXTRA_DATA", BalanceNow);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}