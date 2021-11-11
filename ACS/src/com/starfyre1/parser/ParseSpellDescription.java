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

public class ParseSpellDescription {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//		File in = new File("C:\\Users/User/Desktop/spells/Mages.txt");
		//		File out = new File("C:\\Users/User/Desktop/spells/MagesProcessed.txt");

		File in = new File("C:\\Users/User/Desktop/Athri - BMG/spells/SpellDescriptions.txt"); //$NON-NLS-1$
		File out = new File("C:\\Users/User/Desktop/Athri - BMG/spells/SpellDescriptionsProcessed.txt"); //$NON-NLS-1$

		try {
			BufferedReader brIn = new BufferedReader(new FileReader(in));
			BufferedWriter brOut = new BufferedWriter(new FileWriter(out));

			String name = TKStringHelpers.EMPTY_STRING;
			String description = TKStringHelpers.EMPTY_STRING;
			String[] detailName = new String[4];
			String[] details = new String[4];
			int index = 0;
			boolean hasDetail = false;
			boolean hasStarted = false;

			for (String line; (line = brIn.readLine()) != null;) {
				line = line.trim();
				line = line.replace("\"", "\\\""); //$NON-NLS-1$ //$NON-NLS-2$

				String[] splitLine = line.split("]\\s*|\\s*=\\s*"); //	\\s+| //$NON-NLS-1$
				System.out.println("split: [" + Arrays.stream(splitLine).collect(Collectors.joining("][")) + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

				if (splitLine[0].equals(TKStringHelpers.EMPTY_STRING)) {
					// do nothing
				} else if (splitLine.length == 2) {
					if (splitLine[0].startsWith("[")) { //$NON-NLS-1$
						if (hasStarted) {
							if (hasDetail) {
								brOut.write("\")));\n"); //$NON-NLS-1$
							} else {
								brOut.write("\"));\n"); //$NON-NLS-1$
							}
						}
						hasStarted = true;
						hasDetail = false;
						detailName = new String[4];
						details = new String[4];
						index = 0;

						name = splitLine[0].substring(1);
						description = splitLine[1];
						brOut.write("mSpellDescriptions.add(new SpellDescriptionRecord(\"" + name + "\", \"" + description); //$NON-NLS-1$ //$NON-NLS-2$
					} else {
						hasDetail = true;
						if (index > 0) {
							brOut.write("\")"); //$NON-NLS-1$
						} else {
							brOut.write("\""); //$NON-NLS-1$
						}
						detailName[index] = splitLine[0];
						details[index] = splitLine[1];
						brOut.write(", new Pair(\"" + detailName[index] + "\", \"" + details[index]); // + "\""); //$NON-NLS-1$ //$NON-NLS-2$
						index++;
					}
				} else if (splitLine.length == 1) {
					description = TKStringHelpers.SPACE + splitLine[0];
					brOut.write(description.toString());
				} else {
					String powerLevel = splitLine[1].trim();
					int power = getPowerLevel(powerLevel);
					System.out.println("Power: " + power); //$NON-NLS-1$
				}
				//				brOut.write("new SpellDescriptionRecord(\"" + name + "\", " + description + ", "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				//				brOut.write(");\n\n"); //$NON-NLS-1$
			}
			brOut.write("\")));\n\n"); //$NON-NLS-1$
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
	 * @return the int value of the power level
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
