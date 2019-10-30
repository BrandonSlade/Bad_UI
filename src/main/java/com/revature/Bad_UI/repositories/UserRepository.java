package com.revature.Bad_UI.repositories;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
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
	public User update(User user) {
		Session session = sf.getCurrentSession();
		User checkUser = session.get(User.class, user.getId());
		String hash = checkUser.getHashedPassword();
		String fName = checkUser.getFirstName();
		String lName = checkUser.getLastName();
		String dob = checkUser.getDob();
		String jobTitle = checkUser.getJobTitle();
		String hTown = checkUser.getHomeTown();
		String cTown = checkUser.getCurrTown();
		String bio = checkUser.getBio();
		session.merge(user);
		String hql = "update User u set u.hashedPassword = :hp, u.firstName = :fn, u.lastName = :ln, u.dob = :dob,"
				+ "u.jobTitle = :jt, u.homeTown = :ht, u.currTown = :ct, u.bio = :bio where u.id = :id";
		session.createQuery(hql).setParameter("hp", hash).setParameter("fn", fName).setParameter("ln", lName).setParameter("dob", dob)
		.setParameter("jt", jobTitle).setParameter("ht", hTown).setParameter("ct", cTown).setParameter("bio", bio)
		.setParameter("id", user.getId()).executeUpdate();
		User updatedUser = session.get(User.class, user.getId());
		return updatedUser;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User deleteById(int id) {
		Session session = sf.getCurrentSession();
		User user = session.get(User.class, id );
		if (user == null) throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		session.delete(user);
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User login(Credentials credentials) throws NoSuchAlgorithmException {
	Session session = sf.getCurrentSession();
		
		List<User> userList = session.createQuery("from User u where u.username = :username")
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
