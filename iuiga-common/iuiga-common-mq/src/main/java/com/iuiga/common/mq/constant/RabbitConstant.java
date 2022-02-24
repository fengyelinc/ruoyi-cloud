package com.iuiga.common.mq.constant;

/**
 * MQ配置
 */
public class RabbitConstant {
    /**
     * 交换机名称
     */
    public static final String EXCHANGE_SCAN_RFID = "wips.scan.rfid";

    /**
     * DAS总捡交换机
     */
    public static final String EXCHANGE_PICK_DAS = "wips.pick.das";

    /**
     * DPS总捡交换机
     */
    public static final String EXCHANGE_PICK_DPS = "wips.pick.dps";

    /**
     * DAS播种交换机
     */
    public static final String EXCHANGE_PROCESS_DAS = "wips.process.das";

    /**
     * DPS分拣交换机
     */
    public static final String EXCHANGE_PROCESS_DPS = "wips.process.dps";

    /**
     * 上架校验订单队列
     */
    public static final String QUEUE_ON_SHELF_DPS = "wips.onShelf.dps";

    /**
     * 校验库位库存队列
     */
    public static final String QUEUE_STOCK_VALIDATION = "wips.stock.validation";

    /**
     * 绑定SKU校验订单队列
     */
    public static final String QUEUE_SCAN_SKU = "wips.scan.sku";


    /**
     * DAS MQ消息类型-绑定SKU
     */
    public static final String SERVICE_WIPS_DAS_BIND = "das.bind";

    /**
     * DAS MQ消息类型-SKU播种完成
     */
    public static final String SERVICE_WIPS_DAS_PICK = "das.pick";

    /**
     * DPS MQ消息类型-绑定周转筐
     */
    public static final String SERVICE_WIPS_DPS_BIND = "dps.bind";

    /**
     * DPS MQ消息类型-SKU分拣完成
     */
    public static final String SERVICE_WIPS_DPS_PICK = "dps.pick";

    /**
     * MQ消息类型-总捡SKU完成
     */
    public static final String SERVICE_WIPS_PICK_SKU = "pick.sku";

    /**
     * MQ消息类型-总捡任务完成
     */
    public static final String SERVICE_WIPS_PICK_TASK = "pick.task";

    /**
     * IDC 订单完成状态检测校验队列
     */
    public static final String QUEUE_IDC_JOB_VALIDATION = "idc.job.validation";

    /**
     * WIPS-电子标签命令队列
     */
    public static final String QUEUE_TAG_COMMAND = "wips.tag.command";

    /**
     * WIPS-电子标签日志队列
     */
    public static final String QUEUE_LOG_TAG = "wips.log.tag";

    /**
     * WIPS-API日志队列
     */
    public static final String QUEUE_LOG_API = "wips.log.api";

    /**
     * WIPS-错误日志
     */
    public static final String QUEUE_LOG_ERROR = "wips.log.error";

    /**
     * WIPS作业任务队列
     */
    public static final String QUEUE_WIPS_JOB = "wips.job";

    /**
     * WIPS作业任务-接收订单
     */
    public static final String SERVICE_WIPS_JOB_RECEIVE = "wips.job.receive";

    /**
     * WIPS作业任务-指示作业
     */
    public static final String SERVICE_WIPS_JOB_START = "wips.job.start";

    /**
     * 总捡完成同步给WMS
     */
    public static final String QUEUE_IDC_JOB_PICK_SYNC_WMS_DPS = "wms.sync.pick.dps";

    /**
     * 总捡完成同步给WMS
     */
    public static final String QUEUE_IDC_JOB_PICK_SYNC_WMS_DAS = "wms.sync.pick.das";

    /**
     * 分拣/播种完成同步给WMS
     */
    public static final String QUEUE_IDC_JOB_BATCH_SYNC_WMS_DPS = "wms.sync.job.dps";

    /**
     * 分拣/播种完成同步给WMS
     */
    public static final String QUEUE_IDC_JOB_BATCH_SYNC_WMS_DAS = "wms.sync.job.das";
}
