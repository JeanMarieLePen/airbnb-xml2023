package com.xml.mainapp.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.xml.mainapp.model.data.Termin;

@Service
public class TerminService {
	
	/**
	 * @param zaSkracivanje
	 * @param drugi
	 * @return Razlika: A\B
	 */
	public ArrayList<Termin> razlika(Termin A, Termin B) {
		ArrayList<Termin> ostatak = new ArrayList<Termin>();
		if (A.unutarTermina(B)) {
			ostatak.add(new Termin(A.getPocetak(), B.getPocetak()));
			ostatak.add(new Termin(B.getKraj(), A.getKraj()));
		}
		else if (B.pripadaTerminu(A.getPocetak()))
			ostatak.add(new Termin(B.getKraj(), A.getKraj()));
		else if (B.pripadaTerminu(A.getKraj()))
			ostatak.add(new Termin(A.getPocetak(), B.getPocetak()));
		
		if (!ostatak.isEmpty())
			return ostatak;
		else {
			ArrayList<Termin> a = new ArrayList<Termin>();
			a.add(A);
			return a;
		}
		
	}
	public ArrayList<Termin> razlika( Termin A, List<Termin> B) {
		ArrayList<Termin> r = new ArrayList<Termin>();
		for (Termin t : B) {
			if (!A.preklapanjeSa(t)) // ne preklapaju se
				continue;
			r.addAll(razlika(t, B));
		}
		return r;
	}
	public ArrayList<Termin> razlika(List<Termin> A, Termin B) {
		ArrayList<Termin> r = new ArrayList<Termin>();
		// List<Termin>
		// filt=A.stream().filter(x->x.preklapanjeSa(B)).collect(Collectors.toList());
		for (Termin t : A) {
			if (!t.preklapanjeSa(B)) // ne preklapaju se
				continue;
			r.addAll(razlika(t, B));
		}
		return r;
	}

	public ArrayList<Termin> razlika(List<Termin> A, List<Termin> B) {
		ArrayList<Termin> razlika = new ArrayList<Termin>();
		for (Termin b : B) {
			razlika.addAll(razlika(A, b));
		}
		return razlika;
	}

	public ArrayList<Termin> slobodniTerminiRazlikaZauzeti(List<Termin> slobodni, List<Termin> zauzeti) {
		return razlika(slobodni, zauzeti);
	}

	/**
	 * @param slobodniTermini
	 * @ spaja termine koji se nadovezuju
	 * @return
	 */
	public List<Termin> nadovezi(List<Termin> slobodniTermini) {
		ArrayList<Termin> sortirani=(ArrayList<Termin>) slobodniTermini.stream().sorted(Comparator.comparing(Termin::getPocetak)).collect(Collectors.toList());
				ArrayList<Termin> nova=new ArrayList<Termin>();
		//soritrani u rastucem redosledu
		int k=sortirani.size()-1;
		for(int i=k;  i>0; i--) {
			if(prviPaDrugi(sortirani.get(i-1),sortirani.get(i))) { //ako se zadnja dva nadovezuju, ukloniti i zameniti spojenim
				Termin temp= new Termin(sortirani.get(i-1).getPocetak(),sortirani.get(i).getKraj());
				sortirani.remove(i);
				sortirani.remove(i-1);
				sortirani.add(temp);
			}
		}return sortirani;
		
	}

	private Termin spoji(Termin t1, Termin t2) {
		if(t1.nadovezujuSe(t1)) {
			if(t1.getPocetak().isEqual(t2.getKraj()))
				return new Termin(t2.getPocetak(),t1.getKraj());
			else //if(t1.getKraj().isEqual(t2.getPocetak()))
				return new Termin(t1.getPocetak(),t2.getKraj());	
		}else return null;
	}
	public boolean prviPaDrugi(Termin t1, Termin t2){
		return t1.getKraj().equals(t2.getPocetak());
	}
	public Termin preklapanje(Termin A, Termin B) {
		Termin ostatak = null;
		if (A.unutarTermina(B)) {
			ostatak=new Termin(B.getPocetak(), B.getKraj());
		}
		if(B.unutarTermina(A)) {
			ostatak=new Termin(A.getPocetak(), A.getKraj());
		}
		else if (B.pripadaTerminu(A.getPocetak()))
			ostatak=new Termin(A.getPocetak(), B.getKraj());
		else if (B.pripadaTerminu(A.getKraj()))
			ostatak=new Termin(B.getPocetak(), A.getKraj());
		return ostatak;
	}
	public boolean isPreklapanje(Termin A, Termin B) {
		if (A.unutarTermina(B) || B.unutarTermina(A)) {
			return true;
		}
		else if (B.pripadaTerminu(A.getPocetak()))
			return true;
		else if (B.pripadaTerminu(A.getKraj()))
			return true;
		return false;
	}
}
