package com.revature.Bad_UI.repositories;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.revature.Bad_UI.beans.Credentials;
import com.revature.Bad_UI.beans.User;

@Repository
public class UserRepository {

SessionFactory sf;
	
	@Inject
	public UserRepository(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public User getById(int id) {
		Session session = sf.getCurrentSession();
		return session.get(User.class, id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public User create(User user) {
		Session session = sf.getCurrentSession();
		session.save(user);
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User login(Credentials credentials) throws NoSuchAlgorithmException {
	Session session = sf.getCurrentSession();
		
		List<User> userList = session.createQuery("Select u from Users where u.username = :username")
				.setParameter("username", credentials.getUsername()).list();
		User user = userList.get(0);
		if(user.getHashedPassword().equals(credentials.getHashedPassword())) {
			return user;
		}
		else{
			
			return null ;
		}
	}

}
