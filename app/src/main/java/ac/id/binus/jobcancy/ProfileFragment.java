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
import ac.id.binus.jobcancy.Repository.DatabaseHelper;
import ac.id.binus.jobcancy.Repository.MsApplicantRepository;

public class ProfileFragment extends Fragment {
    View view;
    MsApplicantRepository msApplicantRepository;
    MsApplicant msApplicant;
    DatabaseHelper databaseHelper;
    private SharedPref pref;

    private List<MsApplicant> applicantList;

    private Button btnEdit, btnLogout;
    private TextView Fullname, Email, Phonenumber, Address, CountryOrigin, LastEducation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        msApplicant = new MsApplicant();
        msApplicantRepository = new MsApplicantRepository(getActivity());
        databaseHelper = new DatabaseHelper(getActivity());

        pref = new SharedPref(getActivity());

        applicantList = new ArrayList<>();

        Fullname = (TextView) view.findViewById(R.id.tv_fullname);
        Email = (TextView) view.findViewById(R.id.tv_email);
        Phonenumber = (TextView) view.findViewById(R.id.tv_phonenumber);
        Address = (TextView) view.findViewById(R.id.tv_address);
        CountryOrigin = (TextView) view.findViewById(R.id.tv_countryorigin);
        LastEducation = (TextView) view.findViewById(R.id.tv_lasteducation);

        btnEdit = (Button) view.findViewById(R.id.btnEdit);
        btnLogout = (Button) view.findViewById(R.id.btnLogout);

        // Make a instance of shared preferences
        SharedPref sharedPref = new SharedPref(getActivity());
        final MsApplicant msApplicant2 = sharedPref.loadApplicant();

        applicantList = msApplicantRepository.getMsApplicant(msApplicant2.getApplicantEmail());

        if (applicantList.size() == 1) {
            Fullname.setText(applicantList.get(0).getApplicantName());
            Email.setText(applicantList.get(0).getApplicantEmail());
            Phonenumber.setText(applicantList.get(0).getApplicantPhoneNumber());
            Address.setText(applicantList.get(0).getApplicantAddress());
            CountryOrigin.setText(applicantList.get(0).getApplicantCountryOrigin());
            LastEducation.setText(applicantList.get(0).getApplicantLastEducation());
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
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

