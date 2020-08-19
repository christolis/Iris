package com.volmit.iris.object;

import com.volmit.iris.util.Desc;
import com.volmit.iris.util.DontObfuscate;
import com.volmit.iris.util.MaxNumber;
import com.volmit.iris.util.MinNumber;
import com.volmit.iris.util.Required;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Desc("Represents a post processor")
@Data
@EqualsAndHashCode(callSuper = false)
public class IrisPostProcessor
{
	@Required
	@MinNumber(0)
	@MaxNumber(8)
	@DontObfuscate
	@Desc("The phase to run this filter in. Filters in the same phase iterate across x z chunks all at once per block. Seperate phases run another entire iteration across the chunk after the previous phase has finished.")
	private int phase = 0;

	@Required
	@DontObfuscate
	@Desc("The processor to use. Take a look at the list of processors in docs.")
	private String processor = "";

	public IrisPostProcessor()
	{
		this("");
	}

	public IrisPostProcessor(String f, int p)
	{
		this.phase = p;
		this.processor = f;
	}

	public IrisPostProcessor(String f)
	{
		this(f, 0);
	}
}