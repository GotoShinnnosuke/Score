package jp.ac.ohara.score.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Student")
@SecondaryTable(name="test",
pkJoinColumns = @PrimaryKeyJoinColumn(name="student_no"))
@AllArgsConstructor
@NoArgsConstructor
public class StudentScoreModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column(name="student_no",length=10,nullable=false,updatable =false,insertable=false)
	private String studentNo;
	
	@Column(name="name",length=10,nullable=true,updatable =false,insertable=false)
	private String name;
	
	@Column(name="ent_year",length=10,nullable=true,updatable =false,insertable=false)
	private Integer entYear;
	
	@Column(name="class_num",length=3,nullable=true,updatable =false,insertable=false)
	private String classNum;
	
	@Column(name="is_attend",nullable=true,updatable =false,insertable=false)
	private Boolean isAttend;
	
	@Column(name="school_cd",length=3,nullable=true,updatable =false,insertable=false)
	private String schoolCd;
	
	@Column(name="subject_cd",length=3,nullable=false,updatable =false,insertable=false)
	private String subjectCd;
	
	@Column(name="count",length=10,nullable=false,updatable =false,insertable=false)
	private Integer count;
	
	@Column(name="score",length=10,nullable=true,updatable =true,insertable=true)
	private Integer score;
	
	
}
