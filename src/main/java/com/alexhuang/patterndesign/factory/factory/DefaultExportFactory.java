package com.alexhuang.patterndesign.factory.factory;

import com.alexhuang.patterndesign.factory.export.ExportHtmlFile;
import com.alexhuang.patterndesign.factory.export.ExportlPdfFile;
import com.alexhuang.patterndesign.factory.export.IExportFile;

public class DefaultExportFactory implements IExportFactory {

    public IExportFile factory(String type) {
		if ("html".equals(type)) {
			return new ExportHtmlFile();
		} else if ("pdf".equals(type)) {
			return new ExportlPdfFile();
		} else {
			throw new RuntimeException("not found object !!!");
		}
    }
}
