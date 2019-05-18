package jiabao.plane_wars;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitialActivity extends AppCompatActivity {

    private Button btnstart;
    private Button btnpaihang;
    private Button btnguanyu;
    private Button btntuichu;
    static private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        initView();

        //点击排行榜按钮触发的事件
        btnpaihang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialActivity.this,PaihangActivity.class);
                startActivity(intent);
            }
        });

        //点击关于按钮触发的事件
        btnguanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialActivity.this, GuanyuActivity.class);
                startActivity(intent);
            }
        });

        //点击退出游戏按钮触发的事件
        btntuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //定义一个新的对话框对象
                AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(InitialActivity.this);
                //设置对话框提示内容
                alertdialogbuilder.setMessage("确定要退出程序吗？");
                //定义对话框2个按钮标题及接受事件的函数
                alertdialogbuilder.setPositiveButton("确定",click1);
                alertdialogbuilder.setNegativeButton("取消",click2);
                //创建并显示对话框
                AlertDialog alertdialog1=alertdialogbuilder.create();
                alertdialog1.show();
            }
        });
    }

    private DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            //当按钮click1被按下时执行结束进程
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    };

    private DialogInterface.OnClickListener click2=new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            //当按钮click2被按下时则取消操作
            dialog.cancel();
        }
    };

    private void initView(){
        btnstart= (Button) findViewById(R.id.btn_start);
        btnpaihang= (Button) findViewById(R.id.btn_paihang);
        btnguanyu= (Button) findViewById(R.id.btn_guanyu);
        btntuichu= (Button) findViewById(R.id.btn_tuichu);
    }

    //按下开始游戏按钮触发的事件
    public void startgame(View v){
        final EditText etname = new EditText(this);
        //绑定当前窗口，设置标题
        new AlertDialog.Builder(this).setTitle("请输入游戏昵称")
                .setView(etname)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //按下确定键之后的事件
                        Toast.makeText(getApplicationContext(), etname.getText().toString(), Toast.LENGTH_LONG).show();
                        boolean isSaveSuccess = SPSaveInfo.SaveUserName(InitialActivity.this, etname.getText().toString().trim(),Integer.toString(num));
                        if (isSaveSuccess) {
//                            Toast.makeText(InitialActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(InitialActivity.this,MainActivity.class);
                            startActivity(intent);
                            num++;
                        }
                    }
                }).setNegativeButton("取消", null).show();
    }
}
