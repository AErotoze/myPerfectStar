package MPS.scripts;

import MPS.scripts.world.MPS_WorldGen;
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

public class MPS_ModPlugin extends BaseModPlugin {
    @Override
    public void onNewGame() {
        new MPS_WorldGen().generate(Global.getSector());
    }

    @Override
    public void onApplicationLoad() throws Exception {
    }
}
