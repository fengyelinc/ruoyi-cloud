<template>
  <div class="app-container home">
    <el-row
      style="
        height: 800px;
        width: 800px;
        border: 1px solid;
        padding: 20px;
        margin: 0 auto;
        margin-top: 20px;
      "
    >
      <p style="text-align: center">IUIGA后台管理系统</p>
      <el-button @click.native="start">开始播放</el-button>
      <el-button @click.native="stop">停止播放</el-button>
      <video
        ref="pullStream"
        controls
        autoplay
        muted
        width="700"
        height="600"
      ></video>
      <!-- <video-player
        class="video-player vjs-custom-skin"
        ref="videoPlayer"
        :playsinline="true"
        :options="playerOptions"
      ></video-player> -->
    </el-row>
  </div>
</template>

<script>
import flv from "flv.js";

export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "1.0.0",
      // 播放器
      player: undefined
      // // 视频播放
      // playerOptions : {
      //   playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
      //   autoplay: false, //如果true,浏览器准备好时开始回放。
      //   muted: false, // 默认情况下将会消除任何音频。
      //   loop: false, // 导致视频一结束就重新开始。
      //   preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
      //   language: 'zh-CN',
      //   aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
      //   techOrder: ['flash', 'html5'],      // 兼容顺序
      //   flash: {
      //     hls: { withCredentials: false },
      //     swf: 'static/video-js.swf' // 引入静态文件swf
      //   },
      //   html5: { hls: { withCredentials: false } },
      //   sources: [{ // 流配置，数组形式，会根据兼容顺序自动切换
      //     type: 'rtmp/hls',
      //     src: 'rtmp://localhost:1935/live/stream'
      //   }],
      //   poster: "", //你的封面地址
      //   // width: document.documentElement.clientWidth,
      //   notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
      //   controlBar: {
      //     timeDivider: true,
      //     durationDisplay: true,
      //     remainingTimeDisplay: false,
      //     fullscreenToggle: true  //全屏按钮
      //   }
      // }
    };
  },
  methods: {
    start() {
      this.play("http://localhost:8080/live/livestream.flv");
    },
    stop() {
      this.destruction();
    },
    play(urls) {
      let video = this.$refs.pullStream; //定义播放路径
      if (flv.isSupported()) {
        this.player = flv.createPlayer(
          {
            type: "flv",
            isLive: true,
            url: urls,
          },
          {
            enableWorker: false, //不启用分离线程
            enableStashBuffer: false, //关闭IO隐藏缓冲区
            isLive: true,
            lazyLoad: false,
          }
        );
      } else {
        console.log("不支持的格式");
        return;
      }
      this.player.attachMediaElement(video);
      this.player.load();
      this.player.play();
    },
    destruction() {
      //销毁对象
      if (this.player) {
        this.player.pause();
        this.player.destroy();
        this.player = null;
      }
    },
  },
};
</script>