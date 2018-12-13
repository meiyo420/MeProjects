package e.lenovo.androidproject6;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Deposit extends AppCompatActivity {

    int userID;
    EditText Depo;
    double depoAmount,TotalCurrent;
    String toConv;
    DialogInterface.OnClickListener dialogClickListener;


    DBTools dbTools = new DBTools(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        Depo = findViewById(R.id.editText2);
        Intent intent = getIntent();

        userID = intent.getExtras().getInt("UserID");


        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        SavenewBalance();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        //No button clicked

                        break;
                }
            }
        };



    }

    public void btnOk(View v){
        TotalCurrent = dbTools.getTotalCurrentBalance(userID);
        depoAmount = Double.parseDouble(Depo.getText().toString());



        toConv = Integer.toString(userID);



        Confirmation();




    }



    private void SavenewBalance()
    {
        TotalCurrent += depoAmount;
        int success = dbTools.toPutWith(toConv,TotalCurrent);

        if(success > 0) {
            Toast.makeText(getApplicationContext(), "Transaction Successful!", Toast.LENGTH_LONG).show();
        }

        finish();


    }


    private  void Confirmation()
    {

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);


        alertbox.setMessage("Are you sure you want to proceed?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("Not yet", dialogClickListener).show();

    }
    private void Amount1()
    {
        if (depoAmount > TotalCurrent)
        {
            Toast.makeText(getApplicationContext(), "Your Withdrawal is Greater than your Current Balance", Toast.LENGTH_LONG).show();

        }
        else
        {

            Confirmation();
        }

    }

}

