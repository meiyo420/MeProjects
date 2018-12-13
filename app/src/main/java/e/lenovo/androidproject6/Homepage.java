package e.lenovo.androidproject6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Homepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

    }
    public void onRegister (View v)
    {

        Intent intent = new Intent(this, Register.class);
        startActivity(intent);

    }

    public void onLogin (View v)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

}