/**
 * Self.
 *
 * @return the prototype response. prototype response builder impl
 */
package com.archetype.app.query.api.vo;

import java.util.List;

import com.archetype.app.query.domain.PrototypeEntity;
import com.archetype.cqrsev.core.vo.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeResponse.
 */
@Data

/**
 * The Class PrototypeResponseBuilderImpl.
 */
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PrototypeResponse extends BaseResponse{
	
	/** The prototype entitys. */
	private List<PrototypeEntity> prototypeEntitys;
	
	/** The count. */
	private long count;
	
	/**
	 * Instantiates a new prototype response.
	 *
	 * @param message the message
	 */
	public PrototypeResponse(String message) {
		super(message);
	}

	
}
