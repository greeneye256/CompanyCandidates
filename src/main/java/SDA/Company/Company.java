package SDA.Company;

import SDA.Candidate.Candidate;
import SDA.Candidate.CandidateStatus;
import SDA.Candidate.Gender;
import SDA.Department.Department;
import SDA.Department.DepartmentName;
import SDA.Department.Marketing;
import SDA.Department.Production;
import SDA.Exceptions.EvaluationIncapacityException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company {

    private List<Candidate> candidates = new ArrayList<>();
    private final List<Department> departments = new ArrayList<>();

    public Company(){
        departments.add(Marketing.getInstance());
        departments.add(Production.getInstance());
        candidates = readFromFile();
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public void addCandidates(List<Candidate> candidates) {
        this.candidates.addAll(candidates);
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void recruiting() {
        candidates.sort(Comparator.comparing(Candidate::getLevelOfCompetence).reversed());
        List<Candidate> candidatesInProduction = new ArrayList<>();
        List<Candidate> candidatesInMarketing = new ArrayList<>();

        for (Candidate candidate : candidates
        ) {
            if (candidate.getDepartamentName().equals(DepartmentName.MARKETING)) {
                candidatesInMarketing.add(candidate);
            } else if (candidate.getDepartamentName().equals(DepartmentName.PRODUCTION)) {
                candidatesInProduction.add(candidate);
            } else throw new EvaluationIncapacityException();
        }
        List<Candidate> acceptedCandidatesForMarketing = getCandidatesInMarketing(candidatesInMarketing);
        for (Candidate c:acceptedCandidatesForMarketing
             ) {
            System.out.println(c);
        }
        List<Candidate> acceptedCandidatesForProduction = getCandidatesInProduction(candidatesInProduction);
        for (Candidate c:acceptedCandidatesForProduction
             ) {
            System.out.println(c);
        }
        writeCandidatesToJsonFile(acceptedCandidatesForMarketing,Marketing.getInstance());
        writeCandidatesToJsonFile(acceptedCandidatesForProduction,Production.getInstance());
    }

    public int getNumberOfFemales(){
        return (int) this.candidates.stream()
                .filter(candidate -> candidate.getGender().equals(Gender.FEMALE)).count();
    }

    public int getNumberOfMales(){
        return (int) this.candidates.stream()
                .filter(candidate -> candidate.getGender().equals(Gender.MALE)).count();
    }

    private void writeCandidatesToJsonFile(List<Candidate> candidates, Department department) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (department.getDepartmentName().equals(DepartmentName.MARKETING)){
            try {
                objectMapper.writeValue(new File("candidatiAcceptatiMarketing.json"),candidates);
            }catch (Exception e){
                System.out.println("Writing to json file failed!");
            }
        }
        else try {
            objectMapper.writeValue(new File("candidatiAcceptatiProduction.json"),candidates);
        }catch (Exception e){
            System.out.println("Writing to json file failed!");
        }


    }

    private List<Candidate> readFromFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Candidate> readCandidates = new ArrayList<>();
        try {
            String input =  new String(Files.readAllBytes(Paths.get("candidati.json")));
            readCandidates = objectMapper.readValue(input,
                    objectMapper.getTypeFactory()
                            .constructCollectionType(List.class,Candidate.class));

        }catch (IOException e){
            System.out.println("Reading from file failed!");
        }
        return readCandidates;
    }

    private List<Candidate> getCandidatesInMarketing(List<Candidate> candidatesForMarketing){
        List<Candidate> finalCandidates = new ArrayList<>();
        Marketing marketing = Marketing.getInstance();
        if (candidatesForMarketing.isEmpty()){
            System.out.println("No candidates for marketing");
            return finalCandidates;
        }
        for (Candidate c:candidatesForMarketing
             ) {
            if (marketing.evaluateBasedOnCompetenceLevel(c).equals(CandidateStatus.ACCEPTED)&&marketing.evaluateBasedOnOtherCriteria(c).equals(CandidateStatus.ACCEPTED)){
                c.setStatus(CandidateStatus.ACCEPTED);
                finalCandidates.add(c);
            }
            else c.setStatus(CandidateStatus.REJECTED);
        }
        return finalCandidates;
    }
    private List<Candidate> getCandidatesInProduction(List<Candidate> candidatesForProduction){
        List<Candidate> finalCandidates = new ArrayList<>();
        Production production = Production.getInstance();
        if (candidatesForProduction.isEmpty()){
            System.out.println("No candidates for production");
            return finalCandidates;
        }
        for (Candidate c:candidatesForProduction
             ) {

            if (production.evaluateBasedOnOtherCriteria(c).equals(CandidateStatus.ACCEPTED)&&production.evaluateBasedOnCompetenceLevel(c).equals(CandidateStatus.ACCEPTED)){
                c.setStatus(CandidateStatus.ACCEPTED);
                finalCandidates.add(c);
            }
            else c.setStatus(CandidateStatus.REJECTED);
        }
        return finalCandidates;
    }

}
