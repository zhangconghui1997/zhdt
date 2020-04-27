package com.bf.dt.util;
import com.bf.dt.result.SmsResult;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.SmsClient;


public class TxSmsUtil {
    private static final String SecretId = "AKIDVcUUFpo9WjgMbankuUosg6G8j3lSH3EK";
    private static final String SecretKey = "wc2C5OQrDJAl6CAreDaheTVHmIj0ED7m";
    private static final String Endpoint = "sms.tencentcloudapi.com";
    private static final String APPID = "1400357511";
    private static final String SIGN = "二手烟狙击地";
    private static final String TemplateID = "586768";

    public static SmsResult sendMsg(String message,String phone){

        try{

            Credential cred = new Credential(SecretId, SecretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint(Endpoint);
            httpProfile.setReqMethod("POST");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "ap-beijing", clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppid(APPID);
            req.setSign(SIGN);
            req.setTemplateID(TemplateID);
            String phoneNum = "+86" + phone;
            String[] phoneNumbers = {phoneNum};
            req.setPhoneNumberSet(phoneNumbers);
            String[] templateParams = {};
            req.setTemplateParamSet(templateParams);
            SendSmsResponse res = client.SendSms(req);
            System.out.println(SendSmsResponse.toJsonString(res));


        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }


        return null;
    }



}
