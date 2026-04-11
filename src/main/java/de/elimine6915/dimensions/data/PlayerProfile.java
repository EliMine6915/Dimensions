package de.elimine6915.dimensions.data;

import java.util.UUID;

public class PlayerProfile {
    private final UUID uuid;

    // Levels & XP
    private int mining, miningXP;
    private int foraging, foragingXP;
    private int farming, farmingXP;
    private int digging, diggingXP;
    private int fishing, fishingXP;
    private int combat, combatXP;
    private int seasonLVL, seasonXP;

    public PlayerProfile(UUID uuid, int mining, int miningXP, int foraging, int foragingXP,
                         int farming, int farmingXP, int digging, int diggingXP,
                         int fishing, int fishingXP, int combat, int combatXP,
                         int seasonLVL, int seasonXP) {
        this.uuid = uuid;
        this.mining = mining;
        this.miningXP = miningXP;
        this.foraging = foraging;
        this.foragingXP = foragingXP;
        this.farming = farming;
        this.farmingXP = farmingXP;
        this.digging = digging;
        this.diggingXP = diggingXP;
        this.fishing = fishing;
        this.fishingXP = fishingXP;
        this.combat = combat;
        this.combatXP = combatXP;
        this.seasonLVL = seasonLVL;
        this.seasonXP = seasonXP;
    }

    // Getter & Setter (Beispielhaft für alle, damit dein Code sauber bleibt)
    public UUID getUuid() { return uuid; }

    public int getMining() { return mining; }
    public void setMining(int mining) { this.mining = mining; }
    public int getMiningXP() { return miningXP; }
    public void setMiningXP(int miningXP) { this.miningXP = miningXP; }

    public int getForaging() { return foraging; }
    public void setForaging(int foraging) { this.foraging = foraging; }
    public int getForagingXP() { return foragingXP; }
    public void setForagingXP(int foragingXP) { this.foragingXP = foragingXP; }

    public int getFarming() { return farming; }
    public void setFarming(int farming) { this.farming = farming; }
    public int getFarmingXP() { return farmingXP; }
    public void setFarmingXP(int farmingXP) { this.farmingXP = farmingXP; }

    public int getDigging() { return digging; }
    public void setDigging(int digging) { this.digging = digging; }
    public int getDiggingXP() { return diggingXP; }
    public void setDiggingXP(int diggingXP) { this.diggingXP = diggingXP; }

    public int getFishing() { return fishing; }
    public void setFishing(int fishing) { this.fishing = fishing; }
    public int getFishingXP() { return fishingXP; }
    public void setFishingXP(int fishingXP) { this.fishingXP = fishingXP; }

    public int getCombat() { return combat; }
    public void setCombat(int combat) { this.combat = combat; }
    public int getCombatXP() { return combatXP; }
    public void setCombatXP(int combatXP) { this.combatXP = combatXP; }

    public int getSeasonLVL() { return seasonLVL; }
    public void setSeasonLVL(int seasonLVL) { this.seasonLVL = seasonLVL; }
    public int getSeasonXP() { return seasonXP; }
    public void setSeasonXP(int seasonXP) { this.seasonXP = seasonXP; }
}