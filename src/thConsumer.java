import java.util.concurrent.LinkedBlockingQueue;

public class thConsumer implements Runnable
{
	private LinkedBlockingQueue<PCB>	QReady	;
	private LinkedBlockingQueue<PCB>	QWait	;
	private	thProducer	producer	;
	
	//	constructor method
	
	public thConsumer	(LinkedBlockingQueue<PCB> qr0
						,LinkedBlockingQueue<PCB> qw0
						,thProducer	prod0
						)
	{
		this.QReady	= qr0	;
		this.QWait	= qw0	;
		this.producer	= prod0	;
	}
	
	@Override
    public void run() 
	{	
		PCB	pcb	= null	;
		
		while	(producer.isRunning() || !QWait.isEmpty())
		{
			try 
			{
				pcb	= QWait.take()	;
				System.out.printf	("###Cons: -QWait\t PCB: %s\n"	,pcb.get_ID())	;
				
				QReady.put(pcb)	;
				System.out.printf	("###Cons: +QReady\t PCB: %s\n"	,pcb.get_ID())	;

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


