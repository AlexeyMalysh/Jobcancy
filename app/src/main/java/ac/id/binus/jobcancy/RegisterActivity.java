package ac.id.binus.jobcancy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class RegisterActivity extends AppCompatActivity {

    private TabLayout Tab;
    private ViewPager ViewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Tab = (TabLayout) findViewById(R.id.tablayout_id);
        ViewPage = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EmployeeRegistFragment(), "Employee");
        adapter.addFragment(new EmployerRegistFragment(), "Employer");
        ViewPage.setAdapter(adapter);
        Tab.setupWithViewPager(ViewPage);
    }
}
