package cn.itcast.rpc

import java.util.UUID

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._

class WorkerD(val masterHost: String, val masterPort: Int, val memory: Int, val cores: Int) extends Actor{

  var masterD: ActorSelection = _

  val workerId: String = UUID.randomUUID().toString

  val HEARTBEAT_INTERVAL = 10000

  override def preStart(): Unit = {
    masterD = context.actorSelection(s"akka.tcp://MasterDSystem@$masterHost:$masterPort/user/MasterD")
    masterD ! RegisterWorkerD(workerId, memory, cores)
  }

  override def receive: Receive = {
    case RegisteredWorkerD(id) => {
      import context.dispatcher
      context.system.scheduler.schedule(0 millis, HEARTBEAT_INTERVAL millis, self, SendHeartBeatD)
    }

    case SendHeartBeatD => {
      println("send heartbeat to master")
      masterD ! HeartbeatD(workerId)
    }
  }
}

object WorkerD{
  def main(args: Array[String]): Unit = {
    val host = args(0)
    val port = args(1).toInt
    val masterHost = args(2)
    val masterPort = args(3).toInt
    val memory = args(4).toInt
    val cores = args(5).toInt

    val configString =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configString)
    val workerDSystem = ActorSystem("WorkdDSystem", config)
    workerDSystem.actorOf(Props(new WorkerD(masterHost, masterPort, memory, cores)), "WorkerD")
    workerDSystem.awaitTermination()
  }
}