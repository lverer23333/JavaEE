package edu.bjtu.ee4j.service;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import edu.bjtu.ee4j.domain_second.Coach;

public interface CoachService {
	Page<Coach> getAllCoaches(Pageable pageable);
    Coach getCoachById(Integer id);
    Coach saveCoach(Coach coach);
	void deleteCoach(Integer id);
	public Coach getCoach(String coach_name);

	
  
	
}
