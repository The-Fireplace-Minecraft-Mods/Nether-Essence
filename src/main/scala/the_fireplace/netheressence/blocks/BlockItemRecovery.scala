package the_fireplace.netheressence.blocks

import java.util.Random

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.{BlockStateContainer, IBlockState}
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.JsonToNBT
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{EnumFacing, EnumHand}
import net.minecraft.world.World
import the_fireplace.netheressence.NetherEssence
import the_fireplace.netheressence.handlers.DeadStackData

object BlockItemRecovery {
	private val STAGE: PropertyInteger = PropertyInteger.create("stage", 0, 2)
	private var active = false;
}

class BlockItemRecovery(material: Material) extends Block(material) {
	setDefaultState(blockState.getBaseState.withProperty(BlockItemRecovery.STAGE, Integer.valueOf(0)))
  setHardness(1.0F)
  setLightLevel(0.6f)
  setUnlocalizedName("item_recovery_block")
  setCreativeTab(NetherEssence.tabNetherEssence)
  setHarvestLevel("pickaxe", 0)
	setTickRandomly(true)

	override def onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, heldItem: ItemStack, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean ={
		if(!worldIn.isRemote){
			if(getStage(state) == 1){
				worldIn.setBlockState(pos, state.withProperty(BlockItemRecovery.STAGE, Integer.valueOf(2)))
			}
		}
		false
	}

	override def updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random) {
		super.updateTick(worldIn, pos, state, rand)

		if (DeadStackData.getInstance.deadStacks != null && !DeadStackData.getInstance.deadStacks.isEmpty) {
			if (getStage(state) == 0)
				worldIn.setBlockState(pos, state.withProperty(BlockItemRecovery.STAGE, Integer.valueOf(1)))
			else if(getStage(state) == 2 && !worldIn.isRemote && rand.nextInt(3) == 2 && !BlockItemRecovery.active){
				spawnItems(worldIn, pos, rand)
			}
		} else {
			if (getStage(state) >= 1)
				worldIn.setBlockState(pos, state.withProperty(BlockItemRecovery.STAGE, Integer.valueOf(0)))
		}
	}

	def spawnItems(worldIn: World, pos: BlockPos, rand: Random) : Unit = {
		BlockItemRecovery.active = true
		worldIn.setBlockToAir(pos)
		var offset:Int=0
		if (!DeadStackData.getInstance.deadStacks.isEmpty) {
		for(i <- 0 to rand.nextInt(if (DeadStackData.getInstance.deadStacks.size() > 9) 9 else DeadStackData.getInstance.deadStacks.size())) {
			if (DeadStackData.getInstance.deadStacks.get(i - offset) != null) {
				var item: EntityItem = new EntityItem(worldIn, pos.getX, pos.getY, pos.getZ, ItemStack.loadItemStackFromNBT(JsonToNBT.getTagFromJson(DeadStackData.getInstance.deadStacks.get(i - offset))))
				worldIn.spawnEntity(item)
				DeadStackData.getInstance.deadStacks.remove(i - offset)
				offset += 1
			}
		}
		}else{
			worldIn.setBlockState(pos, getDefaultState)
		}
		BlockItemRecovery.active = false
	}

	protected def getStage(state: IBlockState): Int = state.getValue(BlockItemRecovery.STAGE).asInstanceOf[Integer].intValue

	def withStage(stage: Int) : IBlockState = this.getDefaultState.withProperty(BlockItemRecovery.STAGE, Integer.valueOf(stage))

	override def getStateFromMeta(meta: Int): IBlockState = this.withStage(meta)

	override def getMetaFromState(state: IBlockState): Int = this.getStage(state)

	override protected def createBlockState = new BlockStateContainer(this, BlockItemRecovery.STAGE)
}
