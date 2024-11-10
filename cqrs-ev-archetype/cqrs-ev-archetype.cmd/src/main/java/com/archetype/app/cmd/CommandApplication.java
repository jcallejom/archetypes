package com.archetype.app.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.archetype.app.cmd.api.command.ChangePrototypeDayCommand;
import com.archetype.app.cmd.api.command.ClosePrototypeCommand;
import com.archetype.app.cmd.api.command.ICommandHandler;
import com.archetype.app.cmd.api.command.OpenPrototypeCommand;
import com.archetype.cqrsev.core.infrastructure.CommandDispatcher;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandApplication.
 */

@OpenAPIDefinition(info = 
@Info(title = "Command Prototype API", description = "A CQRS microservice with Event sourcing in EDA Architecture")
)
@SpringBootApplication(scanBasePackages = {"com.archetype"} )
public class CommandApplication {
	
	
	/** The command handler. */
	@Autowired
	private ICommandHandler commandHandler;

/** The command dispatcher. */
//	@Qualifier("PrototypeCommandDispatcher")
	@Autowired
	private CommandDispatcher commandDispatcher;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}
	
	/**
	 * Register handlers.
	 */
	@PostConstruct
	public void registerHandlers() {
		
		commandDispatcher.registerHandler(OpenPrototypeCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(ChangePrototypeDayCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(ClosePrototypeCommand.class, commandHandler::handle);
	}
}
