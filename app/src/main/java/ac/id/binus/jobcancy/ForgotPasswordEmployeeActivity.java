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

import ac.id.binus.jobcancy.Model.MsApplicant;
import ac.id.binus.jobcancy.Repository.MsApplicantRepository;

public class ForgotPasswordEmployeeActivity extends AppCompatActivity {
    MsApplicantRepository msApplicantRepository;
    MsApplicant msApplicant;
    EditText Email, Password, ConfirmPass;
    Button BtnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        msApplicantRepository = new MsApplicantRepository(this);
        msApplicant = new MsApplicant();
        Email = findViewById(R.id.et_email);
        Password = findViewById((R.id.et_password));
        ConfirmPass = findViewById(R.id.et_confirm_password);
        BtnReset = findViewById(R.id.btn_reset);

        BtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String newPassword = Password.getText().toString();
//                String confirmnewpassword = et_confirmnewpassword.getText().toString();

                if(validateyanglain()){

                    msApplicantRepository.updatePassword(email, newPassword);
                    Toast.makeText(getApplicationContext(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(ForgotPasswordEmployeeActivity.this, LoginActivity.class);
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
        String strEmail = Email.getText().toString();
        String strPassword = Password.getText().toString();
        String strConfirmnewpassword = ConfirmPass.getText().toString();

        //Validasi email
        if (strEmail == null || strEmail.equals("") || strEmail.equals(" ")) {
            valid = false;
            Email.setError("Please enter valid email!");
        }
        else if(!validateEmail(strEmail)){
            valid = false;
            Email.setError("Wrong email format!");
        }
        else {
            valid = true;
            Email.setError(null);
        }

        //Validasi password
        if (strPassword == null || strPassword.equals("") || strPassword.equals(" ")) {
            valid = false;
            Password.setError("Please enter valid password!");
        } else {
            if (strPassword.length() > 5) {
                Password.setError(null);
            } else {
                valid = false;
                Password.setError("Password must be at least 6 character!");
            }
        }

        //Validasi confirm password
        if (strConfirmnewpassword == null || strConfirmnewpassword.equals("") ||strConfirmnewpassword.equals(" ")) {
            valid = false;
            ConfirmPass.setError("Please enter valid password!");
        }
        else if(!strConfirmnewpassword.equals(strPassword)){
            valid = false;
            ConfirmPass.setError("Password is not the same!");
        }
        else {
            if (strConfirmnewpassword.length() > 5) {
                ConfirmPass.setError(null);
            } else {
                valid = false;
                ConfirmPass.setError("Password must be at least 6 character!");
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
