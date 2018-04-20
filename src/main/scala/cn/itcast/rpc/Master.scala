package cn.itcast.rpc

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.duration._

class Master(val host: String, val port: Int) extends Actor{

  //wokerId -> workInfo
  val idToWorker = new mutable.HashMap[String, WorkerInfo]()
  //workInfo
  val workers = new mutable.HashSet[WorkerInfo]()//使用set删除快，也可使用LinkList
  //超时时间检查间隔
  val CHECK_INTERVAL = 15000

  println("constructor invoked")

  override def preStart(): Unit = {
    println("preStart invoked")

    import context.dispatcher
    context.system.scheduler.schedule(0 millis, CHECK_INTERVAL millis, self, CheckTimeOutWorker)
  }

  override def receive: Receive = {
    case RegisterWorker(id, memory, cores) => {
      //判断一下，是不是已经注册过
      if (!idToWorker.contains(id)) {

        val workerInfo = new WorkerInfo(id, memory, cores)
        idToWorker(id) = workerInfo
        workers += workerInfo
        sender ! RegisteredWorker(s"akka.tcp://MasterSystem@$host:$port/user/Master")
      }
    }

    case Heartbeat(id) => {
      if (idToWorker.contains(id)) {
        val workerInfo = idToWorker(id)
        //报活
        val currentTime = System.currentTimeMillis()
        workerInfo.lastHeartbeatTime = currentTime
      }
    }

    case CheckTimeOutWorker => {
      val currentTime = System.currentTimeMillis()
      val toRemove = workers.filter(x => currentTime - x.lastHeartbeatTime > CHECK_INTERVAL)
      for (x <- toRemove) {
        workers -= x
        idToWorker -= x.id
      }
      println(workers.size)
    }
  }
}

object Master {
  def main(args: Array[String]): Unit = {
    val host = args(0)
    val port = args(1).toInt

    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)
    //ActorSystem老大，负责创建和监控下面的system
    val actorSystem = ActorSystem("MasterSystem", config)

    //创建actor
    val master = actorSystem.actorOf(Props(new Master(host, port)), "Master")
    actorSystem.awaitTermination()
  }
}
