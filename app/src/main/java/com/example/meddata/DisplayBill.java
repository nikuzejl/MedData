package com.example.meddata;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayBill extends Activity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb ;

    TextView date, hospital, fee, copay;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bill);
        date = (TextView) findViewById(R.id.date);
        hospital = (TextView) findViewById(R.id.hospital);
        fee= (TextView) findViewById(R.id.fee);
        copay = (TextView) findViewById(R.id.copay);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String _date= rs.getString(rs.getColumnIndex(DBHelper.BILLS_COLUMN_DATE));
                String _hospital = rs.getString(rs.getColumnIndex(DBHelper.BILLS_COLUMN_HOSPITAL));
                String _fee = rs.getString(rs.getColumnIndex(DBHelper.BILLS_COLUMN_FEE));
                String _copay= rs.getString(rs.getColumnIndex(DBHelper.BILLS_COLUMN_COPAY));


                if (!rs.isClosed())  {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id._button);
                b.setVisibility(View.INVISIBLE);

                date.setText((CharSequence)_date);
                date.setFocusable(false);
                date.setClickable(false);

                hospital.setText((CharSequence)_hospital);
                hospital.setFocusable(false);
                hospital.setClickable(false);

                fee.setText((CharSequence)_fee);
                fee.setFocusable(false);
                fee.setClickable(false);

                copay.setText((CharSequence)_copay);
                copay.setFocusable(false);
                copay.setClickable(false);

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_bill, menu);
            } else{
                getMenuInflater().inflate(R.menu.main_menu, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.Edit_Contact:
                Button b = (Button)findViewById(R.id._button);
                b.setVisibility(View.VISIBLE);
                date.setEnabled(true);
                date.setFocusableInTouchMode(true);
                date.setClickable(true);

                hospital.setEnabled(true);
                hospital.setFocusableInTouchMode(true);
                hospital.setClickable(true);

                fee.setEnabled(true);
                fee.setFocusableInTouchMode(true);
                fee.setClickable(true);

                copay.setEnabled(true);
                copay.setFocusableInTouchMode(true);
                copay.setClickable(true);

                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to delete this?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateContact(id_To_Update,date.getText().toString(),
                        hospital.getText().toString(), fee.getText().toString(),
                        copay.getText().toString() )){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(mydb.insertContact(date.getText().toString(),
                        hospital.getText().toString(), fee.getText().toString(),
                        copay.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }
    }
}