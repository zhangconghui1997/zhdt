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
import java.io.File;
import java.io.IOException;
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
        String fileDirPath = new String("src/main/resources/static/img/drug");
        File fileDir = new File(fileDirPath);
        File newFile = new File(fileDir.getAbsolutePath() + File.separator + renameFile);
        try {
            file.transferTo(newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return MsgResult.success("200","/img/drug"+renameFile,"ok");
}

    @RequestMapping(value = "/dDescUpload")
    @ResponseBody
        public  MsgResult dDescUpload(MultipartFile dDesc) {
        File localFile = null;
        try {
            if (dDesc.isEmpty()) {
                System.out.println("文件为空");
            }
            String folder = "f:/drug";
            localFile = new File(folder,new Date().getTime() + ".txt");
            dDesc.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  MsgResult.success("200",localFile,"ok");
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
