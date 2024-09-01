package com.discord.helper_bot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
    public String INRXAG;
    public String INRXAU;
    public String INRXPT;
}
