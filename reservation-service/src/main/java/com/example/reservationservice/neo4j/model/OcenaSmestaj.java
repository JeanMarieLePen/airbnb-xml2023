package com.example.reservationservice.neo4j.model;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@RelationshipProperties
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OcenaSmestaj{
	@Id
	@GeneratedValue
	private Long id;
	private int ocena;
	private LocalDateTime datumIVreme;
	@TargetNode
	private Smestaj smestaj;
}
