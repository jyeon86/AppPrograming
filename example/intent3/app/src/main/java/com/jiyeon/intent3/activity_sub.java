package com.jiyeon.intent3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class activity_sub extends AppCompatActivity {
    //1단계 객체 만들기
    TextView textView_sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        //2단계 객체찾기등록
        textView_sub = (TextView)findViewById(R.id.textView_sub);

        //보내는 액티비티에서 전달한 Intent를 intent 변수에 저장한다
        Intent intent = getIntent();
        //데이터를 열어보려면 get**Extra()함수를 쓴다.
        //**는 데이터형
        //ex)int형>> getIntExtra(), string형 >> getStringExtry()
        //읽어오는 데이터형에 따라 함수 이름이 바뀐다.
        int int_value = intent.getIntExtra("INT_TYPE_NAME", -1);//값을 못읽었을때의 기본값-1 지정
        boolean bool_value = intent.getBooleanExtra("STR_TYPE_NAME", false);
        double doub_value = intent.getDoubleExtra("DOUBLE_TYPE_NAME",-1);
        String str = intent.getStringExtra("STR_TYPE_NAME");

        textView_sub.setText("int : "+ int_value + "bool : " + bool_value + "doub : " + doub_value + "str : " +str);
    }
}
