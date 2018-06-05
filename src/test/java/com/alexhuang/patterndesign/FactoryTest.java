package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.factory.export.IExportFile;
import com.alexhuang.patterndesign.factory.factory.ExportHtmlFactory;
import com.alexhuang.patterndesign.factory.factory.IExportFactory;

/**
 * 有三个相关的工厂模式，如下：
《JAVA与模式》之工厂方法模式（常用）
https://www.cnblogs.com/java-my-life/archive/2012/03/25/2416227.html
《JAVA与模式》之简单工厂模式（常用）
https://www.cnblogs.com/java-my-life/archive/2012/03/22/2412308.html
《JAVA与模式》之抽象工厂模式
https://www.cnblogs.com/java-my-life/archive/2012/03/28/2418836.html
 *
 */

public class FactoryTest {

	public static void main(String[] args) {
		
        IExportFactory exportFactory = new ExportHtmlFactory();
        IExportFile ef = exportFactory.factory("financial");
        
        String data = "this is export data";
        ef.export(data);
        
    }
	
}
