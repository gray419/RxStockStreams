package com.graywolf.rxstockstreams.data;

import com.graywolf.rxstockstreams.data.Cursor;
import com.graywolf.rxstockstreams.data.Message;

import java.util.List;

/**
 * Created by robert on 8/15/15.
 */
public class StreamResponse {
    private Cursor cursor;
    private List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }
}
