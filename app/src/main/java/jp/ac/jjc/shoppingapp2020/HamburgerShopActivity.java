package jp.ac.jjc.shoppingapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HamburgerShopActivity extends AppCompatActivity {

    public static int BalanceNow;
    final int Hamb = 200;
    final int cHam = 250;
    final int Potato = 150;
    final int Cola = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamburger_shop);

        ReturnButtonListener listener = new ReturnButtonListener();
        Button btnReturn = findViewById(R.id.backB);
        btnReturn.setOnClickListener(listener);

        Intent intent = getIntent();
        BalanceNow = intent.getIntExtra("EXTRA_DATA", MainActivity.BalanceNow);

        List<Map<String, String>> menuList = new ArrayList<>();
        Map<String, String>  menu = new HashMap<>();
        menu.put("name", "ハンバーガー");
        menu.put("price", Hamb + "円");
        menuList.add(menu);
        menu = new HashMap<>();
        menu.put("name", "チーズバーガー");
        menu.put("price", cHam + "円");
        menuList.add(menu);
        menu = new HashMap<>();
        menu.put("name", "ポテト");
        menu.put("price", Potato + "円");
        menuList.add(menu);
        menu = new HashMap<>();
        menu.put("name", "コーラ");
        menu.put("price", Cola + "円");
        menuList.add(menu);

        String[] from = {"name", "price"};
        int [] to = {android.R.id.text1, android.R.id.text2};

        SimpleAdapter adapter = new SimpleAdapter(HamburgerShopActivity.this, menuList, android.R.layout.simple_expandable_list_item_2, from, to);
        ListView lvMenu = findViewById(R.id.lvMenu);

        lvMenu.setAdapter(adapter);
        lvMenu.setOnItemClickListener(new ListItemClickListener());
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            /*
            String item = (String) parent.getItemAtPosition(position);
            String show = item + "を買いました。" + android.R.id.text1 + "です。";
            Toast.makeText(HamburgerShopActivity.this, show, Toast.LENGTH_LONG).show();*/

            String show = "";
            String notEnough = "お金が足りません。";

            switch(position){
                case 0:
                    if(BalanceNow >= Hamb){
                        show = "ハンバーガーを買いました。" + String.valueOf(Hamb) + "円です。";
                        BalanceNow -= Hamb;
                    }else show = notEnough;
                    break;
                case 1:
                    if(BalanceNow >= cHam){
                        show = "チーズバーガーを買いました。" + String.valueOf(cHam) + "円です。";
                        BalanceNow -= cHam;
                    }else show = notEnough;
                    break;
                case 2:
                    if(BalanceNow >= Potato){
                        show = "ポテトを買いました。" + String.valueOf(Potato) + "円です。";
                        BalanceNow -= Potato;
                    }else show = notEnough;
                    break;
                case 3:
                    if(BalanceNow >= Cola){
                        show = "コーラを買いました。" + String.valueOf(Cola) + "円です。";
                        BalanceNow -= Cola;
                    }else show = notEnough;
                    break;
            }
            Toast.makeText(HamburgerShopActivity.this, show, Toast.LENGTH_LONG).show();
        }
    }

    private class ReturnButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent = new Intent();
            intent.putExtra("EXTRA_DATA", BalanceNow);
            intent.putExtra("result", "101");

            setResult(RESULT_OK, intent);
            finish();
        }
    }
}