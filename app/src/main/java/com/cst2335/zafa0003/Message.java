package com.cst2335.zafa0003;

public class Message {
    String message;
    boolean send;

    public Message(String message, boolean send) { //[3]
        this.message = message;
        this.send = send;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getDirection(){
        return this.send;
    }

    public void setDirection(boolean direction){
        this.send = direction;
    }
}
//[3] https://github.com/gourteacher/Exercises_S22