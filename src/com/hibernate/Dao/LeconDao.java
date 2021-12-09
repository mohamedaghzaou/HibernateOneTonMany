package com.hibernate.Dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.hibernate.Model.Eleves;
import com.hibernate.Model.Lacon;
import com.hibernate.hibernateutils.hibernateUtils;

public class LeconDao implements IDAO<Lacon, Integer> {

	private static LeconDao leconDao = null;
	private Logger logger = Logger.getLogger(getClass());

	public static LeconDao getVoiteurDoa() {

		if (leconDao == null) {
			leconDao = new LeconDao();
		}
		return leconDao;
	}

	@Override
	public Lacon findById(Integer id) {
		Lacon l = null;

		logger.info("retrieving course started, course id " + id);

		try (Session s = hibernateUtils.getSession()) {
			s.beginTransaction();
			l = s.get(Lacon.class, id);
			s.getTransaction().commit();
			logger.info("retrievied successfully, course id " + id);

		} catch (Exception e) {
			logger.error("error occured while retrieving course id : " + id + e.getMessage());
		}

		return l;
	}

	@Override
	public List<Lacon> findAll() {
		List<Lacon> Lecon = null;
		logger.info("retrieving all students operation started");

		try (Session s = hibernateUtils.getSession()) {
			s.beginTransaction();
			Lecon = s.createQuery("from Lacon", Lacon.class).list();
			s.getTransaction().commit();
			logger.info("all courses retrievied successfully, number of courses retrieved : " + Lecon.size());

		} catch (Exception e) {
			logger.error("error occured while retrieving  courses" + e.getMessage());

		}
		return Lecon;
	}

	@Override
	public Lacon update(Lacon obj) {
		if (obj == null) {
			throw new RuntimeException("Object is null");
		}

		if (obj.getId() == null || obj.getId().intValue() == 0) {

			throw new RuntimeException("course should have an id");
		}
		logger.info("updating course operation started, course id : " + obj.getId());

		Session s = hibernateUtils.getSession();
		try {
			s.beginTransaction();
			s.update(obj);
			s.getTransaction().commit();
			logger.info("course successfully updated, course id :" + obj.getId());

		} catch (Exception e) {
			s.getTransaction().rollback();
			logger.error("error occured while updating a course : " + e.getMessage());

		} finally {
			s.close();
		}
		return obj;
		
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean isDeleted = false;
		logger.info("deleting course operation started");
		Session s = hibernateUtils.getSession();

		try {
			s.beginTransaction();
			Lacon v = new Lacon();
			v.setId(id);
			s.delete(v);
			s.getTransaction().commit();
			logger.info("course successfully deleted, student id :" + id);
			isDeleted = true;
		} catch (Exception e) {
			s.getTransaction().rollback();

			logger.error("error occured while deleting a course :" + e.getMessage());

		} finally {
			s.close();
		}

		return isDeleted;
	}

	@Override
	public Lacon save(Lacon obj) {
		logger.info("adding course operation started");

		Session s = hibernateUtils.getSession();
		try {
			s.beginTransaction();
			s.save(obj);
			s.getTransaction().commit();
			logger.info("course successfully added, course id :" + obj.getId());

		} catch (Exception e) {
			s.getTransaction().rollback();
			logger.error("error occured while adding course :" + e.getMessage());

		} finally {
			s.close();
		}
		return obj;
	}

}
