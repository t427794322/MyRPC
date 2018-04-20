package cn.itcast.rpc

trait RemoteMessageC extends Serializable{

}

//worker -> master
case class RegisterWorkerC(val id: String, val memory: Int, val cores: Int) extends RemoteMessageC

//master -> worker
case class RegisteredWorkerC(val masterUrl: String) extends RemoteMessageC

//master -> self
case object CheckTimeoutWorkerC

//worker -> self
case object SendHeartbeatC

//worker -> master
case class HeartBeatC(val id: String) extends RemoteMessageC

