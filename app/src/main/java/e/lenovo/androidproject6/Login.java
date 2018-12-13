package e.lenovo.androidproject6;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    EditText  Account_Num, Pin_Num;
    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Account_Num = findViewById(R.id. accountNumber);
        Pin_Num = findViewById(R.id. pinNumber);
    }

    public void btnEnter (View v)
    {


     String ActNum = Account_Num.getText().toString();
     String PinNm = Pin_Num.getText().toString();

        HashMap<String, String> result = dbTools.getUserByUserNamePassword(ActNum, PinNm);

        if (result.isEmpty()){

            Account_Num.setText("");
            Pin_Num.setText("");
            Toast.makeText(this, "User name and/or password is incorrect", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Login successfull, welcome " + result.get("FullName") , Toast.LENGTH_LONG ).show();
            Intent intent = new Intent(getApplicationContext(), Options.class);
            String names = result.get("UserID");

            Bundle b = new Bundle();
            b.putInt("UserID", Integer.valueOf(result.get("UserID")));
            b.putString("FullName", result.get("FullName"));
           intent.putExtras(b);

            startActivity(intent);
            finish();
        }


    }



}
