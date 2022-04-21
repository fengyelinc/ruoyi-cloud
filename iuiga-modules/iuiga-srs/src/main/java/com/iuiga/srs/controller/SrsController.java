package com.iuiga.srs.controller;

import com.alibaba.fastjson.JSON;
import com.iuiga.common.core.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * SRS Controller
 */
@RestController
@RequestMapping("/control")
@Api("SRS管理")
public class SrsController extends BaseController {


    /************************  SRS推流流程  ************************
     * 推流 (connect -> publish) --> 关流 (unpublish -> close)
     **************************************************************/

    /************************  SRS拉流流程  ************************
     * 拉流 (play) --> 停流 (stop)
     **************************************************************/

    /**************************************************************
     * 需要到srs的conf目录下修改对应conf配置文件，回调接口返回0表示成功，其他表示失败
     * vhost __defaultVhost__ {
     *     hls {
     *         enabled         on;
     *     }
     *     http_remux {
     *         enabled     on;
     *         mount       [vhost]/[app]/[stream].flv;
     *     }
     * 	http_hooks {
     *         # whether the http hooks enalbe.
     *         # default off.
     *         enabled         on;
     *         # when client connect to vhost/app, call the hook,
     *         # the request in the POST data string is a object encode by json:
     *         #       {
     *         #           "action": "on_connect",
     *         #           "client_id": 1985,
     *         #           "ip": "192.168.1.10", "vhost": "video.test.com", "app": "live",
     *         #           "tcUrl": "rtmp://video.test.com/live?key=d2fa801d08e3f90ed1e1670e6e52651a",
     *         #           "pageUrl": "http://www.test.com/live.html"
     *         #       }
     *         # if valid, the hook must return HTTP code 200(Stauts OK) and response
     *         # an int value specifies the error code(0 corresponding to success):
     *         #       0
     *         # support multiple api hooks, format:
     *         #       on_connect http://xxx/api0 http://xxx/api1 http://xxx/apiN
     *         on_connect	http://192.168.0.11:8081/srs/control/connect;
     * 		on_close	http://192.168.0.11:8081/srs/control/close;
     * 		on_publish	http://192.168.0.11:8081/srs/control/publish;
     * 		on_unpublish	http://192.168.0.11:8081/srs/control/unpublish;
     * 		on_play	http://192.168.0.11:8081/srs/control/play;
     * 		on_stop	http://192.168.0.11:8081/srs/control/stop;
     *     }
     * }
     **************************************************************/

    @ApiOperation("连接")
    @PostMapping("/connect")
    public int connect(@RequestBody Map map) {
        // 开启直播通知
        System.out.println("connect success: params: "+ JSON.toJSONString(map));
        return 0;
    }

    @ApiOperation("关闭")
    @PostMapping("/close")
    public int close(@RequestBody Map map) {
        // 关闭直播通知
        System.out.println("close success: params: "+ JSON.toJSONString(map));
        return 0;
    }

    @ApiOperation("推流")
    @PostMapping("/publish")
    public int publish(@RequestBody Map map) {
        // 直播用户鉴权
        System.out.println("publish success: params: "+ JSON.toJSONString(map));
        return 0;
    }

    @ApiOperation("停止推流")
    @PostMapping("/unpublish")
    public int unpublish(@RequestBody Map map) {
        // 提示前端、通知等
        System.out.println("unpublish success: params: "+ JSON.toJSONString(map));
        return 0;
    }

    @ApiOperation("播放")
    @PostMapping("/play")
    public int play(@RequestBody Map map) {
        // 观看用户鉴权
        System.out.println("play success: params: "+ JSON.toJSONString(map));
        return 0;
    }

    @ApiOperation("停止播放")
    @PostMapping("/stop")
    public int stop(@RequestBody Map map) {
        // 不知道有什么用
        System.out.println("stop success: params: "+ JSON.toJSONString(map));
        return 0;
    }
}
