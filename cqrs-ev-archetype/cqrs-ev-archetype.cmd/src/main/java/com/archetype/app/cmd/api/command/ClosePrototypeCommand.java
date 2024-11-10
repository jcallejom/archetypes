package com.archetype.app.cmd.api.command;

import java.time.LocalDate;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.archetype.app.common.events.PrototypeClosedEvent;
import com.archetype.app.common.events.PrototypeOpenedEvent;
import com.archetype.app.common.vo.PrototypeNumberVo;
import com.archetype.cqrsev.core.commands.BaseCommand;
import com.archetype.cqrsev.core.events.BaseEvent;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

// TODO: Auto-generated Javadoc
/**
 * The Class ClosePrototypeCommand.
 */
@Validated
@EqualsAndHashCode(callSuper=false)
@Getter
@AllArgsConstructor
@Jacksonized//permite la serializacion/des correcta de clases inmutables
@SuperBuilder
@Schema(name = "ClosePrototypeCommand", description = "command represent a close prototype on eventStore")
public final class ClosePrototypeCommand extends BaseCommand{


	/**
	 * Instantiates a new close prototype command.
	 *
	 * @param id the id
	 */
	public ClosePrototypeCommand(@NotNull String id) {
		super(id);
	}
	

}
