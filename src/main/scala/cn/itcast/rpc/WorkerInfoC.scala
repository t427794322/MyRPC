package cn.itcast.rpc

class WorkerInfoC(val id: String, val memory: Int, val cores: Int) {

  var lastHeartbeatTime : Long = _

}
