package stock.test;

import java.util.List;
import java.util.Random;

public class TicketSouce implements Runnable {

	@Override
	public void run() {

		List<String> list = TestThread.getList();
		
		while(true) {
			
			String s = null;
			synchronized (list) {
				if (!list.isEmpty()) {
					System.out.println( (s = list.remove(0))  + Thread.currentThread());
				}
			}
			
			try {
				// 休眠1s秒中，为了使效果更明显，否则可能出不了效果
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(s != null) {
				if (new Random().nextInt(47) % 3 == 0) {
					synchronized (list) {
						list.add(s);
					}
				}
			}
			
		}

		/*for (int j = 0; j < list.size(); j++) {
			String item = "";
			synchronized (list) {
				item = list.remove(index)
			}

			try {
				// 休眠1s秒中，为了使效果更明显，否则可能出不了效果
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			TestThread.remove(item);
			System.out.println(Thread.currentThread().getName() + "号窗口卖出"
					+ item + "号票");
		}*/
	}

}
