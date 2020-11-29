package ac.id.binus.jobcancy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ac.id.binus.jobcancy.Model.MsApplicant;
import ac.id.binus.jobcancy.Model.MsEmployer;
import ac.id.binus.jobcancy.Repository.DatabaseHelper;
import ac.id.binus.jobcancy.Repository.MsApplicantRepository;
import ac.id.binus.jobcancy.Repository.MsEmployerRepository;

public class ProfileEmployerFragment extends Fragment {
    View view;
    MsEmployerRepository msEmployerRepository;
    MsEmployer msEmployer;
    DatabaseHelper databaseHelper;
    private SharedPref pref;

    private List<MsEmployer> employerList;

    private Button btnEdit, btnLogout;
    private TextView Fullname, Email, CompanyName, CompanyAddress, CompanyDesc, CompanyReq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_employer, container, false);
        msEmployer = new MsEmployer();
        msEmployerRepository = new MsEmployerRepository(getActivity());
        databaseHelper = new DatabaseHelper(getActivity());

        pref = new SharedPref(getActivity());

        employerList = new ArrayList<>();

        Fullname = (TextView) view.findViewById(R.id.tv_fullname);
        Email = (TextView) view.findViewById(R.id.tv_email);
        CompanyName = (TextView) view.findViewById(R.id.tv_companyname);
        CompanyAddress = (TextView) view.findViewById(R.id.tv_companyaddress);
        CompanyDesc = (TextView) view.findViewById(R.id.tv_companydescription);
        CompanyReq = (TextView) view.findViewById(R.id.tv_companyrequirement);

        btnEdit = (Button) view.findViewById(R.id.btnEdit);
        btnLogout = (Button) view.findViewById(R.id.btnLogout);

        // Make a instance of shared preferences
        SharedPref sharedPref = new SharedPref(getActivity());
        final MsEmployer msEmployer2 = sharedPref.loadEmployer();

        employerList= msEmployerRepository.getMsEmployer(msEmployer2.getEmployerEmail());

            if(employerList.size() == 1) {
                Fullname.setText(employerList.get(0).getEmployerName());
                Email.setText(employerList.get(0).getEmployerEmail());
                CompanyName.setText(employerList.get(0).getCompanyName());
                CompanyAddress.setText(employerList.get(0).getCompanyAddress());
                CompanyDesc.setText(employerList.get(0).getCompanyDescription());
                CompanyReq.setText(employerList.get(0).getCompanyRequirement());
            }
        btnEdit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
                Intent intent = new Intent(getActivity(), EditProfileEmployerActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
                pref.clear();
                Intent intent = new Intent(getActivity(), ChooseActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}

