package com.innovation.rule;

import java.util.Date;

import com.mdimension.jchronic.Chronic;
import com.mdimension.jchronic.utils.Span;

public class RuleEngine {

	public static Date parseSubject(String subject) {
		Span span = Chronic.parse(subject);
		return span.getBeginCalendar().getTime();
	}
}
