package cn.itcast.rpc

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.duration._

class MasterD extends Actor{

  println("constructor invoked")

  val idToWorkers = new mutable.HashMap[String, WorkerInfoD]()
  val workerSet = new mutable.HashSet[WorkerInfoD]()

  val CHECKTIMEOUT_INTERVAL = 15000

  override def preStart(): Unit = {
    println("masterD preStarted")

    import context.dispatcher
    context.system.scheduler.schedule(0 millis, CHECKTIMEOUT_INTERVAL millis, self, CheckTimeOutD)
  }

  override def receive: Receive = {
    case RegisterWorkerD(id, memory, cores) => {
      if (!idToWorkers.contains(id)) {
        val workInfoD = new WorkerInfoD(id, memory, cores)
        workInfoD.lastHeartTime = System.currentTimeMillis()
        idToWorkers(id) = workInfoD
        workerSet += workInfoD
        sender ! RegisteredWorkerD("akka.tcp://MasterDSystem@$masterHost:$masterPort/user/MasterD")
      }
    }

    case HeartbeatD(id) => {
      if (idToWorkers.contains(id)) {
        val workerInfo = idToWorkers(id)
        workerInfo.lastHeartTime = System.currentTimeMillis()
      }
    }

    case CheckTimeOutD => {
      println(workerSet.size)
      val currentTs = System.currentTimeMillis()
      val toRemove = workerSet.filter(x => currentTs - x.lastHeartTime > CHECKTIMEOUT_INTERVAL)
      for (x <- toRemove) {
        idToWorkers -= x.id
        workerSet -= x
      }
    }
  }
}

object MasterD {
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

    val actorSystem = ActorSystem("MasterDSystem", config)
    val masterD = actorSystem.actorOf(Props(new MasterD), "MasterD")
    actorSystem.awaitTermination()
  }
}
