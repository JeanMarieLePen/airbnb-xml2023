package com.xml2023.mainapp.neo4j.model;

import java.util.List;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Smestaj{
	@Id
	private String id;
	@Property(name = "vlasnik_id")
	private String vlasnik;
//	@Relationship(type = "OCENJUJE", direction = Relationship.Direction.INCOMING)
//	private List<OcenaSmestaj> dobijeneOcene;
}
