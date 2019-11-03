package SDA.Department;

import SDA.Candidate.Candidate;
import SDA.Candidate.CandidateStatus;

public interface Evaluator {
    CandidateStatus evaluateBasedOnCompetenceLevel(Candidate candidate);
    CandidateStatus evaluateBasedOnOtherCriteria(Candidate candidate);
}
