package ac.id.binus.jobcancy.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ac.id.binus.jobcancy.Model.MsApplicant;

import java.util.ArrayList;
import java.util.List;

public class MsApplicantRepository extends DatabaseHelper{
    public MsApplicantRepository(Context context) {super(context); }

    public Boolean insertToMsApplicant(MsApplicant applicant, String Email){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();

        Cursor result =  db.rawQuery( "SELECT * FROM MsApplicant WHERE ApplicantEmail = ?", new String[]{  Email} );
        Cursor result2 =  db.rawQuery( "SELECT * FROM MsEmployer WHERE EmployerEmail = ?", new String[]{ Email} );



        if(result.getCount() == 0 && result2.getCount() == 0) {
            value.put("ApplicantName", applicant.getApplicantName());
            value.put("ApplicantUsername", applicant.getApplicantUsername());
            value.put("ApplicantEmail", applicant.getApplicantEmail());
            value.put("ApplicantPassword", applicant.getApplicantPassword());
            value.put("ApplicantGender", applicant.getApplicantPassword());
            value.put("ApplicantAddress", applicant.getApplicantAddress());
            value.put("ApplicantPhoneNumber", applicant.getApplicantPhoneNumber());
            value.put("ApplicantDateOfBirth", applicant.getApplicantDateOfBirth());
            value.put("ApplicantCountryOrigin", applicant.getApplicantCountryOrigin());
            value.put("ApplicantLastEducation", applicant.getApplicantLastEducation());

            db.insert("MsApplicant", null, value);
            db.close();
            return true;
        }
        return false;
    }

    public Boolean updateMsApplicant(MsApplicant applicant, String oldEmail){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();

        // Check if name exists
//        Cursor result =  db.rawQuery( "SELECT * FROM MsApplicant WHERE ApplicantEmail = ?", new String[]{ newEmail} );

        if (true) {
            value.put("ApplicantName", applicant.getApplicantName());
//        value.put("ApplicantUsername", applicant.getApplicantUsername());
//        value.put("ApplicantEmail", applicant.getApplicantEmail());
//        value.put("ApplicantPassword", applicant.getApplicantPassword());
//        value.put("ApplicantGender", applicant.getApplicantGender());
            value.put("ApplicantPhoneNumber", applicant.getApplicantPhoneNumber());
            value.put("ApplicantAddress", applicant.getApplicantAddress());
//        value.put("ApplicantDateOfBirth", applicant.getApplicantDateOfBirth());
            value.put("ApplicantCountryOrigin", applicant.getApplicantCountryOrigin());
            value.put("ApplicantLastEducation", applicant.getApplicantLastEducation());


//            Log.d("<TestUpdate>", "test update: " + IDApplicant);
            db.update("MsApplicant", value, "ApplicantEmail= ?", new String[]{oldEmail});
            db.close();

            return true;
        }
        return false;
    }

    public void deleteMsApplicant(int id){
        SQLiteDatabase db = getWritableDatabase();

        db.delete("MsApplicant", "IDApplicant=" + id, null);
    }

    public List<MsApplicant> getMsApplicant(String email){
        SQLiteDatabase db = getWritableDatabase();

        List<MsApplicant> ListApplicant = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM MsApplicant WHERE ApplicantEmail LIKE ?", new String[]{email});


        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            MsApplicant applicant = new MsApplicant();

            applicant.setApplicantName(cursor.getString(cursor.getColumnIndex("ApplicantName")));
            applicant.setApplicantUsername(cursor.getString(cursor.getColumnIndex("ApplicantUsername")));
            applicant.setApplicantEmail(cursor.getString(cursor.getColumnIndex("ApplicantEmail")));
            applicant.setApplicantPassword(cursor.getString(cursor.getColumnIndex("ApplicantPassword")));
            applicant.setApplicantGender(cursor.getString(cursor.getColumnIndex("ApplicantGender")));
            applicant.setApplicantAddress(cursor.getString(cursor.getColumnIndex("ApplicantAddress")));
            applicant.setApplicantPhoneNumber(cursor.getString(cursor.getColumnIndex("ApplicantPhoneNumber")));
            applicant.setApplicantDateOfBirth(cursor.getString(cursor.getColumnIndex("ApplicantDateOfBirth")));
            applicant.setApplicantCountryOrigin(cursor.getString(cursor.getColumnIndex("ApplicantCountryOrigin")));
            applicant.setApplicantLastEducation(cursor.getString(cursor.getColumnIndex("ApplicantLastEducation")));

            ListApplicant.add(applicant);
            cursor.moveToNext();
        }

        return ListApplicant;
    }

    public Integer getApplicant(String email) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MsApplicant WHERE ApplicantEmail LIKE ?", new String[]{email});
        if(cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex("IDApplicant"));
        }
        return null;
    }

    public Boolean checkLogin(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MsApplicant WHERE ApplicantEmail = ? AND ApplicantPassword = ?", new String[]{email, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void updatePassword(String email, String newPassword){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("ApplicantPassword", newPassword);
        db.update("MsApplicant", value, "ApplicantEmail = ?", new String[]{email});
    }
}