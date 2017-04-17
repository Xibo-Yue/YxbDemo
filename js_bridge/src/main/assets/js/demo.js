
        function testDiv() {
            document.getElementById("show").innerHTML = document.getElementsByTagName("html")[0].innerHTML;
        }

        function testClick() {
            var str1 = document.getElementById("text1").value;
            var str2 = document.getElementById("text2").value;

            //发送消息给java代码
            var data = "name=" + str1 + ",pass=" + str2;

            window.WebViewJavascriptBridge.send(
                data
                , function(responseData) {

                    document.getElementById("show").innerHTML = "repsonseData from java, data = " +responseData
                }
            );

        }

        function testClick1() {
            var str1 = document.getElementById("text1").value;
            var str2 = document.getElementById("text2").value;

            //调用本地java方法
            window.WebViewJavascriptBridge.callHandler(
                'submitFromWeb'
                , {'param': str1}
                , function(responseData) {
                    document.getElementById("show").innerHTML = "send get responseData from java, data = " + responseData
                }
            );
        }

        function bridgeLog(logContent) {
            document.getElementById("show").innerHTML = logContent;
        }//注册事件监听
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
        //注册回调函数，初始化函数
        connectWebViewJavascriptBridge(function(bridge) {
            bridge.init(function(message, responseCallback) {
                console.log('JS got a message', message);
                var data = {
                    'Javascript Responds': 'Wee!'
                };
                console.log('JS responding with', data);
                responseCallback(data);
            });

            bridge.registerHandler("functionInJs", function(data, responseCallback) {
                document.getElementById("show").innerHTML = ("data from Java: = " + data);
                var responseData = "Javascript Says Right back aka!";
                responseCallback(responseData);
            });
        })