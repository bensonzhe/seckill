//存放主要交互逻辑js
//模块化，分包
var seckill = {
    //封装秒杀相关ajax的url
    URL: {
        now: function () {
            return "/seckill/time/now";
        },
        exposer: function (seckillId) {
            return "/seckill/" + seckillId + "/exposer";
        },
        execution: function (seckillId, md5) {
            return "/seckill/" + seckillId + "/" + md5 + "/execution";
        }
    },
    //秒杀逻辑
    handleSeckill: function (seckillId, node) {
        // node.hide()
        //     .html('<button class="btn btn-primary btn-log" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            alert("success");
        });
        //     //在回调函数中，执行逻辑
        //     if (result && result['success']) {
        //         var exposer = result['data'];
        //         if (exposer['exposer']) {
        //             //开启秒杀
        //             var md5 = exposer['md5'];
        //             var killUrl = seckill.URL.execution(seckillId, md5);
        //             $("#killBtn").one('click', function () {
        //                 //绑定执行秒杀的操作
        //                 //1、先禁用
        //                 $(this).addClass("disable");
        //                 //2、发送请求
        //                 $.post(killUrl, {}, function (result) {
        //                     if (result && result['success']) {
        //                         var killResutl = result['data'];
        //                         var status = killResutl['status'];
        //                         var stateInfo = killResutl['stateInfo'];
        //                         //3、显示秒杀结果
        //                         node.html('<span class="label label-success">' + stateInfo + '</span>');
        //                     }
        //                 });
        //             });
        //             node.show();
        //         } else {
        //             //未开启秒杀，防止客户端的时间比服务器走的快
        //             var now = exposer['now'];
        //             var start = exposer['start'];
        //             var end = exposer['end'];
        //             //重新计时
        //             seckill.countDown(seckillId, now, start, end);
        //         }
        //     } else {
        //         console.log("result:" + result);
        //     }
        // });
    },
    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    countDown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $("#seckill-box");
        //时间判断
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html("秒杀结束！");
        } else if (nowTime < startTime) {
            //秒杀未开始
            var killTime = new Date(startTime + 1000); //防止计时偏移
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime("秒杀倒计时： %D天 %H时 %M分 %S秒");
                seckillBox.html(format);
                //时间完成后的回调事件
            }).on("finish.countdown", function () {
                //获取秒杀地址，控制显示逻辑，执行秒杀
                seckill.handleSeckill(seckillId, seckillBox);
            });
        } else {
            //秒杀开始
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证和登录，计时
            //cookie中查找手机号
            var killPhone = $.cookie("killPhone");
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];

            if (!seckill.validatePhone(killPhone)) {
                //绑定phone
                //控制输出
                var killPhoneModal = $("#killPhoneModal");
                killPhoneModal.modal({
                    show: true, //显示弹出层
                    backdrop: 'static', //禁止位置关闭
                    keyboard: false //关闭键盘事件
                });
                //绑定点击事件
                $("#killPhoneBtn").click(function () {
                    var inputPhone = $("#killPhoneKey").val();
                    if (seckill.validatePhone(inputPhone)) {
                        //写入cookie
                        $.cookie("killPhone", inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $("#killPhoneMessage").hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });
            }
            //已经登录
            //计时交互
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    seckill.countDown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log("result:" + result);
                }
            });
        }
    }
};