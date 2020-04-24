// 配置API接口地址
// var root = '/api/v1'
// var root = '/api/debugapi'
// var root = '/api'
var root = '/api'
// var root = ''
// 引用axios
var axios = require('axios');
// import Notice from 'iview/src/components/notice';
import {Message,Spin} from 'iview';

// 自定义判断元素类型JS
function toType (obj) {
  return ({}).toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase()
}
// 参数过滤函数
function filterNull (o) {
  for (var key in o) {
    if (o[key] === null) {
      delete o[key]
    }
    if (toType(o[key]) === 'string') {
      o[key] = o[key].trim()
    } else if (toType(o[key]) === 'object') {
      o[key] = filterNull(o[key])
    } else if (toType(o[key]) === 'array') {
      o[key] = filterNull(o[key])
    }
  }
  return o
}
/*
  接口处理函数
  这个函数每个项目都是不一样的，我现在调整的是适用于
  https://cnodejs.org/api/v1 的接口，如果是其他接口
  需要根据接口的参数进行调整。参考说明文档地址：
  https://cnodejs.org/topic/5378720ed6e2d16149fa16bd
  主要是，不同的接口的成功标识和失败提示是不一致的。
  另外，不同的项目的处理方法也是不一致的，这里出错就是简单的alert
*/

function apiAxios (method, url, params, success, failure) {
  if (params) {
    params = filterNull(params)
  }
  axios({
    method: method,
    url: url,
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null,
    baseURL: root,
    withCredentials: false,
    transformRequest: [function (data) {
      // Do whatever you want to transform the data
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    }
  })
  .then(function (res) {
    let response=res.data;
    if(success){
      success(response)
    }
  })
  .catch(function (err) {
    Message.destroy();
    clearTimeout(tiemObj);
    let res = err.response;
    if (res) {
      if(res.status===401&&res.data.OtherData.ErrorCode==1){
        Message.error(res.data.Message);//错误处理
        setTimeout(() => {
          location.reload();
        }, 1000);
      }
      else{
        Message.error("网络请求出错");//错误处理
      }
      return
    }
    else{
      Message.error("网络请求出错");//错误处理
    }
  })
}

let requestCount=0,tiemObj;
// http request 拦截器
axios.interceptors.request.use(
  config => {
    requestCount++;
    if(requestCount==1){
      tiemObj=setTimeout(() => {
        Message.loading({content:'加载中...',duration: 0});
      }, 800);
    }
   
    // config.headers["Content-Type"] = "application/json; charset=urf-8";
    //console.log(config);
    return config;
  }
);

// http response 拦截器
axios.interceptors.response.use(
  response => {
    requestCount--;
    if(requestCount===0){
      Message.destroy()
      clearTimeout(tiemObj);
    }
    return response;
});
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axios.defaults.headers.post['Content-Type'] = 'application/json; charset=utf-8';



// 返回在vue模板中的调用接口
export default {
  get: function (url, params, success, failure) {
    return apiAxios('GET', url, params, success, failure)
  },
  post: function (url, params, success, failure) {
    return apiAxios('POST', url, params, success, failure)
  },
  put: function (url, params, success, failure) {
    return apiAxios('PUT', url, params, success, failure)
  },
  delete: function (url, params, success, failure) {
    return apiAxios('DELETE', url, params, success, failure)
  }
}