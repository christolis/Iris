package com.volmit.iris.v2.generator.actuator;

import com.volmit.iris.v2.scaffold.engine.Engine;
import com.volmit.iris.v2.scaffold.engine.EngineAssignedActuator;
import com.volmit.iris.v2.scaffold.hunk.Hunk;
import org.bukkit.block.Biome;

public class IrisBiomeActuator extends EngineAssignedActuator<Biome>
{
    public IrisBiomeActuator(Engine engine) {
        super(engine, "Biome");
    }

    @Override
    public void onActuate(int x, int z, Hunk<Biome> h) {
        int i,zf;
        Biome v;

        for(int xf = 0; xf < h.getWidth(); xf++)
        {
            for(zf = 0; zf < h.getDepth(); zf++)
            {
                v = getComplex().getTrueBiomeDerivativeStream().get(xf+x, zf+z);

                for(i = 0; i < h.getHeight(); i++)
                {
                    h.set(xf, i, zf, v);
                }
            }
        }
    }
}
