/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.ToolKit.TKDice;
import com.starfyre1.ToolKit.TKStringHelpers;

import java.util.ArrayList;

public class SocialClassRecord {
	private static final String	UNEXPECTED_VALUE					= "Unexpected value: ";					//$NON-NLS-1$
	private static final String	RANDOM_MAGIC_ITEM					= "Random Magic Item";					//$NON-NLS-1$
	private static final String	FULL_SET_ARMOR						= "Full set Armor ";					//$NON-NLS-1$
	private static final String	BOTH_ITEMS_MADE_FROM				= "Both items made from ";				//$NON-NLS-1$
	private static final String	UPPER_ARMOR_OF_CHOICE_UP_TO_CHAIN	= "Upper Armor of Choice up to Chain";	//$NON-NLS-1$
	private static final String	WEAPON_OF_CHOICE					= "Weapon of Choice";					//$NON-NLS-1$
	private static final String	RANDOM_WEAPON						= "Random Weapon";						//$NON-NLS-1$
	private static final String	DAGGER								= "Dagger";								//$NON-NLS-1$
	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	private static final String	ROYAL								= "Royal";								//$NON-NLS-1$
	private static final String	ROYAL_BASTARD						= "Royal Bastard";						//$NON-NLS-1$
	private static final String	GREATER_NOBILITY					= "Greater Nobility";					//$NON-NLS-1$
	private static final String	MINOR_NOBILITY						= "Minor Nobility";						//$NON-NLS-1$
	private static final String	BASTARD_NOBILITY					= "Bastard Nobility";					//$NON-NLS-1$
	private static final String	RICH_MERCHANT						= "Rich Merchant";						//$NON-NLS-1$
	private static final String	MERCHANT							= "Merchant";							//$NON-NLS-1$
	private static final String	SKILLED_LABORER						= "Skilled Laborer";					//$NON-NLS-1$
	private static final String	LABORER								= "Laborer";							//$NON-NLS-1$
	private static final String	PEASANT								= "Peasant";							//$NON-NLS-1$
	private static final String	FREED_SLAVE							= "Freed Slave";						//$NON-NLS-1$
	private static final String	SLAVE								= "Slave";								//$NON-NLS-1$
	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private String				mSocialClass						= TKStringHelpers.EMPTY_STRING;
	private ArrayList<String>	mExtraItems							= new ArrayList<>();

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	public SocialClassRecord(boolean generate) {
		if (generate) {
			generateSocialClass();
		}
	}

	public SocialClassRecord(String socialClass) {
		mSocialClass = socialClass;
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public SocialClassRecord clone() {
		SocialClassRecord clone = new SocialClassRecord(false);
		clone.mSocialClass = mSocialClass;
		clone.mExtraItems = mExtraItems;

		return clone;
	}

	private void generateSocialClass() {
		int percent = TKDice.roll(100);
		if (percent < 6) {
			mSocialClass = SLAVE;
		} else if (percent < 11) {
			mSocialClass = FREED_SLAVE;
		} else if (percent < 46) {
			mSocialClass = PEASANT;
		} else if (percent < 56) {
			mSocialClass = LABORER;
		} else if (percent < 66) {
			mSocialClass = SKILLED_LABORER;
		} else if (percent < 76) {
			mSocialClass = MERCHANT;
		} else if (percent < 86) {
			mSocialClass = RICH_MERCHANT;
		} else if (percent < 96) {
			mSocialClass = BASTARD_NOBILITY;
		} else if (percent < 98) {
			mSocialClass = MINOR_NOBILITY;
		} else if (percent < 99) {
			mSocialClass = GREATER_NOBILITY;
		} else if (percent < 100) {
			mSocialClass = ROYAL_BASTARD;
		} else {
			mSocialClass = ROYAL;
		}
	}

	//DW This needs to move to materials/equipment/weapons
	private String generateRandomMaterial() {
		int roll = TKDice.roll(4);

		String value = switch (roll) {
			case 1:
				//				Material	Weight	HB		Asp		Break	Damage
				//			1)	Ang			Normal	+5%		+0		+2		+1
				yield "Ang"; //$NON-NLS-1$
			case 2:
				//				Material	Weight	HB		Asp		Break	Damage
				//			2)	Borang		1/2 	+5%		+1		+4		+1
				yield "Borang"; //$NON-NLS-1$

			case 3:
				//				Material	Weight	HB		Asp		Break	Damage
				//			3)	Ardacer		1/2		+5%		+2		+6		+2
				yield "Ardacer"; //$NON-NLS-1$

			case 4:
				//				Material	Weight	HB		Asp		Break	Damage
				//			4)	Ethru		1/2		+10%	+1		+4		+2
				yield "Ethru"; //$NON-NLS-1$
			default:
				throw new IllegalArgumentException(UNEXPECTED_VALUE + roll);
		};

		return value;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The socialClass. */
	public String getSocialClass() {
		return mSocialClass;
	}

	public int updateCoins(int coins) {
		int value = switch (mSocialClass) {
			case SLAVE:
				mExtraItems.add(DAGGER);
				yield coins / 4;

			case FREED_SLAVE:
				yield TKDice.roll(10) * 10;

			case PEASANT:
				yield coins;

			case LABORER:
				yield coins + TKDice.roll(10);

			case SKILLED_LABORER:
				yield coins + TKDice.roll(2, 10);

			case MERCHANT:
				yield coins + TKDice.roll(10) * 3;

			case RICH_MERCHANT:
				yield coins + TKDice.roll(10) * 10;

			case BASTARD_NOBILITY:
				if (TKDice.roll(100) > 75) {
					mExtraItems.add(RANDOM_WEAPON);
				}
				yield coins + TKDice.roll(6) * 50;

			case MINOR_NOBILITY:
				if (TKDice.roll(100) > 50) {
					mExtraItems.add(WEAPON_OF_CHOICE);
				}
				yield coins + TKDice.roll(4) * 100;

			case GREATER_NOBILITY:
				mExtraItems.add(WEAPON_OF_CHOICE);
				mExtraItems.add(UPPER_ARMOR_OF_CHOICE_UP_TO_CHAIN);
				if (TKDice.roll(100) > 75) {
					mExtraItems.add(BOTH_ITEMS_MADE_FROM + generateRandomMaterial());
				}
				yield coins + TKDice.roll(6) * 100;

			case ROYAL_BASTARD:
				mExtraItems.add(WEAPON_OF_CHOICE);
				boolean specialMetal = TKDice.roll(100) > 50;
				if (specialMetal) {
					mExtraItems.add(FULL_SET_ARMOR + generateRandomMaterial());
				} else {
					mExtraItems.add(FULL_SET_ARMOR);
				}
				if (TKDice.roll(100) > 90) {
					mExtraItems.add(RANDOM_MAGIC_ITEM);
				}

				yield coins + TKDice.roll(8) * 100;

			case ROYAL:
				mExtraItems.add(WEAPON_OF_CHOICE);
				mExtraItems.add(FULL_SET_ARMOR + generateRandomMaterial());
				if (TKDice.roll(100) > 70) {
					mExtraItems.add(RANDOM_MAGIC_ITEM);
				}

				yield coins + TKDice.roll(10) * 100;

			default:
				yield coins;
		};

		return value;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/

}
