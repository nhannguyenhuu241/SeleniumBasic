package config;

import callback.onDataShowSuccess;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadingExelFile {

    onDataShowSuccess mCallback;
    public ReadingExelFile(onDataShowSuccess callback) {
        this.mCallback = callback;
    }
    List<String> array = new ArrayList<String>();

    public void readExcel(String filePath, String fileName, String sheetName) throws IOException, InterruptedException {

        //Create an object of File class to open xlsx file

        File file = new File(filePath + "/" + fileName);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = null;

        //Find the file extension by splitting file name in substring  and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if (fileExtensionName.equals(".xlsx")) {

            //If it is xlsx file then create object of XSSFWorkbook class

            workbook = new XSSFWorkbook(inputStream);

        }

        //Check condition if the file is xls file

        if (fileExtensionName.equals(".xls")) {

            //If it is xls file then create object of HSSFWorkbook class

            workbook = new HSSFWorkbook(inputStream);

        }

        //Read sheet inside the workbook by its name

        Sheet guru99Sheet = workbook.getSheet(sheetName);

        //Find number of rows in excel file

        int rowCount = guru99Sheet.getLastRowNum() - guru99Sheet.getFirstRowNum();

        //Create a loop over all the rows of excel file to read it

        for (int i = 0; i < rowCount + 1; i++) {

            Row row = guru99Sheet.getRow(i);
            //Create a loop to print cell values in a row

            for (int j = 0; j < row.getLastCellNum(); j++) {
                //Print Excel data in console
                String value = row.getCell(j).getStringCellValue();
                array.add(value);
            }
            mCallback.onSuccessGetData(array);

        }
    }
}
