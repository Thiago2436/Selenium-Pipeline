package com.sol.qa.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author NaveenKhunteta
 * Created Date: Dec 25th, 2019
 * mail me at naveenanimation20@gmail.com in case of any PR or query
 * Licensed under NaveenAutomation Labs
 */

public class XlsReader {
  private String path;
  private FileInputStream fis = null;
  private FileOutputStream fileOut = null;
  private XSSFWorkbook workbook = null;
  private XSSFSheet sheet = null;
  private XSSFRow row = null;
  private XSSFCell cell = null;

  public XlsReader(final String pathFile) {

    this.path = pathFile;
    try {
      fis = new FileInputStream(pathFile);
      workbook = new XSSFWorkbook(fis);
      sheet = workbook.getSheetAt(0);
      fis.close();
      } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      }
    }
  // returns the row count in a sheet

  public final int getRowCount(final String sheetName) {
    int index = workbook.getSheetIndex(sheetName);
    if (index == -1) {
      return 0;
      } else {
      sheet = workbook.getSheetAt(index);
      int number = sheet.getLastRowNum() + 1;
      return number;
      }

    }

  /**
   * This method gets sheet name, column name , row number and returns cell data.
   */
  public String getCellData(final String sheetName, final String colName, final int rowNum) {

    try {
      if (rowNum <= 0) {
        return "";
        }
      int index = workbook.getSheetIndex(sheetName);
      int colNum = -1;
      if (index == -1) {
        return "";
        }
      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(0);
      for (int i = 0; i < row.getLastCellNum(); i++) {
        // System.out.println(row.getCell(i).getStringCellValue().trim());
        if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
          colNum = i;
          }
        }
      if (colNum == -1) {
        return "";
        }
      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(rowNum - 1);
      if (row == null) {
        return "";
        }
      cell = row.getCell(colNum);

      if (cell == null) {
        return "";
        }
      // System.out.println(cell.getCellType().name());
      //
      if (cell.getCellType().name().equals("STRING")) {
        return cell.getStringCellValue();
        } else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

        String cellText = (long) cell.getNumericCellValue() + "";

        // format in form of M/D/YY
        /*
         * double d = cell.getNumericCellValue(); System.out.println(d); Calendar cal =
         * Calendar.getInstance(); cal.setTime(HSSFDateUtil.getJavaDate(d)); cellText =
         * (String.valueOf(cal.get(Calendar.YEAR))); cellText =
         * cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" +
         * cellText;
         */

        return cellText;
        } else {
        cell.getCellType();
        if (CellType.BLANK != null) {
          return "";
          } else {
          return String.valueOf(cell.getBooleanCellValue());
          }
        }

      } catch (Exception e) {

      e.printStackTrace();
      return "row " + rowNum + " or column " + colName + " does not exist in xls";
      }
    }

  /**
   * Code has been updated as per new POI version - 4.x.x.
   * @author
   * @param sheetName
   * @param colNum
   * @param rowNum
   * @return
   */
  // returns the data from a cell
  public String getCellData(final String sheetName, final int colNum, final int rowNum) {
    try {
      if (rowNum <= 0) {
        return "";
        }

      int index = workbook.getSheetIndex(sheetName);

      if (index == -1) {
        return "";
        }
      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(rowNum - 1);
      if (row == null) {
        return "";
        }
      cell = row.getCell(colNum);
      if (cell == null) {
        return "";
        }

      //
      if (cell.getCellType().name().equals("STRING")) {
        return cell.getStringCellValue();
        } else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

        String cellText = NumberToTextConverter.toText(cell.getNumericCellValue());

        if (HSSFDateUtil.isCellDateFormatted(cell)) {
          // format in form of M/D/YY
          double d = cell.getNumericCellValue();

          Calendar cal = Calendar.getInstance();
          cal.setTime(HSSFDateUtil.getJavaDate(d));
          cellText = (String.valueOf(cal.get(Calendar.YEAR)));
          cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

          // System.out.println(cellText);

          }

        return cellText;
        } else {
        cell.getCellType();
        if (CellType.BLANK != null) {
          return "";
          } else {
          return String.valueOf(cell.getBooleanCellValue());
          }
        }
      } catch (Exception e) {

      e.printStackTrace();
      return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
      }
    }

  /**
   * This method gets sheet name, column name , row number and it enter the data given as parameter.
   */
  public boolean setCellData(final String sheetName, final String colName, final int rowNum, final String data) {
    try {
      fis = new FileInputStream(path);
      workbook = new XSSFWorkbook(fis);

      if (rowNum <= 0) {
        return false;
        }

      int index = workbook.getSheetIndex(sheetName);
      int colNum = -1;
      if (index == -1) {
        return false;
        }

      sheet = workbook.getSheetAt(index);

      row = sheet.getRow(0);
      for (int i = 0; i < row.getLastCellNum(); i++) {
        // System.out.println(row.getCell(i).getStringCellValue().trim());
        if (row.getCell(i).getStringCellValue().trim().equals(colName)) {
          colNum = i;
          }
        }
      if (colNum == -1) {
        return false;
        }

      sheet.autoSizeColumn(colNum);
      row = sheet.getRow(rowNum - 1);
      if (row == null) {
        row = sheet.createRow(rowNum - 1);
        }

      cell = row.getCell(colNum);
      if (cell == null) {
        cell = row.createCell(colNum);
        }

      // cell style
      // CellStyle cs = workbook.createCellStyle();
      // cs.setWrapText(true);
      // cell.setCellStyle(cs);
      cell.setCellValue(data);

      fileOut = new FileOutputStream(path);

      workbook.write(fileOut);

      fileOut.close();

      } catch (Exception e) {
      e.printStackTrace();
      return false;
      }
    return true;
    }
  // returns true if data is set successfully else false
  // public boolean setCellData(String sheetName,String colName,int rowNum,
  // String data,String url){
  // //System.out.println("setCellData setCellData******************");
  // try{
  // fis = new FileInputStream(path);
  // workbook = new XSSFWorkbook(fis);
  //
  // if(rowNum<=0)
  // return false;
  //
  // int index = workbook.getSheetIndex(sheetName);
  // int colNum=-1;
  // if(index==-1)
  // return false;
  //
  //
  // sheet = workbook.getSheetAt(index);
  // //System.out.println("A");
  // row=sheet.getRow(0);
  // for(int i=0;i<row.getLastCellNum();i++){
  // //System.out.println(row.getCell(i).getStringCellValue().trim());
  // if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
  // colNum=i;
  // }
  //
  // if(colNum==-1)
  // return false;
  // sheet.autoSizeColumn(colNum);
  // row = sheet.getRow(rowNum-1);
  // if (row == null)
  // row = sheet.createRow(rowNum-1);
  //
  // cell = row.getCell(colNum);
  // if (cell == null)
  // cell = row.createCell(colNum);
  //
  // cell.setCellValue(data);
  // XSSFCreationHelper createHelper = workbook.getCreationHelper();
  //
  // //cell style for hyperlinks
  // //by default hypelrinks are blue and underlined
  // CellStyle hlink_style = workbook.createCellStyle();
  // XSSFFont hlink_font = workbook.createFont();
  // hlink_font.setUnderline(XSSFFont.U_SINGLE);
  // hlink_font.setColor(IndexedColors.BLUE.getIndex());
  // hlink_style.setFont(hlink_font);
  // //hlink_style.setWrapText(true);
  //
  // XSSFHyperlink link = createHelper.createHyperlink(Xls_Reader.LINK_FILE);
  // link.setAddress(url);
  // cell.setHyperlink(link);
  // cell.setCellStyle(hlink_style);
  //
  // fileOut = new FileOutputStream(path);
  // workbook.write(fileOut);
  //
  // fileOut.close();
  //
  // }
  // catch(Exception e){
  // e.printStackTrace();
  // return false;
  // }
  // return true;
  // }

  /**
   * This method gets sheet name and it add the sheet.
   */
  public boolean addSheet(final String sheetname) {
    try {
      workbook.createSheet(sheetname);
      fileOut = new FileOutputStream(path);
      workbook.write(fileOut);
      fileOut.close();
      } catch (Exception e) {
      e.printStackTrace();
      return false;
      }
    return true;
    }

  /**
   * This method gets sheet name and it remove that sheet.
   */
  public boolean removeSheet(final String sheetName) {
    int index = workbook.getSheetIndex(sheetName);
    if (index == -1) {
      return false;
      }
    try {
      workbook.removeSheetAt(index);
      fileOut = new FileOutputStream(path);
      workbook.write(fileOut);
      fileOut.close();
      } catch (Exception e) {
      e.printStackTrace();
      return false;
      }
    return true;
    }

  /**
   * This method gets sheet name and column name and add one column.
   */
  public boolean addColumn(final String sheetName, final String colName) {
    // System.out.println("**************addColumn*********************");

    try {
      fis = new FileInputStream(path);
      workbook = new XSSFWorkbook(fis);
      int index = workbook.getSheetIndex(sheetName);
      if (index == -1) {
        return false;
        }

      XSSFCellStyle style = workbook.createCellStyle();
      // style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
      // style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

      sheet = workbook.getSheetAt(index);

      row = sheet.getRow(0);
      if (row == null) {
        row = sheet.createRow(0);
        }

      // cell = row.getCell();
      // if (cell == null)
      // System.out.println(row.getLastCellNum());
      if (row.getLastCellNum() == -1) {
        cell = row.createCell(0);
        } else {
        cell = row.createCell(row.getLastCellNum());
        }

      cell.setCellValue(colName);
      cell.setCellStyle(style);

      fileOut = new FileOutputStream(path);
      workbook.write(fileOut);
      fileOut.close();

      } catch (Exception e) {
      e.printStackTrace();
      return false;
      }

    return true;

    }

  /**
   * This method gets termName , column Number as parameter and it removes particular column.
   */
  public boolean removeColumn(final String sheetName, final int colNum) {
    try {
      if (!isSheetExist(sheetName)) {
        return false;
        }
      fis = new FileInputStream(path);
      workbook = new XSSFWorkbook(fis);
      sheet = workbook.getSheet(sheetName);
      XSSFCellStyle style = workbook.createCellStyle();
      // style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
      XSSFCreationHelper createHelper = workbook.getCreationHelper();
      // style.setFillPattern(XSSFCellStyle.NO_FILL);
      for (int i = 0; i < getRowCount(sheetName); i++) {
        row = sheet.getRow(i);
        if (row != null) {
          cell = row.getCell(colNum);
          if (cell != null) {
            cell.setCellStyle(style);
            row.removeCell(cell);
            }
          }
        }
      fileOut = new FileOutputStream(path);
      workbook.write(fileOut);
      fileOut.close();
      } catch (Exception e) {
      e.printStackTrace();
      return false;
      }
    return true;

    }

  /**
   * This method gets sheetName as parameter and returns whether sheet name exists or not.
   */
  public boolean isSheetExist(final String sheetName) {
    int index = workbook.getSheetIndex(sheetName);
    if (index == -1) {
      index = workbook.getSheetIndex(sheetName.toUpperCase());
      return false;
      }  else {
      return true;
      }
    }
  /**
   * This method gets sheetName , column Name , row Name as parameter and returns cell data time.
   */
  public String getCellDataTime(final String sheetName, final int colNum, final int rowNum) {
    try {
      if (rowNum <= 0) {
        return "";
        }

      int index = workbook.getSheetIndex(sheetName);
      //colNum = -1;
      if (index == -1) {
        return "";
        }

      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(0);

      if (colNum == -1) {
        return "";
        }

      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(rowNum - 1);
      if (row == null) {
        return "";
        }
      cell = row.getCell(colNum);

      if (cell == null) {
        return "";
        }

      // System.out.println(cell.getCellType().name());
      //
      if (cell.getCellType().name().equals("STRING")) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
                // Dates count as numeric and must be handled separately
        if (DateUtil.isCellDateFormatted(cell)) {
          timeFormat.format(cell.getDateCellValue());
          }
        return timeFormat.format(cell.getDateCellValue());
        } else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
        // Dates count as numeric and must be handled separately
        if (DateUtil.isCellDateFormatted(cell)) {
          timeFormat.format(cell.getDateCellValue());

          }
        // System.out.println(timeFormat.format(cell.getDateCellValue()));

        return timeFormat.format(cell.getDateCellValue()).replace("am", "AM").replace("pm", "PM");
        } else {
        cell.getCellType();
        if (CellType.BLANK != null) {
          return "";
          } else {
          return String.valueOf(cell.getBooleanCellValue());
          }
        }
      } catch (Exception e) {

      e.printStackTrace();
      return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
      }

    }
  /**
   * This method gets sheetName , column Name , row Name as parameter and returns cell data value.
   */
  public String getCellDate(final String sheetName, final String colName, final int rowNum) {

    try {
      if (rowNum <= 0) {
        return "";
        }

      int index = workbook.getSheetIndex(sheetName);
      int colNum = -1;
      if (index == -1) {
        return "";
        }

      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(0);
      for (int i = 0; i < row.getLastCellNum(); i++) {
        // System.out.println(row.getCell(i).getStringCellValue().trim());
        if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
          colNum = i;
          }
        }
      if (colNum == -1) {
        return "";
        }

      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(rowNum - 1);
      if (row == null) {
        return "";
        }
      cell = row.getCell(colNum);

      if (cell == null) {
        return "";
        }

      // System.out.println(cell.getCellType().name());
      //
      if (cell.getCellType().name().equals("STRING")) {
        return cell.getStringCellValue();
        } else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

        // Dates count as numeric and must be handled separately
        if (DateUtil.isCellDateFormatted(cell)) {
          timeFormat.format(cell.getDateCellValue());

          }

        // System.out.println(timeFormat.format(cell.getDateCellValue()));

        return timeFormat.format(cell.getDateCellValue());
        } else {
        cell.getCellType();
        if (CellType.BLANK != null) {
          return "";
          } else {
          return String.valueOf(cell.getBooleanCellValue());
          }
        }
      } catch (Exception e) {

      e.printStackTrace();
      return "row " + rowNum + " or column " + colName + " does not exist  in xls";
      }

    }

  /**
   * This method gets cell sheetname and returns cell column total count.
   */
  public int getColumnCount(final String sheetName) {
    // check if sheet exists
    if (!isSheetExist(sheetName)) {
      return -1;
      }

    sheet = workbook.getSheet(sheetName);
    row = sheet.getRow(0);

    if (row == null) {
      return -1;
      }

    return row.getLastCellNum();

    }

  // String sheetName, String testCaseName,String keyword ,String URL,String
  // message
  // public boolean addHyperLink(String sheetName,String
  // screenShotColName,String testCaseName,int index,String url,String
  // message){
  // //System.out.println("ADDING addHyperLink******************");
  //
  // url=url.replace('\\', '/');
  // if(!isSheetExist(sheetName))
  // return false;
  //
  // sheet = workbook.getSheet(sheetName);
  //
  // for(int i=2;i<=getRowCount(sheetName);i++){
  // if(getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)){
  // //System.out.println("**caught "+(i+index));
  // setCellData(sheetName, screenShotColName, i+index, message,url);
  // break;
  // }
  // }
  //
  //
  // return true;
  // }

  /**
   * This method gets cell sheetname , col no and row no and returns cell row number.
   */
  public int getCellRowNum(final String sheetName, final String colName, final String cellValue) {

    for (int i = 2; i <= getRowCount(sheetName); i++) {
      if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
        return i;
        }
      }
    return -1;

    }

  /**
   * This method gets cell sheetname , col no and row no and returns cell data number.
   */
  public int getCellDataNumber(final String sheetName, final int colNum, final int rowNum) {
    int cellText = 0;
    try {

      if (rowNum <= 0) {
        return cellText;
        }

      int index = workbook.getSheetIndex(sheetName);

      if (index == -1) {
        return cellText;
        }

      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(rowNum - 1);
      if (row == null) {
        return cellText;
        }
      cell = row.getCell(colNum);
      if (cell == null) {
        return cellText;
        }

      if ((cell.getCellType().name().equals("NUMERIC"))) {

        cellText = (int) cell.getNumericCellValue();
        return cellText;
        }
      } catch (Exception e) {

      e.printStackTrace();

      }
    return cellText;
    }

  public final String getPath() {
    return path;
    }
  }
