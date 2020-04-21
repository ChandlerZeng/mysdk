//发送消息给安卓
function testClick() {
   var data = document.getElementById("text1").value;
  //调用java中的方法   submitFromWeb是方法名
    window.WebViewJavascriptBridge.callHandler(

       'submitFromWeb'
       , {'param': data}
       , function(responseData) {
            alert(responseData)
       }
   );
}

function testClick2() {
  //调用java中的方法   submitFromWeb是方法名
    window.WebViewJavascriptBridge.callHandler(

       'submitFromWeb2'
       , {'param': 'hello from js method2'}
       , function(responseData) {
            alert(responseData)
       }
   );
}

function testClick3() {
  //调用java中的方法   submitFromWeb是方法名
    window.WebViewJavascriptBridge.callHandler(

       'submitFromWeb3'
       , {'param': 'hello from js method3'}
       , function(responseData) {
//            alert(responseData)
       }
   );
}

function testClick4() {
  //调用java中的方法   submitFromWeb是方法名
    window.WebViewJavascriptBridge.callHandler(

       'submitFromWeb4'
       , {'param': 'hello from js method4'}
       , function(responseData) {
//            alert(responseData)
       }
   );
}

//注册事件监听
function connectWebViewJavascriptBridge(callback) {
   if (window.WebViewJavascriptBridge) {
       callback(WebViewJavascriptBridge)
   } else {
       document.addEventListener(
           'WebViewJavascriptBridgeReady'
           , function() {
               callback(WebViewJavascriptBridge)
           },
           false
       );
   }
}

//注册回调函数，第一次连接时调用 初始化函数
connectWebViewJavascriptBridge(function(bridge) {
    //初始化
   bridge.init(function(message, responseCallback) {
       var data = {
           'Javascript Responds': 'Wee!'
       };
       responseCallback(data);
   });

    //接收安卓发来的消息   并返回给安卓通知
   bridge.registerHandler("functionInJs", function(data, responseCallback) {
        alert(data);
       var responseData = "我接受到了安卓的调用";
       responseCallback(responseData);
   });
})