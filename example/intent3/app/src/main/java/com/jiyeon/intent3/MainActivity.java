package com.jiyeon.intent3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FilenameFilter;

public class MainActivity extends AppCompatActivity {
    //1. 객체만들기
    Button button_main;
    Button button_sd;
    ImageView imageView_sd;
    String[] list;
    TextView textView_love;
    Toolbar toolbar_main;

    public boolean onSupportNavigateUP() {
        Toast.makeText(getApplicationContext(),"안드로이드버튼클릭",show(); return true;)
        onBackPressed();
        return true;
    }
    public boolean onCreateOptionsMenu(Menu amenu){
        getMenuInflater().inflate(R.menu.menu_main, aMenu);
        return true;
    }
    public boolean onOptionItemSelected(){
        switch (item.getItemId()){
            case R.id.item_main_setting:
                Intent intent = new Intent(MainActivity.this, SubActivity.class);{
                intent.putExtra("INT_TYPE_NAME", 1234);
                intent.putExtra("STR_TYPE_NAME", "아!");
                }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //툴바만들기
        toolbar_main = (Toolbar)findViewById(R.id.toolbar_main);
        //툴바의 제목을 수정할 수 있는 setTitle()
        toolbar_main.setTitle("호롤롤");
        //액티비티에 툴바를 설정하는 함수
        setSupportActionBar(toolbar_main);



        // sd카드 1단계: sd카드가 있는지 확인한다.
        //인식된 경우에는 아래 함수가 Environment.MEDIA_MOUNTED 라는 값을 리턴한다.
        String state = Environment.getExternalStorageState();

        //인식된경우
        if(state.equals(Environment.MEDIA_MOUNTED)) {
            // sd 2단계: sd카드의 폴더 주소를 조사한다.
            String sd_path = Environment.getExternalStorageDirectory().getAbsolutePath();

            // sd 3단계: 찾고자 하는 파일의 확장자를 검색하는 필터 객체를 생성한다.
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    return s.endsWith("jpg");
                }
            };
            // sd 4단계 : 만든 파일 필터 객체를 활용하여 SD카드 안에 있는 파일들을 검색한다.
            File sdRoot = new File(sd_path);
            String[] list = sdRoot.list(filter);
            // sd 5단계 : 파일 검색 결과와 SD카드 경로를 조합하여 파일의 완전한 경로를 도출한다.
            //sdcard//
            if (list.length != 0) {
                for (int i = 0; i < list.length; i++) {
                    list[i] = sd_path + "/" + list[i];
                }
            }
        }else{
            Toast.makeText(getApplicationContext(),"sd카드 인식이 안됨", Toast.LENGTH_SHORT).show();

        }
    //버튼과 이미지뷰의 객체

        button_sd = (Button)findViewById(R.id.button_sd);
        imageView_sd = (ImageView) findViewById(R.id.imageView_sd);

        button_sd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(list != null){
                    //list배열에 있는 그림의 주소를 imageView에불러온다


                    //1. list 배열에 있는 그림의 주소(list[0])를 읽어온다.
                    File image_file = new File(list[0]);

                    // 그림 리사이징을 위한 서저을 저장할 BitmapFactiry.Options 객체를 만든다.
                    BitmapFactory.Options options = new BitmapFactory.Options();


                    //BitmapFactory.Options 객체에서 inSampleSiz 값을 바꾼다.
                    //예시)
                    // inSampleSize의 값이 2이면 원래 이미지 크기의 1/2로 리사이징
                    // inSampleSize의 값이 8이면 원래 이미지 크기의 1/8로 리사이징
                    options.inSampleSize = 4;

                    // 파일 경로와 옵션을 적용해서 이미지 (비트맵 파일을 만든다)
                    Bitmap bitmap = BitmapFactory.decodeFile(image_file.getAbsolutePath(),options);

                    //이미지의 크기에 맞게 리사이징하는 createScaledBitmap()
                    //첫번째는 비트맵 파일, 두번째는 너비, 세번째는 높이,
                    //네번째는 리사이즈를 적용할 때 계단현상 보정
                    Bitmap bitmap_resize = Bitmap.createScaledBitmap(bitmap, 400, 300, true);
                    // 웹뷰에 이미지(비트맵파일)을 보여준다.
                    imageView_sd.setImageBitmap(bitmap_resize);

                }
            }
        });






















        //2. 객체 찾게 등록
        button_main = (Button)findViewById(R.id.button_main);

        //3.2 리스너객체만들기
        IntentListener listener = new IntentListener();

        //4.
        button_main.setOnClickListener(listener);
    }
    //3. 리스너를 만들어야함
    //3.1 리스너 객체만들기(클래스)
    class IntentListener implements View.OnClickListener{
       @Override
        public void onClick(View view) {
           //인텐트 객체에 보내는 액티비티, 받는 액티비티를 적어준다.
           Intent intent = new Intent(MainActivity.this, activity_sub.class);
           //intent.putExtra= 데이터를 첨부해서 보낸다.
           //인텐트 객체에 있는 putExtra()함수를 활용해서 데이터를 첨부한다.
           //매개변수1:데이터의 이름, 매개변수2:전달할 데이터
           intent.putExtra("STR_TYPE_NAME", "이게뭐지요?");
           intent.putExtra("BOOLIAN_TYPE_NAME", true);
           intent.putExtra("INT_TYPE_NAME", "1111");

           //다른 액티비티에 이동한다.
           startActivity(intent);
        }
    }
}
