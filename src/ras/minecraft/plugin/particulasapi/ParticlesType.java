/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ras.minecraft.plugin.particulasapi;

public enum ParticlesType {
    LARGE_EXPLOSION("largeexplode"),
    FIREWORK_SPARK("fireworksSpark"),
    BUBBLE("bubble"),
    SUSPENDED("suspended"),
    DEPTH_SUSPEND("depthsuspend"),
    TOWN_AURA("townaura"),
    CRITICAL_HIT("crit"),
    MAGICAL_CRITICAL_HIT("magicCrit"),
    SMOKE("smoke"),
    MOB_SPELL("mobSpell"),
    MOB_SPELL_AMBIENT("mobSpellAmbient"),
    SPELL("spell"),
    INSTANT_SPELL("instantSpell"),
    WITCH_MAGIC("witchMagic"),
    NOTE("note"),
    PORTAL("portal"),
    ENCHANTMENT_TABLE("enchantmenttable"),
    EXPLODE("explode"),
    FLAME("flame"),
    LAVA("lava"),
    FOOTSTEP("footstep"),
    SPLASH("splash"),
    LARGE_SMOKE("largesmoke"),
    CLOUD("cloud"),
    REDSTONE("reddust"),
    SNOWBALL_POOF("snowballpoof"),
    DROP_WATER("dripWater"),
    DROP_LAVA("dripLava"),
    SNOW_SHOVEL("snowshovel"),
    SLIME("slime"),
    HEART("heart"),
    ANGRY_VILLAGER("angryVillager"),
    HAPPY_VILLAGER("happyVillager"),
    HUGE_EXPLOSION("hugeexplosion");
    
     private String text;

  ParticlesType(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  public static ParticlesType fromString(String text) {
    if (text != null) {
      for (ParticlesType b : ParticlesType.values()) {
        if (text.equalsIgnoreCase(b.text)) {
          return b;
        }
      }
    }
    return null;
  }
}
