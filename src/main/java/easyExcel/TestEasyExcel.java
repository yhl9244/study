package easyExcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestEasyExcel {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("D:\\user.xlsx");
            ExcelReader reader = EasyExcelFactory.getReader(fis, new UserExcelListener());
            reader.read(new Sheet(1,0, User.class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
