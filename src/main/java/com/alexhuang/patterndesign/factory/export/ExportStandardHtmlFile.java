package com.alexhuang.patterndesign.factory.export;

public class ExportStandardHtmlFile implements IExportFile {

	@Override
    public boolean export(String data) {
        // TODO Auto-generated method stub
        /**
         * 业务逻辑
         */
        System.out.println("导出标准HTML文件：" + data);
        return true;
    }

}
