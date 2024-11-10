package com.archetype.app.cmd.infrastruture;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archetype.app.cmd.domain.PrototypeAggregate;
import com.archetype.cqrsev.core.domain.AggregateRoot;
import com.archetype.cqrsev.core.handlers.EventSourcingHandler;
import com.archetype.cqrsev.core.infrastructure.EventStore;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeEventSourcingHandler.
 */
//@Qualifier("PrototypeEventSourcingHandler")
@Service
public class PrototypeEventSourcingHandler implements EventSourcingHandler<PrototypeAggregate>{

/** The event store. */
//	@Qualifier("PrototypeEventStore")
	@Autowired
	private EventStore eventStore;
	
	/**
	 * Save.
	 *
	 * @param aggregate the aggregate
	 */
	@Override
	public void save(AggregateRoot aggregate) {
		eventStore.saveEvents(aggregate.getId(), aggregate.getUncommitedChanges(), aggregate.getVersion());
		aggregate.markChangesAsCommited();
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the aggregate with all events by id
	 */
	@Override
	public PrototypeAggregate getById(String id) {
		var aggregate=new PrototypeAggregate();
		var events=eventStore.getEvent(id);
		if(events!=null && !events.isEmpty()) {
			aggregate.replayEvents(events);
			var latestVersion=events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
			aggregate.setVersion(latestVersion.get());
		}
		
		return aggregate;
	}

}
