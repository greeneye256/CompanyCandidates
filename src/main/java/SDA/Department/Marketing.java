package SDA.Department;

import SDA.Candidate.Candidate;
import SDA.Candidate.CandidateStatus;
import SDA.Candidate.Gender;

public class Marketing extends Department {
    private static Marketing instance = new Marketing();
    private int maximumNoOfMales = getOpenPositions() * 40 / 100;
    private int maximumNoOfFemales = getOpenPositions() * 60 / 100;

    private Marketing() {
        super(DepartmentName.MARKETING, 6, 8);
    }

    public static Marketing getInstance() {
        return instance;
    }

    public CandidateStatus evaluateBasedOnCompetenceLevel(Candidate candidate) {
        if (candidate.getLevelOfCompetence() < this.getMinLevelOfCompetence()) {
            return CandidateStatus.REJECTED;
        } else return CandidateStatus.ACCEPTED;
    }

    @Override
    public CandidateStatus evaluateBasedOnOtherCriteria(Candidate candidate) {
        if (candidate.getGender().equals(Gender.FEMALE) && maximumNoOfFemales > 0) {
            maximumNoOfFemales--;
            return CandidateStatus.ACCEPTED;
        } else if (candidate.getGender().equals(Gender.MALE) && maximumNoOfMales > 0) {
            maximumNoOfMales--;
            return CandidateStatus.ACCEPTED;
        }
        return CandidateStatus.REJECTED;
    }
}
