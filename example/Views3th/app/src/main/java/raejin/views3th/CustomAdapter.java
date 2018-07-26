package raejin.views3th;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<Listview_Item_Type1> data_list;
    Context context;
    LayoutInflater layoutInflater;
    int item_layout_id;

    /**
     *
     * @param context 엑티비티의 정보
     * @param item_layout_id 레이아웃 파일의 ID
     * @param data_list 리스트뷰 안에 표현되는 데이터 객체
     */
    CustomAdapter(Context context, int item_layout_id,
                  ArrayList<Listview_Item_Type1> data_list) {
        this.data_list = data_list;
        this.context = context;
        this.item_layout_id = item_layout_id;
        this.layoutInflater = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     *
     * @return 리스트뷰에 표현할 데이터의 수
     */
    @Override
    public int getCount() {
        return data_list.size();
    }

    /**
     *
     * @param i 리스트뷰 항목의 위치
     * @return 리스트뷰 안에 표현되는 데이터 객체
     */
    @Override
    public Object getItem(int i) {
        return data_list.get(i);
    }

    /**
     *
     * @param i 리스트뷰 항목의 위치
     * @return 리스트뷰 항목의 위치
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     *
     * @param i 리스트뷰 안 항목의 위치
     * @param view 항목 객체
     * @param viewGroup 리스트뷰 객체
     * @return 항목의 레이아웃 객체
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        /**
         * >> onclick()에서 final로 선언해야 하는 이유
         * 메서드 내에서 생성된 익명 객체는 메서드 실행이 끝나도 힙 메모리 영역에 존재해서 계속 사용할수 있습니다.
         * 매개 변수나 로컬 변수는 메서드 실행이 끝나면 스택 메모리 영역에서 사라지기 때문에
         * 익명 객체에서는 사옹할 수 없게 되서 문제가 발생합니다.
         */
        if(view == null) {
            view = layoutInflater.inflate(
                    item_layout_id,
                    viewGroup,
                    false);
        }

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView_photo);
        TextView textView = (TextView)view.findViewById(R.id.textView_name);

        imageView.setImageResource(data_list.get(i).image_id);
        textView.setText(data_list.get(i).name);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,
                        "이 사진은 "+data_list.get(pos).getName()+"의 사진입니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
