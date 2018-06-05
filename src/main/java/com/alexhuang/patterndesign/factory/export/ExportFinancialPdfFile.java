package com.alexhuang.patterndesign.factory.export;

public class ExportFinancialPdfFile implements IExportFile {

	@Override
    public boolean export(String data) {
        // TODO Auto-generated method stub
        /**
         * 业务逻辑
         */
        System.out.println("导出财务版PDF文件：" + data);
        return true;
    }

}
