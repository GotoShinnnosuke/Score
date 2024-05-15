package jp.ac.ohara.score.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jp.ac.ohara.score.models.TeacherModel;
import jp.ac.ohara.score.repositories.TeacherRepository;

@Service
@Transactional
public class TeacherService {
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private TeacherRepository repository;
	
	public void teacherSave(@Nonnull TeacherModel teachermodel) {
		teachermodel.setPassword(this.passwordencoder.encode(teachermodel.getPassword()));
		this.repository.save(teachermodel);
}
}