package com.innovation.rule;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.mdimension.jchronic.Chronic;
import com.mdimension.jchronic.utils.Span;
import com.wanasit.chrono.Chrono;
import com.wanasit.chrono.ParsedResult;

public class RuleEngine {
	
	
	public static Date parseSubject(String subject){
		Span span = Chronic.parse(subject);
		return span.getBeginCalendar().getTime();
	}
/*
	public static void main(String[] args) throws ParseException {

		
		
		Date result = Chrono.ParseDate("July 15, 2014 6:41 p.m. ET");
		// result == Tue Jul 15 18:41:00 CDT 2014

		List<ParsedResult> results = Chrono.Parse("July 15, 2014 6:41 p.m. ET");
		// results.get(0).text == "July 15, 2014 6:41 p.m. ET"
		// results.get(0).start.date() == Tue Jul 15 18:41:00 CDT 2014
		// results.get(0).end == null
		//System.out.println(results.get(0));

		result = Chrono.ParseDate("last day next month");
		//System.out.println(result);
		
		Span span = Chronic.parse("this month end");
		System.out.println(span.toString());
		
		//DateFormat dateFormat = new SimpleDateFormat();
		//Date date = dateFormat.parse(span.toString());
		
		System.out.println(span.getBeginCalendar().getTime());
		// results.get(0).index == 16
		// results.get(0).text == "November 11-13"
		// results.get(0).start.date() == Tue Nov 11 12:00:00 CST 2014
		// results.get(0).end.date() == Thu Nov 13 12:00:00 CST 2014
	}*/
}
