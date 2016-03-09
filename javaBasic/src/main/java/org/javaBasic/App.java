package org.javaBasic;

import java.lang.reflect.Method;
import java.math.BigDecimal;

public class App {
	private static String className = "org.javaBasic.App";
	public enum Action{
		add,subtract,multiple,divide;
	}

	@enumDes(methodClass="App",methodName="testWeekDay")
	public void testWeekDay() {
		WeekDay test = WeekDay.Friday;
		System.out.println(test+" "+test.ordinal());
		for(WeekDay day : WeekDay.values())
			System.out.print(day+" "+day.ordinal()+"\t");
	}
	
	@enumDes(methodClass="App",methodName="testWeekDayDes")
	public void testWeekDayDes() {
		WeekDayDes des = WeekDayDes.Friday;
		System.out.println(des.ordinal());
		System.out.println(des.getDes());
		for(WeekDayDes day : WeekDayDes.values()) {
			System.out.println(day.ordinal()+"\t"+day.getDes());
		}
	}
	
	@enumDes(methodClass="App",methodName="testBigDecimal")
	public void testBigDecimal(String typeEnum, String arg1, String arg2) {
		Action type = Action.valueOf(Action.class,typeEnum);
		System.out.println("===================example action=================");
		System.out.println("0.05+0.01="+(0.05+0.01));
		System.out.println("1.0-0.42="+(1.0-0.42));
		System.out.println("4.015*100="+(4.015*100));
		System.out.println("123.3/100="+123.3/100);
		System.out.println("==============before===============");
		BigDecimal b1 = new BigDecimal(arg1);
		BigDecimal b2 = new BigDecimal(arg2);
		switch(type) {
		case add:
			System.out.println(arg1+"+"+arg2+"="+(Double.valueOf(arg1)+Double.valueOf(arg2)));
			System.out.println(arg1+"+"+arg2+"="+b1.add(b2).doubleValue());
			break;
		case subtract:
			System.out.println(arg1+"-"+arg2+"="+(Double.valueOf(arg1)-Double.valueOf(arg2)));
			System.out.println(arg1+"-"+arg2+"="+b1.subtract(b2).doubleValue());
			break;
		case multiple:
			System.out.println(arg1+"*"+arg2+"="+(Double.valueOf(arg1)*Double.valueOf(arg2)));
			System.out.println(arg1+"*"+arg2+"="+b1.multiply(b2).doubleValue());
			break;
		case divide:
			System.out.println(arg1+"/"+arg2+"="+(Double.valueOf(arg1)/Double.valueOf(arg2)));
			System.out.println(arg1+"/"+arg2+"="+b1.divide(b2).doubleValue());
			break;
		}
		System.out.println("================end=================");
	}
	
	public static void info(String method,String ... strings ) {
		try{
			Class cl = Class.forName(className);
			Object obj = cl.newInstance();
			Method m;
			if(strings.length == 0)
				m = cl.getMethod(method);
			else{
				Class[] args = new Class[strings.length];	
				for(int i = 0; i < strings.length; i++)
					args[i] = String.class;
				m = cl.getMethod(method, args);
			}
			enumDes des = m.getAnnotation(enumDes.class);
			System.out.println("当前执行的是"+des.methodClass()+"的"+des.methodName());
			if(strings.length == 0)
				m.invoke(obj);
			else
				m.invoke(obj,strings);
		}catch (Exception e) {
			System.out.println(method+" execute wrong");
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		App.info("testWeekDay");
		App.info("testWeekDayDes");
		App.info("testBigDecimal","divide","123.3","100");
	}

}
