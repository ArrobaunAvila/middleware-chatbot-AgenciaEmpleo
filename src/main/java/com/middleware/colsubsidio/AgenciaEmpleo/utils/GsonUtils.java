package com.middleware.colsubsidio.AgenciaEmpleo.utils;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonUtils {
    public String serialize(Object src) {
        Gson gson = new Gson();
        return gson.toJson(src);
    }

    public  <T> T toObject(String src, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(src, clazz);
    }

    public static <T> List<T> toList(String src, Class<T> clazz) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();
        return gson.fromJson(src, listType);
    }
    /*
    * Convierte un objeto a Json
    */
    public static Map<String, String> deserializeFlat(String json) {

        GsonUtils gsonUtils = new GsonUtils();
        Type t = new TypeToken<Map<String, String>>() {
        }.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(t, gsonUtils.new FlattenDeserializer())
                .setPrettyPrinting()
                .create();

        Map<String, String> map = gson.fromJson(json, t);

        return map;

    }

    /**
     * Transforma un json en un conjunto de claves y valores, Objetos indexar valores
     */
    public class FlattenDeserializer implements JsonDeserializer<Map<String, String>> {

        @Override
        public Map<String, String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Map<String, String> map = new HashMap<>();
            if (json.isJsonArray()) {
                int i = 0;
                for (JsonElement e : json.getAsJsonArray()) {
                    map.putAll(deserialize(e, typeOfT, context));
                    i++;
                }
            } else if (json.isJsonObject()) {
                for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                    if (entry.getValue().isJsonPrimitive()) {
                        map.put(entry.getKey(), entry.getValue().getAsString());
                    } else {
                        map.putAll(deserialize(entry.getValue(), typeOfT, context));
                    }
                }
            }

            return map;

        }
    }
 }

