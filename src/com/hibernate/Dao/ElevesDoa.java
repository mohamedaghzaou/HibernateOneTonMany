package com.hibernate.Dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.hibernate.Model.Eleves;
import com.hibernate.hibernateutils.hibernateUtils;

public class ElevesDoa implements IDAO<Eleves, Integer> {

	private static ElevesDoa elevesDoa = null;
	private Logger logger = Logger.getLogger(getClass());

	public static ElevesDoa getVoiteurDoa() {

		if (elevesDoa == null) {
			elevesDoa = new ElevesDoa();
		}
		return elevesDoa;
	}

	@Override
	public Eleves findById(Integer id) {
		Eleves e = null;

		logger.info("retrieving student started, Car id " + id);

		try (Session s = hibernateUtils.getSession()) {
			s.beginTransaction();
			e = s.get(Eleves.class, id);
			s.getTransaction().commit();
			logger.info("student retrievied successfully, student id " + id);

		} catch (Exception ee) {
			logger.error("error occured while retrieving student id : " + id + ee.getMessage());

		}

		return e;
	}

	@Override
	public List<Eleves> findAll() {
		List<Eleves> eleves = null;
		logger.info("retrieving all students operation started");

		try (Session s = hibernateUtils.getSession()) {
			s.beginTransaction();
			eleves = s.createQuery("from Eleves", Eleves.class).list();
			s.getTransaction().commit();
			logger.info("all students retrievied successfully, number of students retrieved : " + eleves.size());

		} catch (Exception e) {
			logger.error("error occured while retrieving  students" + e.getMessage());

		}
		return eleves;
	}

	@Override
	public Eleves update(Eleves obj) {
		if (obj == null) {
			throw new RuntimeException("Object is null");
		}

		if (obj.getId() == null || obj.getId().intValue() == 0) {

			throw new RuntimeException("student should have an id");
		}
		logger.info("updating  student operation started, student id : " + obj.getId());

		Session s = hibernateUtils.getSession();
		try {
			s.beginTransaction();
			s.update(obj);
			s.getTransaction().commit();
			logger.info("student successfully updated, student id :" + obj.getId());

		} catch (Exception e) {
			s.getTransaction().rollback();
			logger.error("error occured while updating a student : " + e.getMessage());

		} finally {
			s.close();
		}
		return obj;
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean isDeleted = false;
		logger.info("deleting student operation started");
		Session s = hibernateUtils.getSession();

		try {
			s.beginTransaction();
			Eleves v = new Eleves();
			v.setId(id);
			s.delete(v);
			s.getTransaction().commit();
			logger.info("student successfully deleted, student id :" + id);
			isDeleted = true;
		} catch (Exception e) {
			s.getTransaction().rollback();

			logger.error("error occured while deleting a student :" + e.getMessage());

		} finally {
			s.close();
		}

		return isDeleted;
	}

	@Override
	public Eleves save(Eleves obj) {
		logger.info("adding student operation started");

		Session s = hibernateUtils.getSession();
		try {
			s.beginTransaction();
			s.save(obj);
			s.getTransaction().commit();
			logger.info("student successfully added, student id :" + obj.getId());

		} catch (Exception e) {
			s.getTransaction().rollback();
			logger.error("error occured while adding student :" + e.getMessage());

		} finally {
			s.close();
		}
		return obj;
	}

}
