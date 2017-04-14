package the_fireplace.netheressence.handlers

import java.io._
import java.util

import net.minecraftforge.common.DimensionManager

/**
	* @author The_Fireplace
	*/
object DeadStackData {
	private var instance: DeadStackData = _
	var data: File = _

	def getInstance: DeadStackData = {
		if (instance == null) load()
		instance
	}

	def save() {
		if (data == null) data = new File(DimensionManager.getCurrentSaveRootDirectory, "deadstacks.dat")
		saveToFile()
	}

	def load() {
		if (data == null) data = new File(DimensionManager.getCurrentSaveRootDirectory, "deadstacks.dat")
		readFromFile()
	}

	private def readFromFile() {
		val f: File = data
		if (f.exists) {
			try {
				val stream: ObjectInputStream = new ObjectInputStream(new FileInputStream(f))
				instance = stream.readObject.asInstanceOf[DeadStackData]
				stream.close()
			}
			catch {
				case e: IOException =>
					e.printStackTrace()
					instance = new DeadStackData
					f.delete
				case e: ClassNotFoundException =>
					e.printStackTrace()
					instance = new DeadStackData
					f.delete
			}
		}
		if (instance == null) instance = new DeadStackData
	}

	private def saveToFile() {
		try {
			if(!data.exists)
				if(!data.createNewFile) {
					System.out.println("Error: Nether Essence was unable to save dead stack data.")
					return
				}
			val out: ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(data))
			out.writeObject(instance)
			out.close()
		}
		catch {
			case e: IOException => e.printStackTrace()
		}
	}
}

class DeadStackData private() extends Serializable {
	deadStacks = new util.ArrayList[String]()
	DeadStackData.instance = this
	var deadStacks: util.ArrayList[String] = _

	override def toString: String = {
		classOf[DeadStackData].getName + "@" + Integer.toHexString(this.hashCode) + " [" + deadStacks.toString + "]"
	}
}