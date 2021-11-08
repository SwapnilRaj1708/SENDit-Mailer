package com.template.dto;

import lombok.Data;

@Data
public class MailRequest {

    private String name;
    private String to;
    private String from;
    private String subject;
    private String location;
    private String description;
    private String headline;
    private String tagline;
}
