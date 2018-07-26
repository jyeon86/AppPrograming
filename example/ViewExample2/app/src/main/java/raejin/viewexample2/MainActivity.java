package raejin.viewexample2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    //2번째 단계 : 컴포넌트의 객체 만들기 위한 변수
    VideoView videoView_video;
    RatingBar ratingBar_big, ratingBar_small;
    TextView textView_showNum;
    SeekBar seekBar_pickNum;
    WebView webView_internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2번째 단계 : 컴포넌트의 객체 만들기 위한 변수에 컴포넌트 객체를 만들어 저장한다.
        videoView_video = (VideoView)findViewById(R.id.videoView_video);

        ratingBar_big = (RatingBar)findViewById(R.id.ratingBar_big);
        ratingBar_small = (RatingBar)findViewById(R.id.ratingBar_small);

        textView_showNum = (TextView)findViewById(R.id.textView_showNum);
        seekBar_pickNum = (SeekBar)findViewById(R.id.seekBar_pickNum);

        webView_internet = (WebView)findViewById(R.id.webView_internet);

        //웹뷰 예외 : 웹페이지를 표시할 때 새창으로 표시하지 않도록 설정한다.
        WebSettings ws = webView_internet.getSettings();
        //웹뷰 예외 : 자바스크립트 사용을 허용한다.
        ws.setJavaScriptEnabled(true);
        webView_internet.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //웹뷰 예외 : 웹뷰가 표시할 사이트 주소를 설정한다.
        webView_internet.loadUrl("http://www.naver.com");


        //비디오뷰 예외 : 비디오가 어디에 저장되어 있는지 설정한다.
        Uri uri = Uri.parse("android.resource://"
                            +getPackageName()+"/raw/network");
        //비디오뷰 예외 : 비디오의 위치를 저장하고 있는 uri 객체를 비디오뷰에 전달한다.
        videoView_video.setVideoURI(uri);
        //비디오뷰 예외 : 비디오를 바로 재생하고 싶은 경우 start()함수를 실행한다.
        videoView_video.start();

        //비디오뷰 예외 : 미디어 컨트롤러를 추가해서 비디오를 제어할 수 있는 버튼을 넣는다.
        // 따라서 미디어 컨트롤러 객체를 만든다.
        MediaController mediaController = new MediaController(this);
        //비디오뷰 예외 : 비디오 뷰의 객체에 미디어 컨트롤러 객체를 등록한다.
        videoView_video.setMediaController(mediaController);

        //4번째 단계 : 리스너의 객체를 만들자.
        RatingListener ratingListener = new RatingListener();

        //5번째 단계 : 리스너 객체를 컴포넌트에 등록하자.
        ratingBar_big.setOnRatingBarChangeListener(ratingListener);

        // 4,5 단계를 한번에 처리한다.
        seekBar_pickNum.setOnSeekBarChangeListener(new SeekListener());
    }

    //3번째 단계 : 컴포넌트에 등록할 리스너의 클래스를 작성하자.
    class SeekListener implements SeekBar.OnSeekBarChangeListener{
        // 시크바를 드래그할 때
        /**
         *
         * @param seekBar 조작한 seekbar 객체
         * @param i 내가 조작한 눈금의 값
         * @param b 사용자가 seekbar를 조작하면 true, 아니면 false
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            textView_showNum.setText("시크바의 값은? : " + i);
        }
        // 시크바를 드래그 하기 시작하려고 터치할 때
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Toast.makeText(getApplicationContext(),
                    "드래그를 시작합니다.",
                    Toast.LENGTH_SHORT).show();
        }
        // 시크바에서 터치를 땔 때
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Toast.makeText(getApplicationContext(),
                    "드래그를 종료합니다.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    class RatingListener implements RatingBar.OnRatingBarChangeListener{
        /**
         *
         * @param ratingBar 내가 조작한 레이팅 바의 객체
         * @param v 별점
         * @param b 사용자가 조작하여 별점을 입력한 경우 true, 아니면 false
         */
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            switch(((View)ratingBar).getId()) {
                case R.id.ratingBar_big:
                    Toast.makeText(getApplicationContext(),
                            "저의 점수는요? " + String.valueOf(v),
                            Toast.LENGTH_SHORT).show();
                    ratingBar_small.setRating(v);
                    break;
            }
        }
    }
}
