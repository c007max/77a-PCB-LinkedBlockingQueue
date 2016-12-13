import java.util.concurrent.LinkedBlockingQueue;

public class thConsumer implements Runnable
{
	private LinkedBlockingQueue<PCB>	QWait	;
	private	thProducer	producer	;
	
	//	constructor method
	
	public thConsumer	(LinkedBlockingQueue<PCB> qw0
						,thProducer	prod0
						)
	{
		this.QWait	= qw0	;
		this.producer	= prod0	;
	}
	
	@Override
    public void run() 
	{		
		while	(producer.isRunning() || !QWait.isEmpty())
		{
			try 
			{
				System.out.printf	("###consumer removing\t PCB: %s\n"	,QWait.take().get_ID())	;

				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
      
		System.out.printf	("###consumer completed\n")	;
    }
}


