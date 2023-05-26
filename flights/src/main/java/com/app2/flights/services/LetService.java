package com.app2.flights.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.bson.Document;
import org.hibernate.criterion.Projection;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.SUM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.app2.flights.dtos.LetDTO;
import com.app2.flights.dtos.LetDTO2;
import com.app2.flights.dtos.LetDTOSimple;
import com.app2.flights.dtos.PretragaDTO;
import com.app2.flights.mappers.AdresaMapper;
import com.app2.flights.mappers.LetMapper;
import com.app2.flights.model.data.Adresa;
import com.app2.flights.model.data.Let;
import com.app2.flights.model.data.Porudzbina;
import com.app2.flights.model.data.StatusPorudzbine;
import com.app2.flights.model.user.Korisnik;
import com.app2.flights.repositories.LetRep;
import com.app2.flights.repositories.PorudzbinaRep;

@Service
public class LetService {

	@Autowired
	private LetRep letRep;
	@Autowired
	private LetMapper letMapper;
	@Autowired
	private AdresaMapper adresaMapper;
	@Autowired 
	private PorudzbinaRep pRep;

	@Autowired 
	private MongoTemplate monTempl;

	public LetDTO addNew(LetDTO letDTO) {
		this.letRep.save(new Let(letDTO));
		return letDTO;
	}

	public LetDTOSimple getLetById(String id) {
		Let l = letRep.findById(id).orElse(null);
		if(l==null) {
			return null;
		}else {
			return letMapper.toDTOSimple(l);
		}
	}

	public LetDTO editLet(LetDTO ldto) {
		Let l = letRep.findById(ldto.getId()).orElse(null);
		if(l == null) {
			return null;
		}else {
			l.setBrojLeta(ldto.getBrojLeta());
			l.setCena(ldto.getCena());
			l.setDatumIVreme(ldto.getDatumIVreme());
			l.setKapacitet(ldto.getKapacitet());
			l.setLokOd(new Adresa(ldto.getLokOd()));
			l.setLokDo(new Adresa(ldto.getLokDo()));
			letRep.save(l);
			return letMapper.toDTO(l);
		}
	}

	public LetDTO removeLet(String id) {
		System.out.println("BRISANJE LETA SA ID : "+id);
		Let l = letRep.findById(id).orElse(null);
		if(l == null) {
			System.out.println("LET NIJE PRONADJEN");
			return null;
		}else {
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(now, l.getDatumIVreme());
			long days = duration.toDays();
			if(!pRep.findAll().stream().filter(p -> p.getStatus().equals(StatusPorudzbine.REZERVISANA)).findFirst().isPresent() && days > 3+1){
				letRep.deleteById(id);
				return letMapper.toDTO(l);
			}else {
				return null;
			}
		}
	}

	public List<LetDTOSimple> findAllLetovi()
	{
		List<Let> letovi = letRep.findAll();

		return letovi.stream()
				.map(letMapper::toDTOSimple)
				.collect(Collectors.toList());
	}

	public int brojSlobodnihMesta(String id) {

		Let l= letRep.findById(id).orElse(null);
		if (l==null) return -1;
		int kapacitet=l.getKapacitet();
		int slobMesta=0;
		int zauzeto=0;

		List<Porudzbina> allRez=pRep.findAllByLet(id);
		for(Porudzbina por : allRez) {
			if(por.getStatus().equals(StatusPorudzbine.REZERVISANA))
				zauzeto+=por.getBrojKarata();
		}

		slobMesta=kapacitet-zauzeto;
		System.out.println("Kap - Zauz = brSlob  : "+kapacitet+" - "+zauzeto+" = "+slobMesta);
		return slobMesta;
	}

	@PersistenceContext private EntityManager entityManager;
	/**
	 * @param dto
	 * @return
	 */
	public List<LetDTOSimple> pretraga(PretragaDTO dto) {
		Query query= new Query();
		/***VREME***/
		if(dto.getPocetak()!=null && dto.getKraj()!=null) {
			Criteria preIposle=Criteria.where("datumIVreme").gte(dto.getPocetak()).lte(dto.getKraj());
			query.addCriteria(preIposle);
		}
		if(dto.getPocetak()!=null && dto.getKraj()==null) {//pocetakTermin
			Criteria posle=Criteria.where("datumIVreme").gte(dto.getPocetak());
			query.addCriteria(posle);
		}
		if(dto.getPocetak()==null && dto.getKraj()!=null) {//krajTermin
			Criteria pre=Criteria.where("datumIVreme").lte(dto.getKraj());
			query.addCriteria(pre);
		}
		/***CENA***/
		if(dto.getMinCena()>-1 && dto.getMaxCena()>-1) {
			Criteria skupljeIJeftinije=Criteria.where("cena").gte(dto.getMinCena()).lte(dto.getMaxCena());
			query.addCriteria(skupljeIJeftinije);
		}
		if(dto.getMinCena()>-1 && dto.getMaxCena()==-1)  {
			Criteria skupljeOd=Criteria.where("cena").gte(dto.getMinCena());
			query.addCriteria(skupljeOd);
		}
		if(dto.getMinCena()==-1 && dto.getMaxCena()>-1) {
			Criteria jeftinijeOd=Criteria.where("cena").lte(dto.getMaxCena());
			query.addCriteria(jeftinijeOd);
		}
		/***LOKACIJE***/
		if(dto.getPocetnaLok()!=null && !dto.getPocetnaLok().getAdresa().trim().equals("")) {
			Criteria kreceIz= Criteria.where("lokOd.adresa").regex(dto.getPocetnaLok().getAdresa().toLowerCase());
			query.addCriteria(kreceIz);
		}
		if(dto.getKrajnjaLok()!=null && !dto.getKrajnjaLok().getAdresa().trim().equals("")) {
			Criteria sleceU= Criteria.where("lokDo.adresa").regex(dto.getKrajnjaLok().getAdresa().toLowerCase());
			query.addCriteria(sleceU);
		}
		List<Let> letovi= monTempl.find(query,Let.class);
		if(dto.getBrKarata()>-1) { //vezati porudzbine za letove
			letovi=letovi.stream().filter(x-> x.getKapacitet()- brZauzetihMesta(x)>=dto.getBrKarata()).collect(Collectors.toList());
		}
		List<Document> l=pretragaT(1);
		return  letovi.stream().map(x->letMapper.toDTOSimple(x)).collect(Collectors.toList());
	}

	public int brZauzetihMesta(Let l) {
		int retVal = 0;
		List<Porudzbina> lista = new ArrayList<Porudzbina>();
		retVal = pRep.findAll().stream().filter(p -> p.getLet().equals(l.getId())).map(x -> x.getBrojKarata()).collect(Collectors.summingInt(Integer::intValue));
		return retVal;
	}
	public List<Document> pretragaT(int brK) {
		//https://stackoverflow.com/questions/56376939/spring-data-mongo-get-sum-of-array-of-object
		AggregationOperation  filterRez=Aggregation.match(Criteria.where("listaPorudzbina.status").is(StatusPorudzbine.REZERVISANA.toString()));
		AggregationOperation  unwindPor=Aggregation.unwind("listaPorudzbina");
		AggregationOperation  sumaKarata= Aggregation.group().sum("listaPorudzbina.brojKarata").as("brojKarata");
		ProjectionOperation projectionOperation = Aggregation.project().andExclude("listaPorudzbina");
		Aggregation ag=  Aggregation.newAggregation(unwindPor,projectionOperation,sumaKarata);
		monTempl.aggregate(ag, Let.class, Document.class).forEach(doc->System.out.println(doc.toJson()));
		//System.out.println("DUZINA DTO2 : " + list.size());
		return null;

	}
}