package jp.ac.jjc.shoppingapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class LotteryActivity extends AppCompatActivity {

    public static int BalanceNow;

    static String Result;
    static int Winning;
    static int GachaRate;
    Random random = new Random();
    final int firstP = 10000;
    final int secondP = 1000;
    final int thirdP = 300;
    final int fourthP = 100;
    final int nonP = 0;
    final String winFirstP = "結果は、１等　です。";
    final String winSecondP = "結果は、２等　です。";
    final String winThirdP = "結果は、３等　です。";
    final String winFourthP = "結果は、４等　です。";
    final String lose = "結果は、ハズレ　です…。";
    final String notEnough = "お金が足りません。";
    TextView txResult;
    TextView txWinning;
    TextView txMoney;
    ImageView imSecret;
    int imgID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery);

        Intent intent = getIntent();
        BalanceNow = intent.getIntExtra("EXTRA_DATA", MainActivity.BalanceNow);

        Button btnReturn = findViewById(R.id.backB);
        ReturnButtonListener listener1 = new ReturnButtonListener();
        btnReturn.setOnClickListener(listener1);

        Button drawLots = findViewById(R.id.drawlotsB);
        GachaListener listener2 = new GachaListener();
        drawLots.setOnClickListener(listener2);

        txResult = findViewById(R.id.result);
        txWinning = findViewById(R.id.prize);
        txMoney = findViewById(R.id.moneyNow);
        txMoney.setText(String.valueOf(BalanceNow));
    }

    private class GachaListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            imSecret = findViewById(R.id.secretImage);
            imSecret.setImageResource(0);

            if(BalanceNow>=300) {
                BalanceNow -= 300;
                GachaRate = random.nextInt(100) + 1;
                if (GachaRate == 1) {
                    Result = winFirstP;
                    Winning = firstP;
                    imgID = R.drawable.congratulation;
                    imSecret.setImageResource(imgID);
                } else if (GachaRate > 2 && GachaRate <= 10) {
                    Result = winSecondP;
                    Winning = secondP;
                } else if (GachaRate > 10 && GachaRate <= 20) {
                    Result = winThirdP;
                    Winning = thirdP;
                } else if (GachaRate > 20 && GachaRate <= 30) {
                    Result = winFourthP;
                    Winning = fourthP;
                } else if (GachaRate > 30 && GachaRate <= 100) {
                    Result = lose;
                    Winning = nonP;
                }
                BalanceNow += Winning;
                txResult.setText(Result);
                txWinning.setText(Winning + "円当たりました。");

            }else {
                txResult.setText(notEnough);
                txWinning.setText("");

            }
            txMoney.setText(String.valueOf(BalanceNow));
        }
    }

    private class ReturnButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("result", "103");
            intent.putExtra("EXTRA_DATA", BalanceNow);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}