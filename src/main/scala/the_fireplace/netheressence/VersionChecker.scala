package the_fireplace.netheressence

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import java.util.Arrays
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.event.ClickEvent
import net.minecraft.event.ClickEvent.Action
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ChatComponentText
import net.minecraft.util.ChatStyle
import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.fml.client.FMLClientHandler
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLInterModComms
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLServerStartedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent
import the_fireplace.netheressence.NetherEssence

@Mod(modid = VersionChecker.MODID, name = VersionChecker.MODNAME, version = VersionChecker.VERSION)
object VersionChecker {

  private final val HostMODID = NetherEssence.MODID

  private final val HostMODNAME = NetherEssence.MODNAME

  private final val HostVERSION = NetherEssence.VERSION

  final val MODID = HostMODID + "vc"

  final val MODNAME = HostMODNAME + " Version Checker"

  final val VERSION = "1.1"

  private final val latest: String = stringAt(NetherEssence.LATEST)

  private final val downloadURL: String = NetherEssence.downloadURL

  private def tryNotifyClient(player: EntityPlayer) {
    if (!Loader.isModLoaded("VersionChecker") && isHigherVersion) {
      val ics = player
      ics.addChatMessage(new ChatComponentText("A new version of " + HostMODNAME + " is available!"))
      ics.addChatMessage(new ChatComponentText("==========" + latest + "=========="))
      ics.addChatMessage(new ChatComponentText("Get it at the following link:"))
      ics.addChatMessage(new ChatComponentText(downloadURL).setChatStyle(new ChatStyle().setItalic(true).setUnderlined(true)
        .setColor(EnumChatFormatting.BLUE)
        .setChatClickEvent(new ClickEvent(Action.OPEN_URL, downloadURL))))
    }
  }

  private def notifyServer() {
    println("Version " + latest + " of " + HostMODNAME + " is available!")
    println("Download it at " + downloadURL)
  }

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) {
    event.getModMetadata.modId = MODID
    event.getModMetadata.name = MODNAME
    event.getModMetadata.version = VERSION
    event.getModMetadata.credits = "The_Fireplace(Making the embeddable Version Checker)"
    event.getModMetadata.authorList = Arrays.asList(Array("The_Fireplace"):_*)
    event.getModMetadata.parent = HostMODID
  }

  @EventHandler
  def init(event: FMLInitializationEvent) {
    tryNotifyDynious()
    FMLCommonHandler.instance().bus().register(this)
  }

  @EventHandler
  def serverStarted(event: FMLServerStartedEvent) {
    tryNotifyServer()
  }

  @SubscribeEvent
  def onPlayerJoinClient(event: ClientConnectedToServerEvent) {
    new Thread() {

      override def run() {
        while (FMLClientHandler.instance().getClientPlayerEntity == null) try {
          Thread.sleep(100)
        } catch {
          case e: InterruptedException =>
        }
        tryNotifyClient(FMLClientHandler.instance().getClientPlayerEntity)
      }
    }
      .start()
  }

  private def isHigherVersion(): Boolean = {
    val _current = splitVersion(HostVERSION)
    val _new = splitVersion(latest)
    for (i <- 0 until Math.max(_current.length, _new.length)) {
      var curv = 0
      if (i < _current.length) curv = _current(i)
      var newv = 0
      if (i < _new.length) newv = _new(i)
      if (newv > curv) return true else if (curv > newv) return false
    }
    false
  }

  private def splitVersion(version: String): Array[Int] = {
    val tmp = version.split("\\.")
    val size = tmp.length
    val out = Array.ofDim[Int](size)
    for (i <- 0 until size) {
      out(i) = java.lang.Integer.parseInt(tmp(i))
    }
    out
  }

  private def tryNotifyServer() {
    if (isHigherVersion) notifyServer()
  }

  private def tryNotifyDynious() {
    if (isHigherVersion) {
      val update = new NBTTagCompound()
      update.setString("modDisplayName", HostMODNAME)
      update.setString("oldVersion", HostVERSION)
      update.setString("newVersion", latest)
      update.setString("updateURL", downloadURL)
      update.setBoolean("isDirectLink", false)
      FMLInterModComms.sendRuntimeMessage(HostMODID, "VersionChecker", "addUpdate", update)
    }
  }

  private def stringAt(url: String): String = {
    var output = ""
    var con: URLConnection = null
    try {
      con = new URL(url).openConnection()
      if (con != null) {
        val br = new BufferedReader(new InputStreamReader(con.getInputStream))
        var input: String = null
        val buf = new StringBuffer()
        while ((input = br.readLine()) != null) {
          buf.append(input)
        }
        output = buf.toString
        br.close()
      }
    } catch {
      case e: MalformedURLException => return "0.0.0.0"
      case e: IOException => return "0.0.0.0"
    }
    output
  }
}
