package jiabao.plane_wars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuanyuActivity extends AppCompatActivity {
    private Button btnrtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanyu);
        btnrtn= (Button) findViewById(R.id.btn_rtn);
        btnrtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuanyuActivity.this,InitialActivity.class);
                startActivity(intent);
            }
        });
    }
}
