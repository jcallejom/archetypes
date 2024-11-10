package com.archetype.app.query.domain;

import java.time.LocalDate;
import java.util.List;

import com.archetype.cqrsev.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeEntity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * The Class PrototypeEntityBuilder.
 */
@Builder
@Entity
public class PrototypeEntity extends BaseEntity{
	
	/** The id. */
	@Id
	private  String id;
	
	/** The prototype code. */
	@Column(nullable = false, unique = true)
	private  String prototypeCode;
  	
	  /** The prototype day. */
	private  LocalDate prototypeDay;
	

	/** The prototype number vo. */
	@JsonIgnore
	@OneToMany(mappedBy = "prototypeEntity",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private  List<PrototypeNumberEntity> prototypeNumbers;
}

