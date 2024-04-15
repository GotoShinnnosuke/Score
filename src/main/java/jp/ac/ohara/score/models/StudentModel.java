package jp.ac.ohara.score.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="Student")
public class StudentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column(name="student_no",length=10,nullable=false)
	private String studentNo;
	
	@Column(name="name",length=10,nullable=true)
	private String name;
	
	@Column(name="ent_year",length=10,nullable=true)
	private Integer entYear;
	
	@Column(name="class_num",length=3,nullable=true)
	private String classNum;
	
	@Column(name="is_attend",nullable=true)
	private Boolean isAttend;
	
	@Column(name="school_cd",length=3,nullable=true)
	private String schoolCd;
	
			
}
