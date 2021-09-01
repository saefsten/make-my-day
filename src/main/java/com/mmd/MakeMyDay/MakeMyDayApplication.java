package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MakeMyDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakeMyDayApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner demo(RoleRepository roleRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			if (roleRepository.count() == 0) {
				Role roleAdmin =  new Role("ADMIN");
				Role roleUser =  new Role("USER");
				roleRepository.save(roleAdmin);
				roleRepository.save(roleUser);
			}

			if (categoryRepository.count() < 2) {
				Category category2 = new Category("CULTURE");
				categoryRepository.save(category2);
				Category category3 = new Category("CUISINE");
				categoryRepository.save(category3);
				Category category4 = new Category("MUSIC");
				categoryRepository.save(category4);
				Category category5 = new Category("HISTORY");
				categoryRepository.save(category5);
				Category category6 = new Category("NIGHTLIFE");
				categoryRepository.save(category6);
				Category category7 = new Category("SHOPPING");
				categoryRepository.save(category7);
				Category category8 = new Category("RELAXATION");
				categoryRepository.save(category8);
				Category category9 = new Category("ADVENTURE");
				categoryRepository.save(category9);
				Category category10 = new Category("FAMILY");
				categoryRepository.save(category10);
			}
		};
	}*/
}
