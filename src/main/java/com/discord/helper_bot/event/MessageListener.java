package com.discord.helper_bot.event;

import com.discord.helper_bot.client.MetalPriceClient;
import com.discord.helper_bot.dto.MetalPriceClientResponse;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

public abstract class MessageListener {

    @Autowired
    MetalPriceClient metalPriceClient;

    public Mono<Void> processCommand(Message eventMessage){
        MetalPriceClientResponse metalPriceResponse = metalPriceClient.getMetalPriceResponse();
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .flatMap(message -> message.getChannel()
                        .flatMap(messageChannel -> {
                            String content = message.getContent().trim().toLowerCase();
                            String rate = getRateForMetal(content, metalPriceResponse);

                            if(rate != null){
                                return messageChannel.createMessage(content + " in INR: " + rate);
                            }
                            return messageChannel.createMessage("Invalid selection! please choose correct metal");
                        })).then();
    }

    private String getRateForMetal(String metal, MetalPriceClientResponse response){
        return switch (metal) {
            case "gold rate" -> response.rates.INRXAU;
            case "silver rate" -> response.rates.INRXAG;
            case "platinum rate" -> response.rates.INRXPT;
            default -> null;
        };
    }
}
