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
@Table(name="Subject")
public class SubjectModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column(name="school_cd",length=3,nullable=false)
	private String schoolCd;
	
	@Column(name="subject_cd",length=3,nullable=false)
	private String subjectCd;
	
	@Column(name="subject_name",length=20,nullable=true)
	private String subjectName;
	
	@Column(name="is_delete",nullable=true)
	private Boolean isDelete;
	
}
