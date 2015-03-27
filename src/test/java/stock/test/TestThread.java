package stock.test;

import java.util.ArrayList;
import java.util.List;

public class TestThread {

	private static List<String> list = new ArrayList<String>();

	public static void main(String args[]) {

		for (int i = 0; i < 20; i++) {
			list.add("" + i);
		}

		TicketSouce mt = new TicketSouce();

		for (int j = 0; j < 13; j++) {
			new Thread(mt, "thread" + j).start();
		}
	}

	public static List<String> getList() {
		return list;
	}

	public static void remove(String item) {

		synchronized (list) {
			list.remove(item);
		}
	}
}
