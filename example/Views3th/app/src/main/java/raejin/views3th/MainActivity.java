package raejin.views3th;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView_basic, listView_custom;
    ArrayAdapter arrayAdapter;
    ArrayList<Listview_Item_Type1> data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
