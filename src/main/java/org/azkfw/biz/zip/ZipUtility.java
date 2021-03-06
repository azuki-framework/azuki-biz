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
package org.azkfw.biz.zip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * このクラスは、Zip機能を集めたユーティリティクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/16
 * @author Kawakicchi
 */
public final class ZipUtility {

	private static final int EOF = -1;
	private static final int ZIP_BUFF_SIZE = 1024;

	/**
	 * コンストラクタ
	 */
	private ZipUtility() {

	}

	/**
	 * デフォルト(ファイル名解析)エンコーダを使用してファイル及びディレクトリをZIP圧縮します。
	 * 
	 * @param aZipFile ZIPファイル名
	 * @param aTargetFiles 圧縮対象のファイル及びディレクトリ名列
	 * @return ZIPファイル
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File zip(final String aZipFile, final String... aTargetFiles) throws ZipException, FileNotFoundException, IOException {
		return zip(aZipFile, aTargetFiles, null);
	}

	/**
	 * ファイル及びディレクトリをZIP圧縮します。
	 * 
	 * @param aZipFile ZIPファイル名
	 * @param aTargetFiles 圧縮対象のファイル及びディレクトリ名リスト
	 * @param aEncoding エンコーディング名
	 * @return ZIPファイル
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File zip(final String aZipFile, final List<String> aTargetFiles, final String aEncoding) throws ZipException,
			FileNotFoundException, IOException {
		return zip(aZipFile, aTargetFiles.toArray(new String[0]), aEncoding);
	}

	/**
	 * ファイル及びディレクトリをZIP圧縮します。
	 * 
	 * @param aZipFile ZIPファイル名
	 * @param aTargetFiles 圧縮対象のファイル及びディレクトリ名配列
	 * @param aEncoding エンコーディング名
	 * @return ZIPファイル
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File zip(final String aZipFile, final String[] aTargetFiles, final String aEncoding) throws ZipException, FileNotFoundException,
			IOException {
		int n = aTargetFiles.length;
		File[] files = new File[n];
		for (int i = 0; i < n; i++) {
			files[i] = new File(aTargetFiles[i]);
		}
		return zip(new File(aZipFile), files, aEncoding);
	}

	/**
	 * ファイル及びディレクトリをZIP圧縮します。
	 * 
	 * @param aZipFile ZIPファイル名
	 * @param aTargetFiles 圧縮対象のファイル及びディレクトリリスト
	 * @param aEncoding エンコーディング名
	 * @return ZIPファイル
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File zip(final File aZipFile, final List<File> aTargetFiles, final String aEncoding) throws ZipException, FileNotFoundException,
			IOException {
		return zip(aZipFile, aTargetFiles.toArray(new File[0]), aEncoding);
	}

	/**
	 * ファイル及びディレクトリをZIP圧縮します。
	 * 
	 * @param aZipFile ZIPファイル名
	 * @param aTargetFiles 圧縮対象のファイル及びディレクトリ配列
	 * @param aEncoding エンコーディング名
	 * @return ZIPファイル
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File zip(final File aZipFile, final File[] aTargetFiles, final Charset aEncoding) throws ZipException, FileNotFoundException,
			IOException {
		return zip(aZipFile, aTargetFiles, aEncoding.name());
	}

	/**
	 * ファイル及びディレクトリをZIP圧縮します。
	 * 
	 * @param aZipFile ZIPファイル名
	 * @param aTargetFiles 圧縮対象のファイル及びディレクトリ配列
	 * @param aEncoding エンコーディング名
	 * @return ZIPファイル
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File zip(final File zipFile, final File[] aTargetFiles, final String aEncoding) throws ZipException, FileNotFoundException,
			IOException {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
		out.setEncoding(aEncoding);
		for (int i = 0; i < aTargetFiles.length; i++) {
			int deleteLength = aTargetFiles[i].getPath().length() - aTargetFiles[i].getName().length();
			zip(out, aTargetFiles[i], deleteLength);
		}
		out.close();
		return zipFile;
	}

	private static void zip(ZipOutputStream out, File targetFile, int deleteLength) throws IOException {
		if (targetFile.isDirectory()) {
			File[] files = targetFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				zip(out, files[i], deleteLength);
			}
		} else {
			ZipEntry target = new ZipEntry(targetFile.getPath().substring(deleteLength));
			out.putNextEntry(target);
			byte buf[] = new byte[ZIP_BUFF_SIZE];
			int count;
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(targetFile));
			while ((count = in.read(buf, 0, ZIP_BUFF_SIZE)) != EOF) {
				out.write(buf, 0, count);
			}
			in.close();
			out.closeEntry();
		}
	}

	/**
	 * デフォルト(ファイル名解析)エンコーダを使用してZIPファイルを解凍します。
	 * 
	 * @param aZipFile ZIPファイル名
	 * @param aOutDir 解凍先ディレクトリ名
	 * @return 解凍されたファイルまたはディレクトリ
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File unzip(final String aZipFile, final String aOutDir) throws ZipException, FileNotFoundException, IOException {
		return unzip(aZipFile, aOutDir, null);
	}

	/**
	 * ZIPファイルを解凍します。
	 * 
	 * @param aZipFile ZIPファイル名
	 * @param aOutDir 解凍先ディレクトリ名
	 * @param aEncoding エンコーディング名
	 * @return 解凍されたファイルまたはディレクトリ
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File unzip(final String aZipFile, final String aOutDir, final String aEncoding) throws ZipException, FileNotFoundException,
			IOException {
		return unzip(new File(aZipFile), new File(aOutDir), false, aEncoding);
	}

	/**
	 * ZIPファイルを解凍します。
	 * 
	 * @param aZipFile ZIPファイル名
	 * @param aOutDir 解凍先ディレクトリ名
	 * @param aMakeDir ファイル名でディレクトリ作成を行う。
	 * @return 解凍されたファイルまたはディレクトリ
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File unzip(final String aZipFile, final String aOutDir, final boolean aMakeDir) throws ZipException, FileNotFoundException,
			IOException {
		return unzip(new File(aZipFile), new File(aOutDir), aMakeDir, (String) null);
	}

	/**
	 * ZIPファイルを解凍します。
	 * 
	 * @param aZipFile ZIPファイル
	 * @param aOutDir 解凍先ディレクトリ
	 * @param aMakeDir ファイル名でディレクトリ作成を行う。
	 * @param aEncoding エンコーディング名
	 * @return 解凍されたファイルまたはディレクトリ
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File unzip(final File aZipFile, final File aOutDir, final boolean aMakeDir, final Charset aEncoding) throws ZipException,
			FileNotFoundException, IOException {
		return unzip(aZipFile, aOutDir, aMakeDir, aEncoding.name());
	}

	/**
	 * ZIPファイルを解凍します。
	 * 
	 * @param aZipFile ZIPファイル
	 * @param aOutDir 解凍先ディレクトリ
	 * @param aMakeDir ファイル名でディレクトリ作成を行う。
	 * @param aEncoding エンコーディング名
	 * @return 解凍されたファイルまたはディレクトリ
	 * @throws ZipException ZIP処理時に問題が発生した場合
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	public static File unzip(final File aZipFile, final File aOutDir, final boolean aMakeDir, final String aEncoding) throws ZipException,
			FileNotFoundException, IOException {
		File outputDir = null;
		if (aMakeDir) {
			String fileName = aZipFile.getName();
			String name = fileName.substring(0, fileName.lastIndexOf("."));
			outputDir = Paths.get(aOutDir.getAbsolutePath(), name).toFile();
		} else {
			outputDir = aOutDir;
		}

		ZipFile zipFile = new ZipFile(aZipFile, aEncoding);
		Enumeration<ZipEntry> enumeration = zipFile.getEntries();
		while (enumeration.hasMoreElements()) {
			ZipEntry entry = enumeration.nextElement();
			if (entry.isDirectory()) {
				File dir = Paths.get(outputDir.getAbsolutePath(), entry.getName()).toFile();
				dir.mkdirs();
			} else {
				File file = new File(outputDir, entry.getName());
				file.getParentFile().mkdirs();
				FileOutputStream out = new FileOutputStream(file);
				InputStream in = zipFile.getInputStream(entry);
				byte[] buf = new byte[ZIP_BUFF_SIZE];
				int size = 0;
				while ((size = in.read(buf)) != EOF) {
					out.write(buf, 0, size);
				}
				out.close();
				in.close();
			}
		}
		zipFile.close();
		return aOutDir;
	}

}