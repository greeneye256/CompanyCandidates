package SDA.Department;

import SDA.Candidate.Candidate;
import SDA.Candidate.CandidateStatus;

public class Department implements Evaluator {

    private DepartmentName departmentName;
    private int minLevelOfCompetence;
    private int openPositions;

    public Department(DepartmentName departmentName, int minLevelOfCompetence, int openPositions) {
        this.departmentName = departmentName;
        this.minLevelOfCompetence = minLevelOfCompetence;
        this.openPositions = openPositions;
    }

    public int getOpenPositions() {
        return openPositions;
    }

    public void setOpenPositions(int openPositions) {
        this.openPositions = openPositions;
    }

    public DepartmentName getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(DepartmentName departmentName) {
        this.departmentName = departmentName;
    }

    public int getMinLevelOfCompetence() {
        return minLevelOfCompetence;
    }

    public void setMinLevelOfCompetence(int minLevelOfCompetence) {
        this.minLevelOfCompetence = minLevelOfCompetence;
    }

    public CandidateStatus evaluateBasedOnCompetenceLevel(Candidate candidate) {
        return CandidateStatus.AWAITING;
    }
    public CandidateStatus evaluateBasedOnOtherCriteria(Candidate candidate) {
        return CandidateStatus.AWAITING;
    }

}
