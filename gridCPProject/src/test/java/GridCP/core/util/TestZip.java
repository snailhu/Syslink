package GridCP.core.util;

import java.util.List;

import org.junit.Test;

public class TestZip {

	@Test
	public void test() throws Exception{
		List<String> list = ZipUtil.unZipFiles("d:/xml/svg.zip", "d:/xml");
		for (String str : list) {
			System.out.println(str);
			String fileName = str.substring(str.lastIndexOf('/') + 1);
			str = str.substring(0, str.lastIndexOf('/'));
			String fileNextPath = str.substring(str.lastIndexOf('/') + 1);
			System.out.println("fileName: "  + fileName);
			System.out.println("fileNextPath: " + fileNextPath);
			System.out.println();
		}
//		String str = "D:/tongyuan/works/configuration/syslink/Cache/e1a2e0487de045f28ad30b77b8fea027/svg/TestPkg_Icon.svg";
	}

}
