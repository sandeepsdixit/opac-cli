import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import org.j2eebuilder.DatabaseDataInstanceLocator;
import org.j2eebuilder.ObjectFactory;
import org.j2eebuilder.definition.DefinitionException;
import org.j2eebuilder.driver.BuilderDriverException;
import org.j2eebuilder.driver.BuilderDriverManager;
import org.j2eebuilder.util.log.LogManager;
import org.j2eebuilder.view.ejb.TransientObjectManagerFactory;

import pojo.PetBean;

public class DriverUsageExample {
	static final LogManager log = LogManager.getLogManager(DriverUsageExample.class);

	static {
		// BasicConfigurator.configure();
		// log.setAdditivity(false);
		log.setLevel(LogManager.FATAL);
		// log.setLevel("org.j2eebuilder.statement", log.FATAL);
		log.setLevel("DriverUsageExample", LogManager.INFO);
		// log.setLevel("org.j2eebuilder.view.ejb.CassandraTransientObjectManager",
		// LogManager.DEBUG);
	}

	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			System.out.println("Usage: java -cp .:opac-cli-all.jar DriverUsageExample ./opac-config.xml");
			return;
		}

		DriverUsageExample driverUsageExample = new DriverUsageExample();
		String driverConfigFilePath = args[0];
		try {
			new DatabaseDataInstanceLocator();

			BuilderDriverManager.getCurrentInstance().register(driverConfigFilePath);

			driverUsageExample.create();
			driverUsageExample.retrieveAndUpdate();
			driverUsageExample.retrieveAndDelete();

			// below will fetch data loaded by initialize data;
			driverUsageExample.findAllPets();

			driverUsageExample.findPetsWithTrailRunningHobby();

			BuilderDriverManager.getCurrentInstance().unregister(driverConfigFilePath);

			// clean up all connections
			TransientObjectManagerFactory.getCurrentInstance().shutdown();

		} catch (BuilderDriverException | SQLException | DefinitionException e) {
			driverUsageExample.log.fatal(e);
		}

	}

	public <T> void create() throws DefinitionException, BuilderDriverException {
		PetBean obj = new PetBean();
		obj.setPetID(99);
		obj.setName("Micky");
		obj.setDescription("A loveable mouse!");

		log.info("+--- running create...");
		boolean authorize = false;
		try {
			ObjectFactory.getCurrentInstance().statelessCreate("Pet", obj, authorize);
		} catch (SQLException e) {
			log.fatal("Unable to create " + obj + " due to " + e.getMessage());
		}
		log.info("done.\n\n");
	}

	public <T> void retrieveAndDelete() throws DefinitionException, BuilderDriverException {

		try {
			log.info("+--- running query findByName('Micky')...");
			Collection<PetBean> colOfPetTO = ObjectFactory.getCurrentInstance().query("return Pet.findByName(?1)",
					new String[] { "*" }, new String[] { "Micky" });

			log.info("found pet:" + colOfPetTO);
			log.info("done.\n\n");

			log.info("+--- running delete...");
			for (PetBean pet : colOfPetTO) {
				ObjectFactory.getCurrentInstance().statelessDelete("Pet", pet);
			}
		} catch (SQLException e) {
			log.fatal("Unable to retrieve and delete due to " + e.getMessage());
		}
		log.info("done.\n\n");
	}

	public <T> void retrieveAndUpdate() throws DefinitionException, BuilderDriverException {

		try {
			log.info("+--- running query findByName('Micky')...");
			Collection<PetBean> colOfPetTO = ObjectFactory.getCurrentInstance().query("return Pet.findByName(?1)",
					new String[] { "*" }, new String[] { "Micky" });

			log.info("found pet:" + colOfPetTO);
			log.info("done.\n\n");

			if (colOfPetTO == null || colOfPetTO.isEmpty()) {
				create();
			}

			Boolean authorize = false;
			Integer modifierID = null;
			log.info("+--- running update...");
			for (PetBean pet : colOfPetTO) {
				pet.setDescription("A lovable mouse and a lot more!!!");
				pet.setLastModifiedOn(new Timestamp(System.currentTimeMillis()));
				ObjectFactory.getCurrentInstance().statelessUpdate("Pet", pet, modifierID, authorize);
			}
		} catch (SQLException e) {
			log.fatal("Unable to retrieve and update due to " + e.getMessage());
		}
		log.info("done.\n\n");
	}

	/**
	 * run a simple query
	 * 
	 * @throws SQLException
	 */
	private void findAllPets() throws SQLException {
		boolean authorize = false;
		String oql = "return Pet.findAll()";
		String[] columnNames = new String[] { "*" };
		String[] paramValues = new String[] {};
		log.info("+--- running query findAllPets...");
		Collection<PetBean> colOfPetTO = ObjectFactory.getCurrentInstance().query(oql, columnNames, paramValues);
		for (PetBean petTO : colOfPetTO) {
			log.info(String.valueOf(petTO));
		}
		log.info("done.\n\n");
	}

	/**
	 * run a complex query with parameter
	 * 
	 * @throws SQLException
	 */
	private void findPetsWithTrailRunningHobby() throws SQLException {
		boolean authorize = false;
		String oql = "return Pet.findAll() -> exists PetHobby.findByPetID(e.petID) -> exists Hobby.findByPrimaryKey(e.hobbyID) as h1 -> filter.keep(h1.name = ?1);";
		String[] columnNames = new String[] { "name" };
		String[] paramValues = new String[] { "trail-runner" };
		Collection<PetBean> colOfPetTO = ObjectFactory.getCurrentInstance().query(oql, columnNames, paramValues);
		log.info("+--- running query findPetsWithTrailRunningHobby...");
		for (PetBean petTO : colOfPetTO) {
			log.info(String.valueOf(petTO));
		}
		log.info("done.\n\n");
	}

}
