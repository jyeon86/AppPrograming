package rj.view2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 버튼 객체를 저장할 맴버 변수를 만든다.
    Button button_english, button_korean;

    // 에디트 택스트 객체를 저장할 맴버 변수를 만들어 놓는다.
    EditText editText_name, editText_password, editText_number;
    TextView textView_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // ID로 레이아웃에 있는 버튼의 객체를 읽어와서 맴버 변수에 저장한다.
        button_english = (Button)findViewById(R.id.button_english);
        button_korean = (Button)findViewById(R.id.button_korean);

        // ID로 레이아웃에 있는 에디트 택스트의 객체를 읽어와서 맴버 변수에 저장한다.
        editText_name = (EditText)findViewById(R.id.editText_name);
        editText_number = (EditText)findViewById(R.id.editText_number);
        editText_password = (EditText)findViewById(R.id.editText_password);
        textView_text = (TextView)findViewById(R.id.textView_text);

        // EngListener의 객체 만들기
        EngListener engListener = new EngListener();
        KorListener korListener = new KorListener();

        BtnListener btnListener = new BtnListener();

        button_korean.setOnClickListener(btnListener);
        button_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "korean 버튼이 클릭되었습니다.",
                        Toast.LENGTH_LONG).show();
            }
        });

        // TextListener의 객체를 생성한다.
        TextListener textListener = new TextListener();

        // 생성한 TextListener의 객체를 edittext에 등록
        editText_name.addTextChangedListener(textListener);
    }

    // 에디트 텍스트에 입력할 때 호출되는 TextListener의 클래스
    class TextListener implements TextWatcher {

        // 입력하기 전
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            /**
             * charSequence : 수정전 내용 charSequence.toString();
             * i : 입력될 글자의 위치(0부터 시작)
             * i1 : 입력된 글자 대신 삭제된 기존 글자 수
             * i2 : 새로 추가되는 글자수
             */
        }

        // 텍스트가 수정될 때
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            /**
             * charSequence : 입력한 내용 charSequence.toString();
             * i : 입력 글자의 위치(0부터 시작)
             * i1 : 입력된 글자 대신 삭제된 기존 글자 수
             * i2 : 새로 추가되는 글자수
             */

            // textview가 표시하는 문자열을 수정해주는 setText() 함수
            textView_text.setText(charSequence);
            /**
             * textview가 표시하는 문자열을 가져오는 getText() 함수
             * String my = textView_text.getText();
             */
        }

        // 입력 후에
        @Override
        public void afterTextChanged(Editable editable) {
            /**
             * editable : 수정 후 글자(문자열)
             */
        }
    }

    class BtnListener implements View.OnClickListener {
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_english:
                    Toast.makeText(getApplicationContext(),
                            "english 버튼이 클릭되었습니다.",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_korean:
                    Toast.makeText(getApplicationContext(),
                            "korean 버튼이 클릭되었습니다.",
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    // english 버튼에게 기능을 부여하기 위한 EngListener를 만드는 클래스
    class EngListener implements View.OnClickListener {

        // 버튼을 클릭했을 때 실행되는 onClick() 함수
        @Override
        public void onClick(View view) {
            // 앱에서 메시지를 출력하는 Toast
            // makeText()함수를 활용하여 메시지를 출력한다.
            // 1번째 매개변수 : getApplicationContext()
            //               => 앱의 정보를 가져오는 함수
            // 2번째 매개변수 : 출력하고 싶은 글자(문자열)
            // 3번째 매개변수 : 글자를 표시하는 시간
            //    Toast.LENGTH_LONG 이나 Toast.LENGTH_SHORT 중 하나 선택
            Toast.makeText(getApplicationContext(),
                    "english 버튼이 클릭되었습니다.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    class KorListener implements View.OnClickListener {
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),
                    "korean 버튼이 클릭되었습니다.",
                    Toast.LENGTH_LONG).show();
        }
    }

}
