import java.util.concurrent.LinkedBlockingQueue;

public class IOThread_MAIN
{
	public static void main(String[] args) throws InterruptedException
	{
		LinkedBlockingQueue<PCB> QReady	= new LinkedBlockingQueue<PCB>();
		LinkedBlockingQueue<PCB> QWait	= new LinkedBlockingQueue<PCB>();
		
		thProducer	producer	= new thProducer	(QReady	,QWait	)	;
		thConsumer	consumer	= new thConsumer	(QReady	,QWait	,producer	)	;

		Thread	producerThread	= new Thread	(producer)	;
		Thread	consumerThread	= new Thread	(consumer)	;
		
		int nodes_T	= 10 ;
				
		for (int ii=0; ii<nodes_T; ii++)
		{
			PCB pcbMain	= new PCB();
			QReady.add(pcbMain)	;
			
			System.out.printf("\t%d: %s\n"	,ii	,pcbMain.showPCB());
		}
		//	0090>>>>Initialization end
			
		//	0200	start producer and consumer threads
		
		producerThread.start()	;
		consumerThread.start()	;
		
		for (int ii=0; ii<nodes_T; ii++)
		{
			PCB pcbMain	= new PCB();
			QReady.add(pcbMain)	;
			
			System.out.printf("\t%d: %s\n"	,ii	,pcbMain.showPCB());
			Thread.sleep(250);

		}
		//	0300	complete

		while (Thread.activeCount()>1)
		{
			for (PCB loopI : QReady)
				System.out.printf("***\tmain-0400: %s\t***\n"	,loopI.showPCB()) ;
			
			Thread.sleep(1000)	;
		}
		
		System.out.printf("@@@\tdone\t@@@\n");
	}
}
