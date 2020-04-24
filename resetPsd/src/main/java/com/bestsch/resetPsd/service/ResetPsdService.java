package com.bestsch.resetPsd.service;

import com.bestsch.openapi.BoaUser;
import com.bestsch.openapi.BschBaseOpenApi;
import com.bestsch.bschapistd.config.EnableBschApiStd;
import com.bestsch.utils.DHttp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author Mr.ZhangW
 * @date 2020/3/12 14:39
 * @description:
 */
@Service
public class ResetPsdService {

    @Autowired
    private BschBaseOpenApi bschBaseOpenApi;

    @Value("${bsch.open.schbase-host}")
    private String BSCH_SCHBASE_HOST;


    @Value("${bsch.open.base-host}")
    private String BSCH_BASE_HOST;


    /**
     * 获取所有的班级列表
     *
     * @param TeaID
     * @param schId
     * @return
     */
    /*public Object getAllClassList(Integer TeaID, Integer schId) {

        //判断是否是班主任
        String url = BSCH_SCHBASE_HOST + "/oapi/OATea/JudgeTeacherType?id=" + TeaID +
                "&schoolId=" + schId;
        String result = DHttp.get(url);

        int a = Integer.parseInt(result);

        if (a > 0) {
            //是班主任或者副班主任
            //获取班级列表
            String url2 = BSCH_SCHBASE_HOST + "/oapi/VSchClass/getClassByUserId?userId=" + TeaID +
                    "&schoolId=" + schId;

            String result2 = DHttp.get(url2);

            JSONArray jsonArray = JSONArray.fromObject(result2);
            List<Object> classList = new ArrayList<>();
            if (jsonArray.size() != 0) {
                //List<Object> list = new ArrayList<>();
                for (Object obj : jsonArray) {
                    HashMap<String, Object> classMap = new HashMap<>();
                    JSONObject jsonObject = JSONObject.fromObject(obj);
                    classMap.put("gradeName", jsonObject.getString("gradeName"));
                    classMap.put("className", jsonObject.getString("className"));
                    classMap.put("classID", jsonObject.getInt("id"));
//                    classMap.put("classID", jsonObject.get("ClassID"));
//                    classMap.put("classID", jsonObject.getInt("id"));
//                    classMap.put("name", jsonObject.getString("name"));
                    classList.add(classMap);
                }
            }
            return classList;

        } else return new ArrayList<>();

    }*/


    /**
     * 获取所有的学生列表
     *
     * @param ClassID
     * @return
     */
    public Object getAllStuList(Integer ClassID) {

        String url = BSCH_SCHBASE_HOST + "/oapi/DbClass/GetClassStu?ClassID=" + ClassID;

        String result = DHttp.get(url);

        JSONArray jsonArray = JSONArray.fromObject(result);
        List<Object> studentList = new ArrayList<>();
        if (jsonArray.size() != 0) {
            //List<Object> list = new ArrayList<>();
            for (Object obj : jsonArray) {
                HashMap<String, Object> map = new HashMap<>();
                JSONObject jsonObject = JSONObject.fromObject(obj);
                map.put("stuPhoto", BSCH_BASE_HOST + jsonObject.get("StuPhoto"));
                map.put("stuName", jsonObject.getString("StuName"));
                map.put("stuID", jsonObject.get("StuID"));
                map.put("userID", jsonObject.getInt("UserID"));
                //System.out.println(map);
                studentList.add(map);
            }
        }
        //studentList.add(map);

        return studentList;
    }


    /**
     * 修改密码
     *
     * @param userID
     * @param stuID
     */
    public void savePwd(Integer userID, String stuID) {//153480  dngjxxs20191301
        //无论如何都要修改用户中心的学生账号
        bschBaseOpenApi.updateUserPassword(userID, stuID + "@bsch");

        //判断学生账号是否存在于思维广场的数据库中，如果存在则也修改
        String url = "http://218.202.225.165:82/api/UserInfo.ashx?act=search&olduid=" + stuID;
        String result = DHttp.get(url);
        System.out.println(result);

        if (result.length() > 0) {
            System.out.println("进入了修改思维广场的接口");
            //说明该学生账号也属于思维广场的那个数据库里面
            //调用那边重置密码的接口
            String url2 = "http://218.202.225.165:82/api/UserInfo.ashx?act=update&olduid=" + stuID
                    + "&newuid=" + stuID + "&newupwd=" + stuID + "@bsch";
            String result2 = DHttp.get(url2);
            if (result2.equals("true")) {
                System.out.println("修改成功！");

            }

        }
    }

    //获取班主任班级
    public List<LinkedHashMap> getTeaLeadClass(Integer userIds, Integer schId) {
        //判断是否是班主任
        String url = BSCH_SCHBASE_HOST + "/oapi/OATea/JudgeTeacherType?id=" + userIds +
                "&schoolId=" + schId;
        String result = DHttp.get(url);

        int a = Integer.parseInt(result);

        if (a > 0) {
            //是班主任或者副班主任
            //获取班级列表
            String url2 = BSCH_SCHBASE_HOST + "/oapi/VSchClass/getClassByTeacherId?userIds=" + userIds;

            String result2 = DHttp.get(url2);

            JSONArray jsonArray = JSONArray.fromObject(result2);
            List<LinkedHashMap> classList = new ArrayList<>();
            if (jsonArray.size() != 0) {
                //List<Object> list = new ArrayList<>();
                for (Object obj : jsonArray) {
                    LinkedHashMap<Object, Object> classMap = new LinkedHashMap<>();

                    JSONObject jsonObject = JSONObject.fromObject(obj);
                    classMap.put("gradeName", jsonObject.getString("GradeName"));
                    classMap.put("className", jsonObject.getString("ClassName"));
                    classMap.put("classID", jsonObject.getInt("id"));
                    classList.add(classMap);
                }
            }
            return classList;

        } else return new ArrayList<>();

    }

    //获取副班主任班级
    public List<LinkedHashMap> getfulTeaLeadClass(int userIds, int schId) {
        //判断是否是班主任
        String url = BSCH_SCHBASE_HOST + "/oapi/OATea/JudgeTeacherType?id=" + userIds +
                "&schoolId=" + schId;
        String result = DHttp.get(url);

        int a = Integer.parseInt(result);

        if (a > 0) {
            //是班主任或者副班主任
            //获取班级列表
            String url2 = BSCH_SCHBASE_HOST + "/oapi/VSchClass/getFuClassByTeacherId?userIds=" + userIds;

            String result2 = DHttp.get(url2);

            JSONArray jsonArray = JSONArray.fromObject(result2);
            List<LinkedHashMap> fulClassList = new ArrayList<>();
            if (jsonArray.size() != 0) {
                //List<Object> list = new ArrayList<>();
                for (Object obj : jsonArray) {
                    LinkedHashMap<Object, Object> classMap = new LinkedHashMap<>();
                    JSONObject jsonObject = JSONObject.fromObject(obj);
                    classMap.put("gradeName", jsonObject.getString("GradeName"));
                    classMap.put("className", jsonObject.getString("ClassName"));
                    classMap.put("classID", jsonObject.getInt("id"));
                    fulClassList.add(classMap);
                }
            }
            return fulClassList;

        } else return new ArrayList<>();

    }


}

