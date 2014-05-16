package ras.minecraft.plugin.particulasapi;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.v1_5_R3.Packet63WorldParticles;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author 
 */
public class ParticlesAPI extends JavaPlugin {
    private static void _createEffect(Location pos, String nameOfEffect, float playersX,
            float playersY, float playersZ, float xOffset, float yOffset,
            float zOffset, float effectSpeed, int amountOfParticles, boolean global) {
        // Make an instance of the packet!
        Packet63WorldParticles sPacket = new Packet63WorldParticles();
        for (Field field : sPacket.getClass().getDeclaredFields()) {
            try {
                // Get those fields we need to be accessible!
                field.setAccessible(true);
                String fieldName = field.getName();
                // Set them to what we want!
                switch (fieldName) {
                    case "a":
                        field.set(sPacket, nameOfEffect);
                        break;
                    case "b":
                        field.setFloat(sPacket, playersX);
                        break;
                    case "c":
                        field.setFloat(sPacket, playersY);
                        break;
                    case "d":
                        field.setFloat(sPacket, playersZ);
                        break;
                    case "e":
                        field.setFloat(sPacket, xOffset);
                        break;
                    case "f":
                        field.setFloat(sPacket, yOffset);
                        break;
                    case "g":
                        field.setFloat(sPacket, zOffset);
                        break;
                    case "h":
                        field.setFloat(sPacket, effectSpeed);
                        break;
                    case "i":
                        field.setInt(sPacket, amountOfParticles);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        for (Player pl : getNearbyPlayers(pos, 10)) {
            ((CraftPlayer) pl).getHandle().playerConnection.sendPacket(sPacket);
        }
    }

    public static void createEffect(Location pos, ParticlesType particleType, float variacion, float velocidad, int numero) {
        _createEffect(pos, particleType.getText(), (float) pos.getX(), (float) pos.getY(), (float) pos.getZ(), variacion, variacion, variacion, velocidad, numero, true);
    }
    
    public static void createEffect(Location loc, ParticlesType particleType){
        createEffect(loc, particleType, 0.0f, 1.0f, 1);
    }
    
    private static List<Entity> getNearbyEntities(Location where, int range) {
        List<Entity> found = new ArrayList<Entity>();

        for (Entity entity : where.getWorld().getEntities()) {
        if (isInBorder(where, entity.getLocation(), range)) {
        found.add(entity);
        }
        }
        return found;
    }
    
    private static List<Player> getNearbyPlayers(Location where, int range) {
        List<Player> found = new ArrayList<Player>();

        for (Entity entity : where.getWorld().getEntities()) {
        if (isInBorder(where, entity.getLocation(), range)) {
            if(entity instanceof Player){ found.add((Player)entity); }
        }
        }
        return found;
    }
    
    private static boolean isInBorder(Location center, Location notCenter, int range) {
        int x = center.getBlockX(), z = center.getBlockZ();
        int x1 = notCenter.getBlockX(), z1 = notCenter.getBlockZ();

        if (x1 >= (x + range) || z1 >= (z + range) || x1 <= (x - range) || z1 <= (z - range)) {
        return false;
        }
        return true;
    }
    
}
