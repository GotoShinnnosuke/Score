package jp.ac.ohara.score.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.score.models.TeacherModel;
import jp.ac.ohara.score.repositories.TeacherRepository;




@Service
@Transactional
public class TeacherLoginService implements UserDetailsService{
	
	@Autowired
	private TeacherRepository repository;
	
	@Override
		public UserDetails loadUserByUsername(String teacherId)throws UsernameNotFoundException {
			System.out.println("serach ID : " +teacherId);
		 	TeacherModel user = this.repository.findByteacherIdEquals(teacherId); // emailで検索するので「EmailEquals」としている
			System.out.println(user.toString());
			return user;
		
}
}
