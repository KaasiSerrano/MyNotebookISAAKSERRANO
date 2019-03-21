package com.example.eventbus;

/**
 * This class is the "bus stop" That will send a message to everyone who is subscribed to receive messages
 */
public class MessageEvent {


    public String Message;

    public MessageEvent(String message) {
        Message = message;
    }
}
