package allergyDetection.db.jpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import library.db.pojos.Role;

public class JPAUserManager implements UserManager {
	
}
	private EntityManager em;
	
	public JPAUserManager() {
		em = Persistence.createEntityManagerFactory("library-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		// Create default roles
		// If they don't exist already
		try {
			this.getRole("librarian");
		} catch(NoResultException e) {
			this.createRole(new Role("librarian"));
			this.createRole(new Role("borrower"));
		}

}
