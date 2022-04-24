<template>
  <div>
    <el-form label-width="100px">
    <el-form-item label="发送消息" prop="text">
      <el-input
        v-model="text"
        placeholder="请输入发送消息"
        clearable
        size="small"
        style="width: 240px"
      />
    </el-form-item>
    <el-form-item>
      <el-button
        type="primary"
        icon="el-icon-search"
        size="mini"
        @click="websocketsend"
      >发送消息</el-button
      >
    </el-form-item>
    <el-form-item>
      <el-button
        type="primary"
        icon="el-icon-search"
        size="mini"
        @click="initWebSocket"
      >链接webSocket</el-button
      >
    </el-form-item>
    <el-form-item>
      <el-button
        type="primary"
        icon="el-icon-search"
        size="mini"
        @click="websocketclose"
      >关闭webSocket</el-button
      >
    </el-form-item>
    </el-form>
    <el-table  :data="messageList">
      <el-table-column label="消息" prop="message" :show-overflow-tooltip="true" />
    </el-table>
  </div>
</template>
<script>

export default {
  name: 'webSocket',
  data: () => {
    return {
      websock: '', // websock 实例
      text: '', //发送文本
      messageList: [] //消息列表
    }
  },
  methods: {
    initWebSocket() { // 初始化weosocket
      if ('WebSocket' in window) {
        // this.websock = new WebSocket('ws://192.168.0.142/dev-api/webSocket')
        // this.websock = new WebSocket('ws://localhost:8080/system/websocket')
        this.websock = new WebSocket('ws://localhost:9211/webSocket2?req=ok&user=admin')
        // this.websock = new WebSocket('wss://skynet.mumutong.cn/export/kols')
        this.websock.onmessage = this.websocketonmessage
        this.websock.onopen = this.websocketonopen
        this.websock.onerror = this.websocketonerror
        this.websock.onclose = this.websocketclose
      }else{
        alert('当前浏览器 Not support websocket')
      }
    },
    websocketonopen() { // 连接建立之后执行send方法发送数据
      this.setMessageInnerHTML("连接成功");
    },

    //将消息显示在网页上
    setMessageInnerHTML(innerHTML) {
      let data = {
        message: innerHTML
      }
      this.messageList.push(data);
    },

    /**
     * 连接建立失败,断开连接
     * 1.查询一次数据库数据
     * 2.查询完后再次建立socket连接
     * */
    websocketonerror() { // 连接建立失败重连
      console.log('连接建立失败')
      // this.initWebSocket()
    },
    websocketonmessage(e) { // 数据接收
      console.log(e.data);
      this.setMessageInnerHTML(e.data);
    },
    websocketsend() { // 数据发送
      this.websock.send(this.text)
    },
    websocketclose(e) { // 关闭
      this.websock.close();
      this.setMessageInnerHTML("断开链接");
    }
  }
}
</script>

