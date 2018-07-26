package raejin.views3th;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView_basic, listView_custom;
    ArrayAdapter arrayAdapter;
    ArrayList<Listview_Item_Type1> data_list;
    Button button_call;
    ProgressBar progressBar_circle;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar_circle = (ProgressBar)findViewById(R.id.progressBar_circle);
        button_call = (Button)findViewById(R.id.button_callSub);
        button2 = (Button)findViewById(R.id.button_callSub);
        button_call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //객체를 화면에 나타내려면 setVisiblity()함수를 쓴다.
                // 매개변수는 View.Visible, View.INVISIBLE(안보임)
                //View.GONE은 공간을 차지하지않고 완전히 숨김.
                progressBar_circle.setVisibility(View.VISIBLE);
                progressBar_circle.onClick(button2).setVisibility(View.GONE);
                //다른 엑티비티로 이동하는 방법
                //1. 인텐트(도로x,택배o) 객체를 만든다.
                // 인텐트 객체 만들 때 필요한 2가지는 MainActiviriy의 정보 = MainActivity.this
                //SubActivity.class

           //     Intent intent = new Intent(MainActivity.this, SubActivity.class);


                //2. startActiviry()함수로 액티비티를 이동한다.
             //   startActivity(intent);

                /*인텐트의 다른 쓰임새 1 > 웹페이지 보이기
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
//*/
                /* 2>전화걸기
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-5678"));
startActivity(intent);
//*/
               //* 3>지도보기
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.6349120, 127.48689820"));
startActivity(intent);
//*/
            }
        });

        final String[] list = {
                "이순신", "광개토대왕", "신사임당", "세종대왕",
                "이성계", "윤봉길", "안중근"
        };

        listView_basic = (ListView)findViewById(R.id.listView_basic);
        listView_custom = (ListView)findViewById(R.id.listView_custom);

        arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                list);

        listView_basic.setAdapter(arrayAdapter);
        listView_basic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             *
             * @param adapterView 리스트 뷰의 객체
             * @param view 리스트뷰 안의 항목 객체
             * @param i 리스트 뷰에서 항목의 위치
             * @param l 리스트 뷰에서 항목의 위치(long 값)
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        "선택한 항목은 " + list[i] + " 입니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        data_list = new ArrayList<Listview_Item_Type1>();
        data_list.add(new Listview_Item_Type1(R.drawable.leesoonsin, "이순신"));
        data_list.add(new Listview_Item_Type1(R.drawable.gwanggaeto, "광개토대왕"));
        data_list.add(new Listview_Item_Type1(R.drawable.saimdang, "신사임당"));
        data_list.add(new Listview_Item_Type1(R.drawable.sejong, "세종대왕"));
        data_list.add(new Listview_Item_Type1(R.drawable.leesungkye, "이성계"));
        data_list.add(new Listview_Item_Type1(R.drawable.yoon, "윤봉길"));
        data_list.add(new Listview_Item_Type1(R.drawable.ann, "안중근"));

        listView_custom.setAdapter(new CustomAdapter(MainActivity.this, R.layout.item_custom,
                data_list));

        listView_custom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        "선택한 항목은 " + data_list.get(i).getName() + " 입니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
