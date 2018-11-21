package com.aaa.sm.controller;

import com.aaa.sm.entity.Emp;
import com.aaa.sm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * className:EmpController
 * discriptoin:
 * author:zz
 * createTime:2018-11-09 16:19
 */
@Controller
@RequestMapping("/emp")
public class EmpController {

   //依赖注入
    @Autowired
    private EmpService empService;


    private ResourceLoader resourceLoader;

    @Autowired
    public EmpController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${upload.path}")
    private String filePath; // D:/images/


    /**
     * 跳转上传
     * @return
     */
    @RequestMapping("/toUpload")
    public String toUpload(){
        return "picupload";
    }

    /**
     * 跳转登录
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    /**
     * 上传
     * @param filePic
     * @return
     */
    @RequestMapping("/picUpload")
    public String picUpload(Emp emp,@RequestParam MultipartFile filePic){
        String s = uploadFile(filePic, filePath);
        emp.setPicPath(s);
        /*//获取原文件名称
        String originalFilename = filePic.getOriginalFilename();
        //获取原文件名称后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //组装新名称
        String newFileName = UUID.randomUUID()+suffix;
        System.out.println("...."+emp.getEname());
        //创建文件
        File file  =new File("D:/images/", newFileName);//D:/images/1.jpg
       *//* if(!file.exists()){
            file.mkdirs();//创建父目录
        }*//*
        try {
            //文件读写
            filePic.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        empService.add(emp);
        return "emplist";
    }

    public String uploadFile(MultipartFile oldfile,String savePath){
        //获取原文件名称
        String originalFilename = oldfile.getOriginalFilename();//1.jpg
        //获取原文件名称后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));//.jpg
        //组装新名称
        String newFileName = UUID.randomUUID()+suffix;//随机串.jpg

        //创建文件
        File file  =new File(savePath, newFileName);//D:/images/随机串.jpg
       /* if(!file.exists()){
            file.mkdirs();//创建父目录
        }*/
        try {
            //文件读写
            oldfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
      return newFileName;
    }

    @RequestMapping("show")
    public ResponseEntity show(String fileName){
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + filePath + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
    /**
     * 跳转列表
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){
        return "demo3.14";
    }


    /**
     * 调用存储过程，根据部门编号返回人员列表
     * @param deptNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/proList")
    public Object getProListByDeptNo(Integer deptNo){
        return empService.getProListByDeptNo(deptNo);
    }
    /**
     * 员工列表
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list(@RequestParam Map map){
      return  empService.getList(map);
    }

    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Emp emp){
        System.out.println(emp.getComm());
      return "1";
    }
}
