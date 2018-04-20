package cn.itcast.rpc

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.duration._

class MasterC extends Actor{

  val idToWorkers = new mutable.HashMap[String, WorkerInfoC]()
  val workersSet = new mutable.HashSet[WorkerInfoC]()
  //检查work节点超时间隔
  val CHECKTIMEOUT_INTERVAL = 15000

  println("constructor invoked")

  override def preStart(): Unit = {
    println("masterC preStart")

    //起检查超时的定时器
    import context.dispatcher
    context.system.scheduler.schedule(0 millis, CHECKTIMEOUT_INTERVAL millis, self, CheckTimeoutWorkerC)
  }

  override def receive: Receive = {
    case RegisterWorker(id, memory, cores) => {
      if (!idToWorkers.contains(id)) {
        val workerInfo = new WorkerInfoC(id, memory, cores)
        workerInfo.lastHeartbeatTime = System.currentTimeMillis()
        idToWorkers.put(id, workerInfo)
        workersSet += workerInfo
        sender ! RegisteredWorkerC("akka.tcp://MasterCSystem@$masterHost:$masterPort/user/MasterC")
      }
    }

    case HeartBeatC(id) => {
      if (idToWorkers.contains(id)) {
        val workInfo = idToWorkers(id)
        workInfo.lastHeartbeatTime = System.currentTimeMillis()
      }
    }

    case CheckTimeoutWorkerC => {
      println(workersSet.size)
      val currentTs = System.currentTimeMillis()
      val toRemove = workersSet.filter(x => currentTs - x.lastHeartbeatTime > CHECKTIMEOUT_INTERVAL)
      for (x <- toRemove) {
        idToWorkers -= x.id
        workersSet -= x
      }
    }
  }
}

object MasterC {
  def main(args: Array[String]): Unit = {
    val host = args(0)
    val port = args(1).toInt

    val configString =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configString)
    val actorSystem = ActorSystem("MasterCSystem", config)
    val materActor = actorSystem.actorOf(Props(new MasterC), "MasterC")
    actorSystem.awaitTermination()
  }
}