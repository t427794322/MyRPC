package cn.itcast.rpc

import java.util.UUID

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._

class WorkerC(val masterHost: String, val masterPort: Int, val memory: Int, val cores: Int) extends Actor{

  var master: ActorSelection = _
  val workerId = UUID.randomUUID().toString
  val HEARTBEAT_INTERVAL: Long = 10000

  override def preStart(): Unit = {
    master = context.actorSelection(s"akka.tcp://MasterCSystem@$masterHost:$masterPort/user/MasterC")
    master ! RegisterWorker(workerId, memory, cores)
  }

  override def receive: Receive = {
    case RegisteredWorkerC(masterUrl) => {
      println(masterUrl)
      //设置发送心跳定时器
      import context.dispatcher
      context.system.scheduler.schedule(0 millis, HEARTBEAT_INTERVAL millis, self, SendHeartbeatC)
    }

    case SendHeartbeatC => {
      println("send hearbeat to masterC")
      master ! HeartBeatC(workerId)
    }
  }
}

object WorkerC {
  def main(args: Array[String]): Unit = {
    val host = args(0)
    val port = args(1).toInt
    val masterHost = args(2)
    val masterPort = args(3).toInt
    val memory = args(4).toInt
    val cores = args(5).toInt

    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)
    val actorSystem = ActorSystem("WorkerSystem", config)
    actorSystem.actorOf(Props(new WorkerC(masterHost, masterPort, memory, cores)), "WorkerC")
    actorSystem.awaitTermination()
  }
}