package com.hibernate.hibernateutils;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.hibernate.Model.Eleves;
import com.hibernate.Model.Lacon;
import com.hibernate.Model.Moniteur;
import com.hibernate.Model.Voiteur;

public class hibernateUtils {

	private static final SessionFactory sessionFactory;
	private static final String database_Name = "autoEcole";
	private static final String USER = "root";
	private static final String password = "password";

	static {
		// data
		Properties prop = new Properties();
		prop.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/" + database_Name);
		prop.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
		prop.setProperty(Environment.USER, USER);
		prop.setProperty(Environment.PASS, password);
		prop.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		prop.setProperty(Environment.SHOW_SQL, "true");
		prop.setProperty(Environment.DEFAULT_SCHEMA, "dbproduct");
		prop.setProperty(Environment.USE_NEW_ID_GENERATOR_MAPPINGS, "false");
		prop.setProperty(Environment.HBM2DDL_AUTO, "create");

		Configuration configuration = new Configuration();
		configuration.setProperties(prop);
		// add classes
		configuration.addAnnotatedClass(Eleves.class);
		configuration.addAnnotatedClass(Lacon.class);
		configuration.addAnnotatedClass(Voiteur.class);
		configuration.addAnnotatedClass(Moniteur.class);

		sessionFactory = configuration.buildSessionFactory();

	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

}
