public class ClientData
{
	private long screenShotDelay 	= 0;
	private long timerWaitAsked		= -1;
	private long timerWaitUntil		= -1;
	
	private boolean canAsk			= false;
	private boolean lockDown		= false;
	private boolean internet		= false;
	private boolean controlled		= false;
	 
	public ClientData()
	{
	}
	
	public void update()
	{
	}
	
	public void setScreenShotDelay(long screenShotDelay)
	{	this.screenShotDelay = screenShotDelay;	}
	
	public void setTimerWaitAsked(long timerWaitAsked)
	{	this.timerWaitAsked = timerWaitAsked;	}
	
	public void setTimerWaitUntil(long timerWaitUntil)
	{	this.timerWaitUntil = timerWaitUntil;	}
	
	public void setCanAsk(boolean canAsk)
	{	this.canAsk = canAsk;	}
	
	public void setLockDown(boolean lockDown)
	{	this.lockDown = lockDown;	}
		
	public void setInternet(boolean internet)
	{	this.internet = internet;	}
		
	public void setControlled(boolean controlled)
	{	this.controlled = controlled;	}
	
	public long getScreenShotDelay()
	{	return screenShotDelay;	}
	
	public long getTimerWaitAsked()
	{	return timerWaitAsked;	}
	
	public long getTimerWaitUntil()
	{	return timerWaitUntil;	}
	
	public boolean getCanAsk()
	{	return canAsk;	}
	
	public boolean getLockDown()
	{	return lockDown;	}
		
	public boolean getInternet()
	{	return internet;	}
		
	public boolean getControlled()
	{	return controlled;	}	
}