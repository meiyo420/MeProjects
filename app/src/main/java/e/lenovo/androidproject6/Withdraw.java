package e.lenovo.androidproject6;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class Withdraw extends AppCompatActivity {

    int userID = 0;
    EditText Withdr;
    DBTools dbTools = new DBTools(this);
    double TotalCurrent =0.00;
    double withAmount;
    DialogInterface.OnClickListener dialogClickListener;

    TextView WAla;


    String toConv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        Intent intent = getIntent();
        userID = intent.getExtras().getInt("UserID");



        Withdr = findViewById(R.id.editText2);

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
        withAmount = Double.parseDouble(Withdr.getText().toString());




       toConv = Integer.toString(userID);


        Amount1();

        //int success = dbTools.toPutWith(toConv,TotalCurrent);



   /*     if(success > 0) {
            Toast.makeText(getApplicationContext(), "Transaction Successful!", Toast.LENGTH_LONG).show();
        }

        if (withAmount > TotalCurrent)
        {
            Toast.makeText(getApplicationContext(), "Transaction Successful!", Toast.LENGTH_LONG).show();

        }
     */

/*
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage("Are you sure you want to proceed?");

        alertbox.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                            if(success > 0) {
                                Toast.makeText(getApplicationContext(), "Transaction Successful!", Toast.LENGTH_LONG).show();
                             //   returnOptions();
                            }
                        returnOptions();
                    }
                });
        alertbox.setNeutralButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //returnHome(); Wala to
                    }
                });
        alertbox.show();
        */
    }

    /*
    private void returnHome(){
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
        finish();
    }
*/

    private void SavenewBalance()
    {
        TotalCurrent -= withAmount;
        int success = dbTools.toPutWith(toConv,TotalCurrent);

        if(success > 0) {
            Toast.makeText(getApplicationContext(), "Transaction Successful!", Toast.LENGTH_LONG).show();
        }
       // returnOptions();
        finish();


    }


    private  void Confirmation()
    {

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);


        alertbox.setMessage("Are you sure you want to proceed?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("Not yet", dialogClickListener).show();

    }
    private void Amount1()
    {
        if (withAmount > TotalCurrent)
        {
            Toast.makeText(getApplicationContext(), "Your Withdrawal is Greater than your Current Balance", Toast.LENGTH_LONG).show();

        }
        else
        {

            Confirmation();
        }

    }



   /* private void returnOptions(){
        Intent intent = new Intent(this, Options.class);
        startActivity(intent);
        finish();
    }
    */
}

