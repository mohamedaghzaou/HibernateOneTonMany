package com.hibernate.Dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.hibernate.Model.Moniteur;
import com.hibernate.hibernateutils.hibernateUtils;

public class MoniteurDoa implements IDAO<Moniteur, Integer> {

	private static MoniteurDoa moniteurDoa = null;
	private Logger logger = Logger.getLogger(getClass());

	public static MoniteurDoa getVoiteurDoa() {

		if (moniteurDoa == null) {
			moniteurDoa = new MoniteurDoa();
		}
		return moniteurDoa;
	}

	@Override
	public Moniteur findById(Integer id) {
		Moniteur m = null;

		logger.info("retrieving Monitor started, Monitor id " + id);

		try (Session s = hibernateUtils.getSession()) {
			s.beginTransaction();
			m = s.get(Moniteur.class, id);
			s.getTransaction().commit();
			logger.info("retrievied successfully, Monitor id " + id);

		} catch (Exception e) {
			logger.error("error occured while retrieving Monitor id : " + id + e.getMessage());
		}

		return m;
	}

	@Override
	public List<Moniteur> findAll() {
		List<Moniteur> Monitors = null;
		logger.info("retrieving all Monitors operation started");

		try (Session s = hibernateUtils.getSession()) {
			s.beginTransaction();
			Monitors = s.createQuery("from Moniteur", Moniteur.class).list();
			s.getTransaction().commit();
			logger.info("all Monitors retrievied successfully, number of cars retrieved : " + Monitors.size());

		} catch (Exception e) {
			logger.error("error occured while Monitors  Cars" + e.getMessage());

		}
		return Monitors;
	}

	@Override
	public Moniteur update(Moniteur obj) {

		if (obj == null) {
			throw new RuntimeException("Object is null");
		}

		if (obj.getId() == null || obj.getId().intValue() == 0) {

			throw new RuntimeException("Monitor should have an id");
		}
		logger.info("updating  Monitor operation started, Monitor id : " + obj.getId());

		Session s = hibernateUtils.getSession();
		try {
			s.beginTransaction();
			s.update(obj);
			s.getTransaction().commit();
			logger.info("Monitor successfully updated, Monitor id :" + obj.getId());

		} catch (Exception e) {
			s.getTransaction().rollback();
			logger.error("error occured while updating a Monitor : " + e.getMessage());

		} finally {
			s.close();
		}
		return obj;
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean isDeleted = false;
		logger.info("deleting Monitor operation started");
		Session s = hibernateUtils.getSession();

		try {
			s.beginTransaction();
			Moniteur v = new Moniteur();
			v.setId(id);
			s.delete(v);
			s.getTransaction().commit();
			logger.info("Monitor successfully deleted, Car id :" + id);
			isDeleted = true;
		} catch (Exception e) {
			s.getTransaction().rollback();

			logger.error("error occured while deleting a Monitor :" + e.getMessage());

		} finally {
			s.close();
		}

		return isDeleted;
	}

	@Override
	public Moniteur save(Moniteur obj) {
		logger.info("adding Moniteur operation started");

		Session s = hibernateUtils.getSession();
		try {
			s.beginTransaction();
			s.save(obj);
			s.getTransaction().commit();
			logger.info("Moniteur successfully added, Car id :" + obj.getId());

		} catch (Exception e) {
			s.getTransaction().rollback();
			logger.error("error occured while adding Moniteur :" + e.getMessage());

		} finally {
			s.close();
		}
		return obj;
	}

}
