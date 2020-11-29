//package ac.id.binus.jobcancy.Repository;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import ac.id.binus.jobcancy.Model.MsCompany;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MsCompanyRepository extends DatabaseHelper{
//    public MsCompanyRepository(Context context) {super(context); }
//
//    public void insertToMsCompany(MsCompany company){
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues value = new ContentValues();
//
//        value.put("CompanyName", company.getCompanyName());
//        value.put("CompanyAddress", company.getCompanyAddress());
//        value.put("CompanyDescription", company.getCompanyDescription());
//        value.put("CompanyRequirement", company.getCompanyRequirement());
//
//        db.insert("MsCompany", null, value);
//    }
//
//    public void updateMsCompany(MsCompany company){
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues value = new ContentValues();
//
//        value.put("CompanyName", company.getCompanyName());
//        value.put("CompanyAddress", company.getCompanyAddress());
//        value.put("CompanyDescription", company.getCompanyDescription());
//        value.put("CompanyRequirement", company.getCompanyRequirement());
//
//        db.update("MsCompany", value, "IDCompany=" + company.getIDCompany(), null);
//    }
//
//    public void deleteMsCompany(int id){
//        SQLiteDatabase db = getWritableDatabase();
//
//        db.delete("MsCompany", "IDCompany=" + id, null);
//    }
//
//    public List<MsCompany> getMsCompany(int id){
//        SQLiteDatabase db = getWritableDatabase();
//
//        List<MsCompany> ListCompany = new ArrayList<>();
//
//        String query = "SELECT * FROM MsCompany WHERE IDCompany =" + id;
//        Cursor cursor = db.rawQuery(query, null);
//
//        cursor.moveToFirst();
//
//        while(!cursor.isAfterLast()){
//            MsCompany company = new MsCompany ();
//
//            company.setCompanyName(cursor.getString(cursor.getColumnIndex("CompanyName")));
//            company.setCompanyAddress(cursor.getString(cursor.getColumnIndex("CompanyAddress")));
//            company.setCompanyDescription(cursor.getString(cursor.getColumnIndex("CompanyDescription")));
//            company.setCompanyRequirement(cursor.getString(cursor.getColumnIndex("CompanyRequirement")));
//
//            ListCompany.add(company);
//            cursor.moveToNext();
//        }
//
//        return ListCompany;
//    }
//
//    public List<MsCompany> getAllMsCompany(){
//        SQLiteDatabase db = getWritableDatabase();
//
//        List<MsCompany> ListCompany = new ArrayList<>();
//
//        String query = "SELECT * FROM MsCompany";
//        Cursor cursor = db.rawQuery(query, null);
//
//        cursor.moveToFirst();
//
//        while(!cursor.isAfterLast()){
//            MsCompany company = new MsCompany ();
//
//            company.setIDCompany(cursor.getInt(cursor.getColumnIndex("IDCompany")));
//            company.setIDEmployer(cursor.getInt(cursor.getColumnIndex("IDEmployer")));
//            company.setCompanyName(cursor.getString(cursor.getColumnIndex("CompanyName")));
//            company.setCompanyAddress(cursor.getString(cursor.getColumnIndex("CompanyAddress")));
//            company.setCompanyDescription(cursor.getString(cursor.getColumnIndex("CompanyDescription")));
//            company.setCompanyRequirement(cursor.getString(cursor.getColumnIndex("CompanyRequirement")));
//
//            ListCompany.add(company);
//            cursor.moveToNext();
//        }
//
//        return ListCompany;
//    }
//}