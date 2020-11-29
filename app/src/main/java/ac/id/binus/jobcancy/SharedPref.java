package ac.id.binus.jobcancy;

import android.content.Context;
import android.content.SharedPreferences;

import ac.id.binus.jobcancy.Model.MsApplicant;
import ac.id.binus.jobcancy.Model.MsEmployer;

import ac.id.binus.jobcancy.Model.MsApplicant;

public class SharedPref {

    // What's the function of this class :
    // take care of saving and loading data from shared preferences xml.
    // remember, the data is saved in the form of xml.

    private SharedPreferences sharedPreferences;

    public SharedPref(Context context){
        sharedPreferences = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);

    }

    public void saveApplicant(MsApplicant msApplicant){
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("fullname", msApplicant.getApplicantName())
        editor.putInt("id", msApplicant.getIDApplicant());
        editor.putString("email", msApplicant.getApplicantEmail());
//        editor.putString("username", msApplicant.getApplicantUsername());
        editor.putString("password", msApplicant.getApplicantPassword());
//        editor.putString("phonenumber", msApplicant.getApplicantPhoneNumber());
//        editor.putString("dateofbirth", msApplicant.getApplicantDateOfBirth());
//        editor.putString("gender", msApplicant.getApplicantGender());
//        editor.putString("address", msApplicant.getApplicantAddress());
//        editor.putString("countryorigin", msApplicant.getApplicantCountryOrigin());
//        editor.putString("lasteducation", msApplicant.getApplicantLastEducation());
        // you can use apply() or commit() to saving data to shared preferences xml
        editor.apply();
    }

    public MsApplicant loadApplicant(){
        MsApplicant msApplicant = new MsApplicant();
//        msApplicant.setApplicantName(sharedPreferences.getString("fullname", ""));
        msApplicant.setIDApplicant(sharedPreferences.getInt("id", 0));
        msApplicant.setApplicantEmail(sharedPreferences.getString("email", ""));
//        msApplicant.setApplicantUsername(sharedPreferences.getString("username", ""));
        msApplicant.setApplicantPassword(sharedPreferences.getString("password", ""));
//        msApplicant.setApplicantPhoneNumber(sharedPreferences.getString("phonenumber", ""));
//        msApplicant.setApplicantDateOfBirth(sharedPreferences.getString("dateofbirth", ""));
//        msApplicant.setApplicantGender(sharedPreferences.getString("gender", ""));
//        msApplicant.setApplicantAddress(sharedPreferences.getString("address", ""));
//        msApplicant.setApplicantCountryOrigin(sharedPreferences.getString("countryorigin", ""));
//        msApplicant.setApplicantLastEducation(sharedPreferences.getString("lasteducation", ""));
        return msApplicant;
    }

    public void saveEmployer(MsEmployer msEmployer){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email2", msEmployer.getEmployerEmail());
        editor.putString("password2", msEmployer.getEmployerPassword());

        editor.apply();
    }

    public MsEmployer loadEmployer(){
        MsEmployer msEmployer = new MsEmployer();
        msEmployer.setEmployerEmail(sharedPreferences.getString("email2", ""));
        msEmployer.setEmployerPassword(sharedPreferences.getString("password2", ""));
        return msEmployer;
    }

    public void clear(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }


}