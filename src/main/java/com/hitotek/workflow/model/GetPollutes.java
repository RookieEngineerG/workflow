package com.hitotek.workflow.model;

import lombok.Data;

@Data
public class GetPollutes extends PageRequest {
    private String polId;
    private Integer paramId;
}
