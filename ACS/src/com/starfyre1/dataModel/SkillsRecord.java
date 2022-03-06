/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.dataModel;

import com.starfyre1.GUI.CharacterSheet;
import com.starfyre1.ToolKit.TKStringHelpers;
import com.starfyre1.dataset.ClassList;
import com.starfyre1.dataset.common.BaseClass;
import com.starfyre1.interfaces.LevelListener;
import com.starfyre1.interfaces.Savable;
import com.starfyre1.startup.ACS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class SkillsRecord implements LevelListener, Savable {

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	public static final String	FILE_SECTTION_START_KEY		= "SKILLS_SECTTION_START";							//$NON-NLS-1$
	public static final String	FILE_SECTTION_END_KEY		= "SKILLS_SECTTION_END";							//$NON-NLS-1$

	public static final String	CONCEAL_LEVEL_BONUS_KEY		= "CONCEAL_LEVEL_BONUS_KEY";						//$NON-NLS-1$
	public static final String	STEALTH_LEVEL_BONUS_KEY		= "STEALTH_LEVEL_BONUS_KEY";						//$NON-NLS-1$
	public static final String	HEAR_LEVEL_BONUS_KEY		= "HEAR_LEVEL_BONUS_KEY";							//$NON-NLS-1$
	public static final String	LOCK_PICK_LEVEL_BONUS_KEY	= "LOCK_PICK_LEVEL_BONUS_KEY";						//$NON-NLS-1$
	public static final String	POCKET_PICK_LEVEL_BONUS_KEY	= "POCKET_PICK_LEVEL_BONUS_KEY";					//$NON-NLS-1$
	public static final String	CLIMB_LEVEL_BONUS_KEY		= "CLIMB_LEVEL_BONUS_KEY";							//$NON-NLS-1$
	public static final String	FIND_TRAP_LEVEL_BONUS_KEY	= "FIND_TRAP_LEVEL_BONUS_KEY";						//$NON-NLS-1$
	public static final String	REMOVE_TRAP_LEVEL_BONUS_KEY	= "REMOVE_TRAP_LEVEL_BONUS_KEY";					//$NON-NLS-1$

	private static final String	APPRAISE_TOOLTIP			= "90 + lvl";										//$NON-NLS-1$
	private static final String	BANDAGING_TOOLTIP			= "(lvl * 10) + (WIS > 12 ? +10) + Class Bonus";	//$NON-NLS-1$
	private static final String	DEPTH_SENSE_TOOLTIP			= "60 + (lvl * 3)";									//$NON-NLS-1$
	private static final String	DETECT_MAGIC_TOOLTIP		= "50 + (lvl * 5)";									//$NON-NLS-1$
	private static final String	DETECT_METALS_TOOLTIP		= "50 + (lvl * 5)";									//$NON-NLS-1$
	private static final String	DETECT_MORALS_TOOLTIP		= "30 + (diff in lvls * 5)";						//$NON-NLS-1$
	private static final String	DETECT_SECRET_DOORS_TOOLTIP	= "50 + (lvl * 5)";									//$NON-NLS-1$
	private static final String	DETECT_TRAPS_TOOLTIP		= "50 + (lvl * 5)";									//$NON-NLS-1$
	private static final String	HERBAL_LORE_TOOLTIP			= "15 + (lvl * 3) + Class Bonus";					//$NON-NLS-1$
	private static final String	HUNTING_TOOLTIP				= "WIS + (lvl * 5) + Weapon Bonus";					//$NON-NLS-1$
	private static final String	PERCEPTION_TOOLTIP			= "15 + INT + WIS + ((lvl -1) * 2) + Class Bonus";	//$NON-NLS-1$
	private static final String	TRACKING_TOOLTIP			= "70 + (lvl * 3) + Modifiers";						//$NON-NLS-1$

	private static final String	CONCEAL_TOOLTIP				= "20 + Dex Modifier + Class Bonus + Skill Bonus";	//$NON-NLS-1$
	private static final String	STEALTH_TOOLTIP				= "20 + Dex Modifier + Class Bonus + Skill Bonus";	//$NON-NLS-1$
	private static final String	HEAR_TOOLTIP				= "Class Bonus + Skill Bonus";						//$NON-NLS-1$
	private static final String	LOCK_PICK_TOOLTIP			= "20 + Dex Modifier + Skill Bonus";				//$NON-NLS-1$
	private static final String	PICK_POCKET_TOOLTIP			= "20 + Dex Modifier + Skill Bonus";				//$NON-NLS-1$
	private static final String	CLIMB_TOOLTIP				= "70 + Dex Modifier + Class Bonus + Skill Bonus";	//$NON-NLS-1$
	private static final String	FIND_TRAP_TOOLTIP			= "20 + Wis & INT Modifier + Skill Bonus";			//$NON-NLS-1$
	private static final String	REMOVE_TRAP_TOOLTIP			= "20 + Dex Modifier + Skill Bonus";				//$NON-NLS-1$

	private static final String	UNALLOCATED_TOOLTIP			= "Unallocated";									//$NON-NLS-1$

	private boolean				mIsInnate[]					= new boolean[20];

	//	private static final int	BANDAGING					= 0;
	private static final int	HUNTING						= 1;
	private static final int	TRACKING					= 2;
	private static final int	DETECT_MAGIC				= 3;
	private static final int	DETECT_MORALS				= 4;
	private static final int	DETECT_METALS				= 5;
	private static final int	DETECT_SECRET_DOORS			= 6;
	private static final int	DETECT_TRAPS				= 7;
	private static final int	APPRAISE					= 8;
	private static final int	DEPTH_SENSE					= 9;
	private static final int	CONCEAL						= 10;
	private static final int	STEALTH						= 11;
	private static final int	HEAR						= 12;
	private static final int	LOCK_PICK					= 13;
	private static final int	PICK_POCKET					= 14;
	private static final int	CLIMB						= 15;
	private static final int	FIND_TRAP					= 16;
	private static final int	REMOVE_TRAP					= 17;
	//	private static final int	HERBAL_LORE					= 18;
	private static final int	BERSERK						= 19;

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/
	private CharacterSheet		mCharacterSheet;

	private int					mAppraise					= 0;
	private int					mBandaging					= 0;
	private int					mDepthSense					= 0;
	private int					mDetectMagic				= 0;
	private int					mDetectMetals				= 0;
	private int					mDetectMorals				= 0;
	private int					mDetectSecretDoors			= 0;
	private int					mDetectTraps				= 0;
	private int					mHerbalLore					= 0;
	private int					mHunting					= 0;
	private int					mPerception					= 0;
	private int					mTracking					= 0;

	private int					mBerserk					= 0;

	private int					mClimb						= 0;
	private int					mConceal					= 0;
	private int					mFindTrap					= 0;
	private int					mHear						= 0;
	private int					mLockPick					= 0;
	private int					mPickPocket					= 0;
	private int					mRemoveTrap					= 0;
	private int					mStealth					= 0;

	private int					mClimbLevelBonus			= 0;
	private int					mConcealLevelBonus			= 0;
	private int					mFindTrapLevelBonus			= 0;
	private int					mHearLevelBonus				= 0;
	private int					mLockPickLevelBonus			= 0;
	private int					mPickPocketLevelBonus		= 0;
	private int					mRemoveTrapLevelBonus		= 0;
	private int					mStealthLevelBonus			= 0;

	private int					mUnallocated				= 0;

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/
	/**
	 * Creates a new {@link SkillsRecord}.
	 *
	 * @param characterSheet
	 * @param generate Should we generate skills (false if we haven't built the character yet)
	 */
	public SkillsRecord(CharacterSheet characterSheet, boolean generate) {
		mCharacterSheet = characterSheet;
		if (generate) {
			updateValues();
		}
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/
	@Override
	public void updateRecord() {
		updateValues();
	}

	private void updateValues() {
		AttributesRecord stats = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();
		BaseClass classInfo = headerRecord.getCharacterClass();
		if (classInfo != null) {
			int lvl = headerRecord.getLevel();

			boolean isInnateClass[] = classInfo.getInnateSkills();
			if (isInnateClass != null) {
				mIsInnate = isInnateClass;
			}

			generateBandaging(lvl, stats.getModifiedStat(AttributesRecord.WIS), classInfo);
			generateHunting(classInfo, stats.getModifiedStat(AttributesRecord.WIS));
			generateTracking(classInfo);
			generateDetectMagic(classInfo);
			generateDetectMorals();
			generateDetectMetals(classInfo);
			generateDetectSecretDoors(classInfo);
			generateDetectTraps(lvl, classInfo);
			generateAppraise(classInfo);
			generateDepthSense(classInfo);
			generateHerbalLore(lvl, classInfo);
			generateBerserk();
			generatePerception(classInfo, lvl, stats.getModifiedStat(AttributesRecord.INT), stats.getModifiedStat(AttributesRecord.WIS));
			generateUnallocatedSkills();

			generateConceal(classInfo);
			generateStealth(classInfo);
			generateHear(classInfo);
			generateLockPick();
			generatePickPocket();
			generateClimb(classInfo);
			generateFindTrap();
			generateRemoveTrap();
		} else {
			clearRecords();
		}
	}

	public void generateBandaging(int lvl, int wisdom, BaseClass classInfo) {
		// implement if needed - Everyone can bandage, does not need to be innate
		// boolean innate = mIsInnate[BANDAGING];
		int base = 10 * lvl;
		int wisdomBonus = wisdom > 12 ? 10 : 0;
		int classBonus = classInfo.getBandaging();

		// DW fix... need to check all magic areas
		String magicArea = ACS.getInstance().getCharacterSheet().getSpellListDisplay().getMagicArea();
		int magicAreaBonus = switch (magicArea) {
			case ClassList.NATURAL_LORE:
			case ClassList.ARCANE_LORE:
				yield 20;
			case ClassList.SAUTRIAN:
			case ClassList.GRAUN:
			case ClassList.LORRELL:
				yield 10;
			default:
				yield 0;

		};

		mBandaging = base + wisdomBonus + classBonus + magicAreaBonus;
	}

	public void generateHunting(BaseClass classInfo, int wisdom) {
		boolean innate = mIsInnate[HUNTING];

		// Add +20% if they are hunting with a Spear or a Bow.
		int base = 0;
		int classBonus = 0;
		if (innate) {
			base = wisdom;
			classBonus = classInfo.getHunting();
		}
		mHunting = base + classBonus;
	}

	public void generatePerception(BaseClass classInfo, int lvl, int intel, int wisdom) {
		int base = intel + wisdom + 15 + (lvl - 1) * 2;
		int classBonus = classInfo.getPerception();
		mPerception = base + classBonus;
	}

	public void generateTracking(BaseClass classInfo) {
		boolean innate = mIsInnate[TRACKING];
		int base = 0;
		int classBonus = 0;

		if (innate) {
			base = 70;
			classBonus = classInfo.getTracking();
		}
		mTracking = base + classBonus;
	}

	public void generateDetectMagic(BaseClass classInfo) {
		boolean innate = mIsInnate[DETECT_MAGIC];
		int base = 0;
		int classBonus = 0;
		if (innate) {
			base = 50;
			classBonus = classInfo.getDetectMagic();
		}

		mDetectMagic = base + classBonus;
	}

	public void generateDetectMorals() {
		boolean innate = mIsInnate[DETECT_MORALS];
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();
		int lvlBonus = headerRecord.getLevel() * 5;
		mDetectMorals = innate ? 30 + lvlBonus : 0;
	}

	public void generateDetectMetals(BaseClass classInfo) {
		boolean innate = mIsInnate[DETECT_METALS];
		mDetectMetals = innate ? classInfo.getDetectMetals() : 0;
	}

	public void generateDetectSecretDoors(BaseClass classInfo) {
		boolean innate = mIsInnate[DETECT_SECRET_DOORS];
		int base = 0;
		int classBonus = 0;

		if (innate) {
			base = 50;
			classBonus = classInfo.getDetectSecretDoors();
		}
		mDetectSecretDoors = base + classBonus;
	}

	public void generateDetectTraps(int lvl, BaseClass classInfo) {
		boolean innate = mIsInnate[DETECT_TRAPS];
		int base = 0;
		int classBonus = 0;
		if (innate) {
			base = 50 + lvl * 5;
			classBonus = classInfo.getDetectTraps();
		}
		mDetectTraps = base + classBonus;
	}

	public void generateAppraise(BaseClass classInfo) {
		boolean innate = mIsInnate[APPRAISE];
		int base = 0;
		int classBonus = 0;
		if (innate) {
			base = 90;
			classBonus = classInfo.getAppraise();
		}
		mAppraise = base + classBonus;
	}

	public void generateDepthSense(BaseClass classInfo) {
		boolean innate = mIsInnate[DEPTH_SENSE];
		int base = 0;
		int classBonus = 0;
		if (innate) {
			base = 60;
			classBonus = classInfo.getDepthSense();
		}
		mDepthSense = base + classBonus;
	}

	private int getDexModifier() {
		// DW modify due to worn armor (This modifies your Dex - max of 24)
		/*		DX Modifiers due to Armor
				No Armor	+2*
				H. Cloth		+1*
				S. Cloth		+1
				Leather		+0
				Lacquered	+0
				Studded		-0
				Ring		-1
				Chain		-2
				Scale 		-4
				Banded 		-6
				Plate 		-8
		*/

		int dex = mCharacterSheet.getAttributesRecord().getModifiedStat(AttributesRecord.DEX);
		int mod;
		if (dex < 3) {
			mod = -500;
		} else if (dex < 8) {
			mod = -20;
		} else if (dex < 10) {
			mod = -10;
		} else if (dex < 13) {
			mod = -5;
		} else if (dex < 15) {
			mod = 0;
		} else if (dex < 17) {
			mod = 5;
		} else if (dex < 19) {
			mod = 10;
		} else if (dex < 20) {
			mod = 15;
		} else if (dex < 21) {
			mod = 20;
		} else if (dex < 22) {
			mod = 25;
		} else if (dex < 24) {
			mod = 30;
		} else {
			mod = 35;
		}

		return mod;
	}

	public void generateConceal(BaseClass classInfo) {
		boolean innate = mIsInnate[CONCEAL];
		int base = 0;
		int classBonus = 0;
		int dexModifier = 0;
		if (innate) {
			base = 20;
			classBonus = classInfo.getConceal();
			dexModifier = getDexModifier();
		}

		mConceal = base + mConcealLevelBonus + classBonus + dexModifier;
	}

	public void generateStealth(BaseClass classInfo) {
		boolean innate = mIsInnate[STEALTH];
		int base = 0;
		int classBonus = 0;
		int dexModifier = 0;
		if (innate) {
			base = 20;
			classBonus = classInfo.getStealth();
			dexModifier = getDexModifier();
		}

		mStealth = base + mStealthLevelBonus + classBonus + dexModifier;
	}

	public void generateHear(BaseClass classInfo) {
		// DW need to modify by this table...

		/*
		  		Listener is:				Chance to Hear 		Range
				Human, Non-thief with a save		20%			60'
				Vs. Surprise of less than 75%


				Human, Non-thief with a save		30%			60'
				Vs. Surprise of more than 75%

				Elven, Half-Elven and Dwarrow		30% 		120'

				Other--(some monsters will have		20%			60'
				high hearing abilities)

				Thief					Varies with lvl		60' *
					*	Add 5’ per 10% above 100% to hear.

				Halve all chances when listening to a door.

		*/

		boolean innate = mIsInnate[HEAR];
		int classBonus = classInfo.getHear();

		mHear = classBonus + (innate ? mHearLevelBonus : 0);
	}

	public void generateLockPick() {
		boolean innate = mIsInnate[LOCK_PICK];
		int base = 0;
		int dexModifier = 0;
		if (innate) {
			base = 20;
			dexModifier = getDexModifier();
		}

		mLockPick = base + mLockPickLevelBonus + dexModifier;
	}

	public void generatePickPocket() {
		boolean innate = mIsInnate[PICK_POCKET];
		int base = 0;
		int dexModifier = 0;
		if (innate) {
			base = 20;
			dexModifier = getDexModifier();
		}

		mPickPocket = base + mPickPocketLevelBonus + dexModifier;
	}

	public void generateClimb(BaseClass classInfo) {
		boolean innate = mIsInnate[CLIMB];
		int base = 0;
		int dexModifier = 0;
		int classBonus = 0;
		if (innate) {
			base = 70;
			dexModifier = getDexModifier();
			classBonus = classInfo.getClimb();
		}

		mClimb = base + mClimbLevelBonus + classBonus + dexModifier;
	}

	public void generateFindTrap() {
		boolean innate = mIsInnate[FIND_TRAP];
		int base = 0;
		int mod = 0;
		if (innate) {
			mod = findTrapModification();
			base = 20;
		}
		mFindTrap = base + mod + mFindTrapLevelBonus;
	}

	private int findTrapModification() {
		AttributesRecord record = mCharacterSheet.getAttributesRecord();
		int stats = record.getModifiedStat(AttributesRecord.INT) + record.getModifiedStat(AttributesRecord.WIS);
		int mod;
		if (stats < 13) {
			mod = -20;
		} else if (stats < 15) {
			mod = -15;
		} else if (stats < 17) {
			mod = -10;
		} else if (stats < 19) {
			mod = -5;
		} else if (stats < 24) {
			mod = 0;
		} else if (stats < 27) {
			mod = 5;
		} else if (stats < 30) {
			mod = 10;
		} else if (stats < 33) {
			mod = 15;
		} else if (stats < 36) {
			mod = 20;
		} else {
			mod = 25;
		}
		return mod;
	}

	public void generateRemoveTrap() {
		boolean innate = mIsInnate[REMOVE_TRAP];
		int base = 0;
		int dexModifier = 0;
		if (innate) {
			base = 20;
			dexModifier = getDexModifier();
		}

		mRemoveTrap = base + mRemoveTrapLevelBonus + dexModifier;
	}

	public void generateHerbalLore(int lvl, BaseClass classInfo) {
		// implement if needed - Everyone can use herbal lore, does not need to be innate
		// boolean innate = mIsInnate[HERBAL_LORE];
		int base = 15 + lvl * 3;
		int classBonus = classInfo.getHerbalLore();

		mHerbalLore = base + classBonus;
	}

	public void generateBerserk() {
		boolean innate = mIsInnate[BERSERK];
		int berserk = 0;
		if (innate) {
			// DW fill Berserk out
		}

		mBerserk = berserk;
	}

	public void generateUnallocatedSkills() {
		BaseClass characterClass = mCharacterSheet.getHeaderRecord().getCharacterClass();
		if (characterClass == null) {
			return;
		}
		int value = characterClass.getUnallocatedSkills();

		mUnallocated = value - mConcealLevelBonus - mStealthLevelBonus - mHearLevelBonus - mLockPickLevelBonus - //
						mPickPocketLevelBonus - mClimbLevelBonus - mFindTrapLevelBonus - mRemoveTrapLevelBonus;
	}

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/
	/** @return The bandaging. */
	public int getBandaging() {
		return mBandaging;
	}

	/** @return The hunting. */
	public int getHunting() {
		return mHunting;
	}

	/** @return The tracking. */
	public int getTracking() {
		return mTracking;
	}

	/** @return The detectMagic. */
	public int getDetectMagic() {
		return mDetectMagic;
	}

	/** @return The detectMorals. */
	public int getDetectMorals() {
		return mDetectMorals;
	}

	/** @return The detectMetals. */
	public int getDetectMetals() {
		return mDetectMetals;
	}

	/** @return The detectSecretDoors. */
	public int getDetectSecretDoors() {
		return mDetectSecretDoors;
	}

	/** @return The detectTraps. */
	public int getDetectTraps() {
		return mDetectTraps;
	}

	/** @return The appraise. */
	public int getAppraise() {
		return mAppraise;
	}

	/** @return The depthSense. */
	public int getDepthSense() {
		return mDepthSense;
	}

	/** @return The conceal. */
	public int getConceal() {
		return mConceal;
	}

	/** @return The stealth. */
	public int getStealth() {
		return mStealth;
	}

	/** @return The hear. */
	public int getHear() {
		return mHear;
	}

	/** @return The lockPick. */
	public int getLockPick() {
		return mLockPick;
	}

	/** @return The pickPocket. */
	public int getPickPocket() {
		return mPickPocket;
	}

	/** @return The climb. */
	public int getClimb() {
		return mClimb;
	}

	/** @return The findTrap. */
	public int getFindTrap() {
		return mFindTrap;
	}

	/** @return The removeTrap. */
	public int getRemoveTrap() {
		return mRemoveTrap;
	}

	/** @return The herbalLore. */
	public int getHerbalLore() {
		return mHerbalLore;
	}

	/** @return The Perception. */
	public int getPerception() {
		return mPerception;
	}

	/** @return The berserk. */
	public int getBerserk() {
		return mBerserk;
	}

	/** @return The concealLevelBonus. */
	public int getConcealLevelBonus() {
		return mConcealLevelBonus;
	}

	/** @param concealLevelBonus The value to set for concealLevelBonus. */
	public void setConcealLevelBonus(int concealLevelBonus) {
		mConcealLevelBonus = concealLevelBonus;
	}

	/** @return The stealthLevelBonus. */
	public int getStealthLevelBonus() {
		return mStealthLevelBonus;
	}

	/** @param stealthLevelBonus The value to set for stealthLevelBonus. */
	public void setStealthLevelBonus(int stealthLevelBonus) {
		mStealthLevelBonus = stealthLevelBonus;
	}

	/** @return The hearLevelBonus. */
	public int getHearLevelBonus() {
		return mHearLevelBonus;
	}

	/** @param hearLevelBonus The value to set for hearLevelBonus. */
	public void setHearLevelBonus(int hearLevelBonus) {
		mHearLevelBonus = hearLevelBonus;
	}

	/** @return The lockPickLevelBonus. */
	public int getLockPickLevelBonus() {
		return mLockPickLevelBonus;
	}

	/** @param lockPickLevelBonus The value to set for lockPickLevelBonus. */
	public void setLockPickLevelBonus(int lockPickLevelBonus) {
		mLockPickLevelBonus = lockPickLevelBonus;
	}

	/** @return The pickPocketLevelBonus. */
	public int getPickPocketLevelBonus() {
		return mPickPocketLevelBonus;
	}

	/** @param pickPocketLevelBonus The value to set for pickPocketLevelBonus. */
	public void setPickPocketLevelBonus(int pickPocketLevelBonus) {
		mPickPocketLevelBonus = pickPocketLevelBonus;
	}

	/** @return The climbLevelBonus. */
	public int getClimbLevelBonus() {
		return mClimbLevelBonus;
	}

	/** @param climbLevelBonus The value to set for climbLevelBonus. */
	public void setClimbLevelBonus(int climbLevelBonus) {
		mClimbLevelBonus = climbLevelBonus;
	}

	/** @return The findTrapLevelBonus. */
	public int getFindTrapLevelBonus() {
		return mFindTrapLevelBonus;
	}

	/** @param findTrapLevelBonus The value to set for findTrapLevelBonus. */
	public void setFindTrapLevelBonus(int findTrapLevelBonus) {
		mFindTrapLevelBonus = findTrapLevelBonus;
	}

	/** @return The removeTrapLevelBonus. */
	public int getRemoveTrapLevelBonus() {
		return mRemoveTrapLevelBonus;
	}

	/** @param removeTrapLevelBonus The value to set for removeTrapLevelBonus. */
	public void setRemoveTrapLevelBonus(int removeTrapLevelBonus) {
		mRemoveTrapLevelBonus = removeTrapLevelBonus;
	}

	/** @param conceal The value to set for conceal. */
	public void setConceal(int conceal) {
		mConceal = conceal;
	}

	/** @param stealth The value to set for stealth. */
	public void setStealth(int stealth) {
		mStealth = stealth;
	}

	/** @param hear The value to set for hear. */
	public void setHear(int hear) {
		mHear = hear;
	}

	/** @param lockPick The value to set for lockPick. */
	public void setLockPick(int lockPick) {
		mLockPick = lockPick;
	}

	/** @param pickPocket The value to set for pickPocket. */
	public void setPickPocket(int pickPocket) {
		mPickPocket = pickPocket;
	}

	/** @param climb The value to set for climb. */
	public void setClimb(int climb) {
		mClimb = climb;
	}

	/** @param findTrap The value to set for findTrap. */
	public void setFindTrap(int findTrap) {
		mFindTrap = findTrap;
	}

	/** @param removeTrap The value to set for removeTrap. */
	public void setRemoveTrap(int removeTrap) {
		mRemoveTrap = removeTrap;
	}

	/** @return The unallocated points. */
	public int getUnallocatedSkills() {
		return mUnallocated;
	}

	/** @param unallocated The value to set for unallocated points. */
	public void setUnallocatedSkills(int unallocated) {
		mUnallocated = unallocated;
	}

	/** @return The bandaging tooltip. */
	public String getBandagingToolTip() {
		// implement if needed - Everyone can bandage, does not need to be innate
		// boolean innate = mIsInnate[BANDAGING];
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int level = 10 * headerRecord.getLevel();
		int wisdomBonus = mCharacterSheet.getAttributesRecord().getModifiedStat(AttributesRecord.WIS) > 12 ? 10 : 0;
		int classBonus = headerRecord.getCharacterClass().getBandaging();

		if (ACS.showCalculations()) {
			sb.append(BANDAGING_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append("(" + level + ") + (" + wisdomBonus + ") + " + classBonus + " = " + (level + wisdomBonus + classBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		return sb.toString();
	}

	/** @return The hunting tooltip. */
	public String getHuntingToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[HUNTING];

		// Add +20% if they are hunting with a Spear or a Bow.
		int wisBonus = 0;
		int classBonus = 0;
		int weaponBonus = 0;
		if (innate) {
			wisBonus = mCharacterSheet.getAttributesRecord().getModifiedStat(AttributesRecord.WIS);
			classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getHunting();
			// DW __add weapon bonus
			//			weaponBonus = hasWeaponBonus() ? 20 : 0; // Spear or Bow
		}
		if (ACS.showCalculations()) {
			sb.append(HUNTING_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(wisBonus + " + (" + classBonus + ") + " + weaponBonus + " = " + (wisBonus + classBonus + weaponBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		return sb.toString();
	}

	/** @return The tracking tooltip. */
	public String getTrackingToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[TRACKING];
		int base = 0;
		int classBonus = 0;

		if (innate) {
			base = 70;
			classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getTracking();
		}
		if (ACS.showCalculations()) {
			sb.append(TRACKING_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + (" + classBonus + ") = " + (base + classBonus) + " +/- modifiers<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		return sb.toString();
	}

	/** @return The detectMagic tooltip. */
	public String getDetectMagicToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[DETECT_MAGIC];
		int base = 0;
		int classBonus = 0;
		if (innate) {
			base = 50;
			classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getDetectMagic();
		}
		if (ACS.showCalculations()) {
			sb.append(DETECT_MAGIC_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + (" + classBonus + ") = " + (base + classBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		return sb.toString();
	}

	/** @return The detectMorals tooltip. */
	public String getDetectMoralsToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[DETECT_MORALS];
		int base = innate ? 30 : 0;
		if (ACS.showCalculations()) {
			sb.append(DETECT_MORALS_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " = " + base + " +/- level modifiers<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$

		return sb.toString();
	}

	/** @return The detectMetals tooltip. */
	public String getDetectMetalsToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$

		boolean innate = mIsInnate[DETECT_METALS];
		int base = innate ? mCharacterSheet.getHeaderRecord().getCharacterClass().getDetectMetals() : 0;
		if (ACS.showCalculations()) {
			sb.append(DETECT_METALS_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " = " + base + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$

		return sb.toString();
	}

	/** @return The detectSecretDoors tooltip. */
	public String getDetectSecretDoorsToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$

		boolean innate = mIsInnate[DETECT_SECRET_DOORS];
		int base = 0;
		int classBonus = 0;

		if (innate) {
			base = 50;
			classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getDetectSecretDoors();
		}
		if (ACS.showCalculations()) {
			sb.append(DETECT_SECRET_DOORS_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + (" + classBonus + ") = " + (base + classBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		return sb.toString();
	}

	/** @return The detectTraps tooltip. */
	public String getDetectTrapsToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$

		boolean innate = mIsInnate[DETECT_TRAPS];
		int base = 0;
		int level = 0;
		if (innate) {
			base = 50;
			level = mCharacterSheet.getHeaderRecord().getLevel() * 5;
		}
		if (ACS.showCalculations()) {
			sb.append(DETECT_TRAPS_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + (" + level + ") = " + (base + level) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		return sb.toString();
	}

	/** @return The appraise tooltip. */
	public String getAppraiseToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[APPRAISE];
		int base = 0;
		int classBonus = 0;
		if (innate) {
			base = 90;
			classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getAppraise();
		}
		if (ACS.showCalculations()) {
			sb.append(APPRAISE_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + " + classBonus + " = " + (base + classBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		return sb.toString();
	}

	/** @return The depthSense tooltip. */
	public String getDepthSenseToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[DEPTH_SENSE];
		int base = 0;
		int classBonus = 0;
		if (innate) {
			base = 60;
			classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getDepthSense();
		}
		if (ACS.showCalculations()) {
			sb.append(DEPTH_SENSE_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + (" + classBonus + ") = " + (base + classBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		return sb.toString();
	}

	/** @return The conceal tooltip. */
	public String getConcealToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[CONCEAL];
		int base = 0;
		int classBonus = 0;
		int skillBonus = 0;
		int dexModifier = 0;
		if (innate) {
			base = 20;
			classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getConceal();
			skillBonus = getConcealLevelBonus();
			dexModifier = getDexModifier();
		}

		if (ACS.showCalculations()) {
			sb.append(CONCEAL_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + " + dexModifier + " + " + classBonus + " + " + skillBonus + " = " + (base + dexModifier + classBonus + skillBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		return sb.toString();
	}

	/** @return The stealth tooltip. */
	public String getStealthToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[STEALTH];
		int base = 0;
		int dexModifier = 0;
		int classBonus = 0;
		int skillBonus = 0;
		if (innate) {
			base = 20;
			dexModifier = getDexModifier();
			classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getStealth();
			skillBonus = getStealthLevelBonus();
		}
		if (ACS.showCalculations()) {
			sb.append(STEALTH_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + " + dexModifier + " + " + classBonus + " + " + skillBonus + " = " + (base + dexModifier + classBonus + skillBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		return sb.toString();
	}

	/** @return The hear tooltip. */
	public String getHearToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		//		boolean innate = mIsInnate[HEAR];
		int classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getHear();
		int skillBonus = getHearLevelBonus();

		if (ACS.showCalculations()) {
			sb.append(HEAR_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(classBonus + " + " + skillBonus + " = " + (classBonus + skillBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		return sb.toString();
	}

	/** @return The lockPick tooltip. */
	public String getLockPickToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[LOCK_PICK];
		int base = 0;
		int dexModifier = 0;
		int skillBonus = 0;
		if (innate) {
			base = 20;
			dexModifier = getDexModifier();
			skillBonus = getLockPickLevelBonus();
		}
		if (ACS.showCalculations()) {
			sb.append(LOCK_PICK_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + " + dexModifier + " + " + skillBonus + " = " + (base + dexModifier + skillBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		return sb.toString();
	}

	/** @return The pickPocket tooltip. */
	public String getPickPocketToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[PICK_POCKET];
		int base = 0;
		int dexModifier = 0;
		int skillBonus = 0;
		if (innate) {
			base = 20;
			dexModifier = getDexModifier();
			skillBonus = getPickPocketLevelBonus();
		}
		if (ACS.showCalculations()) {
			sb.append(PICK_POCKET_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + " + dexModifier + " + " + skillBonus + " = " + (base + dexModifier + skillBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		return sb.toString();
	}

	/** @return The climb tooltip. */
	public String getClimbToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[CLIMB];
		int base = 0;
		int dexModifier = 0;
		int classBonus = 0;
		int skillBonus = 0;
		if (innate) {
			base = 70;
			dexModifier = getDexModifier();
			classBonus = mCharacterSheet.getHeaderRecord().getCharacterClass().getClimb();
			skillBonus = getClimbLevelBonus();
		}
		if (ACS.showCalculations()) {
			sb.append(CLIMB_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + " + dexModifier + " + " + classBonus + " + " + skillBonus + " = " + (base + dexModifier + classBonus + skillBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		return sb.toString();
	}

	/** @return The findTrap tooltip. */
	public String getFindTrapToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		boolean innate = mIsInnate[FIND_TRAP];
		int base = 0;
		int mod = 0;
		int skillBonus = 0;
		if (innate) {
			mod = findTrapModification();
			base = 20;
			skillBonus = getFindTrapLevelBonus();
		}
		if (ACS.showCalculations()) {
			sb.append(FIND_TRAP_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + " + mod + " + " + skillBonus + " = " + (base + mod + skillBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		return sb.toString();
	}

	/** @return The removeTrap tooltip. */
	public String getRemoveTrapToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$

		boolean innate = mIsInnate[REMOVE_TRAP];
		int base = 0;
		int mod = 0;
		int skillBonus = 0;
		if (innate) {
			base = 20;
			mod = getDexModifier();
			skillBonus = getRemoveTrapLevelBonus();
		}
		if (ACS.showCalculations()) {
			sb.append(REMOVE_TRAP_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + " + mod + " + " + skillBonus + " = " + (base + mod + skillBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		return sb.toString();
	}

	/** @return The herbalLore tooltip. */
	public String getHerbalLoreToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();
		int base = 15;
		int level = headerRecord.getLevel() * 3;
		int classBonus = headerRecord.getCharacterClass().getHerbalLore();

		if (ACS.showCalculations()) {
			sb.append(HERBAL_LORE_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + (" + level + ") + " + classBonus + " = " + (base + level + classBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		return sb.toString();
	}

	/** @return The perception tooltip. */
	public String getPerceptionToolTip() {
		StringBuilder sb = new StringBuilder("<html>"); //$NON-NLS-1$
		AttributesRecord attr = mCharacterSheet.getAttributesRecord();
		HeaderRecord headerRecord = mCharacterSheet.getHeaderRecord();

		int intel = attr.getModifiedStat(AttributesRecord.INT);
		int wis = attr.getModifiedStat(AttributesRecord.WIS);
		int base = 15;
		int level = headerRecord.getLevel() - 1;
		int classBonus = headerRecord.getCharacterClass().getPerception();

		if (ACS.showCalculations()) {
			sb.append(PERCEPTION_TOOLTIP + "<br>"); //$NON-NLS-1$
		}
		sb.append(base + " + " + intel + " + " + wis + " + (" + level * 2 + ") + " + classBonus + " = " + (base + intel + wis + level * 2 + classBonus) + "<br></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		return sb.toString();
	}

	/** @return The UnallocatedSkill tooltip. */
	public String getUnallocatedSkillsToolTip() {
		return UNALLOCATED_TOOLTIP;
	}

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
	@Override
	public StringTokenizer readValues(BufferedReader br) {
		String in;

		try {
			while ((in = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(in);
				while (tokenizer.hasMoreTokens()) {
					String key = tokenizer.nextToken();
					if (key.equals(FILE_SECTTION_END_KEY)) {
						generateUnallocatedSkills();
						return tokenizer;
					} else if (!tokenizer.hasMoreTokens()) {
						// key has no value
						break;
					}
					String value = tokenizer.nextToken();
					setKeyValuePair(key, value);
				}
			}
			generateUnallocatedSkills();
		} catch (IOException ioe) {
			//DW9:: Log this
			System.err.println(ioe.getMessage());
		}
		return null;
	}

	@Override
	public void saveValues(BufferedWriter br) throws IOException {
		writeValues(br);
	}

	@Override
	public void writeValues(BufferedWriter br) throws IOException {

		br.write(FILE_SECTTION_START_KEY + System.lineSeparator());
		br.write(CONCEAL_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mConcealLevelBonus + System.lineSeparator());
		br.write(STEALTH_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mStealthLevelBonus + System.lineSeparator());
		br.write(HEAR_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mHearLevelBonus + System.lineSeparator());
		br.write(LOCK_PICK_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mLockPickLevelBonus + System.lineSeparator());
		br.write(POCKET_PICK_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mPickPocketLevelBonus + System.lineSeparator());
		br.write(CLIMB_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mClimbLevelBonus + System.lineSeparator());
		br.write(FIND_TRAP_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mFindTrapLevelBonus + System.lineSeparator());
		br.write(REMOVE_TRAP_LEVEL_BONUS_KEY + TKStringHelpers.SPACE + mRemoveTrapLevelBonus + System.lineSeparator());
		br.write(FILE_SECTTION_END_KEY + System.lineSeparator());
	}

	@Override
	public void setKeyValuePair(String key, Object obj) {
		String value = (String) obj;
		if (CONCEAL_LEVEL_BONUS_KEY.equals(key)) {
			mConcealLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (STEALTH_LEVEL_BONUS_KEY.equals(key)) {
			mStealthLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (HEAR_LEVEL_BONUS_KEY.equals(key)) {
			mHearLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (LOCK_PICK_LEVEL_BONUS_KEY.equals(key)) {
			mLockPickLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (POCKET_PICK_LEVEL_BONUS_KEY.equals(key)) {
			mPickPocketLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (CLIMB_LEVEL_BONUS_KEY.equals(key)) {
			mClimbLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (FIND_TRAP_LEVEL_BONUS_KEY.equals(key)) {
			mFindTrapLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else if (REMOVE_TRAP_LEVEL_BONUS_KEY.equals(key)) {
			mRemoveTrapLevelBonus = TKStringHelpers.getIntValue(value, 0);
		} else {
			//DW9:: log this
			System.err.println("Unknown key read from file: " + key); //$NON-NLS-1$
		}
	}

	/**
	 *
	 */
	public void clearRecords() {
		mAppraise = 0;
		mBandaging = 0;
		mDepthSense = 0;
		mDetectMagic = 0;
		mDetectMetals = 0;
		mDetectMorals = 0;
		mDetectSecretDoors = 0;
		mDetectTraps = 0;
		mHerbalLore = 0;
		mHunting = 0;
		mPerception = 0;
		mTracking = 0;

		mBerserk = 0;

		mClimb = 0;
		mConceal = 0;
		mFindTrap = 0;
		mHear = 0;
		mLockPick = 0;
		mPickPocket = 0;
		mRemoveTrap = 0;
		mStealth = 0;

		mClimbLevelBonus = 0;
		mConcealLevelBonus = 0;
		mFindTrapLevelBonus = 0;
		mHearLevelBonus = 0;
		mLockPickLevelBonus = 0;
		mPickPocketLevelBonus = 0;
		mRemoveTrapLevelBonus = 0;
		mStealthLevelBonus = 0;

		mUnallocated = 0;
	}

}
