/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.parser;

import com.starfyre1.ToolKit.TKStringHelpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ParseSpells {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//		File in = new File("C:\\Users/User/Desktop/spells/Mages.txt");
		//		File out = new File("C:\\Users/User/Desktop/spells/MagesProcessed.txt");

		File in = new File("C:\\Users/User/Desktop/spells/Priest.txt"); //$NON-NLS-1$
		File out = new File("C:\\Users/User/Desktop/spells/PriestProcessed.txt"); //$NON-NLS-1$

		try {
			BufferedReader brIn = new BufferedReader(new FileReader(in));
			BufferedWriter brOut = new BufferedWriter(new FileWriter(out));
			boolean start = false;
			boolean veryFirst = true;

			for (String line; (line = brIn.readLine()) != null;) {
				line = line.trim();

				String[] splitLine = line.split("[()]"); //	\\s+| //$NON-NLS-1$
				System.out.println("split: [" + Arrays.stream(splitLine).collect(Collectors.joining("][")) + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

				if (splitLine[0].equals(TKStringHelpers.EMPTY_STRING)) {
					// do nothing
				} else if (splitLine.length == 1) {
					if (splitLine[0].startsWith("Power Zero")) { //$NON-NLS-1$
						start = true;
						brOut.write("\n"); //$NON-NLS-1$
					} else if (splitLine[0].startsWith("Power")) { //$NON-NLS-1$
						start = true;
						brOut.write(")));\n\n"); //$NON-NLS-1$
					} else if (!splitLine[0].trim().contains(" ")) { //$NON-NLS-1$
						if (!veryFirst) {
							brOut.write(")));\n\n"); //$NON-NLS-1$
						} else {
							veryFirst = false;
						}
					}
					brOut.write("// " + splitLine[0] + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					String name = splitLine[1].trim();
					int power = getPowerLevel(name);
					if (start) {
						brOut.write("mSpells.add(new ArrayList<SpellRecord>(Arrays.asList( //\n"); //$NON-NLS-1$
						start = false;
					} else {
						brOut.write(", //\n"); //$NON-NLS-1$
					}

					brOut.write("new SpellRecord(\"" + name + "\", " + power + ", "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

					int mana = TKStringHelpers.getIntValue(splitLine[2], 1) * -1;
					brOut.write(mana + ", "); //$NON-NLS-1$

					if (splitLine.length > 3) {
						int extras = 0;
						if (splitLine[3].equals("F")) { //$NON-NLS-1$
							extras += 1;
						}
						if (splitLine[3].equals("*")) { //$NON-NLS-1$
							extras += 1;
						}
						if (splitLine[3].equals("H")) { //$NON-NLS-1$
							extras += 1;
						}
						if (splitLine[3].equals("HF")) { //$NON-NLS-1$
							extras = 5;
						}

						if (!splitLine[3].equals("F") && !splitLine[3].equals("*") && !splitLine[3].equals("H") && !splitLine[3].equals("HF")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
							extras = 1000000000;
						}

						brOut.write(extras + ", \"\")"); //, //\n //$NON-NLS-1$
					} else {
						brOut.write("0, \"\")"); //, //\n //$NON-NLS-1$
					}
				}
			}
			brOut.write(")));\n\n"); //$NON-NLS-1$
			brOut.close();
			brIn.close();
		} catch (

		IOException exception) {
			exception.printStackTrace();
		}
	}

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/

	/**
	 * @param powerLevel
	 * @return int value of name
	 */
	private static int getPowerLevel(String powerLevel) {
		int power = 0;
		if (powerLevel.endsWith("IX")) { //$NON-NLS-1$
			power = 9;
		} else if (powerLevel.endsWith("VIII")) { //$NON-NLS-1$
			power = 8;
		} else if (powerLevel.endsWith("VII")) { //$NON-NLS-1$
			power = 7;
		} else if (powerLevel.endsWith("VI")) { //$NON-NLS-1$
			power = 6;
		} else if (powerLevel.endsWith("V")) { //$NON-NLS-1$
			power = 5;
		} else if (powerLevel.endsWith("IV")) { //$NON-NLS-1$
			power = 4;
		} else if (powerLevel.endsWith("III")) { //$NON-NLS-1$
			power = 3;
		} else if (powerLevel.endsWith("II")) { //$NON-NLS-1$
			power = 2;
		} else if (powerLevel.endsWith("I")) { //$NON-NLS-1$
			power = 1;
		}
		return power;
	}

	/*****************************************************************************
	 * Member Variables
	 ****************************************************************************/

	/*****************************************************************************
	 * Constructors
	 ****************************************************************************/

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	/*****************************************************************************
	 * Setter's and Getter's
	 ****************************************************************************/

	/*****************************************************************************
	 * Serialization
	 ****************************************************************************/
}
