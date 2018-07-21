package com.scalabigdata.soap

import com.scalabigdata.soap.wsdlconsumer._

/**
  * A SOAP consumer of a wsdl
  * uses the scalaxb with the maven-plugin
  *
  * How to:
  * 1 - put the files on ${project.basedir}/src/main/wsdl/
  * 2 - look for generated files on target/generated-sources/scalaxb/
  * 3 - copy them to some folder from your project as this example com.scalabigdata.soap
  * 4 - remove unused imports
  * 5 - create manual imports on all files off scalaxb, soapenvelope12 and wsdlconsumer
  *   import com.scalabigdata.com.scalabigdata.soap.scalaxb
  *   import com.scalabigdata.com.scalabigdata.soap.wsdlconsumer
  *   import com.scalabigdata.com.scalabigdata.soap.soapenvelope12
  * 6 - remove unused manual imports
  * 7 - manual import again on your file(s) like this one com.scalabigdata.com.scalabigdata.soap.wsdlconsumer._"
  */

object ConsumerWSDL extends App {

  val subject =
    <logon xmlns="urn:xtk:session">
      <sessiontoken></sessiontoken>
      <strLogin>dataLake</strLogin>
      <strPassword></strPassword>
      <elemParameters></elemParameters>
    </logon>

  val shipTo = scalaxb.fromXML[Logon](subject)
  println(s"Rendered XML case class is :: ${shipTo}")

  //val document = scalaxb.toXML[Logon](shipTo.copy(sessiontoken = "sst"), None, Some("foo"), wsdlconsumer.defaultScope)
  //println(document)
}
