/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.model.response;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseResponseVo.
 *
 * @param <T> the generic type
 */
@Setter
@Getter
//@JsonIgnoreProperties("version")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public abstract class BaseResponseVo<T extends Serializable> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final transient long serialVersionUID = 2463764888580633674L;
	
	/** The entity class. */
	@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
	private Class<T> entityClass;
//	private String menssage;
//	private StandarizedApiExceptionResponse responseInfo;


	/**
 * Instantiates a new base response vo.
 */
public BaseResponseVo() {
		super();
	}
	
	/**
	 * Instantiates a new base response vo.
	 *
	 * @param data the data
	 */
	@JsonCreator
//	@SuppressWarnings("unchecked")
	public BaseResponseVo(final T data) {
		this();
		this.entityClass = (Class<T>) data.getClass();
//		this.entityClass = (Class<T>) ((data != null) ? data.getClass() : null);
		//this.data = data;
	}

}
