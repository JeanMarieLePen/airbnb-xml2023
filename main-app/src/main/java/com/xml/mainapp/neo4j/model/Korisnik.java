package com.xml.mainapp.neo4j.model;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Korisnik {
	
	@Id
	private String id;
	@Relationship(type = "REZERVISE", direction = Relationship.Direction.OUTGOING)
	private List<Smestaj> rezervisani;
	@Relationship(type = "OCENJUJE")
	private List<OcenaSmestaj> dateOcene;
	
}
