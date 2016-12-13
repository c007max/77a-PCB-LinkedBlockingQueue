import java.util.concurrent.LinkedBlockingQueue;

public class thProducer implements Runnable
{
	private LinkedBlockingQueue<PCB>	QReady	;
	private LinkedBlockingQueue<PCB>	QWait	;

	private boolean	running	;
	
	//	constructor method
	
	public thProducer	(LinkedBlockingQueue<PCB> qr0
						,LinkedBlockingQueue<PCB> qw0
						)
	{
		this.QReady	= qr0	;
		this.QWait	= qw0	;
		this.running	= true	;
	}
	
	public boolean isRunning()
	{
		return running ;
	}
	
	@Override
    public void run() 
	{	
		PCB	pcb	= null	;
		while (!QReady.isEmpty())
		{
			try 
			{
				pcb	= QReady.take()	;
				QWait.put(pcb)	;
				System.out.printf	("@@@prod\t PCB: %s\n"	,pcb.get_ID())	;

				Thread.sleep(500);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}

		System.out.printf	("@@@prod\t completed\n")	;
        running = false;
    }
}


