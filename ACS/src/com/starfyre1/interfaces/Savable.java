/* Copyright (C) Starfyre Enterprises 2021. All rights reserved. */

package com.starfyre1.interfaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public interface Savable {
	public StringTokenizer readValues(BufferedReader br);

	public void saveValues(BufferedWriter br) throws IOException;

	public void writeValues(BufferedWriter br) throws IOException;

	public void setKeyValuePair(String key, String value);
}
