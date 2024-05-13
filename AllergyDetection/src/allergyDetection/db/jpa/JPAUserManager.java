package allergyDetection.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import allergyDetection.db.interfaces.UserManager;
import allergyDetection.db.pojos.Role;
import allergyDetection.db.pojos.User;


public class JPAUserManager implements UserManager {
	

	private EntityManager em;
	
	public JPAUserManager() {
		em = Persistence.createEntityManagerFactory("allergy-detection").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		// Create default roles
		// If they don't exist already
		try {
			this.getRole("doctor");
		} catch(NoResultException e) {
			this.createRole(new Role("doctor"));
			this.createRole(new Role("patient"));
		}

}

	@Override
	public void register(User u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		
	}

	@Override
	public void createRole(Role r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
		
	}

	@Override
	public Role getRole(String name) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?", Role.class);
		q.setParameter(1, name);
		Role r = (Role) q.getSingleResult();
		return r;
		
	}

	@Override
	public List<Role> getAllRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}

	@Override
	public void assignRole(User u, Role r) {
		em.getTransaction().begin();
		u.setRole(r);
		r.addUser(u);
		em.getTransaction().commit();
	}

	@Override
	public User login(String username, String password) {
		User u = null;
		Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
		q.setParameter(1, username);
		q.setParameter(2, password);
		try {
			u = (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return u;
	}
	
	
	
	
	
}
