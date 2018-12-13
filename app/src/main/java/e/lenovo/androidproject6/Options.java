package e.lenovo.androidproject6;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Options extends AppCompatActivity {

    int userID= 0;
    String userName;

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();


        userID = b.getInt("UserID");
        userName = b.getString("UserName");




    }
    public void balanceButton(View v){

        double TotalCurrent = dbTools.getTotalCurrentBalance(userID);




        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle("Your Current Balance:");
        alertbox.setMessage("Your Total Balance is " + TotalCurrent);
        alertbox.show();

    }

    public void withdrawButton(View v) {
        Intent intent = new Intent(this, Withdraw.class);

        intent.putExtra("UserID",userID);


        startActivity(intent);


    }
    public void depositButton(View v) {
        Intent intent = new Intent(this, Deposit.class);


        intent.putExtra("UserID",userID);
        startActivity(intent);


    }


}
