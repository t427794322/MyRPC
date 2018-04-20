package cn.itcast.rpc

class RemoteMessageD extends Serializable {
}

case class RegisterWorkerD(val id: String, val memory: Int, val cores: Int) extends RemoteMessageD

case class RegisteredWorkerD(val masterUrl: String) extends RemoteMessageD

case class HeartbeatD(val workId: String) extends RemoteMessageD

case object SendHeartBeatD

case object CheckTimeOutD