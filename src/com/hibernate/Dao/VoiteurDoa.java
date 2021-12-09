package com.hibernate.Dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.hibernate.Model.Lacon;
import com.hibernate.Model.Voiteur;
import com.hibernate.hibernateutils.hibernateUtils;

public class VoiteurDoa implements IDAO<Voiteur, Integer> {

	private static VoiteurDoa voiteurDoa = null;
	private Logger logger = Logger.getLogger(getClass());

	public static VoiteurDoa getVoiteurDoa() {

		if (voiteurDoa == null) {
			voiteurDoa = new VoiteurDoa();
		}

		return voiteurDoa;
	}

	@Override
	public Voiteur findById(Integer id) {
		Voiteur v = null;

		logger.info("retrieving Car started, Car id " + id);

		try (Session s = hibernateUtils.getSession()) {
			s.beginTransaction();
			v = s.get(Voiteur.class, id);
			s.getTransaction().commit();
			logger.info("retrievied successfully, Car id " + id);

		} catch (Exception e) {
			logger.error("error occured while retrieving Car id : " + id + e.getMessage());

		}

		return v;
	}

	@Override
	public List<Voiteur> findAll() {

		List<Voiteur> voiteurs = null;
		logger.info("retrieving all Cars operation started");

		try (Session s = hibernateUtils.getSession()) {
			s.beginTransaction();
			voiteurs = s.createQuery("from Voiteur", Voiteur.class).list();
			s.getTransaction().commit();
			logger.info("all Cars retrievied successfully, number of cars retrieved : " + voiteurs.size());

		} catch (Exception e) {
			logger.error("error occured while retrieving  Cars" + e.getMessage());

		}
		return voiteurs;

	}

	@Override
	public Voiteur update(Voiteur obj) {

		if (obj.getId() == null || obj.getId().intValue() == 0) {

			throw new RuntimeException("Car should have an id");
		}
		logger.info("updating  Car operation started, Car id : " + obj.getId());

		Session s = hibernateUtils.getSession();
		try {
			s.beginTransaction();
			s.update(obj);
			s.getTransaction().commit();
			logger.info("Car successfully updated, Car id :" + obj.getId());

		} catch (Exception e) {
			s.getTransaction().rollback();
			logger.error("error occured while updating a Car : " + e.getMessage());

		} finally {
			s.close();
		}
		return obj;
	}

	@Override
	public boolean deleteById(Integer id) {

		boolean isDeleted = false;
		logger.info("deleting Car operation started");
		Session s = hibernateUtils.getSession();

		try {
			s.beginTransaction();
			Voiteur v = new Voiteur();
			v.setId(id);
			s.delete(v);
			s.getTransaction().commit();
			logger.info("Car successfully deleted, Car id :" + id);
			isDeleted = true;
		} catch (Exception e) {
			s.getTransaction().rollback();

			logger.error("error occured while deleting a Car :" + e.getMessage());

		} finally {
			s.close();
		}

		return isDeleted;
	}

	@Override
	public Voiteur save(Voiteur obj) {
		logger.info("adding Car operation started");

		Session s = hibernateUtils.getSession();
		try {
			s.beginTransaction();
			s.save(obj);
			s.getTransaction().commit();
			logger.info("Car successfully added, Car id :" + obj.getId());

		} catch (Exception e) {
			s.getTransaction().rollback();
			logger.error("error occured while adding Car :" + e.getMessage());

		} finally {
			s.close();
		}
		return obj;
	}

	public List<Lacon> findByIdLacons(Integer id) {
		List<Lacon> lacons = null;
		logger.info("retrieve courses of a specific  Car, Car id " + id);

		try (Session s = hibernateUtils.getSession()) {
			s.beginTransaction();
			lacons = s.createQuery("from Lacon a where a.eleve.id=:id", Lacon.class).setParameter("id", id).list();
			s.getTransaction().commit();
			logger.info(
					"courses successfully retrieved, Car id " + id + "number of courses retrieved : " + lacons.size());

		} catch (Exception e) {
			logger.error("error occured while retrieving courses , message : " + e.getMessage());

		}
		return lacons;
	}
}
