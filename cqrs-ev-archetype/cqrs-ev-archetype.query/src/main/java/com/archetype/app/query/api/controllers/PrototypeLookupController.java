package com.archetype.app.query.api.controllers;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.app.query.api.queries.FindPrototypeByIdQuery;
import com.archetype.app.query.api.queries.FindPrototypeByPrototypeCodeQuery;
import com.archetype.app.query.api.vo.PrototypeResponse;
import com.archetype.app.query.domain.PrototypeEntity;
import com.archetype.cqrsev.core.infrastructure.QueryDispatcher;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeLookupController.
 */
@RestController
@RequestMapping(path = "/v1/prototypeLookup")
public class PrototypeLookupController {
	
	/** The logger. */
	private final Logger logger = Logger.getLogger(PrototypeLookupController.class.getName());
	
	/** The query dispatcher. */
	@Autowired
	private QueryDispatcher queryDispatcher;


	/**
	 * Gets the prototype by id.
	 *
	 * @param id the id
	 * @return the prototype by id
	 */
	@GetMapping(path = "/byid/{id}")
	public ResponseEntity<PrototypeResponse> getPrototypeById(@PathVariable(value = "id") String id) {
		try {
			List<PrototypeEntity> searchs = queryDispatcher.send(new FindPrototypeByIdQuery(id));
			if (searchs == null || searchs.size() == 0) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			var response = PrototypeResponse.builder().prototypeEntitys(searchs)
					.count(searchs.size())
					.message("The query was successful").build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			var safeErrorMessage = "Error executing query";
			logger.log(Level.SEVERE,safeErrorMessage,e);

			return new ResponseEntity<>(new PrototypeResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	/**
	 * Gets the prototype by prototype code.
	 *
	 * @param code the code
	 * @return the prototype by prototype code
	 */
	@GetMapping(path = "/bycode/{code}")
	public ResponseEntity<PrototypeResponse> getPrototypeByPrototypeCode(@PathVariable(value = "code") String code) {
		try {
			List<PrototypeEntity> searchs = queryDispatcher.send(new FindPrototypeByPrototypeCodeQuery(code));
			if (searchs == null || searchs.size() == 0) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			var response = PrototypeResponse.builder().prototypeEntitys(searchs)
					.count(searchs.size())
					.message("The query was successful").build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			var safeErrorMessage = "Error executing query";
			logger.log(Level.SEVERE,safeErrorMessage,e);

			return new ResponseEntity<>(new PrototypeResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
}
