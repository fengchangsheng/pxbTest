package com.bronzesoft.pxb.imports.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bronzesoft.pxb.neixun.model.NeixunProject;
import com.bronzesoft.pxb.neixun.service.INeixunService;
import com.bronzesoft.pxb.neixun.service.impl.NeixunService;
import com.bronzesoft.pxb.platform.core.controller.BaseController;

import jxl.Sheet;
import jxl.Workbook;

/**
 * @author Lucare.Feng
 * 导入Excel模板数据
 * 2015年7月17日
 * Excel中格式如下   设置单元格格式为日期AM 下面的那种
 * 姓名	开始时间	                                             结束时间
 * 张嘎子	2015/4/23 15:00	  2015/4/23 20:00
 * 刘根子	2015/4/23 13:00	  2015/7/15 10:00
 * 胖墩子	2015/7/17 13:23	  2015/7/17 16:23
 *
 */
@Controller
@RequestMapping("/imp")
public class ImpController extends BaseController{

	@Resource(name = "neixunService")
	private INeixunService neixunService;
	
	@RequestMapping("/doimp")
	@ResponseBody
	public String doImp(){
		try  
        {  
            //实例化一个工作簿对象  
          Workbook workBook = Workbook.getWorkbook(new File("E://内训项目表.xls"));  
          //获取该工作表中的第一个工作表  
          Sheet sheet = workBook.getSheet(0);  
          //获取该工作表的行数，以供下面循环使用  
          int rowSize = sheet.getRows();  
          for(int i=1;i<=rowSize;i++)  
          {  
              String name = sheet.getCell(1,i).getContents();  
              //来信人姓名  
//              Date startDate = java.sql.Date.valueOf(sheet.getCell(2,i).getContents());    
              SimpleDateFormat sf=new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
              String date1 = sheet.getCell(2,i).getContents();
              String date2 = sheet.getCell(3,i).getContents();

              Date startDate = sf.parse(date1);            
              //来信人单位或住址  
//              Date endDate = java.sql.Date.valueOf(sheet.getCell(3,i).getContents());  
              Date endDate = sf.parse(date2);  
              NeixunProject project = new NeixunProject(name,startDate,endDate);
              neixunService.saveNeixunProject(project);
               //执行保存数据到数据库语句…….  
              System.out.print("已成功导入第"+i+"条纪录");  
          }  
        } catch(Exception ex){  
            System.out.print(ex.getMessage());  
        }  
		return "import success!";
	}
	
	
	@RequestMapping("/read")
	public String read(ModelMap mm){
		try {
			List<NeixunProject> projects = neixunService.searchNeixunProject(null);
			mm.addAttribute("projects", projects);
			return "test";
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":searchProject()", e);
		}
		return "";
	}
}
