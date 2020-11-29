package ac.id.binus.jobcancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ac.id.binus.jobcancy.Model.MsEmployer;
import ac.id.binus.jobcancy.Repository.MsEmployerRepository;

public class ForgotPasswordEmployerActivity extends AppCompatActivity {

    MsEmployerRepository msEmployerRepository;
    MsEmployer msEmployer;
    EditText et_email, et_newpassword, et_confirmnewpassword;
    Button btnForgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_employer);

        msEmployerRepository = new MsEmployerRepository(this);
        msEmployer = new MsEmployer();

        et_email = (EditText)findViewById(R.id.et_email);
        et_newpassword = (EditText)findViewById(R.id.et_newpassword);
        et_confirmnewpassword = (EditText)findViewById(R.id.et_confirmnewpassword);
        btnForgotpassword = (Button)findViewById(R.id.btnForgotpassword);

        btnForgotpassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
                String email = et_email.getText().toString();
                String newPassword = et_newpassword.getText().toString();
//                String confirmnewpassword = et_confirmnewpassword.getText().toString();

                if(validateyanglain()){

                    msEmployerRepository.updatePasswordEmployer(email, newPassword);
                    Toast.makeText(getApplicationContext(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(ForgotPasswordEmployerActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Password Changed Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean validateyanglain(){
        boolean valid = false;

        //tangkap values dari editText
        String strEmail = et_email.getText().toString();
        String strPassword = et_newpassword.getText().toString();
        String strConfirmnewpassword = et_confirmnewpassword.getText().toString();

        //Validasi email
        if (strEmail == null || strEmail.equals("") || strEmail.equals(" ")) {
            valid = false;
            et_email.setError("Please enter valid email!");
        }
        else if(!validateEmail(strEmail)){
            valid = false;
            et_email.setError("Wrong email format!");
        }
        else {
            valid = true;
            et_email.setError(null);
        }

        //Validasi password
        if (strPassword == null || strPassword.equals("") || strPassword.equals(" ")) {
            valid = false;
            et_newpassword.setError("Please enter valid password!");
        } else {
            if (strPassword.length() > 5) {
                et_newpassword.setError(null);
            } else {
                valid = false;
                et_newpassword.setError("Password must be at least 6 character!");
            }
        }

        //Validasi confirm password
        if (strConfirmnewpassword == null || strConfirmnewpassword.equals("") ||strConfirmnewpassword.equals(" ")) {
            valid = false;
            et_confirmnewpassword.setError("Please enter valid password!");
        }
        else if(!strConfirmnewpassword.equals(strPassword)){
            valid = false;
            et_confirmnewpassword.setError("Password is not the same!");
        }
        else {
            if (strConfirmnewpassword.length() > 5) {
                et_confirmnewpassword.setError(null);
            } else {
                valid = false;
                et_confirmnewpassword.setError("Password must be at least 6 character!");
            }
        }

        return valid;
    }

    //regex email
    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    public boolean validateEmail(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
