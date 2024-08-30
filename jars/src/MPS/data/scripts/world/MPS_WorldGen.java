package MPS.data.scripts.world;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import java.util.ArrayList;


public class MPS_WorldGen implements SectorGeneratorPlugin {

    /**
     * 为行星添加条件
     *
     * @param primaryEntity
     * @param allConditions
     * @return
     */
    public static MarketAPI addPlanetCondition(SectorEntityToken primaryEntity, ArrayList<String> allConditions) {
        MarketAPI CMarket = primaryEntity.getMarket();
        for (String condition : allConditions) {
            CMarket.addCondition(condition);
        }
        return CMarket;
    }

    /**
     * 为行星添加各类条件·
     *
     * @param factionID
     * @param primaryEntity
     * @param connectedEntities
     * @param name
     * @param size
     * @param marketConditions
     * @param submarkets
     * @param industries
     * @param tarrif
     * @param freePort
     * @param withJunkAndChatter
     * @return
     */
    public static MarketAPI addMarketplace(String factionID, SectorEntityToken primaryEntity,
                                           ArrayList<SectorEntityToken> connectedEntities, String name,
                                           int size, ArrayList<String> marketConditions, ArrayList<String> submarkets, ArrayList<String> industries,
                                           float tarrif,
                                           boolean freePort, boolean withJunkAndChatter) {
        EconomyAPI globalEconomy = Global.getSector().getEconomy();
        String planetID = primaryEntity.getId();
        String marketID = planetID + "_market";

        MarketAPI newMarket = Global.getFactory().createMarket(marketID, name, size);
        newMarket.setFactionId(factionID);
        newMarket.setPrimaryEntity(primaryEntity);
        newMarket.getTariff().modifyFlat("generator", tarrif);

        // Adds submarkets
        if (null != submarkets) {
            for (String market : submarkets) {
                newMarket.addSubmarket(market);
            }
        }

        // Adds market conditions
        for (String condition : marketConditions) {
            newMarket.addCondition(condition);
        }

        // Add market industries
        for (String industry : industries) {
            newMarket.addIndustry(industry);
        }

        // Sets us to a free port, if we should
        newMarket.setFreePort(freePort);

        // Adds our connected entities, if any
        if (null != connectedEntities) {
            for (SectorEntityToken entity : connectedEntities) {
                newMarket.getConnectedEntities().add(entity);
            }
        }

        globalEconomy.addMarket(newMarket, withJunkAndChatter);
        primaryEntity.setMarket(newMarket);
        primaryEntity.setFaction(factionID);

        if (null != connectedEntities) {
            for (SectorEntityToken entity : connectedEntities) {
                entity.setMarket(newMarket);
                entity.setFaction(factionID);
            }
        }

        // Finally, return the newly-generated market
        return newMarket;
    }

    @Override
    public void generate(SectorAPI sector) {
        new MPS_Arakyr().generate(sector);
    }
}
