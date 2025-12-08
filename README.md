⚡ KubeJS Custom Events Addon
A lightweight utility add-on designed to bridge the gap between Java mod code and KubeJS scripts, significantly expanding your capabilities in modpack development.

🎯 Key Features
Custom Global Bindings (OnCustomEvent): Allows any Java code in your modpack to fire named, data-carrying events that can be intercepted and handled instantly by a JavaScript listener.

External Event Bridge (e.g., MineColonies): Provides robust helper methods to easily register JavaScript listeners to complex NeoForge events (e.g., from mods like MineColonies). This transforms intricate mod events into simple, scriptable KubeJS commands.

Simplified Integration: Ideal for developers who need to connect deep Java logic to a dynamic scripting environment without constant recompilation.

📖 User Guide
This guide explains how to use the global bindings added by this addon in your KubeJS scripts.

1. 🏰 Listening to External Mod Events (e.g., MineColonies)
Use the global object MineColonies (exposed by the addon) to register listeners for specific external mod events that fire on the main NeoForge bus.

Example: Building Completion
The method onBuildingConstructionFinished() registers your script logic to the MineColonies BuildingConstructionModEvent.

JavaScript

// Located in server_scripts/*.js

// Use the global object to register the listener
MineColonies.onBuildingConstructionFinished(event => {
    
    const buildingName = event.getBuilding().getName();
    const colonyName = event.getColony().getName();
    
    // Send a message using the server object available in the event
    event.getServer().tell(`[Custom Addon] Building '${buildingName}' finished in ${colonyName}.`);
    
    // You can access all standard methods of the original event object here.
});
2. ⚡ Handling Custom Internal Events
The global function OnCustomEvent() is used to listen for events triggered directly from the Java side of your custom mod code (via the implemented Reflection mechanism).

Example: Listening for a Custom Trigger
Listen for the event ID you defined in your Java code (e.g., "custom_trigger").

JavaScript

// Located in server_scripts/*.js

// Listen for the custom event fired by CustomEventBindings.fireCustomEvent(...)
OnCustomEvent(event => {
    
    console.log(`Custom Event Received! Message: ${event.message}`); 
    console.log(`Value: ${event.value}`);
    
    // Add custom script logic here, e.g., granting rewards or changing recipes.
});
🎯 Summary of Global Access
Your scripts gain access to these two new global elements:

MineColonies: Object used to register listeners for external mod events.

OnCustomEvent(listener): Function used for handling custom, internal events fired by your Java mod.