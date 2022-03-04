package com.smktelkommlg.myintentapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int REQUEST_CODE = 100;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);
        Button btnMoveActivityWithData = findViewById(R.id.btn_move_activity_with_data);
        btnMoveActivityWithData.setOnClickListener(this);
        Button btnMoveActivityWithObject = findViewById(R.id.btn_move_activity_with_object);
        btnMoveActivityWithObject.setOnClickListener(this);
        Button btnDialNumber = findViewById(R.id.btn_dial_number);
        btnDialNumber.setOnClickListener(this);
        Button btnMoveForResult = findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_move_activity:
                Intent moveIntent =  new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_move_activity_with_data:
                Intent intent = new Intent(MainActivity.this, MoveActivity.class);
                intent.putExtra("data1", "somestring data1");
                intent.putExtra("data2", "somestring data2");
                startActivity(intent);
                break;
            case R.id.btn_move_activity_with_object:
                Person manusia = new Person("Athallah Ayudya Paramesti", "athallah@gmail.com", "Kepanjen", 17);
                Intent intent_object = new Intent(MainActivity.this, MoveWithObject.class);
                intent_object.putExtra(MoveWithObject.EXTRA_PERSON, manusia);
                startActivity(intent_object);
                break;
            case R.id.btn_dial_number:
                String phoneNumber = "0812333578688";
                //Uri adalah data yg dikirim ke intent aplikasi lain yaitu dial
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;
            case R.id.btn_move_for_result:
                Intent moveForResultIntent = new Intent(MainActivity.this, MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if (resultCode==MoveForResultActivity.RESULT_CODE){
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0);
                tvResult.setText("Hasil: "+selectedValue);
            }
        }
    }
}