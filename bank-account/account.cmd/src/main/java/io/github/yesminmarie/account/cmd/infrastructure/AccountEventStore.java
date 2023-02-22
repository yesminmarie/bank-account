package io.github.yesminmarie.account.cmd.infrastructure;

import io.github.yesminmarie.account.cmd.domain.AccountAggregate;
import io.github.yesminmarie.account.cmd.domain.EventStoreRepository;
import io.github.yesminmarie.cqrs.core.events.BaseEvent;
import io.github.yesminmarie.cqrs.core.events.EventModel;
import io.github.yesminmarie.cqrs.core.exceptions.AggregateNotFoundException;
import io.github.yesminmarie.cqrs.core.exceptions.ConcurrencyException;
import io.github.yesminmarie.cqrs.core.infrastructure.EventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountEventStore implements EventStore {

    private final EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if(expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion){
            throw new ConcurrencyException();
        }
        var version = expectedVersion;
        for(var event: events){
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            var persistEvent = eventStoreRepository.save(eventModel);
            if(persistEvent != null){
                //TODO
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if(eventStream == null || eventStream.isEmpty()){
            throw new AggregateNotFoundException("Incorrect account ID provided!");
        }
        return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());
    }
}
