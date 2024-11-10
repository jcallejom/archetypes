package com.archetype.app.cmd.api.command;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.archetype.app.common.vo.PrototypeNumberVo;
import com.archetype.app.common.vo.PrototypeType;
import com.archetype.cqrsev.core.commands.BaseCommand;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
// TODO: Auto-generated Javadoc
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * The Class ChangePrototypeDayCommand.
 */
@Validated
@EqualsAndHashCode(callSuper=false)
@Getter
@AllArgsConstructor
@Jacksonized//permite la serializacion/des correcta de clases inmutables
@SuperBuilder
@Schema(name = "ChangePrototypeDayCommand", description = "command represent a change prototype Day on eventStore")
public final class ChangePrototypeDayCommand extends BaseCommand{

    /** The prototype day. */
	@JsonProperty("prototypeday")
    @NotBlank
	@NotNull(message = "prototypeday cannot be null")
	@Schema(name = "prototypeday", requiredMode = Schema.RequiredMode.REQUIRED,example = "25/10/2013", defaultValue = "25/10/2013", description = "lootday date")
	private final String prototypeDay;
	
	/**
	 * Instantiates a new change prototype day command.
	 *
	 * @param id the id
	 * @param prototypeDay the prototype day
	 */
	public ChangePrototypeDayCommand(String id, String prototypeDay) {
		super(id);
		this.prototypeDay = prototypeDay;
	}




	

}
