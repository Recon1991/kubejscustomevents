package com.lill097.customevents;

import net.neoforged.fml.common.Mod;
import dev.latvian.mods.kubejs.script.BindingRegistry; 

@Mod(CustomEventsMod.MOD_ID)
public class CustomEventsMod {
    
    public static final String MOD_ID = "kubejscustomevents";

    public CustomEventsMod() {
    }
    
    public static void registerBindings(BindingRegistry registry) {
        registry.add("OnCustomEvent", CustomEventBindings.ON_CUSTOM_EVENT_BINDING);
    }
}