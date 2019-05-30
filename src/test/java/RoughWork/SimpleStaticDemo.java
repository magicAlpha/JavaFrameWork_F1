package RoughWork;

public class SimpleStaticDemo {
	
	int empid;
	String employeeName;
	static String CEOName;
	
	 static String str="mukesh";
	 
	
	public static void main(String[] args) {
		
		SimpleStaticDemo objSimpleStaticDemo=new SimpleStaticDemo();
		/*
		 * objSimpleStaticDemo.empid=20; objSimpleStaticDemo.employeeName="Deepak";
		 * SimpleStaticDemo.CEOName="Shiv nadar";
		 */
		objSimpleStaticDemo.str="harish";
		
		SimpleStaticDemo objSimpleStaticDemo1=new SimpleStaticDemo();
		/*
		 * objSimpleStaticDemo1.empid=24; objSimpleStaticDemo1.employeeName="Deepu";
		 * SimpleStaticDemo.CEOName="Rahul";
		 */
		
		objSimpleStaticDemo1.str="Nitin";
		
		/*
		 * objSimpleStaticDemo.getDetails(); objSimpleStaticDemo1.getDetails();
		 */
		
		System.out.println(str);
	}

	/*
	 * public void getDetails() { System.out.println("Employee ID "+empid+
	 * "\nEmployee name "+employeeName+"\nCEO is "+CEOName);
	 * 
	 * }
	 */
}
