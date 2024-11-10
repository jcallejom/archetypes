package com.archetype.app.cmd.api.command;

import java.util.Collections;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.archetype.app.common.vo.PrototypeNumberVo;
import com.archetype.cqrsev.core.commands.BaseCommand;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenPrototypeCommand.
 */
//@JsonIgnoreProperties("id")
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(Include.NON_EMPTY)
@Validated
@EqualsAndHashCode(callSuper=false)
@Getter
@AllArgsConstructor
@Jacksonized//permite la serializacion/des correcta de clases inmutables
@SuperBuilder
@Schema(name = "OpenPrototypeCommand", description = "command represent a save prototype on eventStore")
public  final class OpenPrototypeCommand extends BaseCommand{
    
	
    /** The prototype code. */
    @JsonProperty("prototypecode")//nos permite poner alias a objeto
    @Schema(name = "prototypecode", requiredMode = Schema.RequiredMode.REQUIRED,example = "01abct", defaultValue = "01abct", description = "Unique id of prototype that represent the owner of prototype")
   	@NotBlank
	@NotNull(message = "prototypecode cannot be null")
	private  final String prototypeCode;
    

    /** The prototype day. */
 	@JsonProperty("prototypeday")
 	@Schema(name = "prototypeday", requiredMode = Schema.RequiredMode.REQUIRED,example = "25/10/2013", defaultValue = "25/10/2013", description = "lootday date")
    @NotBlank
	@NotNull(message = "prototypeday cannot be null")
	private  final String prototypeDay;
	
    /** The prototype numbers. */	
	@Valid
	@JsonProperty("prototypenumbers")
    @Schema(name = "prototypenumbers", requiredMode = Schema.RequiredMode.REQUIRED, description = "list of prototypeNumbers")
	@NotEmpty (message = "prototypeNumbers cannot be empy")
	private final  List<PrototypeNumberVo> prototypeNumbers;

	/**
	 * Gets the prototype numbers.
	 *
	 * @return the prototype numbers
	 */
	public List<PrototypeNumberVo> getPrototypeNumbers() {
		//return immutable list
		return Collections.unmodifiableList(this.prototypeNumbers);
	}


//	@JsonCreator
//	public OpenPrototypeCommand( String prototypeCode,
//			String prototypeDay,
//			@Valid @NotEmpty(message = "prototypeNumbers cannot be empy") List<PrototypeNumberVo> prototypeNumbers) {
//
//		this.prototypeCode = prototypeCode;
//		this.prototypeDay = prototypeDay;
//		this.prototypeNumbers = prototypeNumbers;
//	}
}
