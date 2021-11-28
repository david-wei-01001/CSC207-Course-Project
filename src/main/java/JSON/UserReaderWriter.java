package JSON;

import User.UserInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserReaderWriter extends ReaderWriter{

    public static UserInfo readerForUser(String fileName) throws IOException, ClassNotFoundException{
        String filepath = "/user1.json";
        String data = ReaderWriter.reader(filepath);
        JSONObject jobj = JSON.parseObject(data);
        // initialize userinfo
        UserInfo userinfo = new UserInfo(jobj.get("userName").toString(),
                jobj.get("email").toString(),
                jobj.get("password").toString());
        // set advanced userinfo
        userinfo.setRewardPoints(Integer.parseInt(jobj.get("rewardPoints").toString()));
        userinfo.setLastLogin(LocalDate.parse(jobj.get("lastLogin").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        userinfo.setTotalLogins(Integer.parseInt(jobj.get("totalLogins").toString()));
        // TODO: set graph info for user
        return userinfo;
    }

}