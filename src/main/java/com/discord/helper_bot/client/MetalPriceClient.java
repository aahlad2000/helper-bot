package com.discord.helper_bot.client;


import com.discord.helper_bot.dto.MetalPriceClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class MetalPriceClient {
    Logger logger = LoggerFactory.getLogger(MetalPriceClient.class);

    @Value("${metal-price-client-key}")
    private String metalPriceKey;

    private final RestClient restClient = RestClient.builder()
            .defaultHeader("Authorization", "Bearer ")
            .baseUrl("https://api.metalpriceapi.com/v1/latest").build();

    public MetalPriceClientResponse getMetalPriceResponse(){
        logger.info("Calling MetalPriceAPI to fetch gold rate");
        return restClient
                .get()
                .uri("?api_key="+ metalPriceKey +"&base=INR&currencies=XAU,XAG,XPT")
                .retrieve()
                .toEntity(MetalPriceClientResponse.class).getBody();
    }

}
