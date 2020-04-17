package com.a.b.c.d;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.Statement;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CDataset extends AppCompatActivity {
    Connection con;
    public static String un = "karad";
    public static String pass = "Rahul@123";
    public static String db = "Dbyesmamu";
    public static String ip = "182.50.133.109";
    private boolean success = false;
    EditText e1;
    private String first;
    String marathi=null;
    String Hindi=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cdataset);
        e1=(EditText)findViewById(R.id.editenglish);
        Button b=findViewById(R.id.btnconvert);

        first = getIntent().getStringExtra("name");
        e1.setText(first);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              SyncData m=new SyncData();
                m.execute("");
            }
        });
    }


    private class SyncData extends AsyncTask<String, String, String>
    {
        String s1=e1.getText().toString();
        @Override
        protected void onPreExecute() //Starts the progress dailog
        {

        }

        @Override
        protected String doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {
            String msg="";
            try
            {
                Cursor c;


                Log.e("username = ", un + pass + db + ip);
                con = connectionclass(un, pass, db, ip);        // Connect to database

                if (con == null)
                {
                    success = false;
                }
                else {

                    // Change below query according to your own database.
                    String query = "SELECT * from tabllang2 where English like'"+s1+"%'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs != null)
                    {
                        while (rs.next())
                        {
                            try {
                                 marathi=rs.getString("Marathi");
                                Hindi=rs.getString("Hindia");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        msg = marathi+"-"+Hindi;
                        success = true;
                    } else {
                        msg = "No Data found!";
                        success = false;
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) // disimissing progress dialoge, showing error and setting up my listview
        {

            Toast.makeText(CDataset.this, msg + "", Toast.LENGTH_LONG).show();
            if (success == false)
            {
            }
            else {
                try {
                    String [] Splited=msg.split("-");
                    String mymarathitext=Splited[0];
                    String myHinditext=Splited[1];
                    EditText e2=findViewById(R.id.edittextmarathi);
                    e2.setText(mymarathitext);
                    EditText e3=findViewById(R.id.editText_Hindi);
                    e3.setText(myHinditext);

                   /* Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                    whatsappIntent.setType("text/plain");
                    whatsappIntent.setPackage("com.whatsapp");
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT, e1.getText().toString()+"="+marathi+"(In Marathi)="+Hindi+"(In Hindi)");
                    try {
                        startActivity(whatsappIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(CDataset.this, "Whatsapp not installed", Toast.LENGTH_SHORT).show();
                    }*/


                    Log.i("Send email", "");

                    String[] TO = {"rahulpawar9766@gmail.com"};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");


                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Language Convertor");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, e1.getText().toString()+"="+marathi+"(In Marathi)="+Hindi+"(In Hindi)");

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        Log.i("Finished sending email", "");
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(CDataset.this,
                                "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }



                } catch (Exception ex)
                {

                }

            }
        }
    }




    @SuppressWarnings("NewApi")
    public Connection connectionclass(String user, String password, String database, String server) {

        Log.e("username = ", user + password + database + server);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + server + ":1433/"+ database + ";user=" + user+ ";password=" + password + ";";
            Log.e("Connection = " , ConnectionURL);
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }

}
