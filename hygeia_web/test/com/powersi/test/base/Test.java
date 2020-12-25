package com.powersi.test.base;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author MissYoung
 * @version 0.1
 * @description
 * @date 2020-02-14 14:56
 */
public class Test {

    public static void main(String... args) throws UnsupportedEncodingException {
        /*String str = "listId=6325&payerId=3232&relation=3&listId=58623&payerId=232323&relation=6";
        String[] arr = str.split("&");
        List<Map<String, String>> list = new ArrayList<>(arr.length / 3);
        int num = arr.length / 3;
        for (int i = 0; i < num; i++) {
            Map<String, String> tempMap = new HashMap<>();
            for (int j = 0; j < 3; j++) {
                tempMap.put(arr[i * 3 + j].substring(0, arr[i * 3 + j].indexOf("=")), arr[i * 3 + j].substring(arr[i * 3 + j].indexOf("=") + 1));
            }
            list.add(tempMap);
        }
        System.out.println(list);

        String type = "00003|00004";
        String[] st = type.split("[|]");
        System.out.println(Arrays.toString(st));*/

        //uploadImageFile();
        //SFTPUtils.uploadFile("F:/Pictures/ball.jpg", "/test");
//        String str = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
//        String id = "430521198510119235";
//        System.out.println(id.matches(str));



//        String resultJson =  "{\"code\":0,\"type\":\"success\",\"message\":\"成功\",\"data\":{\"userName\":\"苏绪旺\",\"idNo\":\"433101198204050511\",\"idType\":\"01\",\"insuOrg\":\"430000\",\"ecIndexNo\":null,\"ecToken\":\"4300001e5gt85sa002fbbc160a0000d719799e\",\"gender\":null,\"birthday\":null,\"nationality\":null,\"email\":null}}";
//
//        Map map = (Map) JSON.parse(resultJson);
//
//        System.out.println(map);
//        JSONObject data = (JSONObject) map.get("data");
//        String id = (String) data.get("idNo");
//        System.out.println(id);
//
//        String str = "API鎵ц\uE511锛氭湇鍔℃墽琛屽彂鐢熷紓甯革紝閿欒\uE1E4鍙� API2715 淇℃伅锛�: 灏辫瘖鐧昏\uE187鏃跺彇浜哄憳淇℃伅鍑洪敊";
//        System.out.println(new String(str.getBytes("gbk"),"utf-8"));

        String reg = "(?!A0301)^(A01|A02|A03).*?";
        System.out.println("A012010".matches(reg));
        System.out.println("2A023".matches(reg));
        System.out.println("A02323".matches(reg));
        System.out.println("A04323".matches(reg));
        System.out.println("6666");

    }

    public static void uploadImageFile(){
        String sftpUser = "sftp";
        String sftpPassword = "sftp$123";
        String sftpHost = "172.18.100.133";
        //int sftpPort = 20000;

        ChannelSftp sftp = null;

        JSch jsch = new JSch();
        try {

            Session sshSession = jsch.getSession(sftpUser, sftpHost);
            sshSession.setPassword(sftpPassword);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            sftp = (ChannelSftp) channel;

            //sftp.cd("/uploadFile/pcloud_image/joborder/");
            sftp.cd("/uploadFile/test");
            File file = new File("ball.jpg");
            FileInputStream fis = new FileInputStream(file);
            sftp.put(fis, "test.jpeg");

            fis.close();

            sftp.getSession().disconnect();
            sftp.quit();
            sftp.disconnect();
        } catch (JSchException | SftpException | IOException e) {
            e.printStackTrace();
        }
    }
}
