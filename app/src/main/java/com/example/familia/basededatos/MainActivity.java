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

    Button btnAddData,btnViewData,btnUpdateData,btnDelete;
    EditText etName,etEmail,etTvShow,etID;

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
        btnUpdateData = (Button) findViewById(R.id.btnUpdateData);
        etID = (EditText) findViewById(R.id.etID);
        btnDelete = (Button) findViewById(R.id.btnDelete);


        AddData();
        ViewData();
        UpdateData();
        DeleteData();

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
                    buffer.append("salon " + data.getString(1) + "\n");
                    buffer.append("sede: " + data.getString(2) + "\n");
                    buffer.append("edificio: " + data.getString(3) + "\n");
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
    public void UpdateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etID.getText().toString().length();
                if (temp > 0) {
                    boolean update = peopleDB.updateData(etID.getText().toString(), etName.getText().toString(),
                            etEmail.getText().toString(), etTvShow.getText().toString());
                    if (update == true) {
                        Toast.makeText(MainActivity.this, "Successfully Updated Data!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Something Went Wrong :(.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "You Must Enter An ID to Update :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etID.getText().toString().length();
                if(temp > 0){
                    Integer deleteRow = peopleDB.deleteData(etID.getText().toString());
                    if(deleteRow > 0){
                        Toast.makeText(MainActivity.this, "Successfully Deleted The Data!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "You Must Enter An ID to Delete :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}

