package sg.edu.rp.c346.id19032110.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // Declaring field variables
    TextView tvAmount;
    EditText etAmount;
    TextView tvPax;
    EditText etPax;
    ToggleButton toggleSVS;
    ToggleButton toggleGST;
    TextView tvBill;
    TextView tvEachPays;
    EditText etDiscount;
    Button buttonSplit;
    Button buttonReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 2: Link the field variables to UI components in layout
        tvAmount = findViewById(R.id.textViewAmount);
        etAmount = findViewById(R.id.editTextAmount);
        tvPax = findViewById(R.id.editTextPax);
        etPax = findViewById(R.id.editTextPax);
        toggleSVS = findViewById(R.id.toggleButtonSVS);
        toggleGST = findViewById(R.id.toggleButtonGST);
        tvBill = findViewById(R.id.textViewTotalBill);
        tvEachPays = findViewById(R.id.textViewEachPays);
        etDiscount = findViewById(R.id.editTextDiscount);
        buttonSplit = findViewById(R.id.Buttonsplit);
        buttonReset = findViewById(R.id.ButtonReset);


        buttonSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double newAmt = 0.0;
                if (!toggleGST.isChecked() && !toggleSVS.isChecked()) {
                    newAmt = Double.parseDouble(etAmount.getText().toString());
                }
                else if (toggleGST.isChecked() && !toggleSVS.isChecked()) {
                    newAmt = Double.parseDouble(etAmount.getText().toString()) * 1.07;
                }
                else if (!toggleGST.isChecked() && toggleSVS.isChecked()) {
                    newAmt = Double.parseDouble(etAmount.getText().toString()) * 1.1;
                }
                else if (toggleSVS.isChecked() && toggleGST.isChecked()) {
                    newAmt = Double.parseDouble(etAmount.getText().toString()) * 1.17;
                }

                // Discount
                if  (etDiscount.getText().toString().trim().length() != 0) {
                    newAmt = newAmt * 1 - Double.parseDouble(etDiscount.getText().toString()) / 100;
                }

                tvBill.setText(String.format("Total bill:$%.2f", newAmt));
                int numPerson = Integer.parseInt(etPax.getText().toString());
                if (numPerson != 1) {
                    tvEachPays.setText(String.format("Each person pays:$%.2f" , newAmt / numPerson));
                }
                else {
                    tvEachPays.setText(String.format("Each person pays:$%.2f" , newAmt));
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.setText("");
                etPax.setText("");
                toggleGST.setChecked(false);
                toggleSVS.setChecked(false);
                etDiscount.setText("");
            }
        });


    }
}
