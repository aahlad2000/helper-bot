package com.discord.helper_bot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetalPriceClientResponse {
    public String success;
    public String base;
    public int timestamp;
    public Rate rates;
}
