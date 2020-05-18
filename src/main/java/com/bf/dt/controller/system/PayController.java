package com.bf.dt.controller.system;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.bf.dt.config.AppPayConfig;
import com.bf.dt.dao.system.UserMapper;
import com.bf.dt.service.system.UserService;
import com.bf.dt.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("pay")
public class PayController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AlipayClient alipayClient = new DefaultAlipayClient(AppPayConfig.WANGGUAN,AppPayConfig.APPID,AppPayConfig.SIYAO,"json",AppPayConfig.BIANMA,AppPayConfig.GONGYAO,"RSA2");
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AppPayConfig.SURL);
        alipayRequest.setNotifyUrl(AppPayConfig.CURL);

        //商户订单号，商户网站订单系统中唯一订单号，必填

        String out_trade_no = new String( "202005181529".getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填

        String total_amount = new String( "0.01".getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填

        String subject = new String( "dt".getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = new String( "dtdeil".getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String content = "app_id=2016102300745470&biz_content={\"out_trade_no\":\"202005181348\",\"total_amount\":\"0.01\",\"subject\":\"dt\"}&charset=UTF-8&format=json&method=alipay.trade.precreate&notify_url=http://notify.dengw.online/do/6e5e3bd0-c2c5-4565-bcfd-bf57ea822672&sign_type=RSA2&timestamp=2019-04-01 14:43:53&version=1.0";

        String form="";
        try {
            form = new String(alipayClient.pageExecute(alipayRequest).getBody().getBytes("ISO-8859-1"),"UTF-8"); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=" + AppPayConfig.BIANMA);
        System.out.println(form);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();







    }


    @RequestMapping("test")
    public void test(HttpServletRequest request){
        String name = request.getParameter("name");
        TimeUtil timeUtil = new TimeUtil();
        timeUtil.renwu(userMapper,"6",name);
    }


}
