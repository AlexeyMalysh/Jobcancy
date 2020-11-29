package ac.id.binus.jobcancy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ac.id.binus.jobcancy.Model.MsEmployer;
import ac.id.binus.jobcancy.Repository.DatabaseHelper;
import ac.id.binus.jobcancy.Repository.MsEmployerRepository;

public class EmployerRegistFragment extends Fragment {
    View view;
    Button btnRegister;
    private DatabaseHelper databaseHelper;
    private MsEmployerRepository msEmployerRepository;
    private MsEmployer msEmployer;
    private EditText Fullname;
    private EditText Username;
    private EditText Email;
    private EditText Password;
    private EditText CompanyAddress;
    private EditText CompanyName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.employer_fragment, container, false);
        databaseHelper = new DatabaseHelper(getActivity());

//        msCompanyRepository = new MsCompanyRepository(this);
//        msCompany = new MsCompany();

        msEmployerRepository = new MsEmployerRepository(getActivity());
        msEmployer = new MsEmployer();

        Fullname = (EditText)view.findViewById(R.id.et_fullname);
        Email = (EditText)view.findViewById(R.id.et_email);
        Username = (EditText)view.findViewById(R.id.et_username);
        Password = (EditText)view.findViewById(R.id.et_password);
        CompanyName = (EditText)view.findViewById(R.id.et_company_name);
        CompanyAddress = (EditText)view.findViewById(R.id.et_company_address);
        btnRegister = (Button) view.findViewById(R.id.submit_registration);
        //btnLogin
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(getActivity(), HomeEmployerActivity.class);
                startActivity(loginIntent);
                getActivity().finish();
            }
        });

        //btnRegister
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFullname = Fullname.getText().toString();
                String strEmail = Email.getText().toString();
                String strUsername = Username.getText().toString();
                String strPassword = Password.getText().toString();
                String strCompanyname = CompanyName.getText().toString();
                String strCompanyaddress = CompanyAddress.getText().toString();
                String strCompanyDescription = "EMPTY";
                String strCompanyRequirement = "EMPTY";

                if(validateyanglain()){
                    msEmployer.setEmployerName(strFullname);
                    msEmployer.setEmployerEmail(strEmail);
                    msEmployer.setEmployerUsername(strUsername);
                    msEmployer.setEmployerPassword(strPassword);
                    msEmployer.setCompanyName(strCompanyname);
                    msEmployer.setCompanyAddress(strCompanyaddress);
                    msEmployer.setCompanyDescription(strCompanyDescription);
                    msEmployer.setCompanyRequirement(strCompanyRequirement);

                    Boolean insertdb = msEmployerRepository.insertToMsEmployer(msEmployer, strEmail);
//                    msCompanyRepository.insertToMsCompany(msCompany);


                    if(insertdb == true){
                        Toast.makeText(getActivity().getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                        Intent regisIntent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(regisIntent);
                        getActivity().finish();
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), "Email Already Exists!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }

    //buat validasi login employer
    public boolean validateyanglain(){
        boolean valid = false;

        //tangkap values dari editText
        String strFullname = Fullname.getText().toString();
        String strEmail = Email.getText().toString();
        String strUsername = Username.getText().toString();
        String strPassword = Password.getText().toString();
        String strCompanyname = CompanyName.getText().toString();
        String strCompanyaddress = CompanyAddress.getText().toString();

        //Validasi fullname
        if (strFullname == null || strFullname.equals("") || strFullname.equals(" ")) {
            valid = false;
            Fullname.setError("Name must not be empty!");
        }
        else {
            Fullname.setError(null);
        }


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

        //Validasi Username
        if (strUsername == null || strUsername.equals("") || strUsername.equals(" ")) {
            valid = false;
            Username.setError("Username must no be empty!");
        }
        else {
            Username.setError(null);
        }

        //Validasi password
        if (strPassword == null || strPassword.equals("") || strPassword.equals(" ")) {
            valid = false;
            Password.setError("Please enter valid password!");
        } else {
            if (strPassword.length() > 5) { ;
                Password.setError(null);
            } else {
                valid = false;
                Password.setError("Password must be at least 6 character!");
            }
        }

        //Validasi Companyname
        if (strCompanyname == null || strCompanyname.equals("") || strCompanyname.equals(" ")) {
            valid = false;
            CompanyName.setError("Company name must no be empty!");
        }
        else {
            CompanyName.setError(null);
        }

        //Validasi Companyaddress
        if (strCompanyaddress == null || strCompanyaddress.equals("") || strCompanyaddress.equals(" ")) {
            valid = false;
            CompanyAddress.setError("Company address must no be empty!");
        }
        else {
            CompanyAddress.setError(null);
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