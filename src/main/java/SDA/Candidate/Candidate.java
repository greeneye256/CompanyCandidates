package SDA.Candidate;
import SDA.Department.DepartmentName;

public class Candidate {
    private String name;
    private int levelOfCompetence;
    private DepartmentName departamentName;
    private CandidateStatus status;
    private Gender gender;

    public Candidate() {
    }

    public Candidate(String name, int levelOfCompetence, DepartmentName departmentName, Gender gender) {
        this.name = name;
        this.levelOfCompetence = levelOfCompetence;
        this.departamentName = departmentName;
        status = CandidateStatus.AWAITING;
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setStatus(CandidateStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelOfCompetence() {
        return levelOfCompetence;
    }

    public void setLevelOfCompetence(int levelOfCompetence) {
        this.levelOfCompetence = levelOfCompetence;
    }

    public DepartmentName getDepartamentName() {
        return departamentName;
    }

    public void setDepartamentName(DepartmentName departamentName) {
        this.departamentName = departamentName;
    }

    public CandidateStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", levelOfCompetence=" + levelOfCompetence +
                ", departament=" + departamentName +
                ", status=" + status +
                '}';
    }
}
