package main.currencyboss.currencyconverterpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void convertCurrency(View view) {

        String first_choice;
        String second_choice;

        EditText input_from_user = (EditText) findViewById(R.id.amount_entered);
        TextView result_text = (TextView) findViewById(R.id.result_text);
        RadioGroup first_currency = (RadioGroup) findViewById(R.id.first_currency);
        RadioGroup second_currency = (RadioGroup) findViewById(R.id.second_currency);

        int amount = Integer.valueOf(input_from_user.getText().toString());

        int checked_first_choice = first_currency.getCheckedRadioButtonId();
        int checked_second_choice = second_currency.getCheckedRadioButtonId();

        if( checked_first_choice == -1 || checked_second_choice == -1 ) {

            Message.message(getApplicationContext(), "Please Select 2 Currencies..");

        } else {

            first_choice = convert(checked_first_choice);
            second_choice = convert(checked_second_choice);

            String symbol = grabCurrencySign( first_choice );
            String second_symbol = grabCurrencySign( second_choice );

            double new_amount = calculateConversion( first_choice, second_choice, amount );

            String result_message = "For " + symbol + amount + ", You can get " + second_symbol + String.format("%.2f", new_amount) + ".";

            result_text.setText(result_message);

        }




    }

    private String grabCurrencySign(String first_choice) {
        switch( first_choice ) {
            case "US":
                return "$";
            case "EU":
                return "€";
            case "BP":
                return "£";
            case "YN":
                return "¥";
            default:
                return "";
        }
    }

    private double calculateConversion(String first_choice, String second_choice, int amount) {

        double new_amount = 0;

        switch ( first_choice ) {
            case "US":
                switch ( second_choice ) {
                    case "US":
                        return amount;
                    case "EU":
                        return (amount*.92542);
                    case "BP":
                        return amount * .80990;
                    case "YN":
                        return amount * 7.08945;
                }

            case "EU":
                switch ( second_choice ) {
                    case "US":
                        return amount * 1.08043;
                    case "EU":
                        return amount;
                    case "BP":
                        return amount * 0.87507;
                    case "YN":
                        return amount * 7.65966;
                }
            case "BP":
                switch ( second_choice ) {
                    case "US":
                        return amount * 1.23451;
                    case "EU":
                        return amount * 1.14245;
                    case "BP":
                        return amount;
                    case "YN":
                        return amount * 8.75198;
                }
            case "YN":
                switch ( second_choice ) {
                    case "US":
                        return amount * 0.14101;
                    case "EU":
                        return amount * 0.13049;
                    case "BP":
                        return amount * 0.11420;
                    case "YN":
                        return amount;
                }
            default:
                return 0;
        }
    }

    public String convert(int checked_id) {

        switch (checked_id) {
            case R.id.us:
                return "US";
            case R.id.euro:
                return "EU";
            case R.id.pound:
                return "BP";
            case R.id.yen:
                return "YN";
            default: return "";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
