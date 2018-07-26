package raejin.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //2단계 : 리스트뷰의 객체를 저장할 변수를 만든다.
    ListView listView_greatman;

    // 어답터에서 리스트뷰에게 전달할 자료들
    final String[] list = {
            "이순신", "광개토대왕", "신사임당", "세종대왕",
            "이성계", "윤봉길", "안중근"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //2단계 : 리스트뷰의 객체를 만든다.
        listView_greatman = (ListView)findViewById(
                R.id.listView_greatman);
        // 리스트뷰 예외
        // 배열 자료를 리스트뷰로 전달해주는 어답터 : ArrayAdapter<T>
        // T대신에 자료형을 넣는다.
        // 생성자 ArrayAdapter<String>()의 매개변수
        // - MainActivity.this = 엑티비티의 정보, 리스트뷰 객체가
        // 있는 엑티비티 이름.this
        // - android.R.layout.simple_list_item_1 = 리스트뷰 안에
        // 들어가는 항목의 레이아웃 파일(안드로이드에서 기본 제공)
        // - list : 리스트 뷰에게 전달할 배열
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, list);

        listView_greatman.setAdapter(adapter);

        //4단계 : 리스너의 객체 만들기
        ItemListener itemListener = new ItemListener();

        //5단계 : 리스너의 객체를 리스트뷰에 등록하기
        listView_greatman.setOnItemClickListener(itemListener);
    }
    //3단계 : 리스너 객체의 클래스 만들기
    class ItemListener implements AdapterView.OnItemClickListener {

        /**
         *
         * @param adapterView 리스트 뷰의 객체
         * @param view 리스트 뷰 안에 포함된 항목의 객체
         * @param i 리스트 뷰에서 항목의 순서번호(0부터 시작)
         * @param l 리스트 뷰에서 항목의 순서번호(long 자료형)
         */
        @Override
        public void onItemClick(AdapterView<?> adapterView,
                                View view, int i, long l) {
            String str = "내가 선택한 위인은? :" + list[i];
            Toast.makeText(getApplicationContext(),
                    str, Toast.LENGTH_SHORT).show();
        }
    }
}
