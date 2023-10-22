package dogs.dogDatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dogs.dogDatabase.domain.DogAppUser;
import dogs.dogDatabase.domain.DogAppUserRepository;

//tätä luokkaa käytetään käyttäjän tunnistamiseen ja autorisointiin
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final DogAppUserRepository repository;
	
	@Autowired
	public UserDetailServiceImpl (DogAppUserRepository userRepository) {
		this.repository = userRepository;
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	DogAppUser curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHashed(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    } 
}
