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
	 * */
	private void dP() {
		System.out.println("dp");
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
