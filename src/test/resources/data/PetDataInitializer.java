package data;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import org.j2eebuilder.ObjectFactory;
import org.j2eebuilder.definition.DefinitionException;
import org.j2eebuilder.driver.BuilderDriverException;
import org.j2eebuilder.util.log.LogManager;

import pojo.PetBean;

public class PetDataInitializer {
	private transient static LogManager log = LogManager.getLogManager(PetDataInitializer.class); // use

	public PetDataInitializer() throws DefinitionException, BuilderDriverException {
		create(getData());
	}

	/**
	 * <pre>
	 * 
	insert into Pet (petID, name, description) values (1, 'Judo', 'A mischievous airdale');
	insert into Pet (petID, name, description) values (2, 'Vithu', 'An awesome parrot who can talk!');
	insert into Pet (petID, name, description) values (3, 'Rock', 'A pet rock! Does nothing at all!');
	 * 
	 * </pre>
	 * 
	 * @return
	 */
	private <T> Collection<T> getData() {
		Collection<T> col = new HashSet();
		PetBean obj = new PetBean();
		obj.setPetID(1);
		obj.setName("Judo");
		obj.setDescription("A mischievous airdale");
		col.add((T) obj);

		obj = new PetBean();
		obj.setPetID(2);
		obj.setName("Vithu");
		obj.setDescription("An awesome parrot who can talk!");
		col.add((T) obj);

		obj = new PetBean();
		obj.setPetID(3);
		obj.setName("Rock");
		obj.setDescription("A pet that does nothing at all!");
		col.add((T) obj);

		return col;
	}

	private <T> void create(Collection<T> col) throws DefinitionException, BuilderDriverException {

		for (T t : col) {
			boolean authorize = false;
			try {
				ObjectFactory.getCurrentInstance().statelessCreate("Pet", t, authorize);
			} catch (SQLException e) {
				log.fatal("Unable to create " + t + " due to " + e.getMessage());
			}
		}
	}
}
