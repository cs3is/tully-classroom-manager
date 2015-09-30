public class CountDown implements Runnable
{
	private ClientInformation cI = null;
	private boolean canAsk = false;
	long sleepTime;
	
	CountDown(ClientInformation cI, boolean canAsk,long sleepTime)
	{
		this.cI = cI;
		this.canAsk = canAsk;
		this.sleepTime = sleepTime;
		cI.setWaitTime(sleepTime);
		System.out.println("count down timer " + sleepTime);
	}
	
	public void run()
	{
		long startTime = (long)(System.nanoTime()/1000000000L);
		while(true)
		{
			try
			{
					if(cI.getWaitTime()<=0)
					{
						cI.setWaitTime(0);
						cI.setCanAsk(canAsk,ClientInformation.NO_WAIT);
						break;
					}
					else
					{
						long endTime = (long)(System.nanoTime()/1000000000L);
						long elapsedTime = endTime-startTime;				
						cI.setWaitTime(sleepTime-elapsedTime);
					}
					try
					{
						Thread.sleep(40);
					}
					catch(Exception e)
					{}
					
			}
			catch(Exception e)
			{}
			
		}
		
		
	}
}