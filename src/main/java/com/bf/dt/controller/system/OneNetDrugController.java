package com.bf.dt.controller.system;

import com.bf.dt.entity.OneNetDrug;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.system.OneNetDrugService;
import com.bf.dt.util.FileUtil;
import com.bf.dt.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RequestMapping("oneNetDrug")
@RestController
public class OneNetDrugController {

    @Autowired
    OneNetDrugService oneNetDrugService;

    @RequestMapping(value = "/fileUpload")
    @ResponseBody
    public MsgResult fileUpload(MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String filename = file.getOriginalFilename();
        String renameFile = FileUtil.renameFile(filename);
        String fileDirPath = new String(FileUtil.dImg);
        File fileDir = new File(fileDirPath);
        File newFile = new File(fileDir.getAbsolutePath() + File.separator + renameFile);
        try {
            file.transferTo(newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return MsgResult.success("200","/img/drug/"+renameFile,"ok");
}

    @RequestMapping(value = "/dDescUpload")
    @ResponseBody
        public  MsgResult dDescUpload(MultipartFile dDesc) {
        String s = dDesc.getOriginalFilename().substring(dDesc.getOriginalFilename().lastIndexOf('.'));
        System.out.println(s);
        File localFile = null;
        String descName = new Date().getTime()+s;
        try {
            if (dDesc.isEmpty()) {
                System.out.println("文件为空");
            }

            localFile = new File(FileUtil.dDesc,descName);
            dDesc.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  MsgResult.success("200",descName,"ok");
    }


    @RequestMapping(value = "/dDescDownload")

    public void dDescDownload(String descName, HttpServletResponse response) {
        // 文件地址，真实环境是存放在数据库中的
        String path = FileUtil.dDesc+descName;
        File file = new File(path);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        if (file.exists()) {
        try {
                response.setContentType("application/force-download");
                // 设置下载后的文件名以及header
                response.addHeader("Content-disposition", "attachment;fileName=" + descName);
                byte[] buf = new byte[1024];
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                // 创建输出对象
                OutputStream os = response.getOutputStream();
                int i = bis.read(buf);
                while (i != -1) {
                    os.write(buf, 0, i);
                    i = bis.read(buf);
                }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        }

    }





    @RequestMapping(value = "/addDrug")
    @ResponseBody
    public MsgResult addDrug(String file, String dDesc, String dName) {
        MsgResult msgResult = new MsgResult();
        try {
            OneNetDrug oneNetDrug = new OneNetDrug();
            oneNetDrug.setUuid(UUIDUtil.getUUID());
            oneNetDrug.setDrugName(dName);
            oneNetDrug.setDrugImg(file);
            oneNetDrug.setDrugDesc(dDesc);
            msgResult = oneNetDrugService.addDrug(oneNetDrug);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msgResult;

    }


    @RequestMapping(value = "/drugList")
    @ResponseBody
    public MsgResult drugList() {
        MsgResult msgResult = oneNetDrugService.drugList();
        return msgResult;
    }





}
