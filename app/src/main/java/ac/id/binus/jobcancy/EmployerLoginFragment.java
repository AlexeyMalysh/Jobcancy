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

import ac.id.binus.jobcancy.Model.MsEmployer;
import ac.id.binus.jobcancy.Repository.DatabaseHelper;
import ac.id.binus.jobcancy.Repository.MsEmployerRepository;


public class EmployerLoginFragment extends Fragment {
    View view;
    EditText Email, Password;
    TextView Register;
    TextView ForgotPassword;
    Button BtnLogin;
    private DatabaseHelper databaseHelper;

    private MsEmployerRepository msEmployerRepository;
    private MsEmployer msEmployer;
    private SharedPref pref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getActivity());
        msEmployerRepository = new MsEmployerRepository(getActivity());
        msEmployer = new MsEmployer();

        view = inflater.inflate(R.layout.fragment_employer_login, container, false);
        Email = view.findViewById(R.id.et_email);
        Password = view.findViewById(R.id.et_password);
        BtnLogin = view.findViewById(R.id.btnLogin);
        ForgotPassword = view.findViewById(R.id.tv_forgot_password);
        Register = view.findViewById(R.id.tv_register);
        pref = new SharedPref(getActivity());
        SharedPref sharedPref = new SharedPref(getActivity());
        final MsEmployer msEmployer = sharedPref.loadEmployer();

        // if the user that loaded from shared pref is not null, then intent to HomeActivity
        if(!msEmployer.getEmployerEmail().equals("") && !msEmployer.getEmployerPassword().equals("")){
            // Move to Home Activity
            Intent intent = new Intent(getActivity(), HomeEmployerActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){

                    String email = Email.getText().toString();
                    String password = Password.getText().toString();

                    Boolean in = msEmployerRepository.checkLoginEmployer(email, password);
                    if(in == true){
                        // move to HomeActivity
                        MsEmployer msEmployer2 = new MsEmployer();
                        msEmployer2.setEmployerEmail(email);
                        msEmployer2.setEmployerPassword(password);

                        // save the user data in shared preferences
                        SharedPref sharedPref = new SharedPref(getActivity());
                        sharedPref.saveEmployer(msEmployer2);

                        Toast.makeText(getActivity().getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(getActivity(), HomeEmployerActivity.class);
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
        String password = Email.getText().toString();

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
                valid = true;
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
