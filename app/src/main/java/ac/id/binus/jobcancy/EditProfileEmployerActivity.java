package ac.id.binus.jobcancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ac.id.binus.jobcancy.Model.MsEmployer;
import ac.id.binus.jobcancy.Repository.MsEmployerRepository;

public class EditProfileEmployerActivity extends AppCompatActivity {
    private ImageButton btnBack, btnEdit;
    private EditText et_fullname, et_companyname, et_companyaddress, et_companydesc, et_companyreq;
    private TextView tv_email;

//    private MsCompanyRepository msCompanyRepository;
//    private MsCompany msCompany;

    private MsEmployerRepository msEmployerRepository;
    private MsEmployer msEmployer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_employer);

        msEmployerRepository = new MsEmployerRepository(this);
        msEmployer = new MsEmployer();

//        msCompanyRepository = new MsCompanyRepository(this);
//        msCompany = new MsCompany();

        et_fullname = (EditText)findViewById(R.id.et_fullname);
        tv_email = (TextView) findViewById(R.id.tv_email);
        et_companyname= (EditText)findViewById(R.id.et_companyname);
        et_companyaddress= (EditText)findViewById(R.id.et_companyaddress);
        et_companydesc= (EditText)findViewById(R.id.et_companydescription);
        et_companyreq= (EditText)findViewById(R.id.et_companyrequirement);
        btnEdit = (ImageButton)findViewById(R.id.btnEdit);
        btnBack = (ImageButton)findViewById(R.id.btnBack);

        // Make a instance of shared preferences
        SharedPref sharedPref = new SharedPref(EditProfileEmployerActivity.this);
        final MsEmployer msEmployer2 = sharedPref.loadEmployer();

        tv_email.setText(msEmployer2.getEmployerEmail());

        btnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
                Intent intent = new Intent(EditProfileEmployerActivity.this, HomeEmployerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //btnEdit
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFullname = et_fullname.getText().toString();
                String strCompanyname = et_companyname.getText().toString();
                String strCompanyaddress = et_companyaddress.getText().toString();
                String strCompanydescription = et_companydesc.getText().toString();
                String strCompanyrequirement = et_companyreq.getText().toString();

                if(validateyanglain()){
                    SharedPref sharedPref = new SharedPref(EditProfileEmployerActivity.this);
                    final MsEmployer msEmployer2 = sharedPref.loadEmployer();

                    msEmployer.setEmployerName(strFullname);
                    msEmployer.setCompanyName(strCompanyname);
                    msEmployer.setCompanyAddress(strCompanyaddress);
                    msEmployer.setCompanyDescription(strCompanydescription);
                    msEmployer.setCompanyRequirement(strCompanyrequirement);

                    Boolean updatedb = msEmployerRepository.updateMsEmployer(msEmployer, msEmployer2.getEmployerEmail());
//                    msCompanyRepository.updateMsCompany(msCompany);

                    if(updatedb == true) {
                        Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
                        Intent updateIntent = new Intent(EditProfileEmployerActivity.this, HomeEmployerActivity.class);
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
        String strCompanyname = et_companyname.getText().toString();
        String strCompanyaddress = et_companyaddress.getText().toString();

        //Validasi fullname
        if (strFullname == null || strFullname.equals("") || strFullname.equals(" ")) {
            valid = false;
            et_fullname.setError("Name must not be empty!");
        }
        else {
            valid = true;
            et_fullname.setError(null);
        }


        //Validasi Companyname
        if (strCompanyname == null || strCompanyname.equals("") || strCompanyname.equals(" ")) {
            valid = false;
            et_companyname.setError("Company name must no be empty!");
        }
        else {
            et_companyname.setError(null);
        }

        //Validasi Companyaddress
        if (strCompanyaddress == null || strCompanyaddress.equals("") || strCompanyaddress.equals(" ")) {
            valid = false;
            et_companyaddress.setError("Company address must no be empty!");
        }
        else {
            et_companyaddress.setError(null);
        }

        return valid;
    }

}