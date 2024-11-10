package com.archetype.app.query.domain;

import com.archetype.app.common.vo.PrototypeType;
import com.archetype.cqrsev.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// TODO: Auto-generated Javadoc

/**
 * The Class PrototypeNumberEntity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * The Class PrototypeNumberEntityBuilder.
 */

/**
 * The Class PrototypeNumberEntityBuilder.
 */
@Builder
@Entity
public class PrototypeNumberEntity extends BaseEntity{
	
	
//	@Id
//	@SequenceGenerator(name = "PrototypeNumber", sequenceName = "sq_PrototypeNumber",allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PrototypeNumber")
//	private  Long id;
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private  String id;
	/** The value. */
	private  Integer value;
	
	/** The type. */
	@Enumerated(EnumType.STRING)
	private  PrototypeType type;
	
	/** The prototype entity. */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private PrototypeEntity prototypeEntity;
}
