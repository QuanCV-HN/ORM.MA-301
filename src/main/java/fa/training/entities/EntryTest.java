package fa.training.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class EntryTest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private Date date;

    @Column(name = "language_valuator",nullable = false)
    private String languageValuator;

    @Column(name = "language_result",nullable = false, columnDefinition = "DECIMAL(4,2) CHECK (language_result BETWEEN 0 AND 10)")
    private BigDecimal languageResult;

    @Column(name = "technical_valuator",nullable = false)
    private String technicalValuator;

    @Column(name = "technical_result",nullable = false,  columnDefinition = "DECIMAL(4,2) CHECK (technical_result BETWEEN 0 AND 10)")
    private BigDecimal technicalResult;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) CHECK (result IN ('pass','fail'))")
    private String result;

    @Column(length = 1000)
    private String remark;

    @Column(name = "candidate_id",nullable = false,insertable = false, updatable = false)
    private Integer candidateId;

    @Column(name = "entryTestSkill",nullable = false)
    private String entryTestSkill;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Override
    public String toString() {
        return "EntryTest{" +
                "testId=" + testId +
                ", time='" + time + '\'' +
                ", date=" + date +
                ", languageValuator='" + languageValuator + '\'' +
                ", languageResult=" + languageResult +
                ", technicalValuator='" + technicalValuator + '\'' +
                ", technicalResult=" + technicalResult +
                ", result='" + result + '\'' +
                ", remark='" + remark + '\'' +
                ", candidateId=" + candidateId +
                ", entryTestSkill='" + entryTestSkill + '\'' +
                '}';
    }
}
