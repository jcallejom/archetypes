package com.archetype.app.cmd.api.vo;

import com.archetype.cqrsev.core.vo.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeResponse.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrototypeResponse extends BaseResponse{
	
	/** The id. */
	private String id;

	/**
	 * Instantiates a new prototype response.
	 *
	 * @param messagge the messagge
	 * @param id the id
	 */
	public PrototypeResponse(String messagge,String id) {
		super(messagge);
		this.id = id;
	}

}
