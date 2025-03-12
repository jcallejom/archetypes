package com.archetype.app.helpers;

import com.archetype.app.infrastructure.out.db.jpa.entity.PrototypeEntity;

public class MockTable {
	public static PrototypeEntity dummyExampleTable() {
		PrototypeEntity table = new PrototypeEntity();
		
		table.setId("1L");
		table.setCampo("test1");
		
		return table;
	}
}
