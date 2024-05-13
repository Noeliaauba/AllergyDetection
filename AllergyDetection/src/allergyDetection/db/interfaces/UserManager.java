package allergyDetection.db.interfaces;

import java.util.List;

import allergyDetection.db.pojos.*;

public interface UserManager {

	public void register(User u);
	public void createRole(Role r);
	public Role getRole(String name);
	public List<Role> getAllRoles();
	public void assignRole(User u, Role r);
	public User login(String username, String password);
	
}
