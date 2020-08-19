package com.volmit.iris.object;

import org.bukkit.block.data.BlockData;

import com.volmit.iris.gen.atomics.AtomicCache;
import com.volmit.iris.noise.CNG;
import com.volmit.iris.util.ArrayType;
import com.volmit.iris.util.B;
import com.volmit.iris.util.Desc;
import com.volmit.iris.util.DontObfuscate;
import com.volmit.iris.util.KList;
import com.volmit.iris.util.RNG;
import com.volmit.iris.util.Required;

import lombok.Data;

@Desc("Find and replace object materials")
@Data
public class IrisObjectReplace
{
	@ArrayType(min = 1, type = String.class)
	@Required
	@Desc("Find this block")
	@DontObfuscate
	private KList<String> find;

	@ArrayType(min = 1, type = String.class)
	@Required
	@Desc("Replace it with this block")
	@DontObfuscate
	private KList<String> replace;

	@Desc("Exactly match the block data or not")
	@DontObfuscate
	private boolean exact = false;

	private transient AtomicCache<CNG> replaceGen = new AtomicCache<>();
	private transient AtomicCache<KList<BlockData>> findData = new AtomicCache<>();
	private transient AtomicCache<KList<BlockData>> replaceData = new AtomicCache<>();

	public IrisObjectReplace()
	{

	}

	public KList<BlockData> getFind()
	{
		return findData.aquire(() -> B.getBlockData(find));
	}

	public KList<BlockData> getReplace()
	{
		return replaceData.aquire(() -> B.getBlockData(replace));
	}

	public BlockData getReplace(RNG seed, double x, double y, double z)
	{
		return replaceGen.aquire(() -> NoiseStyle.STATIC.create(seed).bake()).fit(getReplace(), x, y, z);
	}
}