import java.io.File
import scala.io._
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import org.joda.time.Days
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat
import java.util.GregorianCalendar;

object Stat extends App {
  
  val casedir = "cases/"
  
  val f = new File(casedir)
  /*val contentList = f.listFiles() map (Source.fromFile(_).getLines.mkString)
  val date = contentList map (getCourtDates)*/
  val dates = (for(f <- f.listFiles()) yield {
    getCourtDates(Source.fromFile(f).getLines.mkString, f.getName())
  }).toList 
  
  /*val ds = dates.map(t=>getStartAndEnd(t._1, t._2)).filter(_!=None).map {t =>
    (t.get._1, t.get._2.asInstanceOf[Date], t.get._3.asInstanceOf[Date])
  }*/
  
  val ds1 = dates map (d => (d._1,getDaysBetween(d._2, d._3))) filter {
    d=> d._2 > 0
  }
  
  /*println(ds1 length)*/
  println(ds1 filter(d => d._2<=30) length)
  /*
  val oneM = ds1 filter(d => d._2<=30)
  val res = ds1.map{d => 
    val content = Source.fromFile(casedir+d._1).getLines.mkString
    val regex = """(failed\sto\sprove)|(dismiss)|(not\sliable)|(HKCFI)""".r
    regex.findAllIn(content).toList
  }
  
  println("length:"+res.filter(_.length==0) length)
  */
  
  //println(ds1 filter(d => d._2>15 && d._2 <30) length)
  println(ds1 filter(d => d._2>30 && d._2 <=60) length)
  println(ds1 filter(d => d._2>60 && d._2 <=90) length)
  println(ds1 filter(d => d._2>90 && d._2 <=120) length)
  println(ds1 filter(d => d._2>120 && d._2 <=150) length)
  println(ds1 filter(d => d._2>150 && d._2 <=180) length)
  println(ds1 filter(d => d._2>180 && d._2 <=210) length)
  println(ds1 filter(d => d._2>210 && d._2 <=240) length)
  println(ds1 filter(d => d._2>240 && d._2 <=270) length)
  println(ds1 filter(d => d._2>270 && d._2 <=300) length)
  println(ds1 filter(d => d._2>300 && d._2 <=330) length)
  println(ds1 filter(d => d._2>330 && d._2 <=365) length)
  println(ds1 filter(d => d._2>365 && d._2 <=730) length)
  println(ds1 filter(d => d._2>730 && d._2 <= 1460) length)
  println(ds1 filter(d => d._2>1460 && d._2 <= 3650) length)
  println(ds1 filter(d => d._2>3650) length)
  //println((ds1 map(_._2) reduceLeft(_+_)) / ds1.length)
  
  def getCourtDates(content:String, fileName:String) = {
    val regex = """[1-9]\d{0,1}\s+?(January|February|March|April|May|June|July|August|September|October|November|December)\s+?\d{4}""".r
    val dates = regex.findAllIn(content).toList
    
    val ds = dates.map(getDateStr _).filter(_!=None).map(_.get)
    
    val dss = ds.sortWith((d1, d2)=>{d1.getTime()-d2.getTime()<0})
    
    (fileName, dss.head, dss.reverse.head)
  }
  
  /*def getStartAndEnd(f:String , dateStr:String) = {
    val regex = """[1-9]\d{0,1}.+?(January|February|March|April|May|June|July|August|September|October|November|December)\s+?\d{4}""".r
    //println(f+":"+regex.findAllIn(dateStr).toList.length+":"+regex.findAllIn(dateStr).toList)
    val dates = regex.findAllIn(dateStr).toList
    dates.length match {
      case 2 => Some((f, getDateStr(dates.head),getDateStr(dates.tail.head)))
      case _ => None
    }    
  }*/
  
  def getDateStr(dateStr:String) = try {
    val regex = """([1-9]\d{0,1}).+?(January|February|March|April|May|June|July|August|September|October|November|December)\s+?(\d{4})""".r
    val date = regex.replaceAllIn(dateStr, m=>formatDay(m.group(1))+"/"+formatMonth(m.group(2))+"/"+m.group(3))
    val dateFormat = new SimpleDateFormat("dd/MM/yyyy")
    Some(dateFormat.parse(date))
  } catch {
      case ex => None
  }
  
  def formatDay(day:String) = day match {
    case d if d.length == 1 => "0"+day
    case _ => day
  }
  
  def formatMonth(month:String) = month match {
    case "January" => "01"
    case "February" => "02"
    case "March" => "03"
    case "April" => "04"
    case "May" => "05"
    case "June" => "06"
    case "July" => "07"
    case "August" => "08"
    case "September" => "09"
    case "October" => "10"
    case "November" => "11"
    case "December" => "12"
    case _ => month
  }
  
  def getDaysBetween(start:Date, end:Date) = {
    (end.getTime - start.getTime) / (1000*60*60*24)
  }
}