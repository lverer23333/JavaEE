package edu.bjtu.ee4j.service;

import edu.bjtu.ee4j.domain.Person;
import edu.bjtu.ee4j.repository.primary.ContactRepository;

import org.hibernate.query.Query;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
    private ContactRepository contactRepository;
    
    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
    @Override
    public Iterable<Person> getAllPersons() {
        return this.contactRepository.findAll();
    }
    
    @Override
    public Person getPersonById(Integer id) {
        return (Person) this.contactRepository.findById(id).orElse(null);
    }
    
    @Override
    public Person savePerson(Person person) {
        return (Person) this.contactRepository.save(person);
    }
    
    @Override
    public void deletePerson(Integer id) {
        this.contactRepository.deleteById(id);
    }

   

	@Override
	public String getUser(String email) {
		// TODO Auto-generated method stub
		
		       return  this.contactRepository.getByPasswordAndUsername(email);
		 
		   
	}

	@Override
	public String getUser1(String phone) {
		// TODO Auto-generated method stub
		
		       return  this.contactRepository.getByPasswordAndUsername1(phone);
		 
		   
	}

	

    
}
