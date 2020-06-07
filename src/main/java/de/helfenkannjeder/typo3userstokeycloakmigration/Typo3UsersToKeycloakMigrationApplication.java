package de.helfenkannjeder.typo3userstokeycloakmigration;

import de.helfenkannjeder.typo3userstokeycloakmigration.typo3.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Typo3UsersToKeycloakMigrationApplication implements ApplicationRunner {
	final Migration migration;

	@Autowired
	public Typo3UsersToKeycloakMigrationApplication(Migration migration) {
		this.migration = migration;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Typo3UsersToKeycloakMigrationApplication.class, args);
		SpringApplication.exit(context);
	}

	@Override
	public void run(ApplicationArguments args) {
		migration.doMigration();
	}
}
