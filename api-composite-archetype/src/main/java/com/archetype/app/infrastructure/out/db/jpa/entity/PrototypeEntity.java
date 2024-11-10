package com.archetype.app.infrastructure.out.db.jpa.entity;

import com.archetype.base.core.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "prototype", schema = "public")
//public class PrototypeEntity extends VersionEntity{
public class PrototypeEntity implements BaseEntity{	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name = "column", nullable = true, unique = false)
	String column;
}
