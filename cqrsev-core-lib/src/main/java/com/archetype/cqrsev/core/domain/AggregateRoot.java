package com.archetype.cqrsev.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.archetype.cqrsev.core.events.BaseEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public  abstract class AggregateRoot {
	protected String id;
	private int version =-1;
	private final List<BaseEvent> changes = new ArrayList<>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public List<BaseEvent> getChanges() {
		return changes;
	}
						   
	public List<BaseEvent> getUncommitedChanges(){
		return this.changes;
	}
	
	public void markChangesAsCommited() {
		this.changes.clear();
	}
	
	
	protected void applyChanges(BaseEvent event, Boolean isNewEvent) {
		try {
			var method= getClass().getDeclaredMethod("apply", event.getClass());
			method.setAccessible(true);
			method.invoke(this,event);
		}catch (NoSuchMethodException e) {
			log.error("apply method no found for {}",event.getClass());
			// TODO: handle exception
		}catch (Exception e) {
			log.error("errors about applaying envents to the aggregate",e);
		}finally {
			if(isNewEvent)
				changes.add(event);
		}
		
	
			
	}
	public void raiseEvent(BaseEvent event) {
		applyChanges(event,true);
	}
	
	public void replayEvents(Iterable<BaseEvent> events) {
		events.forEach(event -> applyChanges(event,false));
	}
}
