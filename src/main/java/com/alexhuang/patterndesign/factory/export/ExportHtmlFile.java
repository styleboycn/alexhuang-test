package com.alexhuang.patterndesign.factory.export;

public class ExportHtmlFile implements IExportFile {

	public boolean export(String data) {
		// TODO Auto-generated method stub
		System.out.println("excute ExportHtmlFile 's export(), data : " + data);
		return true;
	}

}
