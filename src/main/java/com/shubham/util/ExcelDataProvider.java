package com.shubham.util;
import com.shubham.util.appData;
import org.testng.annotations.DataProvider;
import com.shubham.util.ExcelObjectMapper;

import java.util.List;
import java.lang.reflect.Method;
public class ExcelDataProvider {

    @DataProvider(name = "excelData")
    public Object[][] getExcelData(java.lang.reflect.Method method) {
        // Get the test class name dynamically
        String testClassName = method.getDeclaringClass().getSimpleName();
        System.out.println("Sheet name (test class): " + testClassName);
        List<appData> list = ExcelObjectMapper.readExcel("src/test/resources/apptestdata.xlsx", testClassName, appData.class);

        Object[][] data = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);
        }

        return data;
    }
}
