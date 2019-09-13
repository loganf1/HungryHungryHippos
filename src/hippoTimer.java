public class hippoTimer 
{
	long start = 0, delay = 0;
	
	public hippoTimer (long delay)
	{
		start = System.currentTimeMillis();
		this.delay = delay;
	}
	
	public boolean isExpired()
	{
		return (System.currentTimeMillis() > (start + delay));
	}
	
}