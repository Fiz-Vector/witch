package me.soda.witch.shared;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class HandleMessage {
    private static final Gson GSON = new Gson();

    public static void handle(Message message, Success success, Failure failure) {
        try {
            String msgType = message.messageType;
            JsonArray jsonArray = GSON.fromJson(message.message, JsonArray.class);
            String msg = jsonArray.size() > 0
                    ? jsonArray.get(0).isJsonPrimitive()
                    ? jsonArray.get(0).getAsJsonPrimitive().isString()
                    ? jsonArray.get(0).getAsString()
                    : jsonArray.get(0).toString()
                    : jsonArray.get(0).toString()
                    : "";
            success.handle(msgType, msg);
        } catch (Exception e) {
            failure.handle(e);
        }

    }

    public interface Success {
        void handle(String msgType, String msg);
    }

    public interface Failure {
        void handle(Exception e);
    }
}
