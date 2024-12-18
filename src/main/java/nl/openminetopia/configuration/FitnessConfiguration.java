package nl.openminetopia.configuration;

import lombok.Getter;
import lombok.SneakyThrows;
import nl.openminetopia.OpenMinetopia;
import nl.openminetopia.modules.fitness.objects.FitnessLevel;
import nl.openminetopia.utils.ConfigurateConfig;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FitnessConfiguration extends ConfigurateConfig {

    private final int maxFitnessLevel;
    private final int defaultFitnessLevel;

    private final int maxFitnessByDrinking;
    private final double drinkingPointsPerPotion;
    private final double drinkingPointsPerWaterBottle;
    private final int drinkingPointsPerFitnessPoint;
    private final int drinkingCooldown;

    private final int maxFitnessByWalking;
    private final int cmPerWalkingPoint;

    private final int maxFitnessByClimbing;
    private final int cmPerClimbingPoint;

    private final int maxFitnessBySprinting;
    private final int cmPerSprintingPoint;

    private final int maxFitnessBySwimming;
    private final int cmPerSwimmingPoint;

    private final int maxFitnessByFlying;
    private final int cmPerFlyingPoint;

    private final int maxFitnessByHealth;
    private final int pointsAbove9Hearts;
    private final int pointsBelow5Hearts;
    private final int pointsBelow2Hearts;

    private final int maxFitnessByEating;
    private final double pointsForLuxuryFood;
    private final double pointsForCheapFood;
    private final List<String> cheapFood;
    private final List<String> luxuryFood;

    private final boolean fitnessDeathPunishmentEnabled;
    private final int fitnessDeathPunishmentAmount;
    private final int fitnessDeathPunishmentDuration;

    private final boolean rainSlowdownEnabled;

    private final Map<String, FitnessLevel> fitnessLevels = new HashMap<>();

    @SneakyThrows
    public FitnessConfiguration(File file) {
        super(file, "fitness.yml", "default-fitness.yml");

        this.maxFitnessLevel = rootNode.node("fitness", "maxFitnessLevel").getInt(225);
        this.defaultFitnessLevel = rootNode.node("fitness", "defaultFitnessLevel").getInt(20);

        this.maxFitnessByDrinking = rootNode.node("fitness", "drinking", "maxFitnessByDrinking").getInt(20);
        this.drinkingPointsPerPotion = rootNode.node("fitness", "drinking", "drinkingPointsPerPotion").getDouble(0.05);
        this.drinkingPointsPerWaterBottle = rootNode.node("fitness", "drinking", "drinkingPointsPerWaterBottle").getDouble(0.02);
        this.drinkingPointsPerFitnessPoint = rootNode.node("fitness", "drinking", "drinkingPointsPerFitnessPoint").getInt(1);
        this.drinkingCooldown = rootNode.node("fitness", "drinking", "drinkingCooldown").getInt(5);

        this.maxFitnessByWalking = rootNode.node("fitness", "statistics", "maxFitnessByWalking").getInt(30);
        this.cmPerWalkingPoint = rootNode.node("fitness", "statistics", "cmPerWalkingPoint").getInt(1000000);

        this.maxFitnessBySprinting = rootNode.node("fitness", "statistics", "maxFitnessBySprinting").getInt(40);
        this.cmPerSprintingPoint = rootNode.node("fitness", "statistics", "cmPerSprintingPoint").getInt(2000000);

        this.maxFitnessByClimbing = rootNode.node("fitness", "statistics", "maxFitnessByClimbing").getInt(30);
        this.cmPerClimbingPoint = rootNode.node("fitness", "statistics", "cmPerClimbingPoint").getInt(500000);

        this.maxFitnessBySwimming = rootNode.node("fitness", "statistics", "maxFitnessBySwimming").getInt(30);
        this.cmPerSwimmingPoint = rootNode.node("fitness", "statistics", "cmPerSwimmingPoint").getInt(600000);

        this.maxFitnessByFlying = rootNode.node("fitness", "statistics", "maxFitnessByFlying").getInt(30);
        this.cmPerFlyingPoint = rootNode.node("fitness", "statistics", "cmPerFlyingPoint").getInt(3000000);

        this.maxFitnessByHealth = rootNode.node("fitness", "health", "maxFitnessByHealth").getInt(10);
        this.pointsAbove9Hearts = rootNode.node("fitness", "health", "pointsAbove9Hearts").getInt(60);
        this.pointsBelow5Hearts = rootNode.node("fitness", "health", "pointsBelow5Hearts").getInt(-50);
        this.pointsBelow2Hearts = rootNode.node("fitness", "health", "pointsBelow2Hearts").getInt(-75);

        this.maxFitnessByEating = rootNode.node("fitness", "eating", "maxFitnessByEating").getInt(20);
        this.pointsForLuxuryFood = rootNode.node("fitness", "eating", "pointsForLuxuryFood").getDouble(5);
        this.pointsForCheapFood = rootNode.node("fitness", "eating", "pointsForCheapFood").getDouble(2);
        this.luxuryFood = rootNode.node("fitness", "eating", "luxuryFood").getList(String.class, List.of("COOKED_BEEF", "MUSHROOM_STEW", "COOKED_PORKCHOP", "COOKED_SALMON", "COOKED_COD", "BAKED_POTATO", "COOKED_RABBIT"));
        this.cheapFood = rootNode.node("fitness", "eating", "cheapFood").getList(String.class, List.of("APPLE", "BREAD", "MELON_BLOCK", "RAW_FISH", "COOKED_CHICKEN", "COOKED_MUTTON", "COOKIE"));

        this.fitnessDeathPunishmentDuration = rootNode.node("fitness", "deathPunishment", "duration").getInt(1440);
        this.fitnessDeathPunishmentEnabled = rootNode.node("fitness", "deathPunishment", "enabled").getBoolean(true);
        this.fitnessDeathPunishmentAmount = rootNode.node("fitness", "deathPunishment", "amount").getInt(-20);

// Use LinkedHashMap to keep the order of the fitness levels in the configuration
//
//
//        Map<String, FitnessLevel> defaultFitnessLevels = new LinkedHashMap<>();
//        defaultFitnessLevels.put("1-9", new FitnessLevel(0.1, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("10-19", new FitnessLevel(0.12, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("20-29", new FitnessLevel(0.15, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("30-39", new FitnessLevel(0.16, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("40-49", new FitnessLevel(0.17, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("50-59", new FitnessLevel(0.19, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("60-69", new FitnessLevel(0.19, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("70-79", new FitnessLevel(0.2, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("80-99", new FitnessLevel(0.22, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("100-119", new FitnessLevel(0.235, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("120-139", new FitnessLevel(0.25, List.of("JUMP_BOOST:0")));
//        defaultFitnessLevels.put("140-159", new FitnessLevel(0.27, List.of("JUMP_BOOST:1")));
//        defaultFitnessLevels.put("160-179", new FitnessLevel(0.29, List.of("JUMP_BOOST:2")));
//        defaultFitnessLevels.put("180-199", new FitnessLevel(0.31, List.of("JUMP_BOOST:2")));
//        defaultFitnessLevels.put("200-209", new FitnessLevel(0.325, List.of("JUMP_BOOST:3")));
//        defaultFitnessLevels.put("210-225", new FitnessLevel(0.335, List.of("JUMP_BOOST:3")));
//
//
//        for (Map.Entry<String, FitnessLevel> fitnessLevelMap : defaultFitnessLevels.entrySet()) {
//            ConfigurationNode levelNode = fitnessNode.node(fitnessLevelMap.getKey());
//
//            FitnessLevel value = fitnessLevelMap.getValue();
//
//            levelNode.node("effects").getList(String.class, value.getEffects());
//            levelNode.node("walkSpeed").getDouble(value.getWalkSpeed());
//        }
        ConfigurationNode fitnessNode = this.rootNode.node("fitness", "levels");

        fitnessNode.childrenMap().forEach((key, val) -> {
            try {
                String level = key.toString();
                FitnessLevel fitnessLevel = new FitnessLevel(val.node("walkSpeed").getDouble(0.1), val.node("effects").getList(String.class, List.of("JUMP_BOOST:1", "LEVITATION:1")));
                this.fitnessLevels.put(level, fitnessLevel);
            } catch (SerializationException e) {
                OpenMinetopia.getInstance().getLogger().severe("An error occurred while loading the fitness levels.");
                e.printStackTrace();
            }
        });

        this.rainSlowdownEnabled = rootNode.node("fitness", "rainSlowdownEnabled").getBoolean(false);
    }
}
