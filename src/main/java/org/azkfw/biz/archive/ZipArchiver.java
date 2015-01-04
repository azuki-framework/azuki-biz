/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.azkfw.biz.archive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipException;

import org.azkfw.biz.zip.ZipUtility;

/**
 * このクラスは、ZIPアーカイブ機能を実装するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/06/30
 * @author Kawakicchi
 */
public class ZipArchiver implements Archiver {

	/** 文字コード */
	private Charset charset;

	/**
	 * コンストラクタ
	 */
	public ZipArchiver() {
		charset = Charset.forName(System.getProperty("file.encoding"));
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aCharset 文字コード
	 */
	public ZipArchiver(final String aCharset) {
		charset = Charset.forName(aCharset);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aCharset 文字コード
	 */
	public ZipArchiver(final Charset aCharset) {
		charset = aCharset;
	}

	@Override
	public void compression(final File aArchiveFile, final File aSourceFile) throws ArchiveException {
		try {
			File[] file = new File[] { aSourceFile };
			ZipUtility.zip(aArchiveFile, file, charset);
		} catch (ZipException ex) {
			throw new ArchiveException(ex);
		} catch (FileNotFoundException ex) {
			throw new ArchiveException(ex);
		} catch (IOException ex) {
			throw new ArchiveException(ex);
		}
	}

	@Override
	public void decompression(final File aArchiveFile, final File aDistDirectory) throws ArchiveException {
		try {
			ZipUtility.unzip(aArchiveFile, aDistDirectory, false, charset);
		} catch (ZipException ex) {
			throw new ArchiveException(ex);
		} catch (FileNotFoundException ex) {
			throw new ArchiveException(ex);
		} catch (IOException ex) {
			throw new ArchiveException(ex);
		}
	}

}
