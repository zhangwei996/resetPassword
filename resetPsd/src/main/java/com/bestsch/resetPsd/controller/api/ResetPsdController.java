package com.bestsch.resetPsd.controller.api;

//import com.bestsch.resetPsd.Config;
//import com.bestsch.resetPsd.controller.BaseController;
//import com.bestsch.resetPsd.model.Student;
//import com.bestsch.resetPsd.model.Teacher;
//import com.bestsch.resetPsd.service.ResetPsdService;
//import com.bestsch.resetPsd.util.Page;
//import org.bouncycastle.jcajce.provider.symmetric.TEA;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

///**
// * @author Mr.ZhangW
// * @date 2020/3/12 11:15
// * @description:重置密码
// */
//@RestController
//@RequestMapping(Config.API_PATH)
//public class ResetPsdController extends BaseController {
//
//    @Autowired
//    private ResetPsdService resetPsdService;
//
//    /**
//     * 获取所有的学生列表
//     * @param page
//     * @param count
//     * @param teacher
//     * @return
//     */
//    @GetMapping("GetStudentList")
//    public Object getStudentList(@RequestParam("page") String page,
//                                 @RequestParam("count") String count,
//                                 Teacher teacher){
//        Page pageVO = getPageVO(page, count);
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        Pageable pageable = PageRequest.of(pageVO.getCurrent() - 1, pageVO.getPageSize(), sort);
//        org.springframework.data.domain.Page<Teacher> all=resetPsdService.findAll(pageable,teacher);
//        Map<String,Object> data=new HashMap<>();
//        data.put("studentList",all.getTotalElements());
//        return data;
//    }
//
//    /**
//     * 获取学生信息
//     * @param usrName
//     * @return
//     */
//    @GetMapping("GetStuMsg")
//    public List<Student> getStuMsg(String usrName){
//        return resetPsdService.getStuMsg(usrName);
//    }
//
//    /**
//     * 重置密码
//     * @param usrName
//     */
//    @PostMapping("SavePsd")
//    public List<Student> savePsd(String usrName){
//        resetPsdService.savePsd(usrName);
//        return resetPsdService.getStuMsg(usrName);
//    }
//
//
//}
//
