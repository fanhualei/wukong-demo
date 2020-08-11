package com.wukong.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testng.annotations.Test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("unchecked")
public class FileControllerTests extends AbstractTestNGSpringContextTests {


    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseUrl="/example/hello/";

    @Test(priority = 0)
    public  void testUpload(){
        String url=baseUrl+"upload";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data; charset=UTF-8");
        headers.setContentType(type);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
        URL path=HelloControllerTests.class.getResource("/");
        FileSystemResource fileSystemResource1 = new FileSystemResource(path.getPath()+"upload1.txt");
        form.add("testPara","paraValue");
        form.add("file", fileSystemResource1);
        ResponseEntity<String> entity = this.restTemplate.postForEntity(url, form,String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(entity.getBody());
    }




    @Test(priority = 1)
    public  void testUploadBatch(){
        String url=baseUrl+"upload/batch";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data; charset=UTF-8");
        headers.setContentType(type);

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();

        URL path=HelloControllerTests.class.getResource("/");

        FileSystemResource fileSystemResource1 = new FileSystemResource(path.getPath()+"upload1.txt");
        FileSystemResource fileSystemResource2 = new FileSystemResource(path.getPath()+"上传文件2.txt");
        form.add("testPara","paraValue");
        form.add("file", fileSystemResource1);
        form.add("file", fileSystemResource2);

        ResponseEntity<String> entity = this.restTemplate.postForEntity(url, form,String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(entity.getBody());
    }

    @Test(priority = 2)
    public void testDelFile() {
        String url=baseUrl+"delFile?fileName=upload1.txt";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("ok");
    }


    //@Test
    public void testDownLoad() throws Exception {
        String url =baseUrl+"download";
        HttpHeaders headers = new HttpHeaders();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            List list = new ArrayList();
            list.add(MediaType.valueOf("application/octet-stream"));
            headers.setAccept(list);

            ResponseEntity<byte[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<byte[]>(headers),
                    byte[].class);

            byte[] result = response.getBody();

            inputStream = new ByteArrayInputStream(result);

            URL path = HelloControllerTests.class.getResource("/");
            File file = new File(path.getPath() + "downloadfile.png");
            if (!file.exists()) {
                file.createNewFile();
            }

            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }


        }
    }

}
