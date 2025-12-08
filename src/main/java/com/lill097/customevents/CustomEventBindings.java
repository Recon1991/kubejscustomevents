package com.lill097.customevents;

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.Map;
import java.lang.reflect.Method;
import java.util.Optional;

public class CustomEventBindings {

    private static final String CUSTOM_TRIGGER_ID = "custom_trigger"; 
    
    private static Optional<Method> postMethod = Optional.empty();

    public static final Function<Object, Object> ON_CUSTOM_EVENT_BINDING = context -> {
        return (Consumer<Object>) listener -> {
        };
    };

    public static void fireCustomEvent(String message, int value) {
        if (postMethod.isEmpty()) {
            try {
                Class<?> globalClass = Class.forName("dev.latvian.mods.kubejs.script.GlobalScript");
                postMethod = Optional.of(globalClass.getMethod("postEvent", String.class, Map.class));
                
            } catch (Exception e) {
                System.err.println("KubeJS Reflection Failed: Cannot find event posting method.");
                return;
            }
        }
        
        try {
            Map<String, Object> data = Map.of(
                "message", message,
                "value", value
            );
            postMethod.get().invoke(null, CUSTOM_TRIGGER_ID, data);
            
        } catch (Exception e) {
            System.err.println("Failed to fire KubeJS custom event via reflection.");
            e.printStackTrace();
        }
    }
}