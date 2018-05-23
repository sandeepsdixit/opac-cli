package data;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import org.j2eebuilder.ObjectFactory;
import org.j2eebuilder.definition.DefinitionException;
import org.j2eebuilder.driver.BuilderDriverException;
import org.j2eebuilder.util.log.LogManager;

import pojo.PetHobbyBean;

public class PetHobbyDataInitializer {
	private transient static LogManager log = LogManager.getLogManager(PetDataInitializer.class); // use

	public PetHobbyDataInitializer() throws DefinitionException, BuilderDriverException {
		create(getData());
	}

	/**
	 * <pre>
	 * 
	insert into PetHobby (petID, hobbyID, name, description) values (1, 1, 'Judo', 'trail-runner');
	insert into PetHobby (petID, hobbyID, name, description) values (1, 2, 'Judo', 'player');
	insert into PetHobby (petID, hobbyID, name, description) values (2, 3, 'Vithu', 'sing');
	 * 
	 * </pre>
	 * 
	 * @return
	 */
	private <T> Collection<T> getData() {
		Collection<T> col = new HashSet();
		PetHobbyBean obj = new PetHobbyBean();
		obj.setPetID(1);
		obj.setHobbyID(1);
		obj.setName("Judo");
		obj.setDescription("trail-runner");
		col.add((T) obj);

		obj = new PetHobbyBean();
		obj.setPetID(1);
		obj.setHobbyID(2);
		obj.setName("Judo");
		obj.setDescription("player");
		col.add((T) obj);

		obj = new PetHobbyBean();
		obj.setPetID(2);
		obj.setHobbyID(3);
		obj.setName("Vithu");
		obj.setDescription("sing");
		col.add((T) obj);

		return col;
	}

	private <T> void create(Collection<T> col) throws DefinitionException, BuilderDriverException {
		for (T t : col) {
			boolean authorize = false;
			try {
				ObjectFactory.getCurrentInstance().statelessCreate("PetHobby", t, authorize);
			} catch (SQLException e) {
				log.fatal("Unable to create " + t + " due to " + e.getMessage());
			}
		}

	}
}
