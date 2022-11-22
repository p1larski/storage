package storage.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import storage.ModelDTOs.ProductDto;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class JExcelHelper {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ProductDto> productList;

    public JExcelHelper(List<ProductDto> productList) {
                this.productList = productList;
                workbook = new XSSFWorkbook();
    }


        private void writeHeaderLine() {
                    sheet = workbook.createSheet("Products");

            Row row = sheet.createRow(0);

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);

            createCell(row, 0, "Product ID", style);
            createCell(row, 1, "Product Name", style);
            createCell(row, 2, "Date of Delivery", style);
            createCell(row, 3, "Left pieces in storage", style);
            createCell(row, 4, "Employee who reclaimed product", style);

}

        private void createCell(Row row, int columnCount, Object value, CellStyle style) {
            sheet.autoSizeColumn(columnCount);
            Cell cell = row.createCell(columnCount);
            if (value instanceof Long) {
            cell.setCellValue((Long) value);
                }
            else if (value instanceof LocalDate) {
            cell.setCellValue((LocalDate) value);
                }else {
            cell.setCellValue((String) value);
                }
            cell.setCellStyle(style);
                }

                    private void writeDataLines() {
            int rowCount = 1;

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontHeight(14);
            style.setFont(font);

        for (ProductDto product : productList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, product.getId(), style);
            createCell(row, columnCount++, product.getArticleName(), style);
            createCell(row, columnCount++, product.getDateOfDelivery(), style);
            createCell(row, columnCount++, product.getNumberOfProductInStorage(), style);
            createCell(row, columnCount++, product.getUsernameWhoReceived(), style);

}
}

        public void export(HttpServletResponse response) throws IOException {
                    writeHeaderLine();
                    writeDataLines();
                    ServletOutputStream outputStream = response.getOutputStream();
                    workbook.write(outputStream);
                    workbook.close();
                    outputStream.close();

}
}
