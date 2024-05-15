package jp.ac.ohara.score.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="test")
@AllArgsConstructor
@NoArgsConstructor
public class TestModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column(name="student_no",length=10,nullable=false)
	private String studentNo;
	
	@Column(name="subject_cd",length=3,nullable=false)
	private String subjectCd;
	
	@Column(name="school_cd",length=10,nullable=false)
	private String schoolCd;
	
	@Column(name="count",length=10,nullable=false)
	private Integer count;
	
	@Column(name="score",length=10,nullable=true)
	private Integer score;
	
	@Column(name="class_num",length=5,nullable=true)
	private String classNum;
	
}
