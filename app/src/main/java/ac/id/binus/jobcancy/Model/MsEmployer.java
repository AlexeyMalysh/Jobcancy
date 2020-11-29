package ac.id.binus.jobcancy.Model;

public class MsEmployer {
    private int IDEmployer;
    private String EmployerName;
    private String EmployerUsername;
    private String EmployerEmail;
    private String EmployerPassword;
    private int IDCompany;
    private String CompanyName;
    private String CompanyAddress;
    private String CompanyDescription;
    private String CompanyRequirement;

    public String getCompanyRequirement() {
        return CompanyRequirement;
    }

    public void setCompanyRequirement(String companyRequirement) {
        CompanyRequirement = companyRequirement;
    }

    public int getIDCompany() {
        return IDCompany;
    }

    public void setIDCompany(int IDCompany) {
        this.IDCompany = IDCompany;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getCompanyDescription() {
        return CompanyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        CompanyDescription = companyDescription;
    }


    public String getEmployerUsername() { return EmployerUsername; }

    public void setEmployerUsername(String employerUsername) { EmployerUsername = employerUsername; }

    public int getIDEmployer() {
        return IDEmployer;
    }

    public void setIDEmployer(int IDEmployer) {
        this.IDEmployer = IDEmployer;
    }

    public String getEmployerName() {
        return EmployerName;
    }

    public void setEmployerName(String employerName) {
        EmployerName = employerName;
    }

    public String getEmployerEmail() {
        return EmployerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        EmployerEmail = employerEmail;
    }

    public String getEmployerPassword() {
        return EmployerPassword;
    }

    public void setEmployerPassword(String employerPassword) {
        EmployerPassword = employerPassword;
    }
}
