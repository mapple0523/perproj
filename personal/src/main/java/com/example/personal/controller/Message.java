package com.example.personal.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    String message = "";
    String href = "";

    public Message(String message, String href) {
        this.message = message;
        this.href = href;
    }
}
