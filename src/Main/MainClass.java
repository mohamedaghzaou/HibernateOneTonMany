package Main;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.Session;

import com.hibernate.Model.Eleves;
import com.hibernate.Model.Lacon;
import com.hibernate.Model.Moniteur;
import com.hibernate.Model.Voiteur;

public class MainClass {
	
	public static void main(String[] args) {
		
		
		Eleves e = new Eleves(null , "mohamed", "aghzou", "cp", "tetouan");
		Voiteur v = new Voiteur(null, "model", "coloeur", "addr", new Date());
		Moniteur m = new Moniteur(null, "monituer", "prenom", new Date() );
		
		
		Session s = com.hibernate.hibernateutils.hibernateUtils.getSession();

		s.beginTransaction();
		
		s.save(e);
		s.save(v);
		s.save(m);
		e.setId(1);
		m.setId(1);
		v.setId(1);

		
		Lacon l = new Lacon(0, e, m , v , new Date() );
		s.save(l);

		
		s.getTransaction().commit();
		s.close();
		
		
	}

}
