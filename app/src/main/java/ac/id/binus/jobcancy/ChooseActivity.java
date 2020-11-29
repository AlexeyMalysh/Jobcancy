package ac.id.binus.jobcancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    Button BtnLogin;
    Button BtnRegister;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        BtnLogin = findViewById(R.id.btnLogin);
        BtnRegister = findViewById(R.id.btnRegister);

        BtnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
                Intent intent = new Intent(ChooseActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        BtnRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
                Intent intent = new Intent(ChooseActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
