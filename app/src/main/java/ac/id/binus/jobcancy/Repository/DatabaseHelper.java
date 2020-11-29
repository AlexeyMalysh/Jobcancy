package ac.id.binus.jobcancy.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//1 Database helper untuk 4 Model
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "JobcancyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE MsApplicant(" +
                "IDApplicant INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ApplicantName TEXT NOT NULL," +
                "ApplicantUsername TEXT NOT NULL," +
                "ApplicantEmail TEXT NOT NULL," +
                "ApplicantPassword TEXT NOT NULL," +
                "ApplicantGender TEXT NOT NULL," +
                "ApplicantAddress TEXT NOT NULL," +
                "ApplicantPhoneNumber TEXT NOT NULL," +
                "ApplicantDateOfBirth TEXT NOT NULL," +
                "ApplicantCountryOrigin TEXT NOT NULL," +
                "ApplicantLastEducation TEXT NOT NULL)";

        db.execSQL(query);

//        String query2 = "CREATE TABLE MsCompany(" +
//                "IDCompany INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "IDEmployer INTEGER NOT NULL," +
//                "CompanyName TEXT NOT NULL," +
//                "CompanyAddress TEXT NOT NULL," +
//                "CompanyDescription TEXT NOT NULL," +
//                "CompanyRequirement TEXT NOT NULL,"+
//                "FOREIGN KEY (IDEmployer) REFERENCES MsEmployer(IDEmployer) ON DELETE CASCADE)";
//
//        db.execSQL(query2);

        String query3 = "CREATE TABLE MsEmployer(" +
                "IDEmployer INTEGER PRIMARY KEY AUTOINCREMENT," +
                "EmployerName TEXT NOT NULL," +
                "EmployerUsername TEXT NOT NULL," +
                "EmployerEmail TEXT NOT NULL," +
                "EmployerPassword TEXT NOT NULL,"+
                "CompanyName TEXT NOT NULL," +
                "CompanyAddress TEXT NOT NULL," +
                "CompanyDescription TEXT NOT NULL," +
                "CompanyRequirement TEXT NOT NULL)";

        db.execSQL(query3);

        String query4 = "CREATE TABLE HeaderApply(" +
                "IDApply INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IDApplicant TEXT NOT NULL," +
                "IDEmployer TEXT NOT NULL," +
                "ApplyDate TEXT NOT NULL," +
                "ApplyStatus INTEGER NOT NULL)";

        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS MsApplicant";
        db.execSQL(query);

//        String query2 = "DROP TABLE IF EXISTS MsCompany";
//        db.execSQL(query2);

        String query3 = "DROP TABLE IF EXISTS MsEmployer";
        db.execSQL(query3);

        String query4 = "DROP TABLE IF EXISTS HeaderApply";
        db.execSQL(query4);

        onCreate(db);
    }
}