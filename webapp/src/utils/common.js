if (process.env.NODE_ENV === 'development') {
  window.debugmode = true;
} else {
  window.debugmode = false
}

$.dateFormat = function (d) {
  var year = d.getFullYear();
  var month = d.getMonth() + 1;
  var date = d.getDate();
  var hour = d.getHours();
  var min = d.getMinutes();
  var sec = d.getSeconds();
  var times = year + '-' + (month < 10 ? ("0" + month) : month) + '-' + (date < 10 ? ("0" + date) : date) +
    ' ' + (hour < 10 ? ("0" + hour) : hour) + ':' + (min < 10 ? ("0" + min) : min) + ":" + (sec < 10 ? ("0" + sec) : sec);
  return times;
}

$.dateFormat2 = (_date, fmt) => {
  var date = new Date(_date);
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
}
