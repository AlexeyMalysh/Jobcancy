package ac.id.binus.jobcancy.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ac.id.binus.jobcancy.Model.MsEmployer;

import java.util.ArrayList;
import java.util.List;

public class MsEmployerRepository extends DatabaseHelper{
    public MsEmployerRepository(Context context) {super(context); }

    public Boolean insertToMsEmployer(MsEmployer employer, String Email){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();

        // Check if name exists
        Cursor result =  db.rawQuery( "SELECT * FROM MsEmployer WHERE EmployerEmail = ?", new String[]{ Email} );
        Cursor result2 =  db.rawQuery( "SELECT * FROM MsApplicant WHERE ApplicantEmail = ?", new String[]{  Email} );


        if (result.getCount() == 0 && result2.getCount() == 0) {

            value.put("EmployerName", employer.getEmployerName());
            value.put("EmployerUsername", employer.getEmployerUsername());
            value.put("EmployerEmail", employer.getEmployerEmail());
            value.put("EmployerPassword", employer.getEmployerPassword());
            value.put("CompanyName", employer.getCompanyName());
            value.put("CompanyAddress", employer.getCompanyAddress());
            value.put("CompanyDescription", employer.getCompanyDescription());
            value.put("CompanyRequirement", employer.getCompanyRequirement());


            db.insert("MsEmployer", null, value);
            db.close();

            return true;
        }
        return false;
    }

    public Boolean updateMsEmployer(MsEmployer employer, String oldEmail){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();

        // Check if name exists
//        Cursor result =  db.rawQuery( "SELECT * FROM MsApplicant WHERE ApplicantEmail = ?", new String[]{ newEmail} );

        if (true) {
            value.put("EmployerName", employer.getEmployerName());
//            value.put("EmployerEmail", employer.getEmployerEmail());
            value.put("CompanyName", employer.getCompanyName());
            value.put("CompanyAddress", employer.getCompanyAddress());
            value.put("CompanyDescription", employer.getCompanyDescription());
            value.put("CompanyRequirement", employer.getCompanyRequirement());

            db.update("MsEmployer", value, "EmployerEmail= ?", new String[]{oldEmail});
            db.close();

            return true;
        }
        return false;
    }

    public void deleteMsEmployer(int id){
        SQLiteDatabase db = getWritableDatabase();

        db.delete("MsEmployer", "IDEmployer=" + id, null);
    }

    public List<MsEmployer> getMsEmployer(String email){
        SQLiteDatabase db = getWritableDatabase();

        List<MsEmployer> ListEmployer = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM MsEmployer WHERE EmployerEmail = ?", new String[]{email});

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            MsEmployer employer = new MsEmployer();

            employer.setEmployerName(cursor.getString(cursor.getColumnIndex("EmployerName")));
            employer.setEmployerUsername(cursor.getString(cursor.getColumnIndex("EmployerUsername")));
            employer.setEmployerEmail(cursor.getString(cursor.getColumnIndex("EmployerEmail")));
            employer.setEmployerPassword(cursor.getString(cursor.getColumnIndex("EmployerPassword")));
            employer.setCompanyName(cursor.getString(cursor.getColumnIndex("CompanyName")));
            employer.setCompanyAddress(cursor.getString(cursor.getColumnIndex("CompanyAddress")));
            employer.setCompanyDescription(cursor.getString(cursor.getColumnIndex("CompanyDescription")));
            employer.setCompanyRequirement(cursor.getString(cursor.getColumnIndex("CompanyRequirement")));

            ListEmployer.add(employer);
            cursor.moveToNext();
        }

        return ListEmployer;
    }

    public Boolean checkLoginEmployer(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MsEmployer WHERE EmployerEmail = ? AND EmployerPassword = ?", new String[]{email, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void updatePasswordEmployer(String email, String newPassword){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("EmployerPassword", newPassword);
        db.update("MsEmployer", value, "EmployerEmail = ?", new String[]{email});

    }
    public List<MsEmployer> getAllMsEmployer(){
        SQLiteDatabase db = getWritableDatabase();

        List<MsEmployer> ListCompany = new ArrayList<>();

        String query = "SELECT * FROM MsEmployer";
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            MsEmployer employer = new MsEmployer();

            employer.setIDEmployer(cursor.getInt(cursor.getColumnIndex("IDEmployer")));
            employer.setCompanyName(cursor.getString(cursor.getColumnIndex("CompanyName")));
            employer.setCompanyAddress(cursor.getString(cursor.getColumnIndex("CompanyAddress")));
            employer.setCompanyDescription(cursor.getString(cursor.getColumnIndex("CompanyDescription")));
            employer.setCompanyRequirement(cursor.getString(cursor.getColumnIndex("CompanyRequirement")));

            ListCompany.add(employer);
            cursor.moveToNext();
        }

        return ListCompany;
    }
}
