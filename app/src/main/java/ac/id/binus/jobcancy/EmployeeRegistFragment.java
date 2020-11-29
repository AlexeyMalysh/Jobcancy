package ac.id.binus.jobcancy;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ac.id.binus.jobcancy.Model.MsApplicant;
import ac.id.binus.jobcancy.Repository.MsApplicantRepository;

public class EmployeeRegistFragment extends Fragment {
    View view;
    private EditText Fullname, Email, Username, Password, PhoneNumber, Address, CountryOfOrigin;
    private Button btnLogin, btnRegister;
    private TextView DateOfBirth;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    private static final String TAG = "RegisterActivity";
    private Spinner Gender, LastEducation;

    private MsApplicantRepository msApplicantRepository;
    private MsApplicant msApplicant;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.employee_fragment, container, false);

        msApplicantRepository = new MsApplicantRepository(getActivity());
        msApplicant = new MsApplicant();

        Fullname = (EditText)view.findViewById(R.id.et_fullname);
        Email = (EditText)view.findViewById(R.id.et_email);
        Username = (EditText)view.findViewById(R.id.et_username);
        Password = (EditText)view.findViewById(R.id.et_password);
        PhoneNumber = (EditText)view.findViewById(R.id.et_phone_number);
//        et_dateofbirth = (EditText)findViewById(R.id.et_dateofbirth);
        Gender = (Spinner)view.findViewById(R.id.sp_gender);
        DateOfBirth = (TextView) view.findViewById(R.id.tv_dateofbirth);
        Address = (EditText)view.findViewById(R.id.et_address);
        CountryOfOrigin = (EditText)view.findViewById(R.id.et_countryoforigin);
//        et_lasteducation = (EditText)findViewById(R.id.et_lasteducation);
        LastEducation = (Spinner)view.findViewById(R.id.sp_lasteducation);
        btnRegister = (Button) view.findViewById(R.id.submit_registration);

        //buat dropdown untuk gender
        Spinner mySpinner = (Spinner)view.findViewById(R.id.sp_gender);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));
        myArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myArrayAdapter);

        //buat dropdown untuk last education
        Spinner mySpinnerLastEducation = (Spinner)view.findViewById(R.id.sp_lasteducation);
        ArrayAdapter<String> myArrayAdapterLastEducation = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lastEducation));
        myArrayAdapterLastEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerLastEducation.setAdapter(myArrayAdapterLastEducation);

        //buat set tanggal lahir dan nampilin kalenderz
        DateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDataSetListener,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);

                String dateofbirth = month + "/" + dayOfMonth + "/" + year;
                DateOfBirth.setText(dateofbirth);
            }
        };
        //btnRegister
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFullname = Fullname.getText().toString();
                String strEmail = Email.getText().toString();
                String strUsername = Username.getText().toString();
                String strPassword = Password.getText().toString();
                String strPhonenumber = PhoneNumber.getText().toString();
//                String strDateofbirth = et_dateofbirth.getText().toString();
                String strDateofbirth = DateOfBirth.getText().toString();
//                String strGender = et_gender.getText().toString();
                String strGender = Gender.getSelectedItem().toString();
                String strAddress = Address.getText().toString();
                String strCountryoforigin = CountryOfOrigin.getText().toString();
                String strLasteducation = LastEducation.getSelectedItem().toString();



                if(validateyanglain()){
                    msApplicant.setApplicantName(strFullname);
                    msApplicant.setApplicantEmail(strEmail);
                    msApplicant.setApplicantUsername(strUsername);
                    msApplicant.setApplicantPassword(strPassword);
                    msApplicant.setApplicantPhoneNumber(strPhonenumber);
                    msApplicant.setApplicantDateOfBirth(strDateofbirth);
                    msApplicant.setApplicantGender(strGender);
                    msApplicant.setApplicantAddress(strAddress);
                    msApplicant.setApplicantCountryOrigin(strCountryoforigin);
                    msApplicant.setApplicantLastEducation(strLasteducation);

                    Boolean insertdb = msApplicantRepository.insertToMsApplicant(msApplicant, strEmail);

                    if(insertdb == true){
                        Toast.makeText(getActivity().getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(loginIntent);
                        getActivity().finish();
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), "Email Already Exists!", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "Register Failed", Toast.LENGTH_SHORT).show();
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
        String strPhonenumber = PhoneNumber.getText().toString();
//                String strDateofbirth = et_dateofbirth.getText().toString();
        String strDateofbirth = DateOfBirth.getText().toString();
//                String strGender = et_gender.getText().toString();
        String strAddress = Address.getText().toString();
        String strCountryoforigin = CountryOfOrigin.getText().toString();

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
            if (strPassword.length() > 5) {
                Password.setError(null);
            } else {
                valid = false;
                Password.setError("Password must be at least 6 character!");
            }
        }

        //Validasi PhoneNumber
        if (strPhonenumber == null || strPhonenumber.equals("") || strPhonenumber.equals(" ")) {
                valid = false;
                PhoneNumber.setError("Phonenumber must not be empty!");
                }
                else {
                PhoneNumber.setError(null);
                }

                //Validasi DateOfBirth
                if (strDateofbirth.isEmpty()) {
                valid = false;
                DateOfBirth.setError("Please input Date!");
                }
                else {
                DateOfBirth.setError(null);
                }

                //Validasi Address
                if (strAddress == null || strAddress.equals("") || strAddress.equals(" ")) {
                valid = false;
                Address.setError("Address must no be empty!");
                }
                else {
                Address.setError(null);
                }

                //Validasi Rex OrangeCountry
                if (strCountryoforigin == null || strCountryoforigin.equals("") || strCountryoforigin.equals(" ")) {
                valid = false;
                CountryOfOrigin.setError("Country must no be empty!");
                }
                else {
                CountryOfOrigin.setError(null);
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
