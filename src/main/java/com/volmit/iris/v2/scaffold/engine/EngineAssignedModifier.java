package com.volmit.iris.v2.scaffold.engine;

import com.volmit.iris.util.PrecisionStopwatch;
import com.volmit.iris.util.RollingSequence;
import com.volmit.iris.v2.scaffold.hunk.Hunk;
import lombok.Getter;

public abstract class EngineAssignedModifier<T> extends EngineAssignedComponent implements EngineModifier<T>
{
    public EngineAssignedModifier(Engine engine, String name)
    {
        super(engine, name);
    }

    public abstract void onModify(Hunk<T> output);

    @Override
    public void modify(Hunk<T> output) {
        onModify(output);
    }
}
