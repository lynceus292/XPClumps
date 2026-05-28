package ru.lynceus.pvpClumps;

import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class XPListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void handleXpSpawn(EntitySpawnEvent event) {
        if (!(event.getEntity() instanceof ExperienceOrb targetOrb)) {
            return;
        }

        int bundledXp = targetOrb.getNearbyEntities(3.0, 3.0, 3.0).stream()
                .filter(entity -> entity instanceof ExperienceOrb)
                .map(entity -> (ExperienceOrb) entity)
                .filter(nearbyOrb -> !nearbyOrb.equals(targetOrb))
                .mapToInt(nearbyOrb -> {
                    int xp = nearbyOrb.getExperience();
                    nearbyOrb.remove();
                    return xp;
                })
                .sum();

        if (bundledXp > 0) {
            targetOrb.setExperience(targetOrb.getExperience() + bundledXp);
        }
    }
}
