package com.archetype.app.common.vo;

// TODO: Auto-generated Javadoc
/**
 * The Enum PrototypeType.
 */
public enum PrototypeType {
	
	/** The n. */
	N("NORMAL"),
/** The e. */
E("EXPECIAL");
	
	/** The name. */
	private final String name;
	
	/**
	 * Instantiates a new prototype type.
	 *
	 * @param name the name
	 */
	PrototypeType(String name) {
		this.name=name;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	
}
