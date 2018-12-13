package e.lenovo.androidproject6;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText Account_Num, Full_Name, Pin_Num;
    TextView wala;
    DialogInterface.OnClickListener dialogClickListener;
    String name,AN,PN;
    double CurrentAmountof = 5000;
    String CurrentAmountof1;

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Account_Num = findViewById(R.id.editAccountNumber);
        Pin_Num = findViewById(R.id.editPin);
        Full_Name = findViewById(R.id.editName);


        CurrentAmountof1 = String.valueOf(CurrentAmountof);


        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        SaveUserDetails();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        //No button clicked

                        break;
                }
            }
        };


    }

    private void ClearAllFields(){
        Full_Name.setText("");
        Account_Num.setText("");
        Pin_Num.setText("");
    }


    private void SaveUserDetails(){

        name = Full_Name.getText().toString();
        AN = Account_Num.getText().toString();
        PN = Pin_Num.getText().toString();

        HashMap<String, String> queryValues = new HashMap<String, String>();
        queryValues.put("FullName", name);
        queryValues.put("ActNum", AN);
        queryValues.put("PinNum", PN);
        queryValues.put("Amount",CurrentAmountof1);


        int success = (int)dbTools.insertCustomer(queryValues);


        if (success > 0)
            Toast.makeText(getApplicationContext(), "User successfully added.", Toast.LENGTH_LONG).show();
        ClearAllFields();
        finish();
    }

    private void SaveNewUser()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Save the newly added user?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("Not yet", dialogClickListener).show();

    }


    public void btnDone(View v)
    {

        if(!isAllRequiredFieldsNotEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please fill out all the required fields that are marked with *.", Toast.LENGTH_LONG).show();
            return;
        }

        else
        {
            SaveNewUser();
        }

    }


    public boolean isAllRequiredFieldsNotEmpty()
    {
        if ((Full_Name.getText().toString().length() != 0) && (Pin_Num.getText().toString().length() != 0) && (Account_Num.getText().toString().length() != 0))
        {
            if (Pin_Num.getText().toString().length() > 4 || Pin_Num.getText().toString().length() < 4) {
                Toast.makeText(getApplicationContext(), "Your Pin Number is Greater or Less than 4 Digits, \nit should be 4 Digits Only  *.", Toast.LENGTH_LONG).show();
                return false;
            }
            else if (Account_Num.getText().toString().length() > 5 || Account_Num.getText().toString().length() < 5 )
            {
                Toast.makeText(getApplicationContext(), "Your Account Number is Greater or Less than 5 Digits, \nit should be 5 Digits Only *.", Toast.LENGTH_LONG).show();
                return false;
            }
            else
                return true;

        }

        else {

            return false;
        }


    }
}
