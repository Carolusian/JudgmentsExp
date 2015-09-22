import scala.io._
import scala.util.matching.Regex

object Spider extends App {
  var i = 1
  val pages = List("http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=0",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=100",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=200",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=300",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=400",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=500",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=600",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=700",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=800",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=900",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=1000",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=1100",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=1200",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=1300",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=1400",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=1500",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=1600",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=1700",
      	"http://www.hklii.hk/cgi-bin/sinosrch.cgi?meta=%2Fhklii;method=auto;mask_path=chi%2Fhk%2Fcases%20eng%2Fhk%2Fcases;mask_world=;query=tort;results=100;submit=Search;rank=on;callback=off;legisopt=;view=relevance;offset=1800");

  pages foreach (downloadList _)
  
  def downloadList(url:String) = {
    val content = Source.fromURL(url, "ISO-8859-1").getLines().mkString
    parseCaseLink(content) foreach {cl => 
      val caseContent = Source.fromURL(cl,"ISO-8859-1").getLines().mkString
      printToFile(new java.io.File("cases/"+i+".html"))(caseContent)
      i=i+1
    }
  }
  
  def parseCaseLink(caseIndexContent:String) = {
    val caseLinkRegex = """http://www.hklii.hk/cgi-bin/disp.pl/eng/hk/cases/[^?]+""".r
    caseLinkRegex.findAllIn(caseIndexContent).toList
  }
  
  def printToFile(f:java.io.File)(content:String) = {
    val p = new java.io.PrintWriter(f)
    p.print(content)
    p.close
  }
}