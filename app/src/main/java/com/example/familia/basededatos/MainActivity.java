package com.example.familia.basededatos;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper peopleDB;

    Button btnAddData,btnViewData;
    EditText etName,etEmail,etTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        peopleDB=new DataBaseHelper(this);
        etName=(EditText) findViewById(R.id.etNewName);
        etEmail=(EditText) findViewById(R.id.etNewEmail);
        etTvShow=(EditText) findViewById(R.id.etNewTvShow);

        btnAddData=(Button)findViewById(R.id.btnAddData);
        btnViewData=(Button)findViewById(R.id.btnViewData);

        AddData();
        ViewData();

    }

    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
             String name= etName.getText().toString();
             String email= etEmail.getText().toString();
             String TvShow= etTvShow.getText().toString();

             boolean insertData=peopleDB.addData(name,email,TvShow);

             if (insertData==true){
                 Toast.makeText(MainActivity.this,"Data succesfully inserted",Toast.LENGTH_LONG).show();

                }else {
                 Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();

             }
            }
        });
    }

    public void ViewData(){
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Cursor data= peopleDB.showData();


                if (data.getCount()==0){
                    display("Error", "no fata found");
                    return;

                }
                StringBuffer buffer=new StringBuffer();
                while (data.moveToNext()){
                    buffer.append("ID: " + data.getString(0) + "\n");
                    buffer.append("Favorite Tv show: " + data.getString(1) + "\n");
                    buffer.append("Email: " + data.getString(2) + "\n");
                    buffer.append("Name: " + data.getString(3) + "\n");
                    display("all stored data", buffer.toString());

                }
        }
    });


}
//metodo para mostrar mensajes
 public void display(String tittle,String message){
     AlertDialog.Builder builder=new AlertDialog.Builder(this);
     builder.setCancelable(true);
     builder.setTitle(tittle);
     builder.setMessage(message);
     builder.show();

 }


}

