package com.example.reservationservice.neo4j.model;
//package com.example.reservationservice.neo4j.model;
//
//import java.time.LocalDateTime;
//
//import org.neo4j.ogm.annotation.EndNode;
//import org.neo4j.ogm.annotation.RelationshipEntity;
//import org.neo4j.ogm.annotation.StartNode;
//import org.springframework.data.neo4j.core.schema.GeneratedValue;
//import org.springframework.data.neo4j.core.schema.Id;
//import org.springframework.data.neo4j.core.schema.Property;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@RelationshipEntity(type = "REZERVISE")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class RezervacijaRelationship {
//
//	@Id
//	@GeneratedValue
//	private Long id;
//	
//	@EndNode
//	@Property(name = "smestaj")
//	private Smestaj smestaj;
//	
//	@StartNode
//	@Property(name = "korisnik")
//	private Korisnik korisnik;
//	
//	@Property(name = "startDate")
//	private LocalDateTime startDate;
//	@Property(name = "endDate")
//	private LocalDateTime endDate;
//
//}
