package ac.id.binus.jobcancy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class SearchEmployerFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_search_employer, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout_id);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_id);
        ViewPagerApplyAdapter adapter = new ViewPagerApplyAdapter(getFragmentManager());
        adapter.addFragment(new EmployeeProcessFragment(), "In Process");
        adapter.addFragment(new EmployeeAcceptedFragment(), "Accepted");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
