Note: See latest updates at http://www.ohioedge.com

# Compile included Pet and Pet data initializer beans
javac -cp .:opac-cli-all.jar *.java pojo/*.java data/*.java

# Edit ./opac-config.xml for your C* config:
opac-config-xml

# Run opac-cli
java -cp .:opac-cli-all.jar Console ./opac-config.xml

# Run the following command at the command prompt to initialize schema:
> initialize schema;

# Run the following command to initialize data in Pet, Hobby and PetHobby tables
> initialize data;
 
# Check if all is okay by running the following query at the command prompt >
> return Pet.findAll();

