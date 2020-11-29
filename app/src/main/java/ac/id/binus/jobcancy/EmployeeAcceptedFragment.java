package ac.id.binus.jobcancy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ac.id.binus.jobcancy.Model.MsApplicant;
import ac.id.binus.jobcancy.Repository.HeaderApplyRepository;

public class EmployeeAcceptedFragment extends Fragment {
    AcceptEmployeeAdapter adapter;
    private List<MsApplicant> applicantList;
    HeaderApplyRepository headerApplyRepository;
    View view;
    RecyclerView recyclerView;
    public EmployeeAcceptedFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.employee_accepted_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        headerApplyRepository = new HeaderApplyRepository(getActivity());
        setData();
        setAdapter();
        return view;
    }
    public void setAdapter() {
        adapter = new AcceptEmployeeAdapter(new AcceptEmployeeAdapter.OnItemClick() {
            @Override
            public void OnClick(MsApplicant msApplicant) {
                Intent intent = new Intent(getActivity(), ProfileApplyEmployeeActivity.class);
                intent.putExtra("ApplicantName", msApplicant.getApplicantName());
                intent.putExtra("ApplicantUsername",msApplicant.getApplicantUsername());
                intent.putExtra("ApplicantEmail",msApplicant.getApplicantEmail());
                intent.putExtra("ApplicantPassword",msApplicant.getApplicantPassword());
                intent.putExtra("ApplicantGender",msApplicant.getApplicantGender());
                intent.putExtra("ApplicantAddress",msApplicant.getApplicantAddress());
                intent.putExtra("ApplicantPhoneNumber", msApplicant.getApplicantPhoneNumber());
                intent.putExtra("ApplicantDateOfBirth", msApplicant.getApplicantDateOfBirth());
                intent.putExtra("ApplicantCountryOrigin", msApplicant.getApplicantCountryOrigin());
                intent.putExtra("ApplicantLastEducation", msApplicant.getApplicantLastEducation());

                startActivity(intent);
            }
        });
        adapter.updateData(applicantList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    public void setData() {
        applicantList = headerApplyRepository.getAllMsApplicantAccept();
    }
}
