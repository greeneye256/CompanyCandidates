package SDA.Department;

import SDA.Candidate.Candidate;
import SDA.Candidate.CandidateStatus;

public class Production extends Department {

    private static Production instance = new Production();
    private int requiredNoOfEmployeesWithMaxLevel = (int)(Math.round((double)getOpenPositions() * 20 / 100));
    private int availlablePositions = getOpenPositions();
    private Production() {
        super(DepartmentName.PRODUCTION, 8,3);
    }

    public int getRequiredNoOfEmployeesWithMaxLevel() {
        return requiredNoOfEmployeesWithMaxLevel;
    }

    public void setRequiredNoOfEmployeesWithMaxLevel(int requiredNoOfEmployeesWithMaxLevel) {
        this.requiredNoOfEmployeesWithMaxLevel = requiredNoOfEmployeesWithMaxLevel;
    }

    public static Production getInstance() {
        return instance;
    }

    public CandidateStatus evaluateBasedOnCompetenceLevel(Candidate candidate) {
        if (availlablePositions > 0 ){
            if (candidate.getLevelOfCompetence() < this.getMinLevelOfCompetence()) {
                return CandidateStatus.REJECTED;
            } else {
                availlablePositions--;
                return CandidateStatus.ACCEPTED;
            }
        }
        return CandidateStatus.REJECTED;
    }

    @Override
    public CandidateStatus evaluateBasedOnOtherCriteria(Candidate candidate) {
        if (requiredNoOfEmployeesWithMaxLevel>0){
            if (candidate.getLevelOfCompetence() == 10){
                requiredNoOfEmployeesWithMaxLevel--;
                return CandidateStatus.ACCEPTED;
            }
            else {
                return CandidateStatus.REJECTED;
            }
        }
        else return CandidateStatus.ACCEPTED;
    }
}
