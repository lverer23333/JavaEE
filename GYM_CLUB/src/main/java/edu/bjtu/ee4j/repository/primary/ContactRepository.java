package edu.bjtu.ee4j.repository.primary;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.bjtu.ee4j.domain.Person;

@Repository
public interface ContactRepository extends CrudRepository<Person, Integer>{

	@Query("select password from Person where email = ?1")
    public String getByPasswordAndUsername(String email);
  //查询用户通过密码和姓名
	@Query("select password from Person where phone_no = ?1")
    public String getByPasswordAndUsername1(String phone);
	
    
}
