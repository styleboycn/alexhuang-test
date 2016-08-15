package com.alexhuang.patterndesign.factory.factory;

import com.alexhuang.patterndesign.factory.export.ExportFile;
import com.alexhuang.patterndesign.factory.export.ExportStandardHtmlFile;
import com.alexhuang.patterndesign.factory.export.ExportStandardPdfFile;

public class ExportPdfFactory implements ExportFactory {

    public ExportFile factory(String type) {
        if("standard".equals(type)){
            return new ExportStandardPdfFile();         
        }else if("financial".equals(type)){       
            return new ExportStandardHtmlFile();      
        }else{
            throw new RuntimeException("没有找到对象");
        }
    }
}
