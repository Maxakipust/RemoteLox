package lox;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class StmtSerializer implements JsonSerializer<Stmt> {
    public JsonElement serialize(Stmt stmt, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jo = new JsonObject();

        jo.addProperty("className", stmt.getClass().getName());

        return jo;
    }
}
