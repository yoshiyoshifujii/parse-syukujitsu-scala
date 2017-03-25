package cli

import java.time.{ LocalDate, LocalDateTime }
import java.time.format.DateTimeFormatter

import service.SyukujitsuParser
import model._

/**
 * Created by matsutomu on 17/03/08.
 */
object SyukujitsuParserCLI extends App {

  var syukujitsuCsvOk =
    """
      |平成28年（2016年）,,平成29年（2017年）,,平成30年（2018年）,
      |名称,月日,名称,月日,名称,月日
      |元日,2016/1/1,元日,2017/1/1,元日,2018/1/1
      |成人の日,2016/1/11,成人の日,2017/1/9,成人の日,2018/1/8
      |建国記念の日,2016/2/11,建国記念の日,2017/2/11,建国記念の日,2018/2/11
      |春分の日,2016/3/20,春分の日,2017/3/20,春分の日,2018/3/21
      |春分の日,2016/3/20,春分の日,2017/3/20,春分の日,2018/3/21
      |春分の日,2016/3/20,春分の日,2017/3/20,春分の日,2018/3/21
      |昭和の日,2016/4/29,昭和の日,2017/4/29,昭和の日,2018/4/29
      |憲法記念日,2016/5/3,憲法記念日,2017/5/3,憲法記念日,2018/5/3
      |みどりの日,2016/5/4,みどりの日,2017/5/4,みどりの日,2018/5/4
      |こどもの日,2016/5/5,こどもの日,2017/5/5,こどもの日,2018/5/5
      |海の日,2016/7/18,海の日,2017/7/17,海の日,2018/7/16
      |山の日,2016/8/11,山の日,2017/8/11,山の日,2018/8/11
      |敬老の日,2016/9/19,敬老の日,2017/9/18,敬老の日,2018/9/17
      |秋分の日,2016/9/22,秋分の日,2017/9/23,秋分の日,2018/9/23
      |体育の日,2016/10/10,体育の日,2017/10/9,体育の日,2018/10/8
      |文化の日,2016/11/3,文化の日,2017/11/3,文化の日,2018/11/3
      |勤労感謝の日,2016/11/23,勤労感謝の日,2017/11/23,勤労感謝の日,2018/11/23
      |天皇誕生日,2016/12/23,天皇誕生日,2017/12/23,天皇誕生日,2018/12/23
      |,,,,,
      |月日は表示するアプリケーションによって形式が異なる場合があります。,,,,,
    """.stripMargin

  var syukujitsuCsvNG =
    """
    |平成28年（2016年）,,平成29年（2017年）,,平成30年（2018年）,
    |名称,月日,名称,月日,名称,月日
    |元日,2016/1/1,元日,2017/1/1,元日,2018/1/1,元日,9999/99/9
    |成人の日,2016/1/11,成人の日,2017/1/9,成人の日,2018/1/8
    """.stripMargin

  val result = SyukujitsuParser.parse(syukujitsuCsvOk)
  result match {
    case Right(ret: List[SyukujitsuBody]) => {
      ret.foreach(x => println(x.date_name + ":" + x.date))
      // println(SyukujitsuParser.convertYearMapList(ret))
      // println(SyukujitsuParser.convertYearMonthMap_for(ret))
      println(SyukujitsuParser.convertYearMonthMap_foldLeft(ret))
    }
    case Left(msg: String) => {
      println(msg)
    }
  }

}
