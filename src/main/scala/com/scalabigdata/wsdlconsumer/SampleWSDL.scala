package com.scalabigdata.soap

import com.scalabigdata.wsdlconsumer.CalcPrazo
import com.scalabigdata.wsdlconsumer.`package`

/*
 * A dammit SOAP consumer of brazilian correios service using SOAP
 * WSDL source: http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx?WSDL
 * uses the scalaxb-maven-plugin
 * to make plugin working, put the .wsdl/.xsd files on ${project.basedir}/src/main/wsdl
 * look for generated files on target/generated-sources/scalaxb/
 * move them to src/main/scala...
 * import the .scala files manually to your project files
 * disable plugin for generating new files (otherwise we gonna have ambiguity)
 */
object SampleWSDL {

  def main(args: Array[String]): Unit = {

    val addressXML =
      <shipTo xmlns="http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx">
        <nCdServico>41106</nCdServico>
        <sCepOrigem>04042003</sCepOrigem>
        <sCepDestino>14780060</sCepDestino>
      </shipTo>

    val parsedAddress = scalaxb.fromXML[CalcPrazo](addressXML)
    println(s"Rendered XML case class is :: ${parsedAddress}")

  }

}
