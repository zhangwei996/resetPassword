export default {
  goodTime (str) {
    let now = new Date().getTime()
    let oldTime = new Date(str).getTime()
    let difference = now - oldTime
    let result = ''
    let minute = 1000 * 60
    let hour = minute * 60
    let day = hour * 24
    let month = day * 30
    let year = month * 12
    let _year = difference / year
    let _month = difference / month
    let _week = difference / (7 * day)
    let _day = difference / day
    let _hour = difference / hour
    let _min = difference / minute

    if (_year >= 1) {
      result = '发表于 ' + ~~(_year) + ' 年前'
    } else if (_month >= 1) {
      result = '发表于 ' + ~~(_month) + ' 个月前'
    } else if (_week >= 1) {
      result = '发表于 ' + ~~(_week) + ' 周前'
    } else if (_day >= 1) {
      result = '发表于 ' + ~~(_day) + ' 天前'
    } else if (_hour >= 1) {
      result = '发表于 ' + ~~(_hour) + ' 个小时前'
    } else if (_min >= 1) {
      result = '发表于 ' + ~~(_min) + ' 分钟前'
    } else {
      result = '刚刚'
    }
    return result
  },
  dateFormat(_date,fmt){
    var date=new Date(_date);
    var o = {
        "M+": date.getMonth() + 1, //月份 
        "d+": date.getDate(), //日 
        "H+": date.getHours(), //小时 
        "m+": date.getMinutes(), //分 
        "s+": date.getSeconds(), //秒 
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
        "S": date.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  },
  dataFilter(_key,arry) {
    for (var key in arry) {
      if (arry.hasOwnProperty(key)) {
        var element = arry[key];
        if(element.CodeID==_key)
          return element.Name;
      }
    }
    return '';
  },
  myUpload(url,fileObj,data,callback){
    var FileController = url;                    // 接收上传文件的后台地址
    if (!fileObj ||fileObj.length==0) {
       console.error('请选择上传文件'); return;
    }
    // FormData 对象
    var form = new FormData();
    //表单数据
    for (let key in data) {
       const element = data[key];
       form.append(key,element);
   }
    form.append("file", fileObj);// 文件对象
    // XMLHttpRequest 对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (resp) {
        var obj = JSON.parse(resp.currentTarget.response);
        if(callback)
          callback(obj);
    };
    xhr.send(form);
  },
  comSetting(){//公共插件基础配置，如有需要，覆盖即可
    const tree={
      view: {
        dblClickExpand: false,
        showIcon: false,
      },
      check: {
        enable: false
      },
      callback:{}
    }

    
    return {
      tree:tree,
    }
  },
  initScroll(classid){
    if(!classid){classid=".mCustomScrollbar";}
    $(classid).mCustomScrollbar({
      axis:"yx",
      scrollInertia:300,
      mouseWheel:{ preventDefault:true },
      // scrollInertia:250,
      // advanced:{autoExpandHorizontalScroll:true}
   });
  },
  openFistNode(_zNodes){//展开一级节点
      if(_zNodes&&_zNodes.length>0){
          for (var key in _zNodes) {
              if (_zNodes.hasOwnProperty(key)) {
                  var element = _zNodes[key];
                  element.open=true;
              }
          }
      }
      else{
          _zNodes.open=true;
      }
    return _zNodes;
  },
  
}