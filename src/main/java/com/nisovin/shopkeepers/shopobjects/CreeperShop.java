package com.nisovin.shopkeepers.shopobjects;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.nisovin.shopkeepers.ShopObjectType;
import com.nisovin.shopkeepers.Shopkeeper;

public class CreeperShop extends LivingEntityShop {

	private boolean powered = false;

	protected CreeperShop(Shopkeeper shopkeeper) {
		super(shopkeeper);
	}

	@Override
	public void load(ConfigurationSection config) {
		super.load(config);
		powered = config.getBoolean("powered", false);
	}

	@Override
	public void save(ConfigurationSection config) {
		super.save(config);
		config.set("powered", this.powered);
	}

	@Override
	protected EntityType getEntityType() {
		return EntityType.CREEPER;
	}

	@Override
	public boolean spawn() {
		boolean spawned = super.spawn();
		if (spawned && this.entity != null && this.entity.isValid() && this.entity instanceof Creeper) {
			((Creeper) this.entity).setPowered(this.powered);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ItemStack getTypeItem() {
		return new ItemStack(Material.WOOL, 1, this.powered ? (short) 3 : (short) 5);
	}

	@Override
	public ShopObjectType getObjectType() {
		return DefaultShopObjectTypes.CREEPER;
	}

	@Override
	public void cycleType() {
		this.powered = !this.powered;
		if (this.entity != null && this.entity.isValid()) {
			((Creeper) this.entity).setPowered(this.powered);
		}
	}
}