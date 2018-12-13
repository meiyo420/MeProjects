package e.lenovo.androidproject6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DBTools extends SQLiteOpenHelper {

    public DBTools(Context applicationContext){
        super(applicationContext,"ExpensesDB.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Creating of tables for Users and User Expenses
        String query = "CREATE TABLE tblUsers (UserID INTEGER PRIMARY KEY AUTOINCREMENT,FullName TEXT," +
                "ActNum TEXT, PinNum TEXT, Amount REAL) ";
        db.execSQL(query);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query = "DROP TABLE IF EXISTS tblUsers";
        db.execSQL(query);



        onCreate(db);

    }

    public int insertCustomer(HashMap<String, String> queryValues){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("FullName",queryValues.get("FullName"));
        values.put("ActNum",queryValues.get("ActNum"));
        values.put("PinNum",queryValues.get("PinNum"));
        values.put("Amount",Double.parseDouble(queryValues.get("Amount")));



        int success = (int) database.insert("tblUsers", null, values);
        database.close();
        return success;
    }


    public HashMap<String, String> getUserByUserNamePassword(String ActNum, String PinNum){

        HashMap<String, String> customerMap = new HashMap<String, String>();
        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM tblUsers WHERE ActNum  = '" + ActNum + "' AND PinNum = '" + PinNum + "'";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                customerMap.put("UserID", cursor.getString(0));
                customerMap.put("FullName",cursor.getString(1));

            }while(cursor.moveToNext());
        }
        database.close();
        return customerMap;
    }

    public double getTotalCurrentBalance (int userID )
    {
        double totalCurrentBal = 0 ;

        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "Select Amount FROM tblUsers WHERE UserID = " + userID;
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst())
        {
            do {
                totalCurrentBal = Double.parseDouble(cursor.getString(0));
            }while(cursor.moveToNext());
        }



        return totalCurrentBal;
    }



    public int toPutWith(String userID, double amount)
    {


        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserID",userID);
        values.put("Amount",amount);

        int success = (int) database.update("tblUsers", values,"UserID = ?",new String[]{userID});
        database.close();
        return success;




    }



}
