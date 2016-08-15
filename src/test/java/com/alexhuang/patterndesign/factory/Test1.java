package com.alexhuang.patterndesign.factory;

import com.alexhuang.patterndesign.factory.export.ExportFile;
import com.alexhuang.patterndesign.factory.factory.ExportFactory;
import com.alexhuang.patterndesign.factory.factory.ExportHtmlFactory;

public class Test1 {

	public static void main(String[] args) {
		
        String data = "";
        ExportFactory exportFactory = new ExportHtmlFactory();
        ExportFile ef = exportFactory.factory("financial");
        ef.export(data);
        
    }
	
}
