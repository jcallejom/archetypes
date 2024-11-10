package com.archetype.app.shared;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import com.archetype.base.core.exception.model.BaseError;
import com.archetype.base.core.utils.MessageSourceHelper;

public enum PrototypeErrors implements BaseError {
	EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND("F-401", HttpStatus.NOT_FOUND);


	private final String code;
	private final Integer httpCode;

	PrototypeErrors(final String code, final HttpStatus httpCode) {
		this.code = code;
		this.httpCode = httpCode.value();
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return MessageSourceHelper.getMessage(this.name(), null, LocaleContextHolder.getLocale());
	}

	public Integer getHttpCode() {
		return this.httpCode;
	}
}
