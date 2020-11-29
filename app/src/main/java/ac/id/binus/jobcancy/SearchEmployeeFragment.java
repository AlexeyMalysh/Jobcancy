package ac.id.binus.jobcancy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ac.id.binus.jobcancy.Model.MsEmployer;
import ac.id.binus.jobcancy.Repository.MsEmployerRepository;

public class SearchEmployeeFragment extends Fragment {
    private CompanyAdapter adapter;
    private List<MsEmployer> companyList;
    private RecyclerView recyclerView;
    private MsEmployerRepository msEmployerRepository;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        msEmployerRepository = new MsEmployerRepository(getActivity());
        setData();
        setAdapter();
        return view;
    }

    public void setData(){

        companyList = msEmployerRepository.getAllMsEmployer();
    }

    public void setAdapter(){
        adapter = new CompanyAdapter(new CompanyAdapter.OnItemClick() {
            @Override
            public void OnClick(MsEmployer msEmployer) {
                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), CompanyDetailActivity.class/*Nama activity target*/);

                intent.putExtra("IDEmployer", msEmployer.getIDEmployer());
                intent.putExtra("CompanyName", msEmployer.getCompanyName());
                intent.putExtra("CompanyAddress", msEmployer.getCompanyAddress());
                intent.putExtra("CompanyDescription", msEmployer.getCompanyDescription());
                intent.putExtra("CompanyRequirement", msEmployer.getCompanyRequirement());

                startActivity(intent);

            }
        });
        adapter.updateData(companyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
