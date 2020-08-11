package com.wukong.examples.controller;

import com.wukong.core.storage.StorageProperties;
import com.wukong.core.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Path;
import java.util.List;

/**
 * 有如下：
 *  2.7 保存上传文件
 *  2.8 删除上传文件
 * @author fanhl
 */
@RestController
@RequestMapping("/example/hello")
@Slf4j
public class FileController {
    @Autowired
    private StorageService storageService;

    @Autowired
    private StorageProperties storageProperties;



    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return saveFile(file);
    }


    private String saveFile(MultipartFile file){
        String subPath = "examples";
        // 还有关于文件格式限制、文件大小限制，详见：中配置。
        Path unixPath= storageService.store(file,subPath);
        String httpUrlPath = StringUtils.replace(unixPath.toString()
                ,storageService.getRootLocation().toString(),"");
        return storageProperties.getImgsRootPath()+httpUrlPath;
    }

    @PostMapping("/upload/batch")
    public String batchUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file ;
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                sb.append(saveFile(file)).append("\n");
            }
        }
        return sb.toString();
    }

    @RequestMapping("/delFile")
    public String delFile(@RequestParam String  fileName) {
        String filePath =storageProperties.getLocation() + "/examples/" + fileName;
        File file = new File(filePath);
        file.getAbsoluteFile().deleteOnExit();
        return "ok";
    }


    /**
     * 下载文件
     * http://127.0.0.1:8080/hello/download?filename=/examples/上传文件2.txt
     * @param filename 文件名不能包含 imgs那个名称
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> getFile(String filename){
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=\""+file.getFilename()+"\"")
                .body(file);
    }


    /**
     * 下载文件  这个可以保证有些文件，必须要登录后，才能看到
     * 这个函数是不会被执行了，因为static-locations将这个目录配置成静态文件
     * @param filename
     * @return
     */
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        //当前这个函数是错误的，应该del 重复的imgs目录
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
