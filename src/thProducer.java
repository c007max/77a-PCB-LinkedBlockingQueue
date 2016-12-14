import java.util.concurrent.LinkedBlockingQueue	;
import java.util.Random	;

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
		Random r1	= new Random()	;
		
		while (!QReady.isEmpty())
		{
			try 
			{
				pcb	= QReady.take()	;
			}	
			catch (InterruptedException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (r1.nextGaussian()	> 1.282)	//	10%
			{
				try 
				{
					QWait.put(pcb)	;
					System.out.printf	("@@@prod\t PCB: %s\n"	,pcb.get_ID())	;

					Thread.sleep(500);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				pcb.add_CPU_used(r1.nextInt(20)+5)	;
				
				if (pcb.get_CPU_left()>0)
				{
					QReady.add(pcb)	; 
					System.out.printf	
							("@@@prod\t PCB %d left: %d\n"	
							,pcb.get_ID()
							,pcb.get_CPU_left()
							)	;
				}
			}
		}

		System.out.printf	("@@@prod\t completed\n")	;
        running = false;
    }
}


