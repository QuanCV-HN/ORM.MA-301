package fa.training.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
public class Interview implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer interviewId;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String interviewer;

    @Column(nullable = false)
    private String comments;

    @Column(name = "interview_result",nullable = false)
    private String interviewResult;

    @Column(length = 1000,nullable = false)
    private String remark;

    @Column(name = "candidate_id",nullable = false,insertable = false, updatable = false)
    private Integer candidateId;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", time='" + time + '\'' +
                ", date=" + date +
                ", interviewer='" + interviewer + '\'' +
                ", comments='" + comments + '\'' +
                ", interviewResult='" + interviewResult + '\'' +
                ", remark='" + remark + '\'' +
                ", candidateId=" + candidateId +
                '}';
    }
}
