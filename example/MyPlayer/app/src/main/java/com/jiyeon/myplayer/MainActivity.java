package com.jiyeon.myplayer;

import android.media.MediaPlayer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button button_plav, button_pause, button_stop, button_next;
    TextView textView_music_title;
    ListView listView_playlist;
    MediaPlayer mediaPlayer;
    ArrayList<String> music_path_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_play = (Button)findViewById(R.id.button_play);
        button_stop = (Button)findViewById(R.id.button_stop);
        button_play_pause = (Button)findViewById(R.id.button_play);
        button_next = (Button)findViewById(R.id.button_next);
        textView_music_title = (TextView) findViewById(R.id.textView_music_title);
        listView_playlist = (ListView) findViewById(R.id.listView_playlist);

        music_path_list = findFileByExt("mp3");

        mediaPlayer = new MediaPlayer();

        try{
            mediaPlayer.setDataSource(mp3_path);
            mediaPlayer.prepare();
        } catch (Exception ioe){
            ioe.printStackTrace();
        }

        Mp3Listener mp3Listener = new Mp3Listener();

        button_play.setOnClickListener(mp3Listener);
        button_stop.setOnClickListener(mp3Listener);
    }
    private ArrayList<String> findFileByExt(String ext) {
        ArrayList<String> temp_array = new ArrayList<String>();
        final String file_ext = ext;
        // 1. SD카드가 인식되어 있는지 확인한다.
        // 인식이 된경우에는 getExternalStorageState() 함수가 Environment.MEDIA_MOUNT 값을 리턴

        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            //2 sd카드 폴더의 주소를 가져옴
            String sd_path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();

            //3. 찾고자 하는 파일의 확장자를 검색해주는 필더 객체를 생성
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    return s.endsWith(file_ext);
                }
            };
            //4. 파일 필터 객체를 활용하여 파일을 검색
            File sdRoot = new File(sd_path);
            String[] file_list = sdRoot.list(filter);
            // 5. 검색 한 파일 리스트를 출력
            for (int i = 0; i < file_list.length; i++) {
                temp_array.add(sd_path + "/" + file_list[i]);
            }
            Log.d("findFileByExt", ext + " file is listed.");
            return temp_array;
        } else {
            Log.d("findFileByExt", "SD card is unmounted.");
            return null;
        }
    }

    class Mp3Listener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button_play:
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }else{mediaPlayer.start();
                    }
                    break;
                case R.id.button_stop:
                    if(mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        try {
                            mediaPlayer.prepare();
                        }catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                        break;
                    }
            }
        }
    }
}
