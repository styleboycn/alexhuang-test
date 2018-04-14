package com.alexhuang.patterndesign.factory.export;

public class ExportlPdfFile implements IExportFile {

	public boolean export(String data) {
		// TODO Auto-generated method stub
		System.out.println("excute ExportlPdfFile 's export(), data : " + data);
        return true;
	}
}
