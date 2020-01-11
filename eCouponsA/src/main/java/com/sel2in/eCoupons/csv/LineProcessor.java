package com.sel2in.eCoupons.csv;

import java.util.List;

/**
 * Sent line from CSV to process.
 * */
public interface LineProcessor {
	void proc(String line, List<String> columns, long lineNumber);
	
}
