package ac.id.binus.jobcancy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import ac.id.binus.jobcancy.Model.HeaderApply;
import ac.id.binus.jobcancy.Model.MsApplicant;
import ac.id.binus.jobcancy.Repository.HeaderApplyRepository;

public class CompanyDetailActivity extends AppCompatActivity {

    private TextView tvCompanyName, tvCompanyAddress, tvCompanyDescription, tvCompanyRequirement;

    private Button btnApply;

    private ImageButton btnBack;

    private HeaderApplyRepository headerApplyRepository;
    private HeaderApply headerApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);

        headerApplyRepository = new HeaderApplyRepository(this);
        headerApply = new HeaderApply();

        tvCompanyName = findViewById(R.id.tv_company_name);
        tvCompanyAddress = findViewById(R.id.tv_company_address);
        tvCompanyDescription = findViewById(R.id.tv_company_description);
        tvCompanyRequirement = findViewById(R.id.tv_company_requirement);

        btnBack = findViewById(R.id.btn_back);
        btnApply = findViewById(R.id.btn_apply);

        final int IDEmployer = getIntent().getIntExtra("IDEmployer", 0);
        String CompanyName = getIntent().getStringExtra("CompanyName");
        String CompanyAddress = getIntent().getStringExtra("CompanyAddress");
        String CompanyDescription = getIntent().getStringExtra("CompanyDescription");
        String CompanyRequirement = getIntent().getStringExtra("CompanyRequirement");

        tvCompanyName.setText(CompanyName);
        tvCompanyAddress.setText(CompanyAddress);
        tvCompanyDescription.setText(CompanyDescription);
        tvCompanyRequirement.setText(CompanyRequirement);

        final SharedPref sharedPref = new SharedPref(CompanyDetailActivity.this);
        final MsApplicant msApplicant = sharedPref.loadApplicant();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyDetailActivity.this, HomeEmployeeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeaderApply headerApply = new HeaderApply();
                MsApplicant msApplicant1 = sharedPref.loadApplicant();

                int IDApplicant = msApplicant.getIDApplicant();

                headerApply.setIDApplicant(msApplicant1.getIDApplicant());
                headerApply.setIDEmployer(IDEmployer);
                headerApply.setApplyDate(String.valueOf(Calendar.getInstance().getTime()));
                headerApply.setApplyStatus(0);

                Boolean in = headerApplyRepository.insertToHeaderApply(headerApply, IDApplicant, IDEmployer);
                if(in == true) {
                    Toast.makeText(CompanyDetailActivity.this, "Apply Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CompanyDetailActivity.this, HomeEmployeeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(CompanyDetailActivity.this, "You have already apply", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CompanyDetailActivity.this, HomeEmployeeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
