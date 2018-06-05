package com.alexhuang.patterndesign.factory.factory;

import com.alexhuang.patterndesign.factory.export.ExportFinancialPdfFile;
import com.alexhuang.patterndesign.factory.export.ExportStandardPdfFile;
import com.alexhuang.patterndesign.factory.export.IExportFile;

public class ExportPdfFactory implements IExportFactory {

	@Override
	public IExportFile factory(String type) {
		// TODO Auto-generated method stub
		if ("standard".equals(type)) {
			return new ExportStandardPdfFile();
		} else if ("financial".equals(type)) {
			return new ExportFinancialPdfFile();
		} else {
			throw new RuntimeException("没有找到对象");
		}
	}
}
