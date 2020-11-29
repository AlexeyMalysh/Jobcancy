package ac.id.binus.jobcancy.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ac.id.binus.jobcancy.Model.HeaderApply;
import ac.id.binus.jobcancy.Model.MsApplicant;

public class HeaderApplyRepository extends DatabaseHelper {

    public HeaderApplyRepository(Context context) { super(context); }

    public boolean insertToHeaderApply(HeaderApply apply, int IDApplicant, int IDEmployer){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();

        Cursor result =  db.rawQuery( "SELECT * FROM HeaderApply WHERE IDApplicant = " + apply.getIDApplicant() +" AND IDEmployer =" + IDEmployer, null);

        if(result.getCount() == 0)
        {
            value.put("IDApplicant", apply.getIDApplicant());
            value.put("IDEmployer", apply.getIDEmployer());
            value.put("ApplyDate", apply.getApplyDate());
            value.put("ApplyStatus", apply.getApplyStatus());

            db.insert("HeaderApply", null, value);
            db.close();
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean updateHeaderApply(HeaderApply apply){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put("ApplyStatus", apply.getApplyStatus());

        db.update("HeaderApply", value, "IDApply=" + apply.getIDApply(), null);

        return true;
    }

    public Boolean deleteHeaderApply(int id){
        SQLiteDatabase db = getWritableDatabase();

        db.delete("HeaderApply", "IDApply=" + id, null);
        return true;
    }

    public List<MsApplicant> getMsApplicant(int id){
        SQLiteDatabase db = getWritableDatabase();

        List<MsApplicant> ListApplicant = new ArrayList<>();

        String query = "SELECT * FROM Note WHERE IDApplicant =" + id;
        Cursor cursor = db.rawQuery(query, null);

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

    public List<MsApplicant> getAllMsApplicantProcess(){
        SQLiteDatabase db = getWritableDatabase();

        List<MsApplicant> ListApplicant = new ArrayList<>();

        String query = "SELECT * FROM MsApplicant ma " +
                "INNER JOIN HeaderApply ha ON ma.IDApplicant = ha.IDApplicant " +
                "INNER JOIN MsEmployer me ON me.IDEmployer = ha.IDEmployer " +
                "WHERE ha.ApplyStatus = 0";
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            MsApplicant applicant = new MsApplicant ();

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

        Log.d("<TEST>", "getAllMsApplicantProcess: " + ListApplicant.size());
        return ListApplicant;
    }

    public List<MsApplicant> getAllMsApplicantAccept(){
        SQLiteDatabase db = getWritableDatabase();

        List<MsApplicant> ListApplicant = new ArrayList<>();

        String query = "SELECT * FROM MsApplicant ma " +
                "JOIN HeaderApply ha ON ma.IDApplicant = ha.IDApplicant " +
                "JOIN MsEmployer mc ON mc.IDEmployer = ha.IDEmployer " +
                "WHERE ha.ApplyStatus = 1";
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            MsApplicant applicant = new MsApplicant ();

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
}
