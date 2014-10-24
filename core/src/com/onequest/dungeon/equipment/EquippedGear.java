package com.onequest.dungeon.equipment;

import com.onequest.dungeon.Player;
import com.onequest.dungeon.equipment.*;

public class EquippedGear {
	Arms arms;
	Chest chest;
	Feet feet;
	Head head;
	Legs legs;
	Shoulders shoulders;
	Weapon weapon;

	public EquippedGear() {
		arms = null;
		chest = null;
		feet = null;
		head = null;
		legs = null;
		shoulders = null;
		weapon = null;
	}

	/**
	 * Equip this gear, removing it from the inventory
	 * 
	 * @param gear
	 *            the gear to equip
	 */
	public boolean equip(Equipment gear) {

		boolean equip = false;

		if (gear instanceof Arms) {
			if (arms != null)
				unequip(arms);

			arms = (Arms) gear;

			equip = true;
		}
		if (gear instanceof Chest) {
			if (chest != null)
				unequip(chest);

			chest = (Chest) gear;

			equip = true;
		}
		if (gear instanceof Feet) {
			if (feet != null)
				unequip(feet);

			feet = (Feet) gear;

			equip = true;
		}
		if (gear instanceof Head) {
			if (head != null)
				unequip(head);

			head = (Head) gear;

			equip = true;
		}
		if (gear instanceof Legs) {
			if (legs != null)
				unequip(legs);

			legs = (Legs) gear;

			equip = true;
		}
		if (gear instanceof Shoulders) {
			if (shoulders != null)
				unequip(shoulders);

			shoulders = (Shoulders) gear;

			equip = true;
		}
		if (gear instanceof Weapon) {
			if (weapon != null)
				unequip(weapon);

			weapon = (Weapon) gear;

			equip = true;
		}

		if (equip)
			Player.remInventory(gear);

		return equip;
	}

	/**
	 * Unequip given item, adding it back to the inventory
	 * 
	 * @param gear
	 * @return
	 */
	public boolean unequip(Equipment gear) {
		if (gear instanceof Arms) {
			Player.addInventory(arms);
			arms = null;
			return true;
		}
		if (gear instanceof Chest) {
			Player.addInventory(chest);
			chest = null;
			return true;
		}
		if (gear instanceof Feet) {
			Player.addInventory(feet);
			feet = null;
			return true;
		}
		if (gear instanceof Head) {
			Player.addInventory(head);
			head = null;
			return true;
		}
		if (gear instanceof Legs) {
			Player.addInventory(legs);
			legs = null;
			return true;
		}
		if (gear instanceof Shoulders) {
			Player.addInventory(shoulders);
			shoulders = null;
			return true;
		}
		if (gear instanceof Weapon) {
			Player.addInventory(weapon);
			weapon = null;
			return true;
		}

		return false;
	}
}
