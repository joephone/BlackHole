package com.transcendence.greenstar.demo.wearecord;

/**
 * RecordListener 录音策略接口
 * @author acer
 */
interface RecordListener {
	
	/**
	 * 在这里进行录音准备工作，重置录音文件名等
	 */
	fun ready()
	/**
	 * 开始录音
	 */
	fun start();
	/**
	 * 录音结束
	 */
	fun stop();
	
	/**
	 * 录音失败时删除原来的旧文件
	 */
	fun deleteOldFile();
	
	/**
	 * 获取录音音量的大小
	 * @return 
	 */
	fun getAmplitude():Double
	
	/**
	 * 返回录音文件完整路径
	 * @return
	 */
	fun  getFilePath():String

}
