package raejin.radioexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch switch_green;
    CheckBox checkBox_blue;
    RadioGroup radioGroup_colors;
    RadioButton radioButton_yellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // step 2. 컴포넌트의 객체 만들기
        switch_green = (Switch)findViewById(R.id.switch_green);
        checkBox_blue = (CheckBox)findViewById(R.id.checkBox_blue);
        checkBox_blue.setEnabled(false);
        radioGroup_colors = (RadioGroup)findViewById(R.id.radioGroup_colors);
        radioButton_yellow = (RadioButton)findViewById(R.id.radioButton_yellow);

        // step 4. 리스너의 객체만들기
        SwitchListener switchListener = new SwitchListener();
        CheckboxListener checkboxListener = new CheckboxListener();
        RadioListener radioListener = new RadioListener();

        // step 5. 리스너의 객체를 컴포넌트에 등록한다.
        switch_green.setOnCheckedChangeListener(switchListener);
        checkBox_blue.setOnClickListener(checkboxListener);
        radioGroup_colors.setOnCheckedChangeListener(radioListener);

    }


    // step 3. 리스너의 클래스 만들기
    class RadioListener implements  RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            /**
             * radioGroup : 라디오 버튼을 묶은 라디오 그룹의 객체
             * i : 체크된 라디오버튼의 아이디
             */
            String str = "";

            switch (i) {
                case R.id.radioButton_green:
                    str = "그린을 선택했습니다.";
                    break;
                case R.id.radioButton_red:
                    str = "빨강을 선택했습니다.";
                    break;
                case R.id.radioButton_yellow:
                    str = "노랑을 선택했습니다.";
                    break;
            }
            Toast.makeText(getApplicationContext(),
                    str, Toast.LENGTH_SHORT).show();
        }
    }

    class CheckboxListener implements CheckBox.OnClickListener {

        @Override
        public void onClick(View view) {
            // isChecked()의 값이 체크가 되어 있다면 true, 아니면 false
            if(checkBox_blue.isChecked()) {
                Toast.makeText(getApplicationContext(),
                        "체크박스가 눌렸어요,",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "체크박스가 안눌렸어요",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    class SwitchListener implements Switch.OnCheckedChangeListener {

        // 사용자가 스위치를 조작하여 값을 바꿀 때
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            /**
             * compoundButton : switch 객체
             * b : switch값이 off면 false, on이면 true 값이 저장됨
             */
            if(b) {
                switch_green.setText("On");
                //스위치에 따라서 체크박스 활성화하기
                checkBox_blue.setEnabled(true);
            } else {
                switch_green.setText("OFF");
                //스위치에 따라서 체크박스 활성화하기
                checkBox_blue.setEnabled(false);
            }
        }
    }
}
