package com.rtd.project;

import com.rtd.project.view.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		Login login = new Login();
		login.showLogin();

		SpringApplication.run(ProjectApplication.class, args);
	}
}
