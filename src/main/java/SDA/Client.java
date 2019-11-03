package SDA;

import SDA.Candidate.Candidate;
import SDA.Candidate.Gender;
import SDA.Company.Company;
import SDA.Department.Department;
import SDA.Department.DepartmentName;
import SDA.Department.Marketing;
import SDA.Department.Production;
import java.util.HashSet;

import static SDA.Candidate.Gender.*;
import static SDA.Department.DepartmentName.*;

public class Client {

    public static void main(String[] args) {

        Company myCompany = new Company();
        for (Candidate c:myCompany.getCandidates()
             ) {
            System.out.println(c);
        }
        myCompany.recruiting();

    }
}
