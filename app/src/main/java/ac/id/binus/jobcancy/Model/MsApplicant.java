package ac.id.binus.jobcancy.Model;

public class MsApplicant{
    private int IDApplicant;
    private String ApplicantName;
    private String ApplicantUsername;
    private String ApplicantEmail;
    private String ApplicantPassword;
    private String ApplicantGender;
    private String ApplicantAddress;
    private String ApplicantPhoneNumber;
    private String ApplicantDateOfBirth;
    private String ApplicantCountryOrigin;
    private String ApplicantLastEducation;

    public String getApplicantUsername() { return ApplicantUsername; }

    public void setApplicantUsername(String applicantUsername) { ApplicantUsername = applicantUsername; }

    public int getIDApplicant() {
        return IDApplicant;
    }

    public void setIDApplicant(int IDApplicant) {
        this.IDApplicant = IDApplicant;
    }

    public String getApplicantName() {
        return ApplicantName;
    }

    public void setApplicantName(String applicantName) {
        ApplicantName = applicantName;
    }

    public String getApplicantEmail() {
        return ApplicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        ApplicantEmail = applicantEmail;
    }

    public String getApplicantPassword() {
        return ApplicantPassword;
    }

    public void setApplicantPassword(String applicantPassword) {
        ApplicantPassword = applicantPassword;
    }

    public String getApplicantGender() {
        return ApplicantGender;
    }

    public void setApplicantGender(String applicantGender) {
        ApplicantGender = applicantGender;
    }

    public String getApplicantAddress() {
        return ApplicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        ApplicantAddress = applicantAddress;
    }

    public String getApplicantPhoneNumber() {
        return ApplicantPhoneNumber;
    }

    public void setApplicantPhoneNumber(String applicantPhoneNumber) {
        ApplicantPhoneNumber = applicantPhoneNumber;
    }

    public String getApplicantDateOfBirth() {
        return ApplicantDateOfBirth;
    }

    public void setApplicantDateOfBirth(String applicantDateOfBirth) {
        ApplicantDateOfBirth = applicantDateOfBirth;
    }

    public String getApplicantCountryOrigin() {
        return ApplicantCountryOrigin;
    }

    public void setApplicantCountryOrigin(String applicantCountryOrigin) {
        ApplicantCountryOrigin = applicantCountryOrigin;
    }

    public String getApplicantLastEducation() {
        return ApplicantLastEducation;
    }

    public void setApplicantLastEducation(String applicantLastEducation) {
        ApplicantLastEducation = applicantLastEducation;
    }
}
