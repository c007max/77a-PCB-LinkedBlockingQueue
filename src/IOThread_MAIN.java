import java.util.concurrent.LinkedBlockingQueue;

public class IOThread_MAIN
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException
	{
		LinkedBlockingQueue<PCB> QReady	= new LinkedBlockingQueue<PCB>();
		LinkedBlockingQueue<PCB> QWait	= new LinkedBlockingQueue<PCB>();
		
		thProducer	producer	= new thProducer	(QReady	,QWait	)	;
		thConsumer	consumer	= new thConsumer	(QWait	,producer	)	;
		
		Thread	producerThread	= new Thread	(producer)	;
		Thread	consumerThread	= new Thread	(consumer)	;
		
		int nodes_T	= 5 ;
				
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

		consumerThread.join()	;
		
		System.out.printf("@@@\tdone\t@@@\n");
	}
}
