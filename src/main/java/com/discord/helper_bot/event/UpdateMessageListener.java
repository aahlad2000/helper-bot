package com.discord.helper_bot.event;

import discord4j.core.event.domain.message.MessageUpdateEvent;
import reactor.core.publisher.Mono;

public class UpdateMessageListener extends MessageListener implements EventListener<MessageUpdateEvent>{

    @Override
    public Class<MessageUpdateEvent> getEventType(){
        return MessageUpdateEvent.class;

    }

    @Override
    public Mono<Void> execute(MessageUpdateEvent event){
        return Mono.just(event)
                .filter(MessageUpdateEvent::isContentChanged)
                .flatMap(MessageUpdateEvent::getMessage)
                .flatMap(super::processCommand);
    }
}
