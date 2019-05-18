package jiabao.plane_wars;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.StatementEvent;

public class SPSaveInfo {

    //保存用户昵称到data.xml文件中
    public static boolean SaveUserName(Context context,String name, String num){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userNum", num);
        editor.putString("userName",name);
        editor.commit();
        return true;
    }

    //保存用户成绩到data.xml文件中
    public static boolean SaveUserGrade(Context context,String grade){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userGrade", grade);
        editor.commit();
        return true;
    }

    //从data.txt文件中获取存储的用户的昵称和分数
    public static Map<String,String > getUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String num = sp.getString("userNum", null);
        String name = sp.getString("userName", null);
        String grade = sp.getString("userGrade",null);
        Map<String,String> userMap = new HashMap<String,String>();
        userMap.put("num", num);
        userMap.put("name",name);
        userMap.put("grade",grade);
        return userMap;
    }
}