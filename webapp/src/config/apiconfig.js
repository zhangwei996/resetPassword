//接口配置
var BaseUrl2;
if(process.env.NODE_ENV == 'production'){
  BaseUrl2=window.host +"/mobiapi/";
}
else{
  BaseUrl2="http://localhost:8082/mobiapi/";
}
export default {
  ResetPsd:{
    GetAllClassList: BaseUrl2 + "GetAllClassList",
    GetAllStuList:BaseUrl2 + "GetAllStuList",
    SavePwd: BaseUrl2 + "SavePwd",
    GetUserInfo: BaseUrl2 + "GetUserInfo",
  }


}

