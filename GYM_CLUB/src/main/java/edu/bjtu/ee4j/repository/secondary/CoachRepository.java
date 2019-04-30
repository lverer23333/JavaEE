package edu.bjtu.ee4j.repository.secondary;


import edu.bjtu.ee4j.domain_second.Coach;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Integer>{

	@Query("select c  from Coach c where coach_name=?1")
    public Coach getByCoach_Name(String coach_name);

	public Iterable<Coach> findAll(Pageable pageable);
    
}
