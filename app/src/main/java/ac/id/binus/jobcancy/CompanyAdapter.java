package ac.id.binus.jobcancy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ac.id.binus.jobcancy.Model.MsEmployer;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>{

    private List<MsEmployer> companyList = new ArrayList<>();
    private OnItemClick listener;

    public CompanyAdapter(OnItemClick listener){
        this.listener = listener;
    }

    class CompanyViewHolder extends RecyclerView.ViewHolder{

        TextView tvCompanyName;
        TextView tvCompanyAddress;
        LinearLayout llCompanyList;

        public CompanyViewHolder(View view){
            super(view);
            tvCompanyName = view.findViewById(R.id.tv_company_name);
            tvCompanyAddress = view.findViewById(R.id.tv_company_address);
            llCompanyList = view.findViewById(R.id.ll_company_list);
        }

        public void bind(final MsEmployer msEmployer){
            tvCompanyName.setText(msEmployer.getCompanyName());
            tvCompanyAddress.setText(msEmployer.getCompanyAddress());

            llCompanyList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(msEmployer);
                }
            });
        }
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 3 parameter : layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_company,
                        parent,
                        false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        MsEmployer msEmployer = companyList.get(position);
        holder.bind(msEmployer);
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public void updateData(List<MsEmployer> newList){
        CompanyDiffUtil myDiffUtil = new CompanyDiffUtil(companyList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(myDiffUtil);
        companyList.clear();
        companyList.addAll(newList);

        result.dispatchUpdatesTo(this);
    }

    interface OnItemClick{
        void OnClick(MsEmployer msEmployer);
    }
}