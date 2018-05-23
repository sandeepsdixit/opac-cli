package data;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import org.j2eebuilder.ObjectFactory;
import org.j2eebuilder.definition.DefinitionException;
import org.j2eebuilder.driver.BuilderDriverException;
import org.j2eebuilder.util.log.LogManager;

import pojo.HobbyBean;

public class HobbyDataInitializer {
	private transient static LogManager log = LogManager.getLogManager(PetDataInitializer.class); // use

	public HobbyDataInitializer() throws DefinitionException, BuilderDriverException {
		create(getData());
	}

	/**
	 * <pre>
	 * 
	insert into Hobby (hobbyID, name, description) values (1, 'trail-runner', 'trail runner');
	insert into Hobby (hobbyID, name, description) values (2, 'bark', 'play with kids');
	insert into Hobby (hobbyID, name, description) values (3, 'sing', 'Sweet notes!');
	 * 
	 * </pre>
	 * 
	 * @return
	 */
	private <T> Collection<T> getData() {
		Collection<T> col = new HashSet();
		HobbyBean obj = new HobbyBean();
		obj.setHobbyID(1);
		obj.setName("trail-runner");
		obj.setDescription("trail runner");
		col.add((T) obj);

		obj = new HobbyBean();
		obj.setHobbyID(2);
		obj.setName("bark");
		obj.setDescription("play with kids");
		col.add((T) obj);

		obj = new HobbyBean();
		obj.setHobbyID(3);
		obj.setName("sing");
		obj.setDescription("Sweet notes!");
		col.add((T) obj);

		return col;
	}

	private <T> void create(Collection<T> col) throws DefinitionException, BuilderDriverException {
		for (T t : col) {
			boolean authorize = false;
			try {
				ObjectFactory.getCurrentInstance().statelessCreate("Hobby", t, authorize);
			} catch (SQLException e) {
				log.fatal("Unable to create " + t + " due to " + e.getMessage());
			}
		}

	}
}
