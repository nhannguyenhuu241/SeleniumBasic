package testfile;

import callback.onDataShowSuccess;
import config.ReadingExelFile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test01 {

    List<String> values = new ArrayList<String>();

    @BeforeTest
    public void setupTest() throws IOException, InterruptedException {
        String filePath = "/Users/nhanne/Desktop/ReadingExcel/src/main/java/config";
        ReadingExelFile exelFile = new ReadingExelFile(new onDataShowSuccess() {
            public void onSuccessGetData(List<String> value) {
                values = value;
            }
        });
        exelFile.readExcel(filePath, "ExportExcel.xls", "Sheet1");
    }

    @Test
    public void inputInfo() throws InterruptedException {
        for (int i = 0; i < values.size(); i++) {
            if (i!= 0 && i % 3 == 0) {
                System.out.print("\n" + values.get(i) + "\t");
            } else {
                System.out.print(values.get(i) + "\t");
            }
            Thread.sleep(1000);
        }
    }

    @AfterTest
    public void endTest() {

    }
}
