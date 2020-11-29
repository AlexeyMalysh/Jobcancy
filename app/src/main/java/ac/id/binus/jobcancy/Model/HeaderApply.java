package ac.id.binus.jobcancy.Model;

public class HeaderApply {
    private int IDApply;
    private int IDApplicant;
    private int IDEmployer;
    private String ApplyDate;
    private int ApplyStatus;

    public int getIDApply() {
        return IDApply;
    }

    public void setIDApply(int IDApply) {
        this.IDApply = IDApply;
    }

    public int getIDApplicant() {
        return IDApplicant;
    }

    public void setIDApplicant(int IDApplicant) {
        this.IDApplicant = IDApplicant;
    }

    public int getIDEmployer() {
        return IDEmployer;
    }

    public void setIDEmployer(int IDEmployer) {
        this.IDEmployer = IDEmployer;
    }

    public String getApplyDate() {
        return ApplyDate;
    }

    public void setApplyDate(String applyDate) {
        ApplyDate = applyDate;
    }

    public int getApplyStatus() {
        return ApplyStatus;
    }

    public void setApplyStatus(int applyStatus) {
        ApplyStatus = applyStatus;
    }
}