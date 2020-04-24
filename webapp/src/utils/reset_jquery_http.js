window.debugmode = false
if (process.env.NODE_ENV === 'development') {
  window.debugmode = true;
} else {
  window.debugmode = false
}


import {
  Message,
  Spin
} from 'iview';

let msgCount = 0,
  delayTiemer;
let msgCancleFunc = () => {};

function showLoading() {
  msgCount++;
  if (msgCount == 1) {
    delayTiemer = setTimeout(() => {
      msgCancleFunc = Message.loading({
        content: '加载中...',
        duration: 0
      });
    }, 500);
  }
}

function closeLoading() {
  msgCount--;
  if (msgCount < 0) msgCount = 0;
  if (msgCount === 0) {
    msgCancleFunc();
    msgCancleFunc = () => {};
    clearTimeout(delayTiemer);
  }
}

$.get = function (p0, p1, p2, p3) {
  var url = p0;
  var data = {};
  var success = p1;
  var datatype = p2;
  if (typeof (p2) == 'function') {
    data = p1;
    success = p2;
    datatype = p3;
  }

  if (debugmode) {
    //ajax调用
    showLoading();
    var jqxhr = $.ajax({
      type: "GET",
      url: url,
      data: data,
      dataType: datatype,
      xhrFields: {
        withCredentials: true
      },
      crossDomain: true,
      headers: {
        bschapirequest: 1,
      },
      success: function (resp) {
        if (resp.code == 302) {
          console.log(resp.urlToRedirectTo)
          var url = resp.urlToRedirectTo
          window.location.href = url
        } else {
          success(resp);
        }
      },
      complete: function () {
        closeLoading();
      },
    });
    return jqxhr;
  } else {
    //bsch bridge调用
    var errfunc;
    var errcallback = {
      error: function (func) {
        errfunc = func;
      }
    };

    var paramStr = "";
    for (var key in data) {
      var value = data[key];
      paramStr += key + "=" + value + "&";
    }
    if (paramStr != "")
      paramStr = "?" + paramStr + "1=1";
    url += paramStr;
    bsch.httpGet(url, function (resp, respCode) {
      if (respCode == 200)
        success(resp);
      else
        errfunc();
    }, datatype);

    return errcallback;
  }

};

$.post = function (p0, p1, p2, p3) {
  var url = p0;
  var data = {};
  var success = p1;
  var datatype = p2;
  if (typeof (p2) == 'function') {
    data = p1;
    success = p2;
    datatype = p3;
  }

  if (debugmode) {
    //ajax调用
    var jqxhr = $.ajax({
      type: "POST",
      url: url,
      data: data,
      dataType: datatype,
      xhrFields: {
        withCredentials: true
      },
      crossDomain: true,
      headers: {
        bschapirequest: 1,
        // ticket:"63fee9ee-d3f1-41b9-b960-fe7f344c342c",
        //DebugUser: debugUser
      },
      success: function (resp) {
        success(resp);
        closeLoading();
      },
      complete: function () {
        closeLoading();
      },
    });
    return jqxhr;
  } else {
    //bsch bridge调用
    var errfunc;
    var errcallback = {
      error: function (func) {
        errfunc = func;
      }
    };

    bsch.httpPost(url, data, function (resp, respCode) {
      if (respCode == 200)
        success(resp);
      else
        errfunc();
    }, datatype);

    return errcallback;
  }

};
