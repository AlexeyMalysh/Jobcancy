package ac.id.binus.jobcancy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private TabLayout Tab;
    private ViewPager ViewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Tab = (TabLayout) findViewById(R.id.tablayout_id);
        ViewPage = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerLoginAdapter adapter = new ViewPagerLoginAdapter(getSupportFragmentManager());
        adapter.addFragment(new EmployeeLoginFragment(), "Employee");
        adapter.addFragment(new EmployerLoginFragment(), "Employer");
        ViewPage.setAdapter(adapter);
        Tab.setupWithViewPager(ViewPage);

    }
}
