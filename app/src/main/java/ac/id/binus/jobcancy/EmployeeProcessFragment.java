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

import java.util.ArrayList;
import java.util.List;

import ac.id.binus.jobcancy.Model.MsApplicant;
import ac.id.binus.jobcancy.Repository.HeaderApplyRepository;

public class EmployeeProcessFragment extends Fragment {
    ProcessEmployeeAdapter adapter;
    View view;
    List<MsApplicant> applicantList;
    RecyclerView recyclerView;
    HeaderApplyRepository headerApplyRepository;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.employee_process_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        headerApplyRepository = new HeaderApplyRepository(getActivity());
        setData();
        setAdapter();
        return view;
    }

    public void setAdapter() {
        applicantList = new ArrayList<>();
        adapter = new ProcessEmployeeAdapter(new ProcessEmployeeAdapter.OnItemClick() {
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
//        adapter.updateData(applicantList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    public void setData() {
        applicantList = headerApplyRepository.getAllMsApplicantProcess();
    }

    private void getData(){

        HeaderApplyRepository headerApplyRepository = new HeaderApplyRepository(getActivity());
        applicantList.clear();
        applicantList.addAll(headerApplyRepository.getAllMsApplicantProcess());
        adapter.updateData(applicantList);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
