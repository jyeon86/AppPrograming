package com.jiyeon.cookingtimer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button_start, button_stop;
    TextView textView_time;
    EditText editText_min, editText_sec;
    ProgressBar progressBar_main;
    int min, sec;
    CookTimerAsyncTask timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setComponents();
    }

    public String printMin(int min){
        if (min<10){
            return "0" + Integer.toString(min);
        }
        return Integer.toString(min);
    }
    public String printSec(int sec){
        if (sec<10){
            return  "0" + Integer.toString(sec);
        }
        return Integer.toString(sec);
    }

    public void setComponents() {
        progressBar_main = (ProgressBar) findViewById(R.id.progressBar_main);
        editText_min = (EditText) findViewById(R.id.editText_min);
        editText_sec = (EditText) findViewById(R.id.editText_sec);
        button_start = (Button) findViewById(R.id.button_start);
        button_stop = (Button) findViewById(R.id.button_stop);
        textView_time = (TextView) findViewById(R.id.textView_time);

        button_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (editText_min.getText().toString().equals("")) {
                    min = 0;
                } else {
                    min = Integer.parseInt(editText_min.getText().toString());
                    //숫자 > 문자열로 바꾸는 방법
                    // 1. + 연산자 활용 > 1 + "" = "1"
                    // 2. toString()함수를 활용 > Integer.toString(숫자)
                }
                //입력받은 초를 변수에 저장.
                //초는 입력 안하고 분만 입력한 경우
                if (editText_sec.getText().toString().equals("")) {
                    sec = 0;
                } else { //초를 입력한 경우
                    sec = Integer.parseInt(editText_sec.getText().toString());
                }
                //초와 분 둘다 입력 안한 경우
                if (sec == 0 && min == 0) {
                    return;
                }
                // 입력한 시간에 따라 프로그레스 바를 설정하기
                // 입력한 분과 초를 합산하여 초 단위로 변환한다.
                int convert_sec = (min * 60) + sec;
                // 프로그래스 바에 변환한 초를 설정한다.
                progressBar_main.setMax(convert_sec);
                progressBar_main.setProgress(0);
                // 1초씩 세면서 프로그래스바의 텍스트 뷰를 수정한다.
                timer = new CookTimerAsyncTask();
                timer.execute();
            }
        });
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timer != null){
                    timer.cancel(false);
                }
            }
        });
    }
    public  class CookTimerAsyncTask extends AsyncTask<Void, Void, Void>{
        protected void onPreExecute(){
            textView_time.setText(printMin(min) + ":" + printSec(sec));
        }
        protected Void doInBackground(Void...arg){
            while(isCancelled() == false){
                try {
                    Log.d("time_test", String.valueOf(sec));
                    Thread.sleep(1000);
                    sec = sec -1;

                    if (sec<0){
                        min = min-1;
                        sec = 59;
                    }
                    if (sec == 0 && min == 0){
                        break;
                    }
                    publishProgress();
                } catch (Exception e){
                    Log.d("CookingTimer", "doInBackground()");
                }
            }
            return null;
        }

        protected void onProgressUpdate(Void... arg){
            progressBar_main.incrementProgressBy(1);
            Log.d("time_test_print", String.valueOf(sec));
            textView_time.setText(printMin(min) + ":" + printSec(sec));
        }
        protected void  onPostExecute(Void result){
            progressBar_main.incrementProgressBy(1);
            textView_time.setText("00:00");
            Toast.makeText(getApplicationContext(),
                    "타이머가 완료되었습니다.", Toast.LENGTH_LONG).show();
        }
        protected void onCancelled(){
            textView_time.setText("00:00");
            progressBar_main.setProgress(0);
            Toast.makeText(getApplicationContext(),
                    "타이머가 취소되었습니다.", Toast.LENGTH_LONG).show();
        }
    }
}