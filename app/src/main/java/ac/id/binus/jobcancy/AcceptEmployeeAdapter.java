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

import ac.id.binus.jobcancy.Model.MsApplicant;

public class AcceptEmployeeAdapter extends RecyclerView.Adapter<AcceptEmployeeAdapter.CompanyViewHolder>{

    private List<MsApplicant> applicantList= new ArrayList<>();
    private OnItemClick listener;

    public AcceptEmployeeAdapter(OnItemClick listener){
            this.listener = listener;
            }

    class CompanyViewHolder extends RecyclerView.ViewHolder{

        TextView tvFullName;
        TextView tvLastEducation;
        LinearLayout llProcessList;

        public CompanyViewHolder(View view){
            super(view);
            tvLastEducation = view.findViewById(R.id.tv_last_education);
            tvFullName = view.findViewById(R.id.tv_full_name);
            llProcessList = view.findViewById(R.id.ll_process_list);
        }

        public void bind(final MsApplicant msApplicant){
            tvLastEducation.setText(msApplicant.getApplicantLastEducation());
            tvFullName.setText(msApplicant.getApplicantName());

            llProcessList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(msApplicant);
                }
            });
        }
    }

        @NonNull
        @Override
        public AcceptEmployeeAdapter.CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 3 parameter : layout
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.process_list_employee,
                            parent,
                            false);
            return new AcceptEmployeeAdapter.CompanyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AcceptEmployeeAdapter.CompanyViewHolder holder, int position) {
            MsApplicant msApplicant = applicantList.get(position);
            holder.bind(msApplicant);

        }

        @Override
        public int getItemCount() {
            return applicantList.size();
        }

        public void updateData(List<MsApplicant> newList){
            ProcessEmployeeDiffUtil myDiffUtil = new ProcessEmployeeDiffUtil(applicantList, newList);
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(myDiffUtil);
            applicantList.clear();
            applicantList.addAll(newList);

            result.dispatchUpdatesTo(this);
        }

    interface OnItemClick{
        void OnClick(MsApplicant msApplicant);
    }
}