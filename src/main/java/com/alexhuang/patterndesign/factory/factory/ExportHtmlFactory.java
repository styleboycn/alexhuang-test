package com.alexhuang.patterndesign.factory.factory;

import com.alexhuang.patterndesign.factory.export.ExportFinancialHtmlFile;
import com.alexhuang.patterndesign.factory.export.ExportStandardHtmlFile;
import com.alexhuang.patterndesign.factory.export.IExportFile;

public class ExportHtmlFactory implements IExportFactory {

	@Override
	public IExportFile factory(String type) {
		// TODO Auto-generated method stub
		if ("standard".equals(type)) {
			return new ExportStandardHtmlFile();
		} else if ("financial".equals(type)) {
			return new ExportFinancialHtmlFile();
		} else {
			throw new RuntimeException("没有找到对象");
		}
	}
}
