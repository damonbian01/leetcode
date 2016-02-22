package org.palindromic;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * 查找最大回文，暴力解法，时间复杂度n^3,动态规划，时间复杂度 n^2
 *
 */
public class App 
{
	/**
	 * 动态规划解法
	 * 0-0 1-1 ...
	 * 0-1 1-2 ...
	 * s[i,j] <====> s[i-1]s[i,j-1]s[j]
	 * 
	 * */
	private void dP() {
		//默认最大回文的开始和结束 ,end-start+1表示最大回文的长度
		int start = 0;
		int end = 0;
		
		int a[][] = new int[2][2];
		
		String str = "123";
		int size = str.length();
		//标记动态规划的矩阵
		boolean [][] arr = new boolean[size][size];
		for(int i = 0; i < size; i++) {
			arr[i][i] = true;
			
			if(i < size - 1) {
				if(str.charAt(i) == str.charAt(i+1)) {
					arr[i][i+1] = true;
					start = i;
					end = i+1;
				}
				else
					arr[i][i+1] = false;
			}
		}
		
		//回文长度从3开始到size，一一进行标记
		for(int i = 3; i < size+1; i++) {
			for(int j = 0; j <= size - i; j++) {
				if(arr[j+1][j+i-2] == true && str.charAt(j) == str.charAt(j+i-1)) {
					arr[j][j+i-1] = true;
					if(j+i-1 - j > end - start) {
						start = j;
						end = j+i-1;
					}
				} else
					arr[j][j+i-1] = false;
			}
		}
		System.out.println(" dp\n start is:"+start+" end is:"+end+"\n substr is:"+str.substring(start, end+1));
	}
	
	
	/**
	 * 暴力解法
	 */
	private void violence() {
		System.out.println("violence");
	}
	
    public static void main( String[] args )
    {
    	String help = "-t means different method ,0(default) means dynamic programming";
    	CommandLineParser cli = new BasicParser();
    	Options options = new Options();
    	options.addOption("h", "help", false, "help message");
    	options.addOption("t", "type", true, "method to work");
    	
    	int type = 0;
    	
    	try {
			CommandLine commandLine = cli.parse(options, args);
			if(commandLine.hasOption('h')) {
				System.out.println(help);
				System.exit(0);;
			}
			if(commandLine.hasOption('t')) {
				type = Integer.parseInt(commandLine.getOptionValue('t'));
			}
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("parse params error");
			System.exit(-1);
		}
    	
    	App start = new App();
    	switch (type) {
		case 0:
			start.dP();
			break;
		case 1:
			start.violence();
			break;
		default:
			System.out.println("not correct params!");
			System.exit(0);
			break;
		}
    }
}
