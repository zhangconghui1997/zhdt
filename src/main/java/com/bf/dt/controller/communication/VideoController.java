package com.bf.dt.controller.communication;

import com.bf.dt.util.VideoUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RequestMapping("video")
@RestController
public class VideoController {

    @RequestMapping("openVideo")
    public String openVideo(String room,String user,String passwd){

            long time = (long)1588929482;
        try {
            String userID = VideoUtil.createUserID(room, user);
            String token = VideoUtil.createToken("3a205b2b", "0b54e725a3cfd22b288f70c624e712bf", room, userID, "AK-2b9be4b25c2d38c409c376ffd2372be1", time);
            System.out.println(token);
            return token;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

}
