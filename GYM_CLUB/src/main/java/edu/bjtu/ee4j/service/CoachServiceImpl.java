package edu.bjtu.ee4j.service;

import edu.bjtu.ee4j.domain_second.Coach;
import edu.bjtu.ee4j.repository.secondary.CoachRepository;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CoachServiceImpl implements CoachService {
	@Autowired
    private CoachRepository coachRepository;
    
    @Autowired
    public void setcoachRepository(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }
    
    @Override
    public Page<Coach> getAllCoaches(Pageable pageable) {
        return (Page<Coach>) this.coachRepository.findAll(pageable);
    }
    
    @Override
    public Coach getCoachById(Integer id) {
        return (Coach) this.coachRepository.findById(id).orElse(null);
    }
    
    @Override
    public Coach saveCoach(Coach Coach) {
        return (Coach) this.coachRepository.save(Coach);
    }
    
    @Override
    public void deleteCoach(Integer id) {
        this.coachRepository.deleteById(id);
    }

   

	@Override
	public Coach getCoach(String coach_name) {
		// TODO Auto-generated method stub
		
		       return  this.coachRepository.getByCoach_Name(coach_name);
		 
		   
	}

	


    
}
