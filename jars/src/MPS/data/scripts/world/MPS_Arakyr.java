package MPS.data.scripts.world;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.CoronalTapParticleScript;
import com.fs.starfarer.api.impl.campaign.ids.*;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.SalvageSpecialAssigner;
import com.fs.starfarer.api.impl.campaign.terrain.BaseRingTerrain;
import com.fs.starfarer.api.impl.campaign.terrain.DebrisFieldTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.MagneticFieldTerrainPlugin;
import com.fs.starfarer.api.util.Misc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static MPS.data.scripts.world.MPS_WorldGen.addPlanetCondition;

public class MPS_Arakyr {

    public void generate(SectorAPI sector) {
        StarSystemAPI system = sector.createStarSystem("Arakyr");
        system.getLocation().set(-10000f, 4000f);
        system.setBackgroundTextureFilename("graphics/backgrounds/background1.jpg");

        PlanetAPI Astar = system.initStar("Arakyr", StarTypes.ORANGE, 980f, 333f, 0.75f, 0.25f, 2f);
        system.setLightColor(new Color(255, 255, 255));

        PlanetAPI arakyr1 = system.addPlanet("arakyr1", Astar, "ArakyrI", Planets.IRRADIATED, (float) (Math.random()*360f), 100f, 4000f, 88f);
        PlanetAPI arakyr2 = system.addPlanet("arakyr2", Astar, "ArakyrII", Planets.PLANET_LAVA_MINOR, (float) (Math.random()*360f), 150f, 5500f, 225f);

        PlanetAPI arakyr3 = system.addPlanet("arakyr3", Astar, "ArakyrIII", Planets.PLANET_TERRAN, 0f, 200f, 7800f, 365f);
        PlanetAPI arakyr3a = system.addPlanet("arakyr3a", arakyr3, "ArakyrIIIa", Planets.BARREN_BOMBARDED, (float) (Math.random()*360f), 45f, 730f, 183f);
        PlanetAPI arakyr3b = system.addPlanet("arakyr3b", arakyr3, "ArakyrIIIb", Planets.ROCKY_ICE, 0f, 35f, 940f, 365f);

        PlanetAPI arakyr4 = system.addPlanet("arakyr4", Astar, "ArakyrIV", Planets.BARREN_DESERT, (float) (Math.random()*360f), 220f, 9040f, 693f);
        PlanetAPI arakyr5 = system.addPlanet("arakyr5", Astar, "ArakyrV", Planets.GAS_GIANT, (float) (Math.random()*360f), 750f, 13490f, 4307f);
        PlanetAPI arakyr6 = system.addPlanet("arakyr6", Astar, "ArakyrVI", Planets.ICE_GIANT, (float) (Math.random()*360f), 580f, 18900f, 10767f);
        PlanetAPI arakyr7 = system.addPlanet("arakyr7", Astar, "ArakyrVII", "cryovolcanic", (float) (Math.random()*360f), 150f, 20240f, 60152f);

        system.addAsteroidBelt(Astar, 60, 11500f, 200f, 300f, 600f, Terrain.ASTEROID_BELT, null);
        system.addAsteroidBelt(Astar, 75, 11600f, 200f, 300f, 600f, Terrain.ASTEROID_BELT, null);
        system.addAsteroidBelt(Astar, 90, 11700f, 200f, 300f, 600f, Terrain.ASTEROID_BELT, null);

        system.addRingBand(Astar, "misc", "rings_dust0", 256f, 1, Color.white, 256f, 11500f, 420f);
        system.addRingBand(Astar, "misc", "rings_dust0", 256f, 1, Color.white, 256f, 11600f, 450f);
        system.addRingBand(Astar, "misc", "rings_dust0", 256f, 2, Color.white, 256f, 11700f, 480f);
        system.addRingBand(Astar, "misc", "rings_asteroids0", 256f, 3, Color.white, 256f, 11600f, 400f);

        SectorEntityToken ring = system.addTerrain(Terrain.RING, new BaseRingTerrain.RingParams(400f + 256f, 11600f, null, "小行星带"));
        ring.setCircularOrbit(Astar, 0, 0, 100f);

        system.addAsteroidBelt(arakyr5, 8, 1100f, 60f, 400f, 800f, Terrain.ASTEROID_BELT, null);
        system.addAsteroidBelt(arakyr5, 10, 1150f, 60f, 400f, 800f, Terrain.ASTEROID_BELT, null);
        system.addAsteroidBelt(arakyr5, 12, 1200f, 60f, 400f, 800f, Terrain.ASTEROID_BELT, null);
        system.addRingBand(arakyr5, "misc", "rings_ice0", 256f, 0, Color.white, 256f, 1050f, 600f);
        system.addRingBand(arakyr5, "misc", "rings_ice0", 256f, 2, Color.white, 256f, 1120f, 630f);
        system.addRingBand(arakyr5, "misc", "rings_ice0", 256f, 1, Color.white, 256f, 1180f, 660f);
        system.addRingBand(arakyr5, "misc", "rings_ice0", 256f, 3, Color.white, 256f, 1250f, 690f);

        ring = system.addTerrain(Terrain.RING, new BaseRingTerrain.RingParams(50f + 256f, 1150f, null, "阿拉基尔V 行星环"));
        ring.setCircularOrbit(arakyr5, 0, 0, 100f);

        SectorEntityToken w_loc1 = system.addCustomEntity(null, null, Entities.SENSOR_ARRAY, Factions.NEUTRAL);
        SectorEntityToken w_loc2 = system.addCustomEntity(null, null, Entities.COMM_RELAY, Factions.NEUTRAL);
        SectorEntityToken w_loc3 = system.addCustomEntity(null, null, Entities.NAV_BUOY, Factions.NEUTRAL);
        w_loc1.setCircularOrbitPointingDown(Astar, 0f, 2800f, 200f);
        w_loc2.setCircularOrbitPointingDown(Astar, 90f, 2800f, 200f);
        w_loc3.setCircularOrbitPointingDown(Astar, 180f, 2800f, 200f);
        SectorEntityToken gate = system.addCustomEntity("MPS_gate", "Ihan-rii", Entities.INACTIVE_GATE,
                Factions.NEUTRAL);
        gate.setCircularOrbit(arakyr3, 120f, 2400f, 365f);

        DebrisFieldTerrainPlugin.DebrisFieldParams params = new DebrisFieldTerrainPlugin.DebrisFieldParams( // 碎片(Debris)
                280f, // 碎片场半径，不应超过1000，影响性能
                1f, // 密度-影响碎片数量，基础为1
                1000000f, // 持续时间
                0f);// 几天后开始渲染
        params.source = DebrisFieldTerrainPlugin.DebrisFieldSource.MIXED;
        params.baseSalvageXP = 2000;// 打捞残骸获得的经验
        SectorEntityToken debires = Misc.addDebrisField(system, params, StarSystemGenerator.random);
        SalvageSpecialAssigner.assignSpecialForDebrisField(debires);
        debires.setDiscoverable(true);// 能够被玩家发现
        debires.setDiscoveryXP(2000f);// 发现后给予经验值
        debires.setSensorProfile(1f);// 可以检测到的范围，1单位=2000大地图su
        debires.getDetectedRangeMod().modifyFlat("gen", 2000f);
        debires.setCircularOrbitWithSpin(gate, 0f, 1f, 100f, 1f, 10f);

        // 尝试：加入星冕分流器、人之领低温休眠舱
        SectorEntityToken cryosleeper = system.addCustomEntity(null, null, Entities.DERELICT_CRYOSLEEPER,
                Factions.DERELICT);
        cryosleeper.setCircularOrbitWithSpin(Astar, 90f,
                Astar.getRadius() + cryosleeper.getRadius() + 3120f, Astar.getRadius() / 20f, 1f,
                11f);
        // cryosleeper.getMemoryWithoutUpdate().set(null, null);
        cryosleeper.setSensorProfile(1f);
        cryosleeper.setDiscoverable(true);
        cryosleeper.setDiscoveryXP(10000f);
        cryosleeper.getDetectedRangeMod().modifyFlat("gen", 3500f);

        SectorEntityToken hypershunt = system.addCustomEntity(null, null, Entities.CORONAL_TAP, null);
        float hypershuntOrbitRadius = Astar.getRadius() + hypershunt.getRadius() + 100f;
        hypershunt.setCircularOrbitPointingDown(Astar, 270f, hypershuntOrbitRadius,
                hypershuntOrbitRadius / 20f);
        hypershunt.setSensorProfile(1f);
        hypershunt.setDiscoverable(true);
        hypershunt.setDiscoveryXP(10000f);
        hypershunt.getDetectedRangeMod().modifyFlat("gen", 3500f);
        system.addScript(new CoronalTapParticleScript(hypershunt));
        system.addTag("theme_derelict_cryosleeper");
        system.addTag("has_coronal_tap");
        system.addTag("theme_interesting");

        SectorEntityToken station = system.addCustomEntity("ROU_system_station", "Void Array", "station_side07",
                Factions.NEUTRAL);
        station.setCircularOrbitPointingDown(Astar, 270f, 2800f, 200f);
        station.setInteractionImage("illustrations", "cargo_loading");
        Misc.setAbandonedStationMarket("ROU_system_station_market", station);
        station.getMarket().getSubmarket(Submarkets.SUBMARKET_STORAGE).getCargo().addItems(
                CargoAPI.CargoItemType.RESOURCES,
                Commodities.OMEGA_CORE, 1);

        MarketAPI arakyr3_Market = addPlanetCondition(arakyr3, new ArrayList<String>(
                Arrays.asList(
                        Conditions.HABITABLE,
                        Conditions.MILD_CLIMATE,
                        Conditions.FARMLAND_BOUNTIFUL,
                        Conditions.ORGANICS_ABUNDANT,
                        Conditions.ORE_ABUNDANT,
                        Conditions.SOLAR_ARRAY
                )
        ));
        SectorEntityToken arakyr3_mirror1 = system.addCustomEntity("MPS_arakyr3_mirror", "阿拉基尔III Alpha 恒星镜",
                Entities.STELLAR_MIRROR, Factions.NEUTRAL);
        SectorEntityToken arakyr3_mirror2 = system.addCustomEntity("MPS_arakyr3_mirror", "阿拉基尔III Beta 恒星镜",
                Entities.STELLAR_MIRROR, Factions.NEUTRAL);
        SectorEntityToken arakyr3_mirror3 = system.addCustomEntity("MPS_arakyr3_mirror", "阿拉基尔III Gamma 恒星镜",
                Entities.STELLAR_MIRROR, Factions.NEUTRAL);
        SectorEntityToken arakyr3_mirror4 = system.addCustomEntity("MPS_arakyr3_mirror", "阿拉基尔III Delta 恒星镜",
                Entities.STELLAR_SHADE, Factions.NEUTRAL);
        SectorEntityToken arakyr3_mirror5 = system.addCustomEntity("MPS_arakyr3_mirror", "阿拉基尔III Epsilon 恒星镜",
                Entities.STELLAR_SHADE, Factions.NEUTRAL);
        SectorEntityToken arakyr3_mirror6 = system.addCustomEntity("MPS_arakyr3_mirror", "阿拉基尔III Zeta 恒星镜",
                Entities.STELLAR_SHADE, Factions.NEUTRAL);
        float mirroOrbitRadius = arakyr3.getRadius() + arakyr3_mirror1.getRadius() + 140f;
        arakyr3_mirror1.setCircularOrbitPointingDown(arakyr3, 30f, mirroOrbitRadius, 365f);
        arakyr3_mirror2.setCircularOrbitPointingDown(arakyr3, 0f, mirroOrbitRadius, 365f);
        arakyr3_mirror3.setCircularOrbitPointingDown(arakyr3, 330f, mirroOrbitRadius, 365f);
        arakyr3_mirror4.setCircularOrbitPointingDown(arakyr3, 150f, mirroOrbitRadius, 365f);
        arakyr3_mirror5.setCircularOrbitPointingDown(arakyr3, 180f, mirroOrbitRadius, 365f);
        arakyr3_mirror6.setCircularOrbitPointingDown(arakyr3, 210f, mirroOrbitRadius, 365f);

        JumpPointAPI arakyr3_jumpPoint = Global.getFactory().createJumpPoint("MPS_arakyr3_jump_point", "Void Edge");
        arakyr3_jumpPoint.setOrbit(Global.getFactory().createCircularOrbit(arakyr3, 270f, 1000f, 360f));
        arakyr3_jumpPoint.setRelatedPlanet(arakyr3);
        arakyr3_jumpPoint.setStandardWormholeToHyperspaceVisual();
        system.addEntity(arakyr3_jumpPoint);

        MarketAPI arakyr1_market = addPlanetCondition(arakyr1, new ArrayList<String>(
                Arrays.asList(
                        Conditions.IRRADIATED,
                        Conditions.NO_ATMOSPHERE,
                        Conditions.VERY_HOT,
                        Conditions.RUINS_EXTENSIVE
                )
        ));
        SectorEntityToken arakyr1_field = system.addTerrain(Terrain.MAGNETIC_FIELD,
                new MagneticFieldTerrainPlugin.MagneticFieldParams(
                        arakyr1.getRadius() + 100f,
                        (arakyr1.getRadius() + 100f) / 2f,
                        arakyr1,
                        arakyr1.getRadius() + 10f,
                        arakyr1.getRadius() + 190f,
                        new Color(50, 20, 100, 120), 0f));
        arakyr1_field.setCircularOrbit(arakyr1, 0f, 0f, 100f);


        MarketAPI arakyr2_market = addPlanetCondition(arakyr2, new ArrayList<String>(
                Arrays.asList(
                        Conditions.VERY_HOT,
                        Conditions.THIN_ATMOSPHERE,
                        Conditions.TECTONIC_ACTIVITY,
                        Conditions.ORE_ULTRARICH,
                        Conditions.RARE_ORE_ULTRARICH
                )
        ));

        MarketAPI arakyr3a_market = addPlanetCondition(arakyr3a, new ArrayList<String>(
                Arrays.asList(
                        Conditions.LOW_GRAVITY,
                        Conditions.NO_ATMOSPHERE,
                        Conditions.RARE_ORE_SPARSE
                )
        ));

        MarketAPI arakyr3b_market = addPlanetCondition(arakyr3b, new ArrayList<String>(
                Arrays.asList(
                        Conditions.LOW_GRAVITY,
                        Conditions.NO_ATMOSPHERE,
                        Conditions.ORE_SPARSE
                )
        ));

        MarketAPI arakyr4_market = addPlanetCondition(arakyr4, new ArrayList<String>(
                Arrays.asList(
                        Conditions.COLD,
                        Conditions.NO_ATMOSPHERE,
                        Conditions.ORGANICS_PLENTIFUL,
                        Conditions.ORE_ABUNDANT,
                        Conditions.RARE_ORE_MODERATE
                )
        ));

        MarketAPI arakyr5_market = addPlanetCondition(arakyr5, new ArrayList<String>(
                Arrays.asList(
                        Conditions.HIGH_GRAVITY,
                        Conditions.HOT,
                        Conditions.VOLATILES_PLENTIFUL
                )
        ));
        MarketAPI arakyr6_market = addPlanetCondition(arakyr6, new ArrayList<String>(
                Arrays.asList(
                        Conditions.HIGH_GRAVITY,
                        Conditions.COLD,
                        Conditions.VOLATILES_PLENTIFUL
                )
        ));

        MarketAPI arakyr7_market = addPlanetCondition(arakyr7, new ArrayList<String>(
                Arrays.asList(
                        Conditions.VERY_COLD,
                        Conditions.DARK,
                        Conditions.ORE_ABUNDANT,
                        Conditions.RARE_ORE_ABUNDANT,
                        Conditions.VOLATILES_ABUNDANT
                )
        ));

        system.autogenerateHyperspaceJumpPoints(true, true);
        cleanup(system);
    }

    private void cleanup(StarSystemAPI system) {
        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin();
        NebulaEditor editor = new NebulaEditor(plugin);
        float minRadius = plugin.getTileSize() * 2f;

        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius * 0.5f, 0, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f, 0.25f);
    }
}
