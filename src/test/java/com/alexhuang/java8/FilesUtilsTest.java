package com.alexhuang.java8;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.alexhuang.utils.dto.PvsProductConfig;

//origin file : TestProductConfigConvert.java
public class FilesUtilsTest {

	private static PvsProductConfig pvsconfig;

	static {
		try {
			pvsconfig = new PvsProductConfig(new String(Files.readAllBytes(Paths.get("src/test/resources/pvsProductConfig.txt"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(sgsConfigCacheHelper.convertPvsCodeToBarCode("T1"));
//		System.out.println(pvsconfig.getProductCode("S2", "T6", "T6"));
		PvsProductConfig.Dto dto = pvsconfig.getCodes("S2");
		System.out.println(dto.getProductCargoType());
		PvsProductConfig.Dto dto2 = pvsconfig.getCodes("S1-DOC");
		System.out.println(dto2.getProductCargoType());
	}

}
