package jiabao.plane_wars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaihangActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvname1;
    private TextView tvname2;
    private TextView tvname3;
    private TextView tvname4;
    private TextView tvname5;
    private TextView tvname6;
    private TextView tvname7;
    private TextView tvname8;
    private TextView tvname9;
    private TextView tvname10;
    private TextView tvgrade1;
    private TextView tvgrade2;
    private TextView tvgrade3;
    private TextView tvgrade4;
    private TextView tvgrade5;
    private TextView tvgrade6;
    private TextView tvgrade7;
    private TextView tvgrade8;
    private TextView tvgrade9;
    private TextView tvgrade10;
    private Button btnrtn;

    private List<Map<String,String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paihang);
        initView();

        Map<String, String> userInfo = SPSaveInfo.getUserInfo(this);
        list = new ArrayList<Map<String, String>>();
        list.add(userInfo);

        Collections.sort(list, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> m1, Map<String, String> m2) {
                if (Integer.parseInt(m1.get("grade")) < Integer.parseInt(m2.get("grade")))
                    return 1;
                return -1;
            }
        });

        int number = Integer.parseInt(userInfo.get("num"));
        for(int i = 0;i<number;i++){
            input(i);
        }
    }

    private void initView(){
        tvname1= (TextView) findViewById(R.id.tv_name1);
        tvname2= (TextView) findViewById(R.id.tv_name2);
        tvname3= (TextView) findViewById(R.id.tv_name3);
        tvname4= (TextView) findViewById(R.id.tv_name4);
        tvname5= (TextView) findViewById(R.id.tv_name5);
        tvname6= (TextView) findViewById(R.id.tv_name6);
        tvname7= (TextView) findViewById(R.id.tv_name7);
        tvname8= (TextView) findViewById(R.id.tv_name8);
        tvname9= (TextView) findViewById(R.id.tv_name9);
        tvname10= (TextView) findViewById(R.id.tv_name10);
        tvgrade1= (TextView) findViewById(R.id.tv_grade1);
        tvgrade2= (TextView) findViewById(R.id.tv_grade2);
        tvgrade3= (TextView) findViewById(R.id.tv_grade3);
        tvgrade4= (TextView) findViewById(R.id.tv_grade4);
        tvgrade5= (TextView) findViewById(R.id.tv_grade5);
        tvgrade6= (TextView) findViewById(R.id.tv_grade6);
        tvgrade7= (TextView) findViewById(R.id.tv_grade7);
        tvgrade8= (TextView) findViewById(R.id.tv_grade8);
        tvgrade9= (TextView) findViewById(R.id.tv_grade9);
        tvgrade10= (TextView) findViewById(R.id.tv_grade10);
        btnrtn= (Button) findViewById(R.id.btn_rtn);
        btnrtn.setOnClickListener(this);
    }

    public void input(int i){
        Map<String ,String > userMap = list.get(i);
        switch (i){
            case 0:
                tvname1.setText("1."+userMap.get("name"));
                tvgrade1.setText(userMap.get("grade"));
                break;
            case 1:
                tvname2.setText("2."+userMap.get("name"));
                tvgrade2.setText(userMap.get("grade"));
                break;
            case 2:
                tvname3.setText("3."+userMap.get("name"));
                tvgrade3.setText(userMap.get("grade"));
                break;
            case 3:
                tvname4.setText("4."+userMap.get("name"));
                tvgrade4.setText(userMap.get("grade"));
                break;
            case 4:
                tvname5.setText("5."+userMap.get("name"));
                tvgrade5.setText(userMap.get("grade"));
                break;
            case 5:
                tvname6.setText("6."+userMap.get("name"));
                tvgrade6.setText(userMap.get("grade"));
                break;
            case 6:
                tvname7.setText("7."+userMap.get("name"));
                tvgrade7.setText(userMap.get("grade"));
                break;
            case 7:
                tvname8.setText("8."+userMap.get("name"));
                tvgrade8.setText(userMap.get("grade"));
                break;
            case 8:
                tvname9.setText("9."+userMap.get("name"));
                tvgrade9.setText(userMap.get("grade"));
                break;
            case 9:
                tvname10.setText("10."+userMap.get("name"));
                tvgrade10.setText(userMap.get("grade"));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(PaihangActivity.this, InitialActivity.class);
        startActivity(intent);
    }

}

