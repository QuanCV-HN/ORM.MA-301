package fa.training.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Candidate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Integer candidateId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "date_of_birth" , nullable = false)
    private Date dateOfBirth;

    @Column( columnDefinition ="INT  CHECK (gender IN (0,1)) " ,nullable = false)
    private Integer gender;

    @Column(name = "graduation_year", nullable = false)
    private Date graduationYear;

    @Column( unique = true)
    private String phone;

    @Column( unique = true)
    private String email;

    @Column(nullable = false)
    private String skill;

    @Column(name = "foreign_language",nullable = false)
    private String foreignLanguage;

    @Column(nullable = false, columnDefinition = "INT CHECK (level BETWEEN 1 AND 5)")
    private Integer level;

    private String cv;

    @Column(name ="allocation_status",nullable = false)
    private Integer allocationStatus;

    @Column(nullable = false, length = 1000)
    private String remark;

    @OneToMany(mappedBy = "candidate")
    List<EntryTest> entryTestList ;

    @OneToMany(mappedBy = "candidate")
    List<Interview> interviewList = new ArrayList<>();

    @Override
    public String toString() {
        return "Candidate{" +
                "candidateId=" + candidateId +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", graduationYear=" + graduationYear +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", skill='" + skill + '\'' +
                ", foreignLanguage='" + foreignLanguage + '\'' +
                ", level=" + level +
                ", cv='" + cv + '\'' +
                ", allocationStatus=" + allocationStatus +
                ", remark='" + remark + '\'' +
                '}';
    }
}
