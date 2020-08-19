package com.volmit.iris.object;

import com.volmit.iris.util.DependsOn;
import com.volmit.iris.util.Desc;
import com.volmit.iris.util.DontObfuscate;
import com.volmit.iris.util.M;
import com.volmit.iris.util.MaxNumber;
import com.volmit.iris.util.MinNumber;
import com.volmit.iris.util.Required;

import lombok.Data;

@Desc("Represents a rotation axis with intervals and maxes. The x and z axis values are defaulted to disabled. The Y axis defaults to on, rotating by 90 degree increments.")
@Data
public class IrisAxisRotationClamp
{
	@DontObfuscate
	@Desc("Should this axis be rotated at all?")
	private boolean enabled = false;

	@Required
	@DependsOn({"max"})
	@MinNumber(-360)
	@MaxNumber(360)
	@DontObfuscate
	@Desc("The minimum angle (from) or set this and max to zero for any angle degrees. Set both to the same non-zero value to force it to that angle only")
	private double min = 0;

	@Required
	@DependsOn({"min"})
	@MinNumber(-360)
	@MaxNumber(360)
	@DontObfuscate
	@Desc("The maximum angle (to) or set this and min to zero for any angle degrees. Set both to the same non-zero value to force it to that angle only")
	private double max = 0;

	@Required
	@DependsOn({"min", "max"})
	@MinNumber(0)
	@MaxNumber(360)
	@DontObfuscate
	@Desc("Iris spins the axis but not freely. For example an interval of 90 would mean 4 possible angles (right angles) degrees. \nSetting this to 0 means totally free rotation.\n\nNote that a lot of structures can have issues with non 90 degree intervals because the minecraft block resolution is so low.")
	private double interval = 0;

	public IrisAxisRotationClamp()
	{

	}

	public IrisAxisRotationClamp(boolean enabled, double min, double max, double interval)
	{
		this.enabled = enabled;
		this.min = min;
		this.max = max;
		this.interval = interval;
	}

	public boolean isUnlimited()
	{
		return min == max && min == 0;
	}

	public double getRadians(int rng)
	{
		if(isUnlimited())
		{
			if(interval < 1)
			{
				interval = 1;
			}

			return Math.toRadians(((double) interval * (Math.ceil(Math.abs((rng % 360D) / (double) interval)))) % 360D);
		}

		if(min == max && min != 0)
		{
			return Math.toRadians(max);
		}

		return Math.toRadians(M.clip(((double) interval * (Math.ceil(Math.abs((rng % 360D) / (double) interval)))) % 360D, Math.min(min, max), Math.max(min, max)));
	}
}