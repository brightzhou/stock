package stock.test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.zeekie.stock.entity.HistoryOperationDO;
import com.zeekie.stock.util.FloatJsonValueProcessor;

public class TestUtils {

	public static void main(String[] args) {
		
		
//		DecimalFormat df = new DecimalFormat(); 
//		 df.setMaximumFractionDigits(3);
//		 df.setRoundingMode(RoundingMode.HALF_UP);
//		 df.applyPattern("#,#######.###");
//		 Float f = Float.parseFloat("8956.101");
//		 
//		 String reslt =df.format(f);
//		 System.out.println(reslt);
//		LocaleTest.print(); 
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Float.class,
				new FloatJsonValueProcessor());

		List li = new ArrayList();
		HistoryOperationDO do1 = new HistoryOperationDO();
		Float f = Float.parseFloat("8956.101");
		do1.setActualFund(f);
		do1.setAssetId("66565");

//		HistoryOperationDO do2 = new HistoryOperationDO();
//		f = Float.parseFloat("0.0014");
//		do2.setActualFund(f);
//		do2.setAssetId("23232");

		li.add(do1);
//		li.add(do2);
		JSONArray array = JSONArray.fromObject(li, jsonConfig);
		array.getJSONObject(0).get("actualFund");
		float s = Float.parseFloat(array.getJSONObject(0).getString("actualFund"))+1;
		System.out.println(s+":"+s+1);
	}

	static class LocaleTest {
		static public void print() {
			String countries[] = { "China", "Canada", "Korea", "Taiwan",
					"France", "Italy", "Japan", "Germany", "US", "UK","CHINESE","SIMPLIFIED_CHINESE" };
			Locale locale[] = { Locale.CHINA, Locale.CANADA, Locale.KOREA,
					Locale.TAIWAN, Locale.FRANCE, Locale.ITALY, Locale.JAPAN,
					Locale.GERMANY, Locale.US, Locale.UK,Locale.CHINESE,Locale.SIMPLIFIED_CHINESE };
			int len = countries.length;
			for (int i = 0; i < len; ++i) {
				System.out.println(countries[i]
						+ ": "
						+ NumberFormat.getNumberInstance().format(120000));
			}
		}
	}

}
