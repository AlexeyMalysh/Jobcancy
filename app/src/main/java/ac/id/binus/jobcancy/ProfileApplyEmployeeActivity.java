package ac.id.binus.jobcancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

import ac.id.binus.jobcancy.Model.HeaderApply;
import ac.id.binus.jobcancy.Model.MsApplicant;
import ac.id.binus.jobcancy.Repository.HeaderApplyRepository;

public class ProfileApplyEmployeeActivity extends AppCompatActivity {

    TextView Fullname, Email, Gender, Address, Phonenumber, CountryOrigin, LastEducation;
    Button btnAccept;
    Button btnReject;
    ImageButton btnBack;
    private HeaderApplyRepository headerApplyRepository;
    private HeaderApply headerApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__apply_employee);
        Fullname = findViewById(R.id.profile_name);
        Email = findViewById(R.id.profile_email);
        Gender = findViewById(R.id.profile_gender);
        Address = findViewById(R.id.profile_address);
        Phonenumber = findViewById(R.id.profile_phonenumber);
        CountryOrigin = findViewById(R.id.profile_country_origin);
        LastEducation = findViewById(R.id.profile_last_education);
        btnAccept = findViewById(R.id.btn_accept);
        btnReject = findViewById(R.id.btn_reject);
        btnBack = findViewById(R.id.back_button);

        final int IDApplicant = getIntent().getIntExtra("IDApplicant", 0);
        String name = getIntent().getStringExtra("ApplicantName");
        String email = getIntent().getStringExtra("ApplicantEmail");
        String gender = getIntent().getStringExtra("ApplicantGender");
        String address= getIntent().getStringExtra("ApplicantAddress");
        String phonenumber = getIntent().getStringExtra("ApplicantPhoneNumber");
        String countryorigin = getIntent().getStringExtra("ApplicantCountryOrigin");
        String lasteducation = getIntent().getStringExtra("ApplicantLastEducation");

        Fullname.setText(name);
        Email.setText(email);
        Gender.setText(gender);
        Address.setText(address);
        Phonenumber.setText(phonenumber);
        CountryOrigin.setText(countryorigin);
        LastEducation.setText(lasteducation);
        SharedPref sharedPref = new SharedPref(ProfileApplyEmployeeActivity.this);
        final MsApplicant msApplicant = sharedPref.loadApplicant();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileApplyEmployeeActivity.this, HomeEmployerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeaderApply headerApply = new HeaderApply();

                Boolean in = headerApplyRepository.updateHeaderApply(headerApply);
                if(in == true) {
                    Toast.makeText(ProfileApplyEmployeeActivity.this, "Apply Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileApplyEmployeeActivity.this, HomeEmployeeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(ProfileApplyEmployeeActivity.this, "You have already apply", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileApplyEmployeeActivity.this, HomeEmployeeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeaderApply headerApply = new HeaderApply();

                Boolean in = headerApplyRepository.deleteHeaderApply(headerApply.getIDApply());
                if(in == true) {
                    Toast.makeText(ProfileApplyEmployeeActivity.this, "Reject Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileApplyEmployeeActivity.this, HomeEmployeeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(ProfileApplyEmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileApplyEmployeeActivity.this, HomeEmployeeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
