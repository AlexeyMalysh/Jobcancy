package ac.id.binus.jobcancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import ac.id.binus.jobcancy.Model.MsApplicant;
import ac.id.binus.jobcancy.Repository.MsApplicantRepository;

public class EditProfileActivity extends AppCompatActivity {
    private ImageButton btnBack, btnEdit;
    private EditText et_fullname, et_phonenumber, et_address, et_countryorigin, et_lasteducation;
    private TextView tv_email;

    private Spinner sp_lasteducation;

    private MsApplicantRepository msApplicantRepository;
    private MsApplicant msApplicant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        msApplicantRepository = new MsApplicantRepository(this);
        msApplicant = new MsApplicant();

        et_fullname = (EditText)findViewById(R.id.et_fullname);
        tv_email = (TextView)findViewById(R.id.tv_email);
        et_phonenumber = (EditText)findViewById(R.id.et_phonenumber);
        et_address= (EditText)findViewById(R.id.et_address);
        et_countryorigin= (EditText)findViewById(R.id.et_countryorigin);
        btnEdit = (ImageButton)findViewById(R.id.btnEdit);
        btnBack = (ImageButton)findViewById(R.id.btnBack);

        // Make a instance of shared preferences
        SharedPref sharedPref = new SharedPref(EditProfileActivity.this);
        final MsApplicant msApplicant2 = sharedPref.loadApplicant();

        tv_email.setText(msApplicant2.getApplicantEmail());

        sp_lasteducation = (Spinner) findViewById(R.id.sp_lasteducation);

        //buat dropdown untuk last education
        Spinner mySpinnerLastEducation = (Spinner)findViewById(R.id.sp_lasteducation);
        ArrayAdapter<String> myArrayAdapterLastEducation = new ArrayAdapter<String>(EditProfileActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lastEducation));
        myArrayAdapterLastEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerLastEducation.setAdapter(myArrayAdapterLastEducation);

        btnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
                Intent intent = new Intent(EditProfileActivity.this, HomeEmployeeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //btnEdit
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFullname = et_fullname.getText().toString();
                String strPhonenumber = et_phonenumber.getText().toString();
                String strAddress = et_address.getText().toString();
                String strCountryorigin = et_countryorigin.getText().toString();
                String strLasteducation = sp_lasteducation.getSelectedItem().toString();
                int IDApplicant;

                if(validateyanglain()){

                    SharedPref sharedPref = new SharedPref(EditProfileActivity.this);
                    final MsApplicant msApplicant2 = sharedPref.loadApplicant();

                    IDApplicant = msApplicant2.getIDApplicant();
                    Log.d("<TEST>", "test klik: " + IDApplicant);
                    msApplicant.setApplicantName(strFullname);
                    msApplicant.setApplicantPhoneNumber(strPhonenumber);
                    msApplicant.setApplicantAddress(strAddress);
                    msApplicant.setApplicantCountryOrigin(strCountryorigin);
                    msApplicant.setApplicantLastEducation(strLasteducation);


                    Boolean updatedb = msApplicantRepository.updateMsApplicant(msApplicant, msApplicant2.getApplicantEmail());


                    if(updatedb == true){
                        Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();

                        Intent updateIntent = new Intent(EditProfileActivity.this, HomeEmployeeActivity.class);
                        startActivity(updateIntent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //buat validasi login employer
    public boolean validateyanglain(){
        boolean valid = false;

        //tangkap values dari editText
        String strFullname = et_fullname.getText().toString();
        String strPhonenumber = et_phonenumber.getText().toString();
        String strAddress = et_address.getText().toString();
        String strCountryorigin = et_countryorigin.getText().toString();

        //Validasi fullname
        if (strFullname == null || strFullname.equals("") || strFullname.equals(" ")) {
            valid = false;
            et_fullname.setError("Name must not be empty!");
        }
        else {
            valid = true;
            et_fullname.setError(null);
        }


        //Validasi PhoneNumber
        if (strPhonenumber == null || strPhonenumber.equals("") || strPhonenumber.equals(" ")) {
            valid = false;
            et_phonenumber.setError("Phonenumber must not be empty!");
        }
        else {
            et_phonenumber.setError(null);
        }

        //Validasi Address
        if (strAddress == null || strAddress.equals("") || strAddress.equals(" ")) {
            valid = false;
            et_address.setError("Address must no be empty!");
        }
        else {
            et_address.setError(null);
        }

        //Validasi Rex OrangeCountry
        if (strCountryorigin == null || strCountryorigin.equals("") || strCountryorigin.equals(" ")) {
            valid = false;
            et_countryorigin.setError("Country must no be empty!");
        }
        else {
            et_countryorigin.setError(null);
        }

        return valid;
    }

}
