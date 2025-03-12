package com.archetype.app.infrastructure.out.db.jpa.repository;

import org.springframework.stereotype.Repository;

import com.archetype.app.infrastructure.out.db.jpa.entity.PrototypeEntity;
import com.archetype.base.core.repository.BaseRepository;
@Repository
public interface IPrototypeEntityRepository extends BaseRepository<PrototypeEntity, String> {

}
