package ac.id.binus.jobcancy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ac.id.binus.jobcancy.Model.MsApplicant;
import ac.id.binus.jobcancy.Repository.DatabaseHelper;
import ac.id.binus.jobcancy.Repository.MsApplicantRepository;


public class EmployeeLoginFragment extends Fragment {
    View view;
    EditText Email, Password;
    TextView Register;
    TextView ForgotPassword;
    Button BtnLogin;
    private DatabaseHelper databaseHelper;
    private MsApplicantRepository msApplicantRepository;
    private MsApplicant msApplicant;
    private SharedPref pref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_employee_login, container, false);
        databaseHelper = new DatabaseHelper(getActivity());
        msApplicantRepository = new MsApplicantRepository(getActivity());
        msApplicant = new MsApplicant();
        pref = new SharedPref(getActivity());

        Email = view.findViewById(R.id.et_email);
        Password = view.findViewById(R.id.et_password);
        BtnLogin = view.findViewById(R.id.btnLogin);
        ForgotPassword = view.findViewById(R.id.tv_forgot_password);
        Register = view.findViewById(R.id.tv_register);
        // Make a instance of shared preferences
        SharedPref sharedPref = new SharedPref(getActivity());
        final MsApplicant msApplicant = sharedPref.loadApplicant();
        if(!msApplicant.getApplicantEmail().equals("") && !msApplicant.getApplicantPassword().equals("" )){
            // Move to Home Activity
            Intent intent = new Intent(getActivity(), HomeEmployeeActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){

                    String email = Email.getText().toString();
                    String password = Password.getText().toString();

                    Boolean in = msApplicantRepository.checkLogin(email, password);
                    if(in == true){

                        // move to HomeActivity
                        Integer applicantId = msApplicantRepository.getApplicant(email);
                        MsApplicant msApplicant2 = new MsApplicant();
                        msApplicant2.setApplicantEmail(email);
                        msApplicant2.setApplicantPassword(password);
                        msApplicant2.setIDApplicant(applicantId);

                        // save the user data in shared preferences
                        SharedPref sharedPref = new SharedPref(getActivity());
                        sharedPref.saveApplicant(msApplicant2);

                        Toast.makeText(getActivity().getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(getActivity(), HomeEmployeeActivity.class);
                        startActivity(mainIntent);
                        getActivity().finish();
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        ForgotPassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
                Intent intent = new Intent(getActivity(), ForgotPasswordEmployeeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    public boolean validate() {
        boolean valid = false;

        //tangkap values dari editText
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        //Validasi email

        if (email == null || email.equals("") || email.equals(" ")) {
            valid = false;
            Email.setError("Please enter valid email!");
        }
        else if(!validateEmail(email)){
            valid = false;
            Email.setError("Wrong email format!");
        }
        else {
            valid = true;
            Email.setError(null);
        }

        //Validasi password
        if (password == null || password.equals("") || password.equals(" ")) {
            valid = false;
            Password.setError("Please enter valid password!");
        } else {
            if (password.length() > 5) {
                Password.setError(null);
            } else {
                valid = false;
                Password.setError("Password must be at least 6 character!");
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
