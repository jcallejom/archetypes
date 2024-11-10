
/**
 * Self.
 *
 * @return the prototype deleted event. prototype deleted event builder impl
 */
package com.archetype.app.common.events;

import java.time.LocalDate;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.archetype.app.common.vo.PrototypeNumberVo;
import com.archetype.cqrsev.core.events.BaseEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;


@Validated
@EqualsAndHashCode(callSuper=false)
@Getter
@AllArgsConstructor
@Jacksonized//permite la serializacion/des correcta de clases inmutables
@SuperBuilder
public final class PrototypeClosedEvent extends BaseEvent{

}
