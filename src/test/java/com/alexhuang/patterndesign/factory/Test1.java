package com.alexhuang.patterndesign.factory;

import com.alexhuang.patterndesign.factory.export.IExportFile;
import com.alexhuang.patterndesign.factory.factory.DefaultExportFactory;
import com.alexhuang.patterndesign.factory.factory.IExportFactory;

public class Test1 {

	public static void main(String[] args) {
		
        IExportFactory exportFactory = new DefaultExportFactory();
        IExportFile ef = exportFactory.factory("pdf");
        
        String data = "this is export data";
        ef.export(data);
        
    }
	
}
