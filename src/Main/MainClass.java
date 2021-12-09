package Main;

import java.util.Date;

import com.hibernate.Dao.IDAO;
import com.hibernate.Dao.VoiteurDoa;
import com.hibernate.Model.Voiteur;

public class MainClass {

	public static void main(String[] args) {

		Voiteur v = new Voiteur(null, "model", "mohamed", "addr", new Date());
		IDAO<Voiteur, Integer> s = VoiteurDoa.getVoiteurDoa();
		System.out.println(VoiteurDoa.getVoiteurDoa().save(v));
		

		

	}

}
