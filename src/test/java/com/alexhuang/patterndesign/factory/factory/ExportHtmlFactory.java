package com.alexhuang.patterndesign.factory.factory;

import com.alexhuang.patterndesign.factory.export.ExportFile;
import com.alexhuang.patterndesign.factory.export.ExportFinancialHtmlFile;
import com.alexhuang.patterndesign.factory.export.ExportStandardHtmlFile;

public class ExportHtmlFactory implements ExportFactory {
    public ExportFile factory(String type) {
            if("standard".equals(type)){
            return new ExportStandardHtmlFile();
        }else if("financial".equals(type)){
            return new ExportFinancialHtmlFile();
        }else{
            throw new RuntimeException("没有找到对象");
        }
    }
}
